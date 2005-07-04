/*
 * Interpreter.java
 */

package runtime;
import java.math.BigInteger;
import metamodel.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This is the main interpreter file.
 * @author npiggin
 * @author sjr
 * @todo javadoc
 */
public class Interpreter {
    /** Creates a new instance of Interpreter */
    public Interpreter() {
    }
    
    /**
     * 
     * @param p 
     * @param c 
     * @throws runtime.LemRuntimeException 
     */
    public void interpret(Procedure p, Context c) throws LemRuntimeException {
        executeProcedure(p, c);
    }
    
    /**
     * 
     * @param p 
     * @param c 
     * @throws runtime.LemRuntimeException 
     */
    public void executeProcedure( Procedure p, Context c ) throws LemRuntimeException {
        LinkedList actions = p.getActions();
        Iterator i = actions.iterator();
        
        while( i.hasNext() ) {
            Action a = (Action)i.next();
            executeAction(a, c);
        }
    }
    
    /**
     * 
     * @param a 
     * @param c 
     * @throws runtime.LemRuntimeException 
     */
    public void executeAction( Action a, Context c ) throws LemRuntimeException {
        if ( a instanceof CreateAction )
            executeCreateAction((CreateAction)a, c);
        else if( a instanceof AssignmentAction ) 
            executeAssignmentAction((AssignmentAction)a, c);
        else {
            throw new LemRuntimeException("executeAction encountered unknown action");
        }
    }
    
    /**
     * Execute the given CreateAction in the given Context. 
     *
     * @param a the CreateAction to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException thrown by the metamodel.Object 
     * constructor
     * @return the newly created runtime.Object
     */
    public runtime.Object executeCreateAction( CreateAction a, Context c ) throws LemRuntimeException {
        // Create the new object
        runtime.Object o = new runtime.Object( a.getClasses() );
        
        // Add it to the context
        c.addObject(o);
        
        // Notify listeners that the object has been added
        LemObjectCreationEvent e = new LemObjectCreationEvent( o, a );
        for( Iterator i = c.getLemEventListeners().iterator(); i.hasNext(); ) {
            LemEventListener l = (LemEventListener)i.next();
            l.objectCreated(e);
        }
        
        return o;
    }
   
    /**
     * Executes the given AssignmentAction in the current context. 
     *
     * @param a the AssignmentAction to execute
     * @param c the Context in which to execute the action
     * @return the Variable that is finally assigned
     * @throws runtime.LemRuntimeException if any error, such as a reference to
     * undefined objects, occur
     */
    public Variable executeAssignmentAction( AssignmentAction a, Context c ) throws LemRuntimeException {
        VariableReference r = a.getVariableReference();
        String name = r.getObjectName();
        runtime.Object o = null;
        
        Variable destination = null;
                
        if( name != null ) {
            // Look up the object reference in the current context
            Variable v = c.getLocalVariable( name );
            if( v == null ) {
                throw new LemRuntimeException( "Variable '" + name + "' is not defined" );
            }
            
            if( !(v.getType().equals( ObjectReferenceType.getInstance() ))) {
                throw new LemRuntimeException( "Variable '" + name + "' is not an object reference" );
            }
            
            // OK, we've got a valid object reference
            // TODO: What if the referenced object has been deleted or reclassified?
            // Why is life so complex?
            runtime.Object source = (runtime.Object)(v.getValue());
            
            // Now we've got the target object. We need to get the named attribute from that object.
            destination = source.getAttribute( r.getVariableName() );
            if( destination == null ) {
                throw new LemRuntimeException( "Attribute '" + name + "' not defined on object named '" + name + "'");
            }
        }
        
        // At this point, we evaluate the expression no matter what
        Variable value = evaluateExpression( a.getExpression(), c );
        
        // If the destination is not defined, then there is no object reference and we should 
        // create a new Variable and place it in the current context
        // TODO: Variable references are declared anyway. We probably don't need this behaviour.
        if( destination == null ) {
            destination = new Variable( value.getType() );
            c.addLocalVariable( r.getVariableName(), value );
            destination.setValue( value.getValue() );
            return destination;
        }
        
        // Otherwise, we do a type check. If there's a mismatch, throw an error
        if( value.getType() != destination.getType() ) {
            throw new LemRuntimeException( "Type mismatch: evaluated '" + value.getType().getName() + "'" 
                    + ", expected '" + destination.getType() );
        }
        
        destination.setValue( value.getValue() );
        return destination;
    }
    
    /**
     * Evaluate the given Expression in the given Context. Returns a new Variable object representing
     * the (Type, Value) pair resulting from the evaluation of the expression.
     *
     * @param e the expression to evaluate
     * @param c the context in which to evalue the expression
     * @throws runtime.LemRuntimeException in the case of errors such as division by zero, type mismatches, etc
     * @return the evaluated expression
     */
    public Variable evaluateExpression( Expression e, Context c ) throws LemRuntimeException {
        if( e instanceof Literal ) {
            Literal l = (Literal)e;
            
            // TODO: Testing only
            Variable v = new Variable( l.getType() );
            v.setValue( new BigInteger( l.getValue() ));
            
            return v;
        }
        
        if( e instanceof BinaryOperation ) {
            BinaryOperation o = (BinaryOperation) e;
            Variable left = evaluateExpression( o.getLeft(), c );
            Variable right = evaluateExpression( o.getRight(), c );
            
            Variable v = new Variable( left.getType() );
            
            if( o.getType() == BinaryOperation.ADDITION ) 
                v.setValue( ((BigInteger)left.getValue()).add((BigInteger)right.getValue()));
            else
                v.setValue( ((BigInteger)left.getValue()).subtract((BigInteger)right.getValue()));
    
            return v;
        }
        
        return null;
    }
}

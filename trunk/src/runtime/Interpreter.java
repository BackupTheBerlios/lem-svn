/*
 * Interpreter.java
 */

package runtime;
import java.math.BigDecimal;
import metamodel.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collection;

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
     * Interpret the given Procedure by calling executeBlock on Procedure's
     * main ActionBlock.
     *
     * @param p the procedure to interpret
     * @param c the Context in which the procedure should be interpreted
     * @throws runtime.LemRuntimeException when any error occurs in the execution of the procedure
     */
    public void interpret(Procedure p, Context c) throws LemRuntimeException {
	ActionBlock block = p.getActionBlock();
        executeBlock(block, c);
    }
    
    /**
     * Execute the given ActionBlock by setting up the "stack frame" for
     * all variable declarations then calling executeAction on all of the
     * block's Actions.
     *
     * @param p the procedure to execute
     * @param c the context in which to execute
     * @throws runtime.LemRuntimeException if any error occurs during the execution of the procedure
     */
    public void executeBlock( ActionBlock block, Context c ) throws LemRuntimeException {
	LocalContext localContext = new LocalContext(c);
	Collection decls = block.getVariableDeclarations();
        LinkedList actions = block.getActions();
	Iterator i;

	/* Set up variables into the local context */
	i = decls.iterator();
	while (i.hasNext()) {
		VariableDeclaration vd = (VariableDeclaration)i.next();
		instantiateVariable ( vd, localContext );
        }
      
	/* Execute actions */
        i = actions.iterator();
        while( i.hasNext() ) {
            Action a = (Action)i.next();
            executeAction(a, localContext);
        }
	localContext.finish();
    }
    
    /**
     * Execute the given action.
     * @param a the action to execute
     * @param c the context in which to execute the action
     * @throws runtime.LemRuntimeException in case any errors occur during the execution of the action
     */
    public void executeAction( Action a, Context c ) throws LemRuntimeException {
        if ( a instanceof CreateAction )
            executeCreateAction((CreateAction)a, c);
        else if( a instanceof AssignmentAction )
            executeAssignmentAction((AssignmentAction)a, c);
	else if( a instanceof IfStatement )
	    executeIfStatement((IfStatement)a, c);
	else if( a instanceof WhileStatement )
	    executeWhileStatement((WhileStatement)a, c);
	else if( a instanceof GenerateAction )
	    executeGenerateAction((GenerateAction)a, c);
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
        
        // Add the object reference to the context
	if( a.getVariable() != null ) 
            c.addVariable( a.getVariable().getVariableName(), new ObjectReferenceVariable( o ));
        
        // Notify listeners that the object has been added
        LemObjectCreationEvent e = new LemObjectCreationEvent( o, a );
        for( Iterator i = c.getLemEventListeners().iterator(); i.hasNext(); ) {
            LemEventListener l = (LemEventListener)i.next();
            l.objectCreated(e);
        }
        
        return o;
    }
   
    /**
     * Execute the given GenerateAction in the given Context. 
     *
     * @param a the GenerateAction to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException thrown by the metamodel.Object 
     * constructor
     */
    public void executeGenerateAction( GenerateAction a, Context c ) throws LemRuntimeException {
        // Create the new signal
	Signal s = new Signal(a.getEvent());

	LinkedList p = a.getParameters();
	if (p != null) {
		/* Evaluate parameters - pass by value obviously */
		for (Iterator i = p.iterator(); i.hasNext();) {
			Expression e = (Expression)i.next();
			Variable result = evaluateExpression(e, c);
			s.setParameter("param1", p); /* todo: need to fix this */
		}
	}

	VariableReference vr = a.getTarget();
        Variable targetRef = getVariable( vr, c );
	if (!(targetRef instanceof ObjectReferenceVariable)) {
		throw new LemRuntimeException("Type mismatch: expected objref");
	}

	runtime.Object target = (runtime.Object)((ObjectReferenceVariable)targetRef).getValue();
	
	/* todo: handle signals to self */
	target.addSignal(s);
	
        // todo: Notify listeners that the signal has been generated
    }
 
    /**
     * Execute the given IfStatement in the given Context. 
     *
     * @param a the IfStatement to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException thrown by the metamodel.Object 
     * constructor
     */
    public void executeIfStatement( IfStatement a, Context c ) throws LemRuntimeException {
	LinkedList condList = a.getConditionalAlternatives();
	Iterator i = condList.iterator();

	while (i.hasNext()) {
		ConditionalAlternative ca = (ConditionalAlternative)i.next();
		Expression condition = ca.getCondition();
		ActionBlock block = ca.getBlock();

		if (condition != null) {
			Variable result = evaluateExpression(condition, c);
			if (!(result instanceof BooleanVariable)) {
				throw new LemRuntimeException("Type mismatch: expected boolean condition");
			}
			if (!((Boolean)((BooleanVariable)result).getValue()).booleanValue())
				continue;

		}

		/* Fallthrough: either condition is Null (we've hit the else
		 * part), or the conditional result has evaluated True.
		 * Execute the associated action block and break the loop.
		 */
		executeBlock(block, c);
                break;
	}
    }

    /**
     * Execute the given WhileStatement in the given Context. 
     *
     * @param a the WhileStatement to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException thrown by the metamodel.Object 
     * constructor
     */
    public void executeWhileStatement( WhileStatement a, Context c ) throws LemRuntimeException {
	Expression loopCond = a.getCondition();
	ActionBlock block = a.getBlock();

	while (true) {
		Variable result = evaluateExpression(loopCond, c);
		if (!(result.getCoreDataType() instanceof BooleanType)) {
			throw new LemRuntimeException("Type mismatch: expected boolean condition");
		}
		if (!((Boolean)((BooleanVariable)result).getValue()).booleanValue())
			break;

		/* Condition evaluated to true */
		executeBlock(block, c);
	}
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
        
        Variable destination = getVariable( r, c );
        
        // At this point, we evaluate the expression no matter what
        Variable value = evaluateExpression( a.getExpression(), c );
        
        if( value.getCoreDataType() != destination.getCoreDataType() ) {
            throw new LemRuntimeException( "Type mismatch: evaluated '" + value.getType().getName() + "'" 
                    + ", expected '" + destination.getType() + ", name: " + r.getVariableName() );
        }
        
        destination.setValue( value.getValue() );
        return destination;
    }
    
    /**
     * Dereference the given VariableReference in the given Context.
     * @param r the VariableReference which references the Variable to be returned
     * @param c the Context in which the VariableReference should be evaluated
     * @throws runtime.LemRuntimeException if the referenced variable does not exist in the given Context
     * @return the referenced Variable
     */
    protected Variable getVariable( VariableReference r, Context c ) throws LemRuntimeException {
        String name = r.getObjectName();
        Variable destination;
        
        if( name != null ) {
            // Look up the object reference in the current context
            Variable v = c.getVariable( name );
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
                throw new LemRuntimeException( "Attribute '" + r.getVariableName() + "' not defined on object named '" + name + "'");
            }
        } else {
            destination = c.getVariable( r.getVariableName () );
        }

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
            Variable v = VariableFactory.newVariable(  l.getType(), l.getValue() );
            
            return v;
        }
        
        if( e instanceof BinaryOperation ) {
            BinaryOperation o = (BinaryOperation) e;
            Variable left = evaluateExpression( o.getLeft(), c );
            Variable right = evaluateExpression( o.getRight(), c );
            
            switch( o.getType() ) {
                case BinaryOperation.ADDITION:
                    return left.add( right );
                case BinaryOperation.SUBTRACTION:
                    return left.subtract( right );
                case BinaryOperation.MULTIPLICATION:
                    return left.multiply( right );
                case BinaryOperation.DIVISION:
                    return left.divide( right );
                case BinaryOperation.EXPONENTIATION:
                    return left.exp( right );
                case BinaryOperation.EQUAL:
                    return left.equal( right );
                case BinaryOperation.LESS_THAN:
                    return left.lessThan( right );
                case BinaryOperation.LESS_THAN_OR_EQUAL:
                    return left.lessThanOrEqual( right );
                case BinaryOperation.GREATER_THAN:
                    return left.greaterThan( right );
                case BinaryOperation.GREATER_THAN_OR_EQUAL:
                    return left.greaterThanOrEqual( right );
                case BinaryOperation.MODULO:
                    return left.mod( right );
                case BinaryOperation.NOT_EQUAL:
                    return left.notEqual( right );
                case BinaryOperation.LOGICAL_AND:
                    return left.logicalAnd( right );
                case BinaryOperation.LOGICAL_OR:
                    return left.logicalOr( right );
            }
            
            throw new LemRuntimeException( "Unknown operation ID: " + o.getType() );
        }
        
        if( e instanceof VariableReference ) {
            // Must look up Variable
            return getVariable( (VariableReference) e, c );
        }
        
        if( e instanceof UnaryOperation ) {
            UnaryOperation o = (UnaryOperation) e;
            Variable operand = evaluateExpression( o.getOperand(), c );
            
            switch( o.getType() ) {
                case UnaryOperation.NEGATION:
                    return operand.negation();
                case UnaryOperation.LOGICAL_NOT:
                    return operand.logicalNot();
            }
            
            throw new LemRuntimeException( "Unknown unary operation ID: " + o.getType() );
        }
        
        return null;
    }
    
    /** This method will instantiate a variable in context c from the given
     * VariableDeclaration.
     * 
     * It will check whether the variable already exists in the context in
     * which case it will ...
     *otherwise it will add a new variable to the context, the declared variable initially has no type or value.
     *@param v The VariableDeclaration to execute.
     *@param c the context in which the action has been executed.
     */
    public void instantiateVariable( VariableDeclaration v, Context c) throws LemRuntimeException {
        String name ; 
        Variable var = VariableFactory.newVariable(v.getVariableType(), null);
        name = v.getVariableName() ; 
        c.addVariable(name , var ) ; 
    }
    
}

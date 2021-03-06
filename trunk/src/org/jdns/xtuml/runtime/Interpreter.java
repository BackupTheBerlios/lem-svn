/*
 * Interpreter.java - This Class interprets and executes Action Language in
 * given contexts.  
 *
 * Copyright (C) 2005 James Ring
 * Copyright (C) 2005 Nick Piggin
 * Copyright (C) 2005 Thuan Seah
 * Copyright (C) 2005 Shuku Torabi
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 */

package org.jdns.xtuml.runtime;
import java.math.BigDecimal;
import org.jdns.xtuml.metamodel.Action;
import org.jdns.xtuml.metamodel.ActionBlock;
import org.jdns.xtuml.metamodel.AssignmentAction;
import org.jdns.xtuml.metamodel.BinaryOperation;
import org.jdns.xtuml.metamodel.BreakStatement;
import org.jdns.xtuml.metamodel.CancelAction;
import org.jdns.xtuml.metamodel.ConditionalAlternative;
import org.jdns.xtuml.metamodel.CreateAction;
import org.jdns.xtuml.metamodel.CreationTransaction;
import org.jdns.xtuml.metamodel.DeleteAction;
import org.jdns.xtuml.metamodel.Event;
import org.jdns.xtuml.metamodel.Expression;
import org.jdns.xtuml.metamodel.ForStatement;
import org.jdns.xtuml.metamodel.GenerateAction;
import org.jdns.xtuml.metamodel.IfStatement;
import org.jdns.xtuml.metamodel.Literal;
import org.jdns.xtuml.metamodel.ObjectReferenceType;
import org.jdns.xtuml.metamodel.PrintAction;
import org.jdns.xtuml.metamodel.Procedure;
import org.jdns.xtuml.metamodel.RelateAction;
import org.jdns.xtuml.metamodel.RelatedToOperation;
import org.jdns.xtuml.metamodel.Relationship;
import org.jdns.xtuml.metamodel.Scenario;
import org.jdns.xtuml.metamodel.SelectExpression;
import org.jdns.xtuml.metamodel.UnaryOperation;
import org.jdns.xtuml.metamodel.UnrelateAction;
import org.jdns.xtuml.metamodel.VariableDeclaration;
import org.jdns.xtuml.metamodel.VariableReference;
import org.jdns.xtuml.metamodel.WhileStatement;
import org.jdns.xtuml.runtime.Object ;
import org.jdns.xtuml.runtime.Instance ;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collection;
import java.util.ArrayList ;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;


/**
 * This is the main interpreter file.
 * @author npiggin
 * @author sjr
 * @todo javadoc
 */
public class Interpreter {
    /** keeps a record of all the InstanceInterpreters running **/
    private ArrayList instanceInterpreters = null;

    /**
     * The object in which we are executing
     */
    private org.jdns.xtuml.runtime.Object currentObject;
    
    /**
     * The context in which the interpreter runs
     */
    private DomainContext context = null;
    
    /**
     * A quick hack to enable breaking of loops
     */
    private boolean hasBreak = false;
    
    /**
     * A quick hack to differentiate between an attribute read and write 
     */
    private boolean attributeWrite = false;
    
    /** Creates a new instance of Interpreter
     * @param obj is the object that the Interpreter is
     * executing the procedures for.
     */
    public Interpreter( org.jdns.xtuml.runtime.Object obj ) {
        currentObject = obj;
		instanceInterpreters = new ArrayList() ; 
    }
    
	/**
	* Interpret the given Procedure by calling executeBlock on Procedure's
	* main ActionBlock.
	*
	* @param p the procedure to interpret
	* @param s the signal which caused the procedure to be executed
	* @param c the Context in which the procedure should be interpreted
	* @throws runtime.LemRuntimeException when any error occurs in the 
	* execution of the procedure
	*/
	public void interpret( Procedure p, Signal s, DomainContext c ) throws LemRuntimeException {
		context = c;

		ProcedureContext procContext = new ProcedureContext( c, s );
		Variable selfVar = new ObjectReferenceVariable( currentObject );
		procContext.addVariable( "self" , selfVar ) ;

		ActionBlock block = p.getActionBlock();
		executeBlock( block, procContext );
		procContext.finish();

        	context = null; // ensure no other entry point tries to use this
	}
    
	/**
	 * Interpret the given Scenario by calling executeBlock on Scenario's
	 * main ActionBlock.
	 *
	 * @param s the scenario to interpret
	 * @param c the Context in which the procedure should be interpreted
	 * @throws runtime.LemRuntimeException when any error occurs in the 
	 * execution of the procedure
	 */
	public void interpret( Scenario s, DomainContext c ) 
			throws LemRuntimeException {
		context = c;
		ActionBlock block = s.getActionBlock();

		org.jdns.xtuml.runtime.Object ob = new org.jdns.xtuml.runtime.Object( c );
		this.currentObject = ob;	
		
		executeBlock( block, c );
		context = null; // ensure no other entry point tries to use this
	}
    
	/**
	* Execute the given ActionBlock by setting up the "stack frame" for
	* all variable declarations then calling executeAction on all of the
	* block's Actions.
	*
	* @param p the procedure to execute
	* @param c the context in which to execute
	* @throws runtime.LemRuntimeException if any error occurs during the 
	* execution of the procedure
	*/
	public void executeBlock( ActionBlock block, Context c ) 
			throws LemRuntimeException {
		LocalContext localContext = new LocalContext( c );
		Collection decls = block.getVariableDeclarations();
		LinkedList actions = block.getActions();
		Iterator i;

		/* Set up variables into the local context */
		i = decls.iterator();
		while ( i.hasNext() ) {
			VariableDeclaration vd = (VariableDeclaration)i.next();
			instantiateVariable( vd, localContext );
		}

		/* Execute actions */
		i = actions.iterator();
		while( i.hasNext() && !hasBreak) {
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
	context.getDebugObject().executeAction(a);
	context.getDebugObject().checkRuntimeState();

        if ( a instanceof CreateAction )
            executeCreateAction((CreateAction)a, c);
	else if (a instanceof CreationTransaction)
	    executeCreationTransaction((CreationTransaction)a, c);
	else if (a instanceof DeleteAction )
	    executeDeleteAction((DeleteAction)a, c);
        else if( a instanceof AssignmentAction )
            executeAssignmentAction((AssignmentAction)a, c);
        else if( a instanceof IfStatement )
            executeIfStatement((IfStatement)a, c);
        else if( a instanceof WhileStatement )
            executeWhileStatement((WhileStatement)a, c);
        else if( a instanceof ForStatement )
            executeForStatement((ForStatement)a, c);
        else if( a instanceof GenerateAction )
            executeGenerateAction((GenerateAction)a, c);
	else if( a instanceof CancelAction )
	    executeCancelAction((CancelAction)a, c);
        else if(a instanceof RelateAction )
            executeRelateAction((RelateAction)a, c);
        else if(a instanceof UnrelateAction )
            executeUnrelateAction((UnrelateAction)a, c);
        else if(a instanceof BreakStatement)
            executeBreakStatement((BreakStatement)a, c);
		else if (a instanceof PrintAction)
			executePrintAction((PrintAction)a, c);
		else
            throw new LemRuntimeException( "executeAction encountered unknown action" );
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
    public org.jdns.xtuml.runtime.Object executeCreateAction( CreateAction a, Context c ) throws LemRuntimeException {
        Iterator i;
        
        // Create the new object
        org.jdns.xtuml.runtime.Object o = new org.jdns.xtuml.runtime.Object(context, a.getClasses());
        
        // Add it to the context
        c.addObject( o );
        
        // Set up InstanceThreads
        i = o.getInstances().iterator();
        while ( i.hasNext() ) {
            Instance instance = ( Instance ) i.next();
            if ( instance.instanceOfClass.isActive() ) {
                InstanceInterpreter instanceThread = new InstanceInterpreter( instance, context );
            }
        }
        
        // Notify listeners that the object has been added
        new LemObjectCreationEvent( o ).notifyAll( c );
        
        return o;
    }
    
    /**
     * Execute the given DeleteAction in the given Context.
     *
     * @param a the DeleteAction to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException
     */
    public void executeDeleteAction( DeleteAction a, Context c ) throws LemRuntimeException {
        int object_id=0;
        ObjectReferenceVariable targetRef =
                ( ObjectReferenceVariable ) getVariable( a.getVariable(), c );
        org.jdns.xtuml.runtime.Object target = ( org.jdns.xtuml.runtime.Object ) targetRef.getValue();
        Integer objectId = new Integer(target.getObjectId().intValue());
        Collection className = new LinkedList();
        java.util.Iterator i = target.getInstances().iterator();
        while(i.hasNext()) {
            Instance inst = (Instance)i.next();
            className.add(inst.getInstanceClass().getName());
        }
        object_id = target.getObjectId().intValue();
        c.delObject( target );
        
        // Notify listeners that the object has been deleted
        new LemObjectDeletionEvent( object_id, className ).notifyAll( c );
    }
    
    /**
     * Execute the given CancelAction in the given Context.
     *
     * @param a the CancelAction to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException
     */
     
    public void executeCancelAction( CancelAction a, Context c ) throws LemRuntimeException {
        // Find the event to be fired on the given object
        Event e = currentObject.findEvent( a.getEventName(), null );
        Signal s = currentObject.cancelDelayedSignalSelf(e);
	if (s == null) {
		throw new LemRuntimeException("Could not cancel a signal");
	}
	
	context.getDebugObject().delEntity();
        int id1 = currentObject.getObjectId().intValue();
        int id2 = s.getSignalId().intValue();
        Collection p = s.getParameters();
        new LemEventCancellationEvent(id1, id2, a.getEventName(), p).notifyAll( c );
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
        LinkedList p = a.getParameters();
        LinkedList pList = null;
        LinkedList passedValues = null;
        int eventid = 0;

	context.getDebugObject().addEntity();

        if ( p != null ) {
            passedValues = new LinkedList();
            pList = new LinkedList();
            /* Evaluate parameters - pass by value obviously */
            Iterator i = p.iterator();
            while ( i.hasNext() ) {
                Expression e = ( Expression ) i.next();
                pList.add( expressionToString(e) );
                Variable result = evaluateExpression( e, c );
                passedValues.add( result );
            }
        }
        
        VariableReference vr = a.getTarget();
        Variable targetRef = getVariable( vr, c );
        if ( !( targetRef instanceof ObjectReferenceVariable ) ) {
            throw new LemRuntimeException( "Type mismatch: expected objref, got " + targetRef.getType().getName() );
        }
        
        org.jdns.xtuml.runtime.Object target = ( org.jdns.xtuml.runtime.Object ) ( ( ObjectReferenceVariable ) targetRef ).getValue();

        // if we are not in Scenario
        int id1=0;
        if(currentObject != null)
            id1 = currentObject.getObjectId().intValue();
        
        int id2 = target.getObjectId().intValue();        


        // Find the event to be fired on the given object
        Event e = target.findEvent( a.getEventName(), a.getParameters() );

        if ( e == null ) {
            // TODO: Don't use single-arg constructor for LemRuntimeException
            throw new LemRuntimeException( "Could not find event named '" + a.getEventName() + "'" );
        }

	String type = e.getName();  
	
        // Create the new signal
	Expression delayExpression = a.getDelayTime();
        if ( delayExpression != null ) {
            Variable v = evaluateExpression( delayExpression, c );
            if ( !( v instanceof NumericVariable ) ) {
                throw new LemRuntimeException( "Type mismatch: expected numeric, got " + v.getType().getName() );
            }
            BigDecimal delay = ( BigDecimal ) v.getValue();
            DelayedSignal ds = new DelayedSignal( e, target, delay );
            if ( passedValues != null )
                ds.setParameters( passedValues );

	    System.out.println(currentObject.getObjectId() + " generating delayed signal (" + delay + ") to " + target.getObjectId());
	    System.out.println(currentObject.getObjectId() + " generating delayed signal (" + delay + ") to " + target.getObjectId());
            eventid = ds.getSignalId().intValue();            
            new LemEventGenerationEvent(id1, id2, eventid, type, pList, delay).notifyAll( c );            
            if ( target == currentObject ) {
                currentObject.addDelayedSignalSelf( ds );
            } else {
                currentObject.addDelayedSignal( ds );
            }

        } else {
            Signal s = new Signal( e );
            Integer signalId = s.getSignalId() ;
            Integer targetObjectId = target.getObjectId() ;
            if ( passedValues != null )
                s.setParameters( passedValues );

	    if (currentObject == null)
		    System.out.print("[main]");
	    else
		    System.out.print(currentObject.getObjectId());
	    System.out.println(" generating signal to " + target.getObjectId());
          eventid = s.getSignalId().intValue();            
          new LemEventGenerationEvent(id1, id2, eventid, type, pList).notifyAll( c ); 
	    synchronized (target) {
		    if (target.isActive()) {
		            if ( target == currentObject ) {
		                target.addSignalSelf( s );
		            } else {
		                target.addSignal( s );
		            }
		    } else {
			    /* Object has become inactive */
			    context.getDebugObject().delEntity();
		    }
	    }
        }
    }
    
    /**
     * Execute the given CreationTransaction in the given Context.
     *
     * @param a the CreationTransaction to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException
     */
    public void executeCreationTransaction(CreationTransaction ct, Context c)
	    				throws LemRuntimeException {
	TransactionContext tc = new TransactionContext(c);
	ActionBlock block = ct.getBlock();
        executeBlock( block, tc );
	tc.finish();
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
        
        while ( i.hasNext() ) {
            ConditionalAlternative ca = ( ConditionalAlternative ) i.next();
            Expression condition = ca.getCondition();
            ActionBlock block = ca.getBlock();
            
            if ( condition != null ) {
                Variable result = evaluateExpression( condition, c );
                if ( !( result instanceof BooleanVariable ) ) {
                    throw new LemRuntimeException( "Type mismatch: expected boolean condition" );
                }
                if ( !( ( Boolean ) ( ( BooleanVariable ) result ).getValue() ).booleanValue() )
                    continue;
                
            }
            
            /* Fallthrough: either condition is Null (we've hit the else
             * part), or the conditional result has evaluated True.
             * Execute the associated action block and break the loop.
             */
            executeBlock( block, c );
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
        
        while ( true ) {
            Variable result = evaluateExpression( loopCond, c );
            if ( !( result instanceof BooleanVariable ) ) {
                throw new LemRuntimeException( "Type mismatch: expected boolean condition" );
            }
            if ( !( ( Boolean ) ( ( BooleanVariable ) result ).getValue() ).booleanValue() )
                break;
            
            if(hasBreak)
                break;
            
            /* Condition evaluated to true */
            executeBlock( block, c );
        }
        hasBreak = false;
    }
    
    /**
     * Execute the given ForStatement in the given Context.
     *
     * @param a the ForStatement to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException thrown by the metamodel.Object
     * constructor
     */
    public void executeForStatement( ForStatement a, Context c ) throws LemRuntimeException {
        String selectName = a.getSelectVariable();
        ActionBlock block = a.getBlock();
        Expression setExp = a.getSetExpression();
        Variable var = evaluateExpression( setExp, c );
        if ( !( var instanceof SetVariable ) ) {
            throw new LemRuntimeException( "Type mismatch: expected expression which evaluates to 'set'" );
        }
        SetVariable set = (SetVariable)var;
        Iterator i = ((Collection)set.getValue()).iterator();
        while (i.hasNext() && !hasBreak) {
            ObjectReferenceVariable select = (ObjectReferenceVariable)i.next();
            Context newContext = new LocalContext( c );
            newContext.addVariable( selectName , select );
            
            executeBlock( block, newContext );
            
            newContext.finish();
        }
        hasBreak = false;
    }
    
    /**
     * Execute the given BreakStatement in the given Context.
     *
     * @param a the BreakStatement to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException thrown by the metamodel.Object
     * constructor
     */
    public void executeBreakStatement( BreakStatement a, Context c ) throws LemRuntimeException {    
        hasBreak = true;
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
        String name = "";
        attributeWrite = true;
        VariableReference r = a.getVariableReference();
        Variable destination = getVariable( r, c );
        attributeWrite = false;
        Variable value = evaluateExpression( a.getExpression(), c );
       
        if ( value.getCoreDataType() != destination.getCoreDataType() ) {
            throw new LemRuntimeException( "Type mismatch: evaluated '" + value.getType().getName() + "', expected '" + destination.getType().getName() + "', name: " + r.getVariableName() );
        }
        
        // notify all logger if the assignment action is an attribute change
        name = r.getObjectName();
        if(name != null) {
            int id=0;
            if(name.equals("self")) {
                id = currentObject.getObjectId().intValue();
            } else {
                id = ((org.jdns.xtuml.runtime.Object)c.getVariable( name ).getValue()).getObjectId().intValue();
            }
            name = r.getVariableName();
            new LemAttributeChangeEvent(id, name, destination.getValue(), value.getValue()).notifyAll( c );
        }
        destination.setValue( value.getValue() );
        return destination;
    }
    
    /**
	 * Executes the given PrintAction, causing some output to go to standard
	 * error.
	 *
	 * @param p the PrintAction to execute
	 * @param c the Context in which the PrintAction executes
	 */
	public void executePrintAction( PrintAction p, Context c ) throws LemRuntimeException {
		Variable v = evaluateExpression( p.getOutputExpression(), c );

		System.err.println( v.getValue() );
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
	String attribute_name = r.getVariableName();
        Variable destination;
        
        if ( name != null ) {
            // if variable the attribute of the current object
            if(name.equals("self") && (currentObject != null)) {
                destination = currentObject.getAttribute( attribute_name );
                if ( destination == null ) {
                    throw new LemRuntimeException( "Attribute '" + r.getVariableName() + "' not defined on object named '" + name + "'" );
                }
                // we are reading a atribute
                if(!attributeWrite) { 
                    new LemAttributeReadEvent(currentObject.getObjectId().intValue(), attribute_name, destination.getValue()).notifyAll( c );
                }
                return destination;
            }            
            // Look up the object reference in the current context
            Variable v = c.getVariable( name );
            if ( v == null ) {
                throw new LemRuntimeException( "Variable '" + name + "' is not defined" );
            }
            
            if ( !( v.getType().equals( ObjectReferenceType.getInstance() ) ) ) {
                throw new LemRuntimeException( "Variable '" + name + "' is not an object reference" );
            }
            
            // OK, we've got a valid object reference
            // TODO: What if the referenced object has been deleted or reclassified?
            // Why is life so complex?
            org.jdns.xtuml.runtime.Object source = ( org.jdns.xtuml.runtime.Object ) ( v.getValue() );
            
            // Now we've got the target object. We need to get the named attribute from that object.
            destination = source.getAttribute( attribute_name );
            if ( destination == null ) {
                throw new LemRuntimeException( "Attribute '" + r.getVariableName() + "' not defined on object named '" + name + "'" );
            }
            // we are reading a atribute
            if(!attributeWrite) { 
                new LemAttributeReadEvent(source.getObjectId().intValue(), attribute_name, destination.getValue()).notifyAll( c );
            }
        } else {
		destination = c.getVariable( r.getVariableName() );
		if (destination == null)
			destination = currentObject.getAttribute(attribute_name);
		if (destination == null)
			throw new LemRuntimeException("Variable " + r.getVariableName() + " is undefined");

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
        if ( e instanceof Literal ) {
            Literal l = ( Literal ) e;
            // TODO: Testing only
            Variable v = VariableFactory.newVariable( l.getType(), l.getValue() );
            return v;
        } else if ( e instanceof SelectExpression ) {
            SelectExpression se = ( SelectExpression ) e;
            return evaluateSelectExpression( se, c );
        } else if ( e instanceof CreateAction ) {
            CreateAction ca = ( CreateAction ) e;
            org.jdns.xtuml.runtime.Object o = executeCreateAction( ca, c );
            return new ObjectReferenceVariable( o );
        } else if ( e instanceof BinaryOperation ) {
            BinaryOperation o = ( BinaryOperation ) e;
            Variable left = evaluateExpression( o.getLeft(), c );
            Variable right = evaluateExpression( o.getRight(), c );
            
            switch ( o.getType() ) {
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
        } else if ( e instanceof VariableReference ) {
            // Must look up Variable
            return getVariable( ( VariableReference ) e, c );
        } else if ( e instanceof UnaryOperation ) {
            UnaryOperation o = ( UnaryOperation ) e;
            Variable operand = evaluateExpression( o.getOperand(), c );
            
            switch ( o.getType() ) {
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
    public void instantiateVariable( VariableDeclaration v, Context c ) throws LemRuntimeException {
        String name ;
        Variable var = VariableFactory.newVariable( v.getVariableType(), null );
        name = v.getVariableName() ;
        c.addVariable( name , var ) ;
    }
    
    
    /**
     * Execute the given RelateAction in the given Context.
     *
     * @param a the RelateAction to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException thrown by the metamodel.Object
     * constructor
     */
    public void executeRelateAction( RelateAction a, Context c ) throws LemRuntimeException {
        // check both ends of the association to see if the classes are of
        // correct type
        boolean valid_active, valid_passive;
        valid_active = valid_passive = false;
        int active_id, passive_id;
        active_id = passive_id = 0;
        Instance active = null;
        Instance passive = null;
        org.jdns.xtuml.runtime.Object obj = null;
        
        org.jdns.xtuml.runtime.Object aP = (org.jdns.xtuml.runtime.Object)(( ObjectReferenceVariable ) c.getVariable( a.getActiveObjectName() )).getValue();
        org.jdns.xtuml.runtime.Object pP = (org.jdns.xtuml.runtime.Object)(( ObjectReferenceVariable ) c.getVariable( a.getPassiveObjectName() )).getValue();
        
        // retrieve the names of the classes participating in the association
        org.jdns.xtuml.metamodel.Class aP_class = a.getAssociation().getActivePerspective().getDomainClass();
        org.jdns.xtuml.metamodel.Class pP_class = a.getAssociation().getPassivePerspective().getDomainClass();
        
        Collection ac = aP.getInstances();
        Collection pc = pP.getInstances();
        
        
        Iterator i = ac.iterator();
        while ( i.hasNext() && !( valid_passive || valid_active ) ) {
            active = ( Instance ) i.next();
            if ( active.getInstanceClass() == aP_class ) {
                valid_active = true;
                active_id = aP.getObjectId().intValue();
            }
            else if ( ( active.getInstanceClass() == pP_class ) && !( a.getVerbClause() ) ) {
                valid_passive = true;
                passive_id = aP.getObjectId().intValue();                
            }
        }
        
        if ( !( valid_active || valid_passive ) )
            throw new LemRuntimeException( "Objects does not have the required instances for association " + a.getAssociation().getName() );
        i = pc.iterator();
        
        while ( i.hasNext() && !( valid_passive && valid_active ) ) {
            
            passive = ( Instance ) i.next();
            if ( passive.getInstanceClass() == pP_class ) {
                valid_passive = true;
                passive_id = pP.getObjectId().intValue();                
            }
            else if ( ( passive.getInstanceClass() == aP_class ) && !( a.getVerbClause() ) ) {
                valid_active = true;
                active_id = pP.getObjectId().intValue();                
            }
        }
        if ( !( valid_active && valid_passive ) )
            throw new LemRuntimeException( "Objects does not have the required instances for association " + a.getAssociation().getName() );
        
        AssociationInstance aInst = new AssociationInstance( a.getAssociation() );
        aInst.setActiveInstance( active );
        aInst.setPassiveInstance( passive );
        
        // creating clause is present
        if(a.getLinkObjectName() != null) {
            CreateAction ca = new CreateAction();
            LinkedList l = new LinkedList();
            l.add(a.getAssociation().getAssociationClassRole().getAssociationClass());
            ca.setClasses((Collection)l);
            obj = executeCreateAction(ca, c);
            if(obj == null)
                throw new LemRuntimeException("Exception occurred in creating link object.");
            aInst.setLinkObjectInstance(obj);
        }
        
        if ( c.containsAssociationInstance( aInst ) != null )
            throw new LemRuntimeException( "The association already exist between the two objects" );
        c.addAssociationInstance( aInst );
        
        // create a new LemEvent and notify all listeners
        if(obj == null)
            new LemRelationshipCreationEvent( active_id, passive_id, a.getAssociation().getName() ).notifyAll( c );
        else 
            new LemRelationshipCreationEvent( active_id, passive_id, a.getAssociation().getName(), obj.getObjectId().intValue()).notifyAll( c );            
    }
    
    public Variable evaluateSelectExpression( SelectExpression se, Context c ) throws LemRuntimeException {
	Variable retVar;
        org.jdns.xtuml.metamodel.Class selectedClass = se.getSelectedClass() ;
        RelatedToOperation rto = se.getRelatedToOperation() ;
        Expression condition = se.getCondition();
	LinkedList idList = new LinkedList();
	LinkedList refList = new LinkedList();
	Context tmp = c;
        while (tmp.getParent() != null)
		tmp = tmp.getParent();
	DomainContext domainContext = (DomainContext)tmp;
		
        synchronized ( domainContext ) {
            Collection objectList = domainContext.getObjectList();
                
            for ( Iterator i = objectList.iterator(); i.hasNext(); ) {
                org.jdns.xtuml.runtime.Object o = ( org.jdns.xtuml.runtime.Object ) i.next();
                    
                for (Iterator j = o.getInstances().iterator(); j.hasNext();) {
                    Instance instance = ( Instance ) j.next();
                        
                    if ( instance.getInstanceClass() == selectedClass ) {
                        Variable var = new ObjectReferenceVariable( o );
                        boolean goodVariable = true;
                            
                        if ( condition != null ) {
                            Variable result;
                            Context newContext = new LocalContext( c ) ;
                            newContext.addVariable( "selected" , var ) ;
                            result = evaluateExpression(condition, newContext);
                            if ( result instanceof BooleanVariable ) {
                                if (!((Boolean) ((BooleanVariable) result).getValue()).booleanValue()) {
                                    goodVariable = false;
                                }
                            } else {
                                throw new LemRuntimeException( "Not a Boolean Expression." ) ;
                            }
			    newContext.finish() ;
                        }
                            
                        if ( goodVariable && rto != null ) {
                            Relationship r = rto.getRelationship() ;
                            org.jdns.xtuml.metamodel.Class relatedClass = rto.getRelatedClass() ;
                            org.jdns.xtuml.metamodel.Class instanceClass = instance.getInstanceClass() ;
                            Map associations = instanceClass.getAssociations() ;
                            if ( ! ( associations.containsKey( r.getName() ) &&
                                    ( ( org.jdns.xtuml.metamodel.Class ) associations.get( r.getName() ) ).getName().equals( relatedClass.getName() ) ) ) {
                                goodVariable = false;
                            }
                        }
                            
                         if ( goodVariable ) {
                            idList.add(((org.jdns.xtuml.runtime.Object)var.getValue()).getObjectId());
                            refList.add(var);
                        }
                    }
                }
            }

	    if (se.getMultiplicity() == SelectExpression.MULTIPLICITY_ALL) {
	            SetVariable set = new SetVariable() ;
		    set.addAllToSet( refList ) ;
		    retVar = set;
	    } else {
		    /* This is a lem runtime bug */
		    if (refList.size() > 1)
            	    	throw new LemRuntimeException( "Select with multiplicity any or one returned more than one object!" );
		    /* This is an action language bug (or lem runtime bug) */
		    if (se.getMultiplicity() == SelectExpression.MULTIPLICITY_ONE && refList.size() == 0)
            	    	throw new LemRuntimeException( "Select with multiplicity one returned no objects!" );

		    if (refList.size() == 1)
			    retVar = (ObjectReferenceVariable)refList.get(0);
		    else
			    retVar = new ObjectReferenceVariable(null);
	    }
        }
        
        System.out.println( Thread.currentThread().getName() + " selected " + refList.size() + " references" );
        int id = currentObject.getObjectId().intValue();
        new LemSelectionEvent(id, expressionToString(condition), idList).notifyAll( c );
        return retVar;
    }
    
    /**
     * Execute the given RelateAction in the given Context.
     *
     * @param a the RelateAction to execute
     * @param c the Context in which to execute the action
     * @throws runtime.LemRuntimeException thrown by the metamodel.Object
     * constructor
     */
    public void executeUnrelateAction( UnrelateAction a, Context c ) throws LemRuntimeException {
        // verify the action is valid
        boolean valid_active, valid_passive;
        valid_active = valid_passive = false;
        int active_id, passive_id;
        active_id = passive_id = 0;
        Instance active = null;
        Instance passive = null;
        
        org.jdns.xtuml.runtime.Object aP = (org.jdns.xtuml.runtime.Object)(( ObjectReferenceVariable ) c.getVariable( a.getActiveObjectName() )).getValue();
        org.jdns.xtuml.runtime.Object pP = (org.jdns.xtuml.runtime.Object)(( ObjectReferenceVariable ) c.getVariable( a.getPassiveObjectName() )).getValue();        

        // retrieve the names of the classes participating in the association
        org.jdns.xtuml.metamodel.Class aP_class = a.getAssociation().getActivePerspective().getDomainClass();
        org.jdns.xtuml.metamodel.Class pP_class = a.getAssociation().getPassivePerspective().getDomainClass();
        
        Collection ac = aP.getInstances();
        Collection pc = pP.getInstances();
        
        Iterator i = ac.iterator();
        while ( i.hasNext() && !( valid_passive || valid_active ) ) {
            active = ( Instance ) i.next();
            if ( active.getInstanceClass() == aP_class ) {
                valid_active = true;
                active_id = aP.getObjectId().intValue();
            }
            else if (( active.getInstanceClass() == pP_class ) && !( a.getVerbClause())) {
                valid_passive = true;
                passive_id = aP.getObjectId().intValue();                
            }
        }
        if ( !( valid_active || valid_passive ) )
            throw new LemRuntimeException( "Objects does not have the required instances for association " + a.getAssociation().getName() );
        
        i = pc.iterator();
        while ( i.hasNext() && !( valid_passive && valid_active ) ) {
            passive = ( Instance ) i.next();
            if ( passive.getInstanceClass() == pP_class ) {
                valid_passive = true;
                passive_id = pP.getObjectId().intValue();                
            }
            else if (( passive.getInstanceClass() == aP_class ) && !( a.getVerbClause()) ) {
                valid_active = true;
                active_id = pP.getObjectId().intValue();                            
            }
        }
        if ( !( valid_active && valid_passive ) )
            throw new LemRuntimeException( "Objects does not have the required instances for association " + a.getAssociation().getName() );
        
        // update the runtime model
        AssociationInstance aInst = new AssociationInstance( a.getAssociation() );
        aInst.setActiveInstance( active );
        aInst.setPassiveInstance( passive );
        AssociationInstance aInst2 = c.containsAssociationInstance( aInst );
        if ( aInst2 == null)
            throw new LemRuntimeException( "The association does not exist between the two objects" );
        
        c.removeAssociationInstance( aInst2 );
        
        // create a new LemEvent and notify all listeners    
        int association_id = aInst2.getAssociationId().intValue();
        if(aInst2.getLinkObjectInstance() == null)
            new LemRelationshipDeletionEvent( active_id, passive_id, a.getAssociation().getName() ).notifyAll( c );
        else 
            new LemRelationshipDeletionEvent( active_id, passive_id, a.getAssociation().getName(), aInst2.getLinkObjectInstance().getObjectId().intValue()).notifyAll( c );                    
    }

        
        public String expressionToString(Expression e) {
            String result = "";
            if ( e instanceof Literal ) {
                Literal l = ( Literal ) e;
                return "literal";
            } else if ( e instanceof BinaryOperation ) {
                BinaryOperation o = ( BinaryOperation ) e;
                String left = expressionToString ( o.getLeft());
                String right = expressionToString ( o.getRight());
            
                switch ( o.getType() ) {
                    case BinaryOperation.ADDITION:
                        return left + " + " + right;
                    case BinaryOperation.SUBTRACTION:
                        return left + " - " + right;
                    case BinaryOperation.MULTIPLICATION:
                        return left + " * " + right;
                    case BinaryOperation.DIVISION:
                        return left + " / " + right;
                    case BinaryOperation.EXPONENTIATION:
                        return left + " ^ " + right;
                    case BinaryOperation.EQUAL:
                        return left + " = " + right;
                    case BinaryOperation.LESS_THAN:
                        return left + " < " + right;
                    case BinaryOperation.LESS_THAN_OR_EQUAL:
                        return left + " <= " + right;
                    case BinaryOperation.GREATER_THAN:
                        return left + " > " + right;
                    case BinaryOperation.GREATER_THAN_OR_EQUAL:
                        return left + " >= " + right;
                    case BinaryOperation.MODULO:
                        return left + " mod " + right;
                    case BinaryOperation.NOT_EQUAL:
                        return left + " /= " + right;
                    case BinaryOperation.LOGICAL_AND:
                        return left + " && " + right;
                    case BinaryOperation.LOGICAL_OR:
                        return left + " || " + right;
                }
            } else if ( e instanceof VariableReference ) {
                // Must look up Variable
                VariableReference v = (VariableReference) e ;
                if(v.getObjectName() != null)
                    result = v.getObjectName() + ".";
                if (v.getVariableName() != null)
                    result = result + v.getVariableName();
                return result;
            } else if ( e instanceof UnaryOperation ) {
                UnaryOperation o = ( UnaryOperation ) e;
                String operand = expressionToString( o.getOperand() );
                
                switch ( o.getType() ) {
                    case UnaryOperation.NEGATION:
                        return "-" + operand;
                    case UnaryOperation.LOGICAL_NOT:
                        return "!" + operand;
                }
            }
            return null;
        }
}

/*
 * InstanceInterpreter.java
 *
 * Copyright (C) 2005 Shokouhmand Torabi
 * Copyright (C) 2005 Nick Piggin
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

package runtime;
import metamodel.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collection;
import java.util.HashMap;

/**
 * This is the main InstanceInterpreter file.
 * @author shuku
 */
public class InstanceInterpreter extends java.lang.Thread {
    /** the instance to which this Interpreter belongs to **/
    private runtime.Instance instance;
    
    /** Interpreter associated with this thread of execution */
    private Interpreter interpreter;
    
    /** The context in which this thread executes */
    private DomainContext context;
    
    /** The scenario to which this instance belongs **/
    private Scenario scenario ;
    
    /** Creates a new instance of Interpreter
     * @param instance the instance to which this interpreter belongs.
     */
    public InstanceInterpreter( runtime.Instance instance, DomainContext c ) {
        this.instance = instance ;
        
        setName( getName() + " (" + instance.getInstanceClass().getName() + ")" );
        context = c;
        instance.instanceInObject.getRunningInterpretersRefcount().get();
        interpreter = new Interpreter( instance.instanceInObject ) ;
        start() ;
    }
    
    /** this method will start the interpretion thread by keep getting the next signal
     *until there is a signal which would lead it to the next state, it will run the procedure associated
     *with the new state ( only if the new state is not a DeletionState ).
     */
    public boolean init() throws LemRuntimeException {
        metamodel.Class c = instance.getInstanceClass() ;
        metamodel.StateMachine m = c.getStateMachine() ;
        int tCount = m.getInitialisingTransitionCount();
        
        if ( tCount == 0 )
            return false;
        
        while ( true ) {
	    Signal s;
            System.out.println(Thread.currentThread().getName() + " [init] InstanceInterpreter getting a next signal");
	    do {
		    if (!context.debugObject.checkRuntimeState())
			    return false;
	            s = instance.getNextSignal(context.debugObject);
	    } while (s == null);
            
            System.out.println(Thread.currentThread().getName() + " [init] InstanceInterpreter got a next signal");

            Iterator i = m.getTransitionList().iterator();
            
            while ( i.hasNext() ) {
                Transition t = ( Transition ) i.next();
                
                /* Skip non-initialising transition */
                if ( t.getFromState() != null )
                    continue;
                
                if ( s.getEvent() == t.getEvent() ) {
                    metamodel.State newState = t.getToState() ;
                    int id = instance.instanceInObject.getObjectId().intValue();
                    instance.currentState = newState ;
                    String to = instance.currentState.getName();
                    new LemStateTransitionEvent(id, "init", to).notifyAll( context );
                    Procedure p = newState.getProcedure();

                    System.out.println( Thread.currentThread().getName()
                      + " [init] transitioning state to " + newState.getName());
		 
		    context.debugObject.makeTransition(t);
		    context.debugObject.runState(newState);
		    if (!context.debugObject.checkRuntimeState()) {
			    context.debugObject.delEntity();
			    return false;
		    }
		    
                    interpreter.interpret( p, s, context );
		    
		    context.debugObject.delEntity();
                    if ( newState instanceof NonDeletionState )
                        return true;
                    else
                        return false;
                }
            }

            context.debugObject.delEntity();
        }
    }
    
    
    /** will run the thread until it enters a deletionstate **/
    public void run() {
        try {
	    boolean ret;
	    instance.isActive = true;
            ret = init();
            while (ret) {
                    ret = advance();
	    }
            System.out.println( "InstanceInterpreter finished" );

	    synchronized (instance.instanceInObject) {
		    instance.isActive = false;
	            if (instance.instanceInObject.getRunningInterpretersRefcount().put()) {
	                /**
	                 * If this is the last running interpreter, then delete
			 * the object from the global object pool (analogous to
			 * a delete object action for passive objects)
	                 */
	                context.delObject( instance.instanceInObject );
			instance.instanceInObject.drainSignals(context.debugObject);
	            }
		    instance.drainSignals(context.debugObject);
		    instance.instanceInObject.notifyAll();
	    }
        } catch ( LemRuntimeException e ) {
            e.printStackTrace();
        }
    }
    
    /** Advances the statemachine by one state (if possible)
     *and will run the procedure associated with the new state if the new state is not a
     *DeletionState.
     **/
    private boolean advance() throws LemRuntimeException {
        metamodel.Class c = instance.getInstanceClass() ;
        metamodel.StateMachine m = c.getStateMachine() ;
        metamodel.State currentState = instance.currentState ;
        
        if ( m.getStateTransitionCount() == 0 )
            return false;
        
        System.out.println( Thread.currentThread().getName() + " InstanceInterpreter getting a next signal" );
	Signal s;
	do {
		if (!context.debugObject.checkRuntimeState())
		    return false;
	        s = instance.getNextSignal(context.debugObject);
	} while (s == null);

        System.out.println( Thread.currentThread().getName() + " InstanceInterpreter got a next signal" );
        
        Iterator i = m.getTransitionList().iterator();
        while ( i.hasNext() ) {
            Transition t = (Transition)i.next();
            
            /* Skip initialising transition */
            if ( t.getFromState() == null )
                continue;
            
            if ( s.getEvent() == t.getEvent()
            			&& t.getFromState() == currentState ) {
                metamodel.State newState = t.getToState();
                int id = instance.instanceInObject.getObjectId().intValue();
                String from = instance.currentState.getName();
                String to = newState.getName();
                instance.currentState = newState;
                new LemStateTransitionEvent(id, from, to).notifyAll( context );
                Procedure p = newState.getProcedure();

               	System.out.println( Thread.currentThread().getName()
                	+ " transitioning state to " + newState.getName() );

		context.debugObject.makeTransition(t);
		context.debugObject.runState(newState);
		if (!context.debugObject.checkRuntimeState()) {
		    context.debugObject.delEntity();
		    return false;
		}

                interpreter.interpret( p, s, context );

		context.debugObject.delEntity();
                if ( newState instanceof NonDeletionState )
                    return true;
                else
                    return false;
            }
        }
	context.debugObject.delEntity();
        return true;
    }
    
    public Scenario getScenario() {
        return scenario;
    }
    
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
}

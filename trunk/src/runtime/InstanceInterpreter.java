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
    
    public static final int PLAY = 0 ;
    public static final int PAUSE = 1 ;
    public static final int STOP = 2 ;
    
    /** the instance to which this Interpreter belongs to **/
    private runtime.Instance instance;
    
    /** Interpreter associated with this thread of execution */
    private Interpreter interpreter;
    
    /** The context in which this thread executes */
    private Context context;
    
    /** The scenario to which this instance belongs **/
    private Scenario scenario ;
    
    private int flag = PLAY ;
    /** Creates a new instance of Interpreter
     * @param instance the instance to which this interpreter belongs.
     */
    public InstanceInterpreter( runtime.Instance instance, Context c ) {
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
            System.out.println(Thread.currentThread().getName() + " [init] InstanceInterpreter getting a next signal");
            Signal s = instance.getNextSignal();
            
            System.out.println(Thread.currentThread().getName() + " [init] InstanceInterpreter got a next signal");
            Iterator i = m.getTransitionList().iterator();
            
            while ( i.hasNext() ) {
                Transition t = ( Transition ) i.next();
                
                /* Skip non-initialising transition */
                if ( t.getFromState() != null )
                    continue;
                
                if ( s.getEvent() == t.getEvent() ) {
                    metamodel.State newState = t.getToState() ;
                    instance.currentState = newState ;
                    System.out.println( Thread.currentThread().getName()
                        + " [init] transitioning state to " + newState.getName() );

                    if ( newState instanceof NonDeletionState ) {
                        Procedure p = newState.getProcedure() ;
                        interpreter.interpret( p , context ) ;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
    }
    
    
    /** will run the thread until it enters a deletionstate **/
    public void run() {
        execution:
        try {
            if ( init() ) {
                while ( true ) {
                    if (flag == PLAY) {
                        if ( !advance() )
                            break ;
                    }else if ( flag == STOP) {
                        break execution ; 
                    }
                }
            }
            System.out.println( "InstanceInterpreter finished" );
            if ( instance.instanceInObject.getRunningInterpretersRefcount().put() ) {
                /**
                 * If this is the last running interpreter, then delete the
                 * object from the global object pool (analogous to a delete
                 * object action for passive objects)
                 */
                context.delObject( instance.instanceInObject );
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
        Signal s = instance.getNextSignal();
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
                instance.currentState = newState;
                System.out.println( Thread.currentThread().getName()
                + " transitioning state to " + newState.getName() );
                if ( newState instanceof NonDeletionState ) {
                    Procedure p = newState.getProcedure();
                    interpreter.interpret( p, context );
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    
    public Scenario getScenario() {
        return scenario;
    }
    
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
    
    /** pause execution of this instance 
     */
    public void pauseExecution( ) {
       
            this.flag = PAUSE ;
       
    }
    
    /** resume execution of this instance 
     */
    
    public void resumeExecution( ) {
            this.flag = PLAY ;
    }
    
    /** stop execution of this instance 
     */
    
    public void stopExecution( ) {
            this.flag = STOP ;
    }
    
    
}

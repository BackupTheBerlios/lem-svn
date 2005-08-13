/*
 * InstanceInterpreter.java
 */

package runtime;
import metamodel.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Collection;
import java.util.HashMap;
import runtime.Context ;


/**
 * This is the main InstanceInterpreter file.
 * @author npiggin
 * @author sjr
 * @todo javadoc
 */
public class InstanceInterpreter extends java.lang.Thread{
    /** the instance to which this Interpreter belongs to **/
    private runtime.Instance instance;
    
    private runtime.Object currentObject;
    /** static constants **/
    final static int STOPPED = 1 ;
    final static int RUNNING = 2 ;
    /** state of the InstanceInterpreter e.g. stopped or running */
    private int state = STOPPED ;
    
    /** Creates a new instance of Interpreter
     * @param obj is the object that the Interpreter is
     * executing the procedures for.
     */
    public InstanceInterpreter(runtime.Instance instance) {
        this.state = STOPPED ;
        this.instance = instance ;
        init() ;
        start() ;
    }
    
    /** this method will start the interpretion thread by getting the next signal,
     *traversing the corresponding transition and interpreting the procedure of the
     *corresponding state of the statemachine.
     */
    public void init() {
        this.state = RUNNING ;
        metamodel.Class c = instance.getInstanceClass() ;
        metamodel.StateMachine m = c.getStateMachine() ;
        try {
            Signal s = instance.getNextSignal() ;
            int tCount = m.getInitialisingTransitionCount() ;
            if ( tCount > 0 ) {
                Transition[] ts =  m.getInitialisingTransitions() ;
                for ( int i  = 0 ; i < tCount ; i++) {
                    Transition t = ts[i] ;
                    if (s.getEvent().getName().equals( t.getEvent().getName() )) {
                        metamodel.State newState = t.getToState() ;
                        instance.currentState = newState ;
                        if ( newState instanceof NonDeletionState) {
                        Procedure p = newState.getProcedure() ;
                        Context newContext = new Context() ;
                        Interpreter interpreter = new Interpreter(null) ;
                        interpreter.interpret( p , newContext ) ;
                        }
                    }
                }
            }
        } catch (Exception e) {
            this.state = STOPPED ;
        }
        this.state = STOPPED ;
    }
    
    
    /** will run the thread until it enters a deletionstate **/        
    public void run() {
        while ( instance.currentState instanceof NonDeletionState && isAlive() ) {
            yield() ; 
            advance() ; 
            try {
                sleep( 100 ) ; 
            }catch (Exception e ) {}
        }
    }
    
    /** Advances the statemachine by one state (if possible) **/    
    public void advance() {
        this.state = RUNNING ;
        metamodel.Class c = instance.getInstanceClass() ;
        metamodel.StateMachine m = c.getStateMachine() ;
        metamodel.State currentState = instance.currentState ;
        try {
            Signal s = instance.getNextSignal() ;
            int tCount = m.getStateTransitionCount() ;
            if ( tCount > 0 ) {
                Transition[] ts =  m.getTransitions() ; 
                for ( int i  = 0 ; i < tCount ; i++) {
                    Transition t = ts[i] ;
                    if (s.getEvent().getName().equals( t.getEvent().getName() )
                    && t.getFromState().getName().equals(currentState.getName()) ) {
                        metamodel.State newState = t.getToState() ;
                        instance.currentState = newState ;
                        if ( newState instanceof NonDeletionState) {
                        Procedure p = newState.getProcedure() ;
                        Context newContext = new Context() ;
                        Interpreter interpreter = new Interpreter(null) ;
                        interpreter.interpret( p , newContext ) ;
                        } 
                    }
                }
            }
        } catch (Exception e) {
            this.state = STOPPED ;
        }
        this.state = STOPPED ;
    }
    
    public void stopInterpreter() throws Exception {
        if (state == STOPPED ) {
            this.stop() ;
        } else {
            sleep ( 100 ) ;
            stopInterpreter() ;
        }
    }
    
    public void resumeInterpreter() throws Exception {
        resume() ;
    }
    
}
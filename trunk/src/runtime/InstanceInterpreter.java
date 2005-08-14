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
 * @author shuku 
 */
public class InstanceInterpreter extends java.lang.Thread{
    /** the instance to which this Interpreter belongs to **/
    private runtime.Instance instance;
    /** static constants **/
    final static int STOPPED = 1 ;
    final static int RUNNING = 2 ;
    /** state of the InstanceInterpreter e.g. stopped or running */
    private int state = STOPPED ;
    
    /** Creates a new instance of Interpreter
     * @param instance the instance to which this interpreter belongs.
     */
    public InstanceInterpreter(runtime.Instance instance) {
        this.state = STOPPED ;
        this.instance = instance ;
        init() ;
        start() ;     
    }
    
    /** this method will start the interpretion thread by keep getting the next signal
     *until there is a signal which would lead it to the next state, it will run the procedure associated
     *with the new state ( only if the new state is not a DeletionState ).
     */
    public void init() {
        this.state = RUNNING ;
        metamodel.Class c = instance.getInstanceClass() ;
        metamodel.StateMachine m = c.getStateMachine() ;
        try {
            signal :
            while ( true ) {
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
                            break signal ;
                        }
                    }
                }
                sleep(100) ;
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
    
    /** Advances the statemachine by one state (if possible)
     *and will run the procedure associated with the new state if the new state is not a
     *DeletionState.
     **/
    private void advance() {
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
    
    /** This method will stop the interpretion of the current instance
     *The interpretor will stop when the a state has been fully executed
     */
    public void stopInterpreter() throws Exception {
        while ( state == RUNNING) {
            sleep( 100 ) ;
        }
        this.stop() ;
    }
    
    /**This method will resume execution of the current state
    */
    public void resumeInterpreter() throws Exception {
        resume() ;
    }
    
}
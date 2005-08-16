/*
 * InstanceInterpreter.java
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
public class InstanceInterpreter extends java.lang.Thread{
    /** the instance to which this Interpreter belongs to **/
    private runtime.Instance instance;

    /** Interpreter associated with this thread of execution */
    private Interpreter interpreter;

    /** The context in which this thread executes */
    private Context context;
    
    /** Creates a new instance of Interpreter
     * @param instance the instance to which this interpreter belongs.
     */
    public InstanceInterpreter(runtime.Instance instance, Context c) {
        this.instance = instance ;
	context = c;
	interpreter = new Interpreter(instance.instanceInObject) ;
        start() ;     
    }
    
    /** this method will start the interpretion thread by keep getting the next signal
     *until there is a signal which would lead it to the next state, it will run the procedure associated
     *with the new state ( only if the new state is not a DeletionState ).
     */
    public boolean init() {
        metamodel.Class c = instance.getInstanceClass() ;
        metamodel.StateMachine m = c.getStateMachine() ;
        int tCount = m.getInitialisingTransitionCount() ;

        try {
            while ( true ) {
		System.out.println("InstanceInterpreter getting a next signal");
                Signal s = instance.getNextSignal() ;
		System.out.println("InstanceInterpreter got a next signal");
		
                if ( tCount > 0 ) {
                    Transition[] ts =  m.getInitialisingTransitions() ;
                    for ( int i  = 0 ; i < tCount ; i++) {
                        Transition t = ts[i] ;
                        if (s.getEvent().getName().equals( t.getEvent().getName() )) {
                            metamodel.State newState = t.getToState() ;
                            instance.currentState = newState ;
			    System.out.println("InstanceInterpreter transitioning state to " + newState.getName());
                            if ( newState instanceof NonDeletionState) {
			        System.out.println("InstanceInterpreter executing procedure");
                                Procedure p = newState.getProcedure() ;
                                interpreter.interpret( p , context ) ;
			        System.out.println("InstanceInterpreter done with proc");
				return true;
                            } else {
				return false;
			    }
                        }
                    }
                }
            }
        } catch (Exception e) {
	    return false;
        }
    }
    
    
    /** will run the thread until it enters a deletionstate **/
    public void run() {
        if (!init())
		return;
	
        while ( advance() );
	System.out.println("InstanceInterpreter finished");
    }
    
    /** Advances the statemachine by one state (if possible)
     *and will run the procedure associated with the new state if the new state is not a
     *DeletionState.
     **/
    private boolean advance() {
        metamodel.Class c = instance.getInstanceClass() ;
        metamodel.StateMachine m = c.getStateMachine() ;
        metamodel.State currentState = instance.currentState ;
        int tCount = m.getStateTransitionCount() ;

        try {
	    System.out.println("InstanceInterpreter getting a next signal");
            Signal s = instance.getNextSignal() ;
	    System.out.println("InstanceInterpreter got a next signal");
            if ( tCount > 0 ) {
                Transition[] ts =  m.getTransitions() ;
                for ( int i  = 0 ; i < tCount ; i++) {
                    Transition t = ts[i] ;
                    if (s.getEvent().getName().equals( t.getEvent().getName() )
                    && t.getFromState().getName().equals(currentState.getName()) ) {
                        metamodel.State newState = t.getToState() ;
                        instance.currentState = newState ;
			    System.out.println("InstanceInterpreter transitioning state to " + newState.getName());
                        if ( newState instanceof NonDeletionState) {
			    System.out.println("InstanceInterpreter executing procedure");
                            Procedure p = newState.getProcedure() ;
                            interpreter.interpret( p , context ) ;
			    System.out.println("InstanceInterpreter done with proc");
			    return true;
                        } else {
			    return false;
			}
                    }
                }
            }
        } catch (Exception e) {
	    return false;
        }
        return true;
    }
}

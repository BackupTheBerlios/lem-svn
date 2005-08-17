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
    public boolean init() throws LemRuntimeException {
	metamodel.Class c = instance.getInstanceClass() ;
	metamodel.StateMachine m = c.getStateMachine() ;
	int tCount = m.getInitialisingTransitionCount();

	if ( tCount == 0 )
		return false;

	while ( true ) {
		System.out.println("InstanceInterpreter getting a next signal");
		Signal s = instance.getNextSignal() ;
		System.out.println("InstanceInterpreter got a next signal");
	    
		Iterator i = m.getTransitionList().iterator();
		while (i.hasNext()) {
		    Transition t = (Transition)i.next();

		    /* Skip non-initialising transition */
		    if ( t.getFromState() != null )
			    continue;

		    if ( s.getEvent() == t.getEvent() ) {
			metamodel.State newState = t.getToState() ;
			instance.currentState = newState ;
			System.out.println("InstanceInterpreter transitioning state to " + newState.getName());
			if ( newState instanceof NonDeletionState) {
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
	try {
	    if (!init())
		return;
	    
	    while ( advance() );
	    System.out.println("InstanceInterpreter finished");
	} catch( LemRuntimeException e ) {
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

		if (m.getStateTransitionCount() == 0)
			return false;
	
		System.out.println("InstanceInterpreter getting a next signal");
		Signal s = instance.getNextSignal() ;
		System.out.println("InstanceInterpreter got a next signal");
		Iterator i =  m.getTransitionList().iterator();
		while (i.hasNext()) {
		    Transition t = (Transition)i.next();
		    
		    /* Skip initialising transition */
		    if ( t.getFromState() == null )
			    continue;
		    
		    if ( s.getEvent() == t.getEvent()
			    	&& t.getFromState() == currentState ) {
			metamodel.State newState = t.getToState() ;
			instance.currentState = newState ;
			System.out.println("InstanceInterpreter transitioning state to " + newState.getName());
			if ( newState instanceof NonDeletionState) {
			    Procedure p = newState.getProcedure() ;
			    interpreter.interpret( p , context ) ;
			    return true;
			} else {
			    return false;
			}
		    }
		}
		return true;
	}
}

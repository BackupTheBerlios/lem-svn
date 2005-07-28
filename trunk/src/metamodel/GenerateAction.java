/*
 * GenerateAction.java
 *
 * Created on July 28, 2005
 */

package metamodel;
import java.util.LinkedList;

/**
 * This is the xtUML signal generation action.
 * @author npiggin
 */
public class GenerateAction extends Action {
	private Event event = null;
	private VariableReference target = null;
	private LinkedList parameters = null;
	
	/** Creates a new instance of GenerateAction */
	public GenerateAction() {
	}
	
	/**
	 * Sets the event for which a signal is to be generated.
	 * @param e the event.
	 */
	public void setEvent( Event e ) {
		event = e;
	}
	
	/**
	 * Gets the event for which a signal is to be generated.
	 * @return the event.
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Sets the target to which the generated signal is to be sent.
	 * @todo this must be of type objref.
	 */
	public void setTarget(VariableReference t) {
		target = t;
	}

	/**
	 * Gets the target to which the generated signal is to be sent.
	 */
	public VariableReference getTarget() {
		return target;
	}
	
	/**
	 * Adds a parameter to the event generation.
	 */
	public void setParameters(LinkedList p) {
		parameters = p;
	}

	/**
	 * Returns the list of parameters for the event generation.
	 */
	public LinkedList getParameters() {
		return parameters;
	}
}

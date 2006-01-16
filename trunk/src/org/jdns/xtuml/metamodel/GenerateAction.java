/*
 * GenerateAction.java
 *
 * Created on July 28, 2005
 */

package org.jdns.xtuml.metamodel;
import java.util.LinkedList;

/**
 * This is the xtUML signal generation action.
 * @author npiggin
 */
public class GenerateAction extends Action {
	private String eventName = null;
	private VariableReference target = null;
	private LinkedList parameters = null;
	private Expression delayTime = null ; 
        
	/** Creates a new instance of GenerateAction */
	public GenerateAction() {
	}
	
	/**
	 * Sets the name of the event for which a signal is to be generated.
	 * @param e the event name.
	 */
	public void setEventName( String eventName ) {
		this.eventName = eventName;
	}
	
	/**
	 * Gets the event name for which a signal is to be generated.
	 * @return the event.
	 */
	public String getEventName() {
		return eventName;
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

    public Expression getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Expression delayTime) {
        this.delayTime = delayTime;
    }
}

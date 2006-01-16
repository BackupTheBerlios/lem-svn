/*
 * CancelAction.java
 *
 * Created on July 28, 2005
 */

package org.jdns.xtuml.metamodel;

/**
 * This is the xtUML signal generation action.
 * @author npiggin
 */
public class CancelAction extends Action {
	private String eventName = null;
        
	/** Creates a new instance of CancelAction */
	public CancelAction() {
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
}

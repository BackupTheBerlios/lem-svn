/* 
 * Transition.java
 *
 * Copyright (C) 2005 Steven Michael Ring
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

/*
 * Transition.java
 *
 * Created on September 24, 2004, 5:04 PM
 */

package org.jdns.xtuml.metamodel;

/**
 * A transition represents a change in the state of a state machine in
 * response to an enabling event.
 *
 * @author  smr
 */
public abstract class Transition implements DescribedEntity {
    
    /** The event which enables this transition [R515]*/
    private Event event = null;
    
    /** The state machine to which this transition belongs [R514 et al]*/
    private StateMachine stateMachine = null;
    
    /** a description of the state machine */
    private String description = "";
    
    /**
     * The state to which this transition takes the state machine.
     * This support metamodel relationships R511 and R509
     */
    private State toState = null;
    
    /** Creates a new instance of Transition */
    public Transition() {
    }
    
    /** Getter for property description.
     * @return Value of property description.
     */
    public java.lang.String getDescription() {
        return description;
    }
    
    /** Setter for property description.
     * @param description New value of property description.
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }
    
    /** Getter for property event.
     * @return Value of property event.
     */
    public Event getEvent() {
        return event;
    }
    
    /** Setter for property event.
     * @param event New value of property event.
     */
    public void setEvent(Event event) {
        this.event=event;
    }

    /**
     * Returns the StateMachine in which this transition resides
     * @return the StateMachine in which this transition resides
     */
    public StateMachine getStateMachine() {
        return stateMachine;
    }

    /** Setter for property stateMachine.
     * @param stateMachine New value of property stateMachine.
     */
    public void setStateMachine(StateMachine stateMachine) {
        this.stateMachine=stateMachine;
    }
    
    /**
     * A transition occurs between a "from" state and a "to" state.
     *
     * @return the State from which this transistion is directed. Returns null
     * this is an "initialising" transition.
     */
    public abstract State getFromState();
        
   
    /**
     * A transition occurs between a "from" state and a "to" state.
     *
     * @return the State to which this transistion is directed. 
     */
    public State getToState() {
        return toState;
    }
    
    /**
     * A transition occurs between a "from" state and a "to" state.
     *
     * @param aState to which this transistion is directed. 
     */
    public void setToState( State aState ) {
        toState = aState;
    }
        
    
}

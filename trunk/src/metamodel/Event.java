/* 
 * Event.java
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
 * Event.java
 *
 * Created on September 24, 2004, 4:46 PM
 */

package metamodel;

/**
 * A signal that a certain event has occured. The event may (or may not) give rise to 
 * a state transition with one or more state machines.
 *
 * @author  smr
 */
public class Event implements DescribedEntity {
    
    /** the name of the event */
    private String name = "";
    
    /** the class for which this is a publicly visible event (R901) */
    private Class domainClass = null;
    
    /** the state machine for which this is a private event (R513) */
    private StateMachine stateMachine = null;
    
    /** the signature for this event [R505] */
    private EventSignature signature = null;
    
    /** a description of the state machine */
    private String description = "";
    
    
    /** Creates a new instance of Event */
    public Event() {
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

    /** Getter for property domainClass.
     * @return Value of property domainClass.
     */
    public Class getDomainClass() {
        return domainClass;
    }

    /** Setter for property domainClass.
     * @param domainClass New value of property domainClass.
     */
    public void setDomainClass(Class domainClass) {
        this.domainClass=domainClass;
    }

    /** Getter for property stateMachine.
     * @return Value of property stateMachine.
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

    /** Getter for property name.
     * @return Value of property name.
     */
    public String getName() {
        return name;
    }

    /** Setter for property name.
     * @param name New value of property name.
     **/
    public void setName(String name) {
        this.name=name;
    }
    
    /**
     * Return the signature for this event
     * @return the signature for this state
     */
    public EventSignature getSignature() {
        return signature;
    }

    /** 
     * Set the signature for this event 
     *
     * @param aSignature for this event
     */
    public void setSignature(EventSignature aSignature) {
        this.signature = aSignature;
        aSignature.setEvent( this );
    }
}


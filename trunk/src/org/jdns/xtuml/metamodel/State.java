/* 
 * State.java
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
 * State.java
 *
 * Created on September 25, 2004, 7:43 AM
 */

package org.jdns.xtuml.metamodel;

/**
 * A state in which a StateMachine exists
 * @author  smr
 */
public abstract class State implements DescribedEntity {
    
    /** the name of the state */
    private String name = "";
    
    /** a description of the state*/
    private String description = "";
    
    /** the state machine to which this state belongs */
    private StateMachine stateMachine = null;
    
    /** the signature for this state [R520] */
    private StateSignature signature = null;
    
    /** the procedure executed by this state */
    private Procedure procedure = null;
    
    /** Creates a new instance of State */
    public State() {
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

    /** 
     * Each state belongs to exactly one state machine.
     *
     * @return the StateMachine to which this state belongs
     */
    public StateMachine getStateMachine() {
        return stateMachine;
    }

    /** 
     * Set the state machine to which this state belongs. 
     * Each state belongs to exactly one state machine
     *
     * @param stateMachine to which this state belongs
     */
    public void setStateMachine(StateMachine stateMachine) {
        this.stateMachine=stateMachine;
    }
    
    /** Getter for property name.
     * @return Value of property name.
     */
    public java.lang.String getName() {
        return name;
    }
    
    /** Setter for property name.
     * @param name New value of property name.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    /**
     * Return the number of this state. State number begin at 1
     *
     * For convenience, states are usually numbered from 1 onwards. At present, there
     * is no syntax in LEM to allow state numbers to be defined.  As a trial measure
     * lets allow the order of state declaration to implicitly define the state number.
     *
     * @param aState whose number is to be returned
     * @return the state number of the supplied state or zero if the state is not 
     * know to the associated state machine (or there is no state machine)
     */
    public int getNumber( State aState ) {
        
        if ( null == stateMachine )
            return 0;               // no state machine
        
        return stateMachine.getStateIndex( aState ) + 1;
    }

    /**
     * Return the signature for this state
     * @return the signature for this state
     */
    public StateSignature getSignature() {
        return signature;
    }

    /** 
     * Set the signature for this state 
     *
     * @param aSignature for this state
     */
    public void setSignature(StateSignature aSignature) {
        this.signature = aSignature;
    }

    /**
     * Return the procedure associated with this state (across R516)
     *
     * @return the procedure associated with this state (across R516)
     */
    public Procedure getProcedure() {
        return procedure;
    }

    /**
     * Set the procedure associated with this state (across R516)
     *
     * @param theProcedure associated with this state (across R516)
     */
    public void setProcedure(Procedure theProcedure) {
        this.procedure = theProcedure;
    }
    
    public String toString() {
        return name;
    }
}

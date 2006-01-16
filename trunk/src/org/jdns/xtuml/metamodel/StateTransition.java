/* 
 * StateTransition.java
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
 * StateTransition.java
 *
 * Created on September 25, 2004, 8:02 AM
 */

package org.jdns.xtuml.metamodel;

/**
 * A StateTransition occurs between a non deletion "from" state and a "to" state.
 * The "to" state may be either a non deletion or a deletion state. 
 *
 * This is an associative class supporting metamodel relationship R505
 *
 * @author  smr
 */
public class StateTransition extends Transition {
    
    /** the state from which this transition occurs [R505] */
    private NonDeletionState fromState = null;
    
    /** Creates a new instance of StateTransition */
    public StateTransition() {
    }
    
    /**
     * A transition occurs between a "from" state and a "to" state.
     *
     * @return the State from which this transistion is directed. Returns null
     * this is an "initialising" transition.
     */
    public State getFromState() {
        return fromState;
    }
    
    /**
     * A transition occurs between a "from" state and a "to" state.
     *
     * @param aState from which this transistion is directed. 
     */
    public void setFromState( NonDeletionState aState ) {
        fromState = aState;
    }
    
}

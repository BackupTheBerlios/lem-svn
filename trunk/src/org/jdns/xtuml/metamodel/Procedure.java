/* 
 * Procedure.java
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
 * Procedure.java
 *
 * Created on October 7, 2004, 6:49 AM
 */

package org.jdns.xtuml.metamodel;

/**
 * A procedure is a collection of actions executed upon entry to a state.
 *
 * @author  smr
 */
public class Procedure {
    
    /** the state to which this procuedure belongs */
    private State state = null;
    
    private ActionBlock block = null;
    
    /** Creates a new instance of Procedure */
    public Procedure() {
    }

    /**
     * Return the state to which this procedure belongs
     * 
     * @return the state to which this procedure belongs
     */
    public State getState() {
        return state;
    }

    /**
     * Set the state to which this procedure belongs
     * 
     * @param theState to which this procedure belongs
     */
    public void setState(State theState) {
        this.state = theState;
    }

    /** returns the procedure's main block */
    public ActionBlock getActionBlock() {
	return block;
    }

    public void setActionBlock( ActionBlock a ) {
        this.block = a;
    }
    
    /**
     * Returns the list of actions contained in this procedure's action block.
     *
     * @return the list of actions
     */
    public java.util.LinkedList getActions() {
        return block.getActions();
    }
}

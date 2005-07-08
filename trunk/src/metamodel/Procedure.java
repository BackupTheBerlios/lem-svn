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

package metamodel;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * A procedure is a collection of actions executed upon entry to a state.
 *
 * @author  smr
 */
public class Procedure {
    
    /** the state to which this procuedure belongs */
    private State state = null;
    
    private LinkedList actions = new LinkedList();
    
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
    
    public void addAction( metamodel.Action a ) {
        actions.add(a);
    }

    public LinkedList getActions() {
	    return actions;
    }
}

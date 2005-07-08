/* 
 * StateSignature.java
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
 * StateSignature.java
 *
 * Created on September 25, 2004, 12:58 PM
 */

package metamodel;

/**
 * The parameter signature for a state. These parameters must be supplied to
 * the state procedure by any event which causes a transition this state.
 *
 * @author  smr
 */
public class StateSignature extends Signature {
    
    /** The state to which this signature belongs */
    private State state = null;
    
    /** Creates a new instance of StateSignature */
    public StateSignature() {
    }

    /**
     * Return the State associated with this StateSignature
     *
     * @return the State associated with this StateSignature
     */
    public State getState() {
        return state;
    }

    /**
     * Set the State associated with this StateSignature
     *
     * @param aState associated with this StateSignature
     */
    public void setState(State aState) {
        this.state = aState;
    }
    
    /** 
     * Return the domain to which this signature belongs
     *
     * @return the domain to which this signature belongs
     */
    public Domain getDomain(){
        return state.getSignature().getDomain();
    }
}

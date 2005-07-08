/* 
 * InitialisingTransition.java
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
 * InitialisingTransition.java
 *
 * Created on September 25, 2004, 7:32 AM
 */

package metamodel;

/**
 * A transition from the creation pseudo state to some other state
 * @author  smr
 */
public class InitialisingTransition extends Transition {
    
    /** Creates a new instance of InitialisingTransition */
    public InitialisingTransition() {
    }
    
    /**
     * An InitialisingTransition occurs between the "creation pseudo state"
     * and a "to" state.
     *
     * @return null as this is an "initialising" transition.
     */
    public State getFromState() {
        return null;
    }
    
}

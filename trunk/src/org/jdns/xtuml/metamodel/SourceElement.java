/* 
 * SourceElement.java
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
 * SourceElement.java
 *
 * Created on April 26, 2004, 6:10 AM
 */

package org.jdns.xtuml.metamodel;
/**
 * Represents an object that has been defined and/or referenced in model/domain source code
 *
 * @author  smr
 */
public abstract class SourceElement {
    
    /** first source code reference to this element */
    private SourceReference firstReferenced = null;
    
    /** position in source code where thie element is formally defined */
    private SourceReference defined = null;
  
    
    /** Creates a new instance of SourceElement */
    public SourceElement() {
    }
    
    /** Getter for property defined.
     * @return Value of property defined.
     *
     */
    public SourceReference getDefined() {
        return defined;
    }
    
    /** Setter for property defined.
     * @param defined New value of property defined.
     *
     */
    public void setDefined(SourceReference defined) {
        this.defined = defined;
    }
    
    /** Getter for property firstReferenced.
     * @return Value of property firstReferenced.
     *
     */
    public SourceReference getFirstReferenced() {
        return firstReferenced;
    }
    
    /** Setter for property firstReferenced.
     * @param firstReferenced New value of property firstReferenced.
     *
     */
    public void setFirstReferenced(SourceReference firstReferenced) {
        if  ( null == this.firstReferenced )
            this.firstReferenced = firstReferenced;
    }
    
}

/* 
 * Attribute.java
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
 * Attribute.java
 *
 * Created on January 31, 2004, 3:23 PM
 */
package org.jdns.xtuml.metamodel;

import java.util.*;

/**
 * Represents the attribute of a class
 *
 * @author  smr
 */
public abstract class Attribute extends DataItem {
    
    /** the identifiers to which this attribute supplies a value */
    protected ArrayList identifiers = new ArrayList();
    
  
    /** Creates a new instance of Attribute */
    public Attribute() {
    }
    
    
    /** 
     * Return an ArrayList containing the Identifiers in which this 
     * attribute participates. The ArrayList may be empty if this
     * attribute does not supply a value to any identifier.
     *
     * @return an ArrayList containing the Identifiers in which this 
     * attribute participates.
     */
    public ArrayList getIdentifiers() {
        return identifiers;
    }
    
    /**
     * Add the supplied identifer to the list of identifiers to which this
     * attribute contributes a value.
     * @param theIdentifier the identifier to add
     */
    public void addIdentifier( Identifier theIdentifier ) {
        identifiers.add( theIdentifier );
    }
}

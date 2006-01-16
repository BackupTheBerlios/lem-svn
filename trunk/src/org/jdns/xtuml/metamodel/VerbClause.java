/* 
 * VerbClause.java
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

package org.jdns.xtuml.metamodel;
/**
 * Represents the VerbClause used to describe a perspective of an association
 *
 * @author  smr
 */
public class VerbClause {
    
    /** the verb clause is a simple string containing a verb */
    private String clause = "";
    
    /** 
     * Creates a new instance of VerbClause using the supplied string
     *
     * @param aVerbClause for this instance 
     */
    public VerbClause( String aVerbClause ) {
        clause = aVerbClause;
    }
    
    /** 
     * Return the string representation of the verb clause
     *
     * @return the string representation of the verb clause
     */
    public String toString() {
        return clause;
    }
    
}

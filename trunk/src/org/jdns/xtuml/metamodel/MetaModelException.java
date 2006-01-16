/* 
 * MetaModelException.java
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
 * MetaModelException.java
 *
 * Created on January 2, 2005, 3:09 PM
 */

package org.jdns.xtuml.metamodel;

/**
 * Instances of this class are thrown when various "semantic" errors occur. For example,
 * adding an attribute of type "double" to a class when no type "double" is defined in the domain.
 * @author smr
 */
public class MetaModelException extends Exception {
    
    /** Creates a new instance of MetaModelException */
    public MetaModelException() {
        super();
    }
    
    /** 
     * Creates a new exception based on the supplied message 
     *
     * @param message describing the exception
     */
    public MetaModelException( String message ) {
        super( message );
    }
    
}

/* 
 * NumericType.java
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
 * NumericType.java
 *
 * Created on November 6, 2004, 4:49 PM
 */

package org.jdns.xtuml.metamodel;
/**
 * A class representing the Numeric core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class NumericType extends CoreDataType {
    
    /** singleton instance of NumericType */
    private static NumericType instance = null;
    
    /** Creates a new instance of NumericType */
    private NumericType() {
        name = "numeric";
    }
    
    /** 
     * Return the single instance of NumericType 
     *
     * @return the single instance of NumericType 
     */
    public static synchronized NumericType getInstance() {
        
        if ( instance == null )  {
            instance = new NumericType();
        }
        return instance;
    }
}


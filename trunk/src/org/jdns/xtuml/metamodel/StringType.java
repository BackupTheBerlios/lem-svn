/* 
 * StringType.java
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
 * A class representing the String core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class StringType extends CoreDataType {
    
    /** singleton instance of StringType */
    private static StringType instance = null;
    
    /** Creates a new instance of StringType */
    private StringType() {
        name = "string";
    }
    
    /** 
     * Return the single instance of StringType 
     *
     * @return the single instance of StringType 
     */
    public static synchronized StringType getInstance() {
        
        if ( instance == null )  {
            instance = new StringType();
        }
        return instance;
    }
}

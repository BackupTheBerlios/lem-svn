/* 
 * ArbitraryIdType.java
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
 * ArbitraryIdType.java
 *
 * Created on January 31, 2004, 12:39 PM
 */


package org.jdns.xtuml.metamodel;
/**
 * A class representing the ArbitaryId core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class ArbitraryIdType extends CoreDataType {
    
    /** singleton instance of ArbitraryIdType */
    private static ArbitraryIdType instance = null;
    
    /** Creates a new instance of ArbitraryIdType */
    private ArbitraryIdType() {
        name = "arbitrary_id";
    }
    
    /** 
     * Return the single instance of ArbitraryIdType 
     *
     * @return the single instance of ArbitraryIdType 
     */
    public static synchronized ArbitraryIdType getInstance() {
        
        if ( instance == null )  {
            instance = new ArbitraryIdType();
        }
        return instance;
    }
}

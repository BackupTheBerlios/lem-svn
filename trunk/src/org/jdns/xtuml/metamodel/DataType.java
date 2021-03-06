/* 
 * DataType.java
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
 * DataType.java
 *
 * Created on January 31, 2004, 12:09 PM
 */

package org.jdns.xtuml.metamodel;
/**
 * Defines a data type
 *
 * @author  smr
 */
public interface DataType {
    
    /**
     * Return the name of the DataType
     * @return the name of the datatype
     */
    public String getName();
    
    /**
     * Return the CoreDataType associated with this DataItem
     *
     * @return the CoreDataType associated with this DataItem
     */
    public abstract CoreDataType getCoreDataType();
}

/* 
 * DataItem.java
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
 * DataItem.java
 *
 * Created on November 8, 2004, 4:32 AM
 */

package org.jdns.xtuml.metamodel;

/**
 * DataItem is an abstract class class representing all typed data times
 *
 * @author  smr
 */
public abstract class DataItem implements DescribedEntity {
  
    /** the name of the dataItem */
    private String name = "";
    
    /** the Class to which the attribute belongs */
    private Class domainClass = null;
    
    /** the type of the attribute */
    private DataType type = null;
    
    /** a description of the attribute */
    private String description = "";
    
    /** Creates a new instance of Attribute */
    public DataItem() {
    }
    
    /** Getter for property domainClass.
     * @return Value of property domainClass.
     *
     */
    public Class getDomainClass() {
        return domainClass;
    }
    
    /** Setter for property domainClass.
     * @param domainClass New value of property domainClass.
     *
     */
    public void setDomainClass(Class domainClass) {
        this.domainClass = domainClass;
    }
    
    /** Getter for property name.
     * @return Value of property name.
     *
     */
    public java.lang.String getName() {
        return name;
    }
    
    /** Setter for property name.
     * @param name New value of property name.
     *
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    /** Getter for property type.
     * @return Value of property type.
     *
     */
    public DataType getType() {
        return type;
    }
    
    /** Setter for property type.
     * @param type New value of property type.
     *
     */
    public void setType(DataType type) {
        this.type = type;
    }
       
    /** Getter for property description.
     * @return Value of property description.
     *
     */
    public java.lang.String getDescription() {
        return description;
    }
    
    /** Setter for property description.
     * @param description New value of property description.
     *
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }
    
    /**
     * Return the CoreDataType associated with this DataItem
     *
     * @return the CoreDataType associated with this DataItem
     */
    public CoreDataType getCoreDataType() {
        return type.getCoreDataType();
    }
    
}

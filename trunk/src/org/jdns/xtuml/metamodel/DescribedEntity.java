/* 
 * DescribedEntity.java
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
 * DescribedEntitiy.java
 *
 * Created on February 1, 2004, 10:05 AM
 */

package org.jdns.xtuml.metamodel;
/**
 * An entity that has an associated description
 *
 * @author  smr
 */
public interface DescribedEntity {
  
    /**
     * Returns the textual description associated with this DescribedEntity
     * @return the textual description associated with this entity
     */
    public String getDescription();
    /**
     * Set the textual description of this DescribedEntity.
     * @param theDescription the description to be associated with this entity
     */
    public void setDescription( String theDescription );
}

/*
 * LemAttributeChangeEvent.java
 *
 * Copyright (C) 2005 James Greenhalgh
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

package org.jdns.xtuml.runtime;

/**
 * Instances of this event are passed to listeners when an attribute has changed.
 * @author James Greenhalgh
 * @co-author Thuan Seah Tan
 * @see AssignmentAction
 */
public class LemAttributeChangeEvent extends LemEvent {
	/**
         * the unique alpha numeric object id of the object
         */
	private Integer object_id;
        
        /**
         * the name of the attribute
         */
	private String attribute_name;
        
        /**
         * the old value of the attribute
         */
	private java.lang.Object old_value;
        
        /**
         * the new value of the attribute
         */
        private java.lang.Object new_value;
	
	/**
	 * Creates a new instance of LemAttributeChangeEvent
	 * @param id the unique alpha numeric object id of the object
	 * @param name the name of the attribute
	 * @param oldValue the old value of the attribute
         * @param newValue the new value of the attribute
	 */
	public LemAttributeChangeEvent(int id, String name, java.lang.Object oldValue, java.lang.Object newValue) {
		object_id = new Integer(id);
                attribute_name = name;
                old_value = oldValue;
                new_value = newValue;
	}

	/**
	 * Returns the unique alpha numeric object id of the object on which 
         * the attribute changed
	 * @return an Integer object with the object id
	 */
	public Integer getObjectId() {
		return object_id;
	}

        /**
        * function to retrieve the name of the attribute
        */
        public String getAttributeName() {
            return attribute_name;
        }
        
	/**
	 * Return the old value of the attribute
	 * @return the old value of the attribute
	 */
	public java.lang.Object getOldValue() {
		return old_value;
	}

	/**
	 * Return the new value of the attribute
	 * @return the new value of the attribute
	 */
	public java.lang.Object getNewValue() {
		return new_value;
	}

	/**
	 * Notify the given listener that this event has occurred.
	 * @param listener The listener to notify
	 */
	public void notify(LemEventListener listener) {
		listener.attributeChange(this);
	}
	
}

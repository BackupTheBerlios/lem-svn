/*
 * LemAttributeReadEvent.java
 *
 * Created on September 6, 2005, 6:57 PM
 *
 * Copyright (C) 2005 Thuan Seah Tan
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

/**
 *
 * @author thuanseah
 */

package runtime;

public class LemAttributeReadEvent extends LemEvent {
    /**
     * the unique alpha-numeric object id of the object
     */
    private Integer object_id;
    
    /**
     * the name of the attribute that is read
     */
    private String attribute_name;
    
    /**
     * value of the attribute that is read
     */
    private java.lang.Object value;
    
    /** Creates a new instance of LemAttributeReadEvent */
    public LemAttributeReadEvent(int id, String name, java.lang.Object v) {
        object_id = new Integer(id);
        attribute_name = name;
        this.value = v;
    }
    
    /**
     * function to retrieve the object id of the object
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
     * function to retrieve the value of the attribute
     */
    public java.lang.Object getValue() {
        return value;
    }
        
    /**
    * Notify the given listener that this event has occurred.
    * @param listener The listener to notify
    */
    public void notify(LemEventListener listener) {
	listener.attributeRead(this);
    }    
}

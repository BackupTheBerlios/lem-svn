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

package runtime;

/**
 * Instances of this event are passed to listeners when an attribute has changed.
 * @author James Greenhalgh
 * @see AssignmentAction
 */
public class LemAttributeChangeEvent extends LemEvent {
	
	private runtime.Object object;
	private Variable oldValue;
	private Variable newValue;
	
	/**
	 * Creates a new instance of LemAttributeChangeEvent
	 * @param o 
	 * @param oldValue 
	 * @param newValue 
	 */
	public LemAttributeChangeEvent(runtime.Object o, Variable oldValue, Variable newValue) {
		this.object = o;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	/**
	 * Returns the object on which the attribute changed
	 * @return the object
	 */
	public runtime.Object getObject() {
		return object;
	}


	/**
	 * Return the old value of the attribute
	 * @return the old value of the attribute
	 */
	public Variable getOldValue() {
		return oldValue;
	}

	/**
	 * Return the new value of the attribute
	 * @return the new value of the attribute
	 */
	public Variable getNewValue() {
		return newValue;
	}

	/**
	 * Notify the given listener that this event has occurred.
	 * @param listener The listener to notify
	 */
	public void notify(LemEventListener listener) {
		listener.attributeChange(this);
	}
	
}

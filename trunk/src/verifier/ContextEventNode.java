/*
 * SignalNode.java
 *
 * Created on September 12, 2005, 4:24 PM
 *
 * Copyright (C) 2005 Shokouhmand Torabi
 * Copyright (C) 2005 David Gavin
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
package verifier;

import javax.swing.JPopupMenu;
import metamodel.Event ; 
/**
 *
 * @author u3958283
 */
public class ContextEventNode extends AbstractDescriptionNode {
	
	Event thisEvent ; 
	LoggerFrame frame ; 
	
	/** Creates a new instance of SignalNode */
	public ContextEventNode(Event e, LoggerFrame frame) {
		this.frame = frame ; 
		this.thisEvent = e ; 
	}
	/**
	 * Returns name property of the Object
	 * @return the Object name.
	 */
	public String toString() {
		return thisEvent.getName() + " : ";
	}
	/**
	 * Returns the description property of the Object.
	 * @return the Object description.
	 */
	public String getDescription(){
		return thisEvent.getDescription() ; 
	}
	/**
	 * Returns the ContextMenu based on the Scenario.
	 * @return the Scenario ContextMenu.
	 */
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		return ContextMenu;
	}			
}

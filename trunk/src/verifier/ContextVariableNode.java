/*
 * ContextParameterNode.java
 *
 * Created on September 14, 2005, 2:24 PM
 *
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

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import runtime.ObjectReferenceVariable;

import runtime.Variable;

/**
 *
 * @author u4128551
 */
public class ContextVariableNode extends AbstractDescriptionNode {
	private Variable thisVariable;
	private JFrame frame;
	/** Creates a new instance of ContextParameterNode */
	public ContextVariableNode(Variable inVariable, JFrame inFrame) {
		thisVariable = inVariable;
		frame = inFrame;
	}
	/**
	 * Returns name property of the Object
	 * @return the Object name.
	 */
	public String toString() {
            if( thisVariable instanceof ObjectReferenceVariable)
            {
		return thisVariable.getType() + " : " + ((runtime.Object)thisVariable.getValue()).getObjectId();
            }
            else{
                return thisVariable.getType() + " : " + thisVariable.getValue().toString();
            }
	}
	/**
	 * Returns the description property of the Object.
	 * @return the Object description.
	 */
	public String getDescription(){
		return ""; 
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

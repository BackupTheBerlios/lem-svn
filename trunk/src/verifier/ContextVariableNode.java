/*
 * ContextVariableNode.java
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
import org.jdns.xtuml.runtime.ObjectReferenceVariable;

import org.jdns.xtuml.runtime.Variable;

/**
 * Tree node appearing appearing inside an ContextSignalNode. For graphically
 * representing and "holding" LEM variable objects. Has no children.
 * @author David Gavin
 */
public class ContextVariableNode extends AbstractDescriptionNode {
    
        /** The LEM variable which ContextVariableNode contains*/
	private Variable thisVariable;
        /**The JFrame that ContextVariableNode contains.*/
	private JFrame frame;
        
	/**
         * Creates a new instance of ContextVariableNode
         * @param inVariable the LEM variable to be contained
         * @param inFrame the JFrame to be contained
         */
	public ContextVariableNode(Variable inVariable, JFrame inFrame) {
		thisVariable = inVariable;
		frame = inFrame;
	}
	/**
	 * Returns Type property of the Object followed by either the Id or the value
         * depending on whether this variable is an ObjectReferenceVariable
	 * @return the Object type follow by the Id or value.
	 */
	public String toString() {
            if( thisVariable instanceof ObjectReferenceVariable)
            {
		return thisVariable.getType() + " : " + ((org.jdns.xtuml.runtime.Object)thisVariable.getValue()).getObjectId();
            }
            else{
                return thisVariable.getType() + " : " + thisVariable.getValue().toString();
            }
	}
	/**
	 * Returns the description property of the Variable.
	 * @return the Variable description.
	 */
	public String getDescription(){
		return ""; 
	}
	/**
	 * Returns the ContextMenu based on the Variable.
	 * @return the Scenario ContextMenu.
	 */
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		return ContextMenu;
	}			
}

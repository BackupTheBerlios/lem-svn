/*
 * SignalNode.java
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

import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import runtime.Signal;
import runtime.Variable;

/**
 * Tree node appearing appearing inside an ObjectNode. For graphically
 * representing and "holding" LEM Signal objects. Has ContextVariableNode as a child.
 * @author David Gavin
 */
public class ContextSignalNode extends AbstractDescriptionNode {
        
        /**The LEM Signal that ContextSignalNode contains.*/
	Signal thisSignal ; 
        /**The LoggerFrame that ContextSignalNode contains.*/
	LoggerFrame frame ; 
	
        
	/**
         * Creates a new instance of SignalNode. Examines LEM signal object and creates
	 *  a subtree based on the objects parameters.
         * @param s the LEM signal to be contained.
         * @param frame the logger frame the node will be displayed in.
         */
	public ContextSignalNode(Signal s, LoggerFrame frame) {
		this.frame = frame ; 
		this.thisSignal = s ; 
		if(s.getParameters() != null){
		Iterator parameters = s.getParameters().iterator();
		DefaultMutableTreeNode parameterLevel = new DefaultMutableTreeNode( "Parameter" ) ;
		if(parameters.hasNext()) {
			add(parameterLevel);
			while(parameters.hasNext()) {
				parameterLevel.add(new ContextVariableNode((Variable)parameters.next(),frame));
			}
		}
		}
	}
	/**
	 * Returns Id of the Signal followed by name property of the signal
	 * @return the Signal Id and name.
	 */
	public String toString() {
		return thisSignal.getSignalId() + " : " + thisSignal.getEvent().getName();
		//return thisSignal.getName() + " : " + thisSignal.getSignature() ;
	}
	/**
	 * Returns the description property of the Signal.
	 * @return the Signal description.
	 */
	public String getDescription(){
		return ""; //thisSignal.getDescription() ; 
	}
	/**
	 * Returns the ContextMenu based on the Signal.
	 * @return the Signal ContextMenu.
	 */
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		return ContextMenu;
	}			
}

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
 *
 * @author David Gavin
 */
public class ContextSignalNode extends AbstractDescriptionNode {
	Signal thisSignal ; 
	LoggerFrame frame ; 
	
	/** Creates a new instance of SignalNode */
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
	 * Returns name property of the Object
	 * @return the Object name.
	 */
	public String toString() {
		return thisSignal.getSignalId() + " : " + thisSignal.getEvent().getName();
		//return thisSignal.getName() + " : " + thisSignal.getSignature() ;
	}
	/**
	 * Returns the description property of the Object.
	 * @return the Object description.
	 */
	public String getDescription(){
		return ""; //thisSignal.getDescription() ; 
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

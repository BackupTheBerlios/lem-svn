/*
 * ScenarioContextNode.java
 *
 * Created on September 9, 2005, 1:59 PM
 *
 * Copyright (C) 2005 Shokouhmand Torabi
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

package org.jdns.xtuml.verifier;
import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdns.xtuml.runtime.Context ;
import org.jdns.xtuml.runtime.DomainContext;


/**
 * Creates a scenario context node. Has methods scenarioContextNode, 
 * toString, getDescription, getContextMenu.
 *
 * @author Shokouhmand Torabi
 */
public class ScenarioContextNode extends AbstractDescriptionNode {
	
	private Context context ;
	
	private LoggerFrame frame ;
	
	/** 
         * Creates a new instance of ScenarioContextNode 
         * @param frame The associated logger frame
         */
	public ScenarioContextNode(Context c, LoggerFrame frame) {
		this.frame = frame ;
		this.context = c ;
		
               // find the DomainContext
		while ((c.getParent()) != null) {
			c = c.getParent() ;
		}
		DomainContext d = (DomainContext) c ;
		Iterator i = d.getObjectList().iterator() ;
		DefaultMutableTreeNode objectLevel = new DefaultMutableTreeNode( "Objects" ) ;
		if ( i.hasNext() ) {
			add( objectLevel ) ;
			while( i.hasNext() ) {
				objectLevel.add( new ObjectNode( (org.jdns.xtuml.runtime.Object)i.next(), frame)) ;
			}
		}		
	}
	
	/**
	 * Returns name property of the scenario
	 * @return the scenario name.
	 */
	public String toString() {
		return "Scenario " ; // + thisScenario.getName();
	}
        
	/**
	 * Returns the description property of the Scenario.
	 * @return the description.
	 */
	public String getDescription(){
		return "" ; //trim(thisScenario.getDescription());
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

/*
 * SignalNode.java
 *
 * Created on September 12, 2005, 4:24 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
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
		return thisEvent.getName() + " : " + thisEvent.getSignature() ;
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

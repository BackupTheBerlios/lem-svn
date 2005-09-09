/*
 * InstanceNode.java
 *
 * Created on September 9, 2005, 2:51 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package verifier;

import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import runtime.Instance;

/**
 *
 * @author u3958283
 */
public class InstanceNode extends AbstractDescriptionNode {
	
	private LoggerFrame frame ; 
	private Instance thisInstance ;	
	
	/** Creates a new instance of InstanceNode */
	public InstanceNode(Instance instance , LoggerFrame frame) {
		this.frame = frame ; 
		this.thisInstance = instance ; 
		
	}
	
	/**
	 * Returns name property of the Instance
	 * @return the Instance name.
	 */
	public String toString() {
		return thisInstance.getInstanceClass().getName() + " : " + thisInstance.getInstanceInObject().getObjectId() ;
	}
	/**
	 * Returns the description property of the Instance.
	 * @return the Object description.
	 */
	public String getDescription(){
		return "" ; //trim(thisInstance.getDescription());
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

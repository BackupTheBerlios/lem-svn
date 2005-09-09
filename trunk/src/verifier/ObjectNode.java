/*
 * ObjectNode.java
 *
 * Created on September 9, 2005, 2:44 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package verifier;

import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import runtime.Instance;

/**
 *
 * @author u3958283
 */
public class ObjectNode extends AbstractDescriptionNode {
	
	private LoggerFrame frame ; 
	private runtime.Object thisObject ;
	
	/** Creates a new instance of ObjectNode */
	public ObjectNode(runtime.Object o, LoggerFrame frame) {
		this.frame = frame ; 
		this.thisObject = o ; 
		Iterator i = thisObject.getInstances().iterator() ; 
		System.out.println("Executed ..") ;  
		while( i.hasNext() ) {
			InstanceNode instanceNode = new InstanceNode((Instance)i.next(), frame ) ;
			add( instanceNode ) ;
		}		
	}
	
	/**
	 * Returns name property of the Object
	 * @return the Object name.
	 */
	public String toString() {
		return "Object " + thisObject.getObjectId();
	}
	/**
	 * Returns the description property of the Object.
	 * @return the Object description.
	 */
	public String getDescription(){
		return "Description" ; //trim(thisObject.getDescription());
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

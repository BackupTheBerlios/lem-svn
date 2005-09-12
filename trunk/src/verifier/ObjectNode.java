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
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.Event;
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
		// add instances to the tree.
		DefaultMutableTreeNode instancesLevel = new DefaultMutableTreeNode( "Instances" ) ;
		DefaultMutableTreeNode eventsLevel = new DefaultMutableTreeNode("Signals") ;
		
		add(instancesLevel ) ;
		while( i.hasNext() ) {
			Instance instance = (Instance) i.next() ;
			InstanceNode instanceNode = new InstanceNode( instance, frame ) ;
			instancesLevel.add( instanceNode ) ;
			metamodel.Class thisClass = instance.getInstanceClass() ;
			// add signals to the object tree.
			Iterator j = thisClass.getEvents().iterator();
			if ( j.hasNext() ) {
				add(eventsLevel ) ;
				while ( j.hasNext() ) {
					Event event = (Event)j.next() ;
					eventsLevel.add( new SignalNode(event,frame) ) ;
				}
			}						
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

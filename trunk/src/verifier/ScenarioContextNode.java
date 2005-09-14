/*
 * ScenarioContextNode.java
 *
 * Created on September 9, 2005, 1:59 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package verifier;
import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import runtime.Context ;
import runtime.DomainContext;


/**
 *
 * @author shuku
 */
public class ScenarioContextNode extends AbstractDescriptionNode {
	
	private Context context ;
	
	private LoggerFrame frame ;
	
	/** Creates a new instance of ScenarioContextNode */
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
				objectLevel.add( new ObjectNode( (runtime.Object)i.next(), frame)) ;
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
	 * @return the Scenario description.
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
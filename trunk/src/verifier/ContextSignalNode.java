/*
 * SignalNode.java
 *
 * Created on September 14, 2005, 1:35 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package verifier;

import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.Parameter;
import runtime.Signal;

/**
 *
 * @author u4128551
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
				parameterLevel.add(new ContextParameterNode((Parameter)parameters.next(),frame));
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

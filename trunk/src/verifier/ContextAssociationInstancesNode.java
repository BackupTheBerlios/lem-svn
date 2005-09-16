/*
 * ContextAssociationInstancesNode.java
 *
 * Created on September 16, 2005, 12:08 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package verifier;

import javax.swing.JPopupMenu;
import runtime.AssociationInstance;

/**
 *
 * @author u3952811
 */
public class ContextAssociationInstancesNode extends AbstractDescriptionNode {
    
    /** Creates a new instance of ContextAssociationInstancesNode */
    private AssociationInstance association = null;
    private LoggerFrame frame = null ; 
    
    
    public ContextAssociationInstancesNode(AssociationInstance a , LoggerFrame frame) {
        this.association = a;
        this.frame = frame ; 
    }
    
    
    public String toString() {
		return association.getInstanceClass().getName() + " : " + association.getInstanceInObject().getObjectId() ;
	}
	/**
	 * Returns the description property of the Instance.
	 * @return the Object description.
	 */
	public String getDescription(){
		return "" ; //trim(thisInstance.getDescription());
	}
    
    /**
     * Creates and returns a JPopupMenu based on the specified attribute.
     * @return the ContextMenu.
     */
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

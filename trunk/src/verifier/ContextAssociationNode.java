/*
 * ContextAssociationNode.java
 *
 * Created on September 14, 2005, 2:12 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package verifier;

import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.Association;
import runtime.AssociationInstance;
import runtime.Instance;

/**
 *
 * @author u3952811
 */
public class ContextAssociationNode extends AbstractDescriptionNode {
    /** Creates a new instance of ContextAssociationNode */
    private Association association = null;
    private LoggerFrame frame = null ;
    private Instance thisInstance ;
    
    public ContextAssociationNode(Instance thisInstance, Association association, LoggerFrame fram) {
        this.frame = frame ; 
        this.association = association ; 
        this.thisInstance = thisInstance;
        // add association instaces to the association subtree ;
        DefaultMutableTreeNode associationLevel = new DefaultMutableTreeNode( "AssociationInstances" );
        Iterator i = thisInstance.getAssociationInstances(association).iterator() ;
        
        if ( i.hasNext() ) {
                add ( associationLevel ) ; 
		while ( i.hasNext() ) {
			associationLevel.add( new ContextAssociationInstancesNode( (AssociationInstance)i.next() , frame )) ; 
		}			
        }
    }
    
    
    public String toString() {
        return association.getName();
    }
    /** 
     * Returns the description of the attribute, will return an empty string if
     * description is null.
     * @return the Description
     */
    public String getDescription() {        
        if (association.getDescription() != null)
            return trim(association.getDescription() );        
        else
            return "" ;                         
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

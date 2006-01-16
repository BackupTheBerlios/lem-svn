/*
 * ContextAssociationNode.java
 *
 * Created on September 14, 2005, 2:12 PM
 *
 * Copyright (C) 2005 Simon Franklin
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
import org.jdns.xtuml.metamodel.Association;
import org.jdns.xtuml.runtime.AssociationInstance;
import org.jdns.xtuml.runtime.Instance;

/**
 * Tree node appearing appearing inside an InstanceNode. For graphically
 * representing and "holding" LEM Association Instance objects. Has
 * ContextAssociationInstanceNode as a child.
 * @author Simon Franklin
 */
public class ContextAssociationNode extends AbstractDescriptionNode {
    
    /**The LEM Association that ContextAssociationNode contains.*/
    private Association association = null;
    /**The LoggerFrame that ContextAssociationNode contains.*/
    private LoggerFrame frame = null ;
    /**The LEM Instance to which this Association belongs **/
    private Instance thisInstance ;
    
    /**
     * Creates a new instance of ContextAssociationNode. Creates a subtree based on
     * the association's AssociationInstances.
     * @param thisInstance the LEM Instace to which the Association belongs.
     * @param association the LEM Association from which the node is created.
     * @param frame the logger frame the node will be displayed in.
     */
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
    
    
    /**
    * Returns the Name property of LEM Association object
    * @return the name of the Association
    */
    public String toString() {
        return association.getName();
    }
    /** 
     * Returns the Description property of LEM Association object, will return
     * an empty string if the description is null.
     * @return the Description of the association
     */
    public String getDescription() {        
        if (association.getDescription() != null)
            return trim(association.getDescription() );        
        else
            return "" ;                         
    }
    /**
     * Creates and returns a JPopupMenu based on the specified association.
     * @return the ContextMenu.
     */
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

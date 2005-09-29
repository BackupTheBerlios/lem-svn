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
import metamodel.Association;
import runtime.AssociationInstance;
import runtime.Instance;

/**
 *
 * @author Simon Franklin
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

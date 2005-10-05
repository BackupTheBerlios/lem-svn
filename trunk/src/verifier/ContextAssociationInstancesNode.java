/*
 * ContextAssociationInstancesNode.java
 *
 * Created on September 16, 2005, 12:08 PM
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

import javax.swing.JPopupMenu;
import runtime.AssociationInstance;

/**
 * Tree node appearing appearing inside a ContextAssociationNode. For graphically
 * representing and "holding" LEM Association Instance objects. Has no children.
 *
 * @author Simon Franklin
 */
public class ContextAssociationInstancesNode extends AbstractDescriptionNode {
    
    /**The LEM AssociationInstance that ContextAssociationInstanceNode contains.*/
    private AssociationInstance association = null;
    /**The LoggerFrame that ContextAssociationInstanceNode contains.*/
    private LoggerFrame frame = null ; 
    
    /**
     * Creates a new instance of ContextAssociationInstancesNode
     * @param a the LEM AssociationInstace from which the node is created.
     * @param frame the logger frame the node will be displayed in
     */
    public ContextAssociationInstancesNode(AssociationInstance a , LoggerFrame frame) {
        this.association = a;
        this.frame = frame ; 
    }
    
        /**
         * Returns the name of the AssociationInstance followed by the Id of the AssociationInstance
	 * @return the name of the AssociationInstance and the id of the AssociationInstance.
	 */
    public String toString() {
			return association.getActiveInstance().getInstanceClass().getName() + " - " + association.getPassiveInstance().getInstanceClass().getName() ; 
	}
	
	/**
	 * Returns the Description property of the AssociationInstance.
	 * @return the description of the AssociationInstance.
	 */
	public String getDescription(){		
		return "" ; //trim(thisInstance.getDescription());
	}
    
    /**
     * Creates and returns a JPopupMenu based on the specified AssociationInstance.
     * @return the ContextMenu.
     */
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

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
 *
 * @author Simon Franklin
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

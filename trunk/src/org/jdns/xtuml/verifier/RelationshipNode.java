/*
 * RelationshipNode.java
 *
 * Created on 27 April 2005, 17:28
 *
 * Copyright (C) 2005 James Ring
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

package org.jdns.xtuml.verifier;

import javax.swing.JPopupMenu;
import org.jdns.xtuml.metamodel.Relationship;

/**
 * Tree node appearing inside a DomainNode. For graphically representing and 
 * "holding" a LEM Relationship object. Has no children.
 * @author James Ring
 */
public class RelationshipNode extends AbstractDescriptionNode{
    /**The LEM Relationship object that RelationshipNode contains.*/
    Relationship relationship = null;
    
    /**
     * Creates a new instance of RelationshipNode. 
     * @param r LEM Relationship object to be contained.
     */
    public RelationshipNode( org.jdns.xtuml.metamodel.Relationship r ) {
        this.relationship = r;
    }
    /**
    * Returns name property of LEM Relationship object.
    * @return the Relationship name.
    */    
    public String toString() {
        return "Relationship " + relationship.getName(); 
    }
        /**
    * Returns the description property of the LEM Relationship object.
    * @return the Relationship description.
    */
    public String getDescription()
    {
        if (relationship.getDescription() != null)
            return trim(relationship.getDescription());
        else 
            return "" ; 
    }
    /**
    * Returns the ContextMenu based on the LEM Relationship object.
    * @return the Relationship ContextMenu.
    */    
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

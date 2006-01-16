/*
 * EventNode.java
 *
 * Created on 8 May 2005, 11:52
 *
 * Copyright (C) 2005 Shokouhmand Torabi
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
import org.jdns.xtuml.metamodel.Event ;

/**
 * Tree node appearing inside a ClassNode. For representing and containing a LEM
 * Event object. Has no children.
 * @author Shokouhmand Torabi
 */
public class EventNode extends AbstractDescriptionNode {
    /**The LEM Event object that EventNode contains*/
    Event event; 
    /** 
     * Creates a new instance of EventNode.
     * @param e the LEM Event object to be contained.
     */
    public EventNode(Event e) {
        event = e ; 
    }
    /**
     * Returns name property of LEM Event object.
     * @return The Name of the Event
     */
    public String toString() {
        return "Event: " + event.getName() ;  
    }
    /**
     * Returns description property of LEM Event object.
     * @return The description of the event.
     */
    public String getDescription() {
         if (event.getDescription() != null ) 
            return trim(event.getDescription());   
        else 
            return "" ;         
    }
    /**
     * Returns ContextMenu based on LEM Event object.
     * @return The Context menu based on the event.
     */
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

/*
 * TransitionNode.java
 *
 * Created on 2 May 2005, 17:57
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

package verifier;

import javax.swing.JPopupMenu;
import org.jdns.xtuml.metamodel.Transition;

/**
 * Tree node appearing inside a StateMachineNode. For graphically representing 
 * and "holding" a LEM Transition object. Has no children.
 * @author Shokouhmand Torabi
 */
public class TransitionNode extends AbstractDescriptionNode {
    /**The LEM Transition object that TransitionNode contains*/
    Transition transition ; 
    /**The name of the LEM Transition object contained.*/
    String name ; 
    
    /** Creates a new instance of TransitionNode.
     * @param Transition LEM Transition object.
     * @param name name of Transition object.
     */
    public TransitionNode( Transition Transition , String name ) {
        this.transition = Transition ; 
        this.name = name ; 
    }
    /**
    * Returns name property of LEM Transition object.
    * @return the Transition name.
    */ 
    public String toString()  {
        return name ; 
    }
    /**
    * Returns the description property of the LEM Transition object.
    * @return the Transition description.
    */    
    public String getDescription()  { 
        if (transition.getDescription() != null)
            return trim(transition.getDescription()) ;
        else
            return "" ;
    }
    /**
    * Returns the ContextMenu based on the LEM Transition object.
    * @return the Transition ContextMenu.
    */    
        public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
    
}

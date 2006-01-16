/*
 * StateMachineNode.java
 *
 * Created on 27 April 2005, 17:34
 *
 * Copyright (C) 2005 James Ring
 * Copyright (C) 2005 David Gavin
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
import org.jdns.xtuml.metamodel.InitialisingTransition;
import org.jdns.xtuml.metamodel.StateMachine;
import org.jdns.xtuml.metamodel.Transition;

/**
 * Tree node appearing inside a ClassNode. For graphically representing and
 * "holding" a LEM StateMachine object. Has StateNodes and TransitionNodes as
 * children.
 * @author James Ring
 */
public class StateMachineNode extends AbstractDescriptionNode {
    /**The LEM StateMachine object that StateMachineNode contains*/
    StateMachine machine = null;
    
    /**
     * Creates a new instance of StateMachineNode. Creates a sublevel containing
     * only StateNodes called stateLevel and a sublevel containing only
     * TransitionNodes called transitionLevel. Creates StateNodes based on LEM
     * State objects in LEM StateMachine object and places them in stateLevel.
     * Creates TransitionNodes based on LEM Transition objects in LEM
     * StateMachine object and places them in transitionLevel. LEM Transition
     * objects are checked for LEM InitialisingTransition objects. If a LEM
     * InitilisingTransition object is found an additional "Create ->" is placed
     * in the name of the corresponding TransitionNode object.
     * @param m LEM StateMachine object.
     */
    public StateMachineNode( StateMachine m ) {
        this.machine = m;
        
        if( m.getStateList() == null ) return;
        
        Iterator i = machine.getStateList().listIterator();
        if(i.hasNext()) {
            DefaultMutableTreeNode stateLevel = new DefaultMutableTreeNode("States");
            while( i.hasNext() ) {
                stateLevel.add( new StateNode((org.jdns.xtuml.metamodel.State) i.next()) ) ;
            }
            add( stateLevel );
        }
        
        i = machine.getTransitionList().listIterator();
        if(i.hasNext()) {
            DefaultMutableTreeNode transitionLevel = new DefaultMutableTreeNode("Transitions");
            while( i.hasNext() ) {
                Transition t = (Transition)i.next();
                String name;
                if( t instanceof InitialisingTransition ) {
                    name = "CREATE ->" + t.getToState().getName();
                } else {
                    name = t.getFromState().getName() + " -> " + t.getToState().getName();
                }
                
                transitionLevel.add( new TransitionNode( t , name ));
            }
            add( transitionLevel );
        }
    }
    /**
     * Returns string "State machine".
     * @return "State machine".
     */
    public String toString() {
        
        return "State machine";
    }
    /**
     * Returns the description property of the LEM StateMachine object.
     * @return the StateMachine description.
     */
    public String getDescription() {
        if (machine.getDescription() != null)
            return trim(machine.getDescription());
        else
            return "" ;
    }
    /**
     * Returns the ContextMenu based on the LEM StateMachine object.
     * @return the StateMachine ContextMenu.
     */
    public JPopupMenu getContextMenu() {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

/*
 * ContextStateMachine.java
 *
 * Created on 15 September 2005, 02:04
 *
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
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.InitialisingTransition;
import metamodel.State;
import metamodel.StateMachine;
import metamodel.Transition;


/**
 *
 * @author David Gavin
 */
public class ContextStateMachineNode extends AbstractDescriptionNode {
    private StateMachine machine = null;
    private State currentState = null;
    private JFrame frame = null;
    /** Creates a new instance of ContextStateMachine */
    public ContextStateMachineNode(StateMachine inMachine,State inCurrentState, JFrame inFrame ) {
        machine = inMachine;
        currentState = inCurrentState;
        frame = inFrame;
        
        
        if( machine.getStateList() == null ) return;
        
        Iterator i = machine.getStateList().listIterator();
        if(i.hasNext()) {
            DefaultMutableTreeNode stateLevel = new DefaultMutableTreeNode("States");
            DefaultMutableTreeNode currentLevel = new DefaultMutableTreeNode("Current State");
            while( i.hasNext() ) {
                State s = (metamodel.State)i.next();
                if(s == currentState) {
                 currentLevel.add(new StateNode( s ) );
                }
                else {
                 stateLevel.add( new StateNode( s ) ) ; 
                }
            }
            add( currentLevel );
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





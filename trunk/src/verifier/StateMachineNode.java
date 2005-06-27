/*
 * StateMachineNode.java
 *
 * Created on 27 April 2005, 17:34
 */

package verifier;

import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.InitialisingTransition;
import metamodel.StateMachine;
import metamodel.Transition;

/**
 * Tree node appearing inside a ClassNode. For graphically representing and 
 * "holding" a LEM StateMachine object. Has StateNodes and TransitionNodes as 
 * children.
 * @author sjr
 */
public class StateMachineNode extends AbstractDescriptionNode {
    
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
        
        DefaultMutableTreeNode stateLevel = new DefaultMutableTreeNode("States");
        DefaultMutableTreeNode transitionLevel = new DefaultMutableTreeNode("Transitions");
        
        Iterator i = machine.getStateList().listIterator();
        while( i.hasNext() ) {          
            stateLevel.add( new StateNode((metamodel.State) i.next()) ) ;                                
        }
        
        i = machine.getTransitionList().listIterator();
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
        
        add( stateLevel );
        add( transitionLevel );
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
    public String getDescription()
    {
        if (machine.getDescription() != null)
            return trim(machine.getDescription());
        else 
            return "" ; 
    }
    /**
     * Returns the ContextMenu based on the LEM StateMachine object.
     * @return the StateMachine ContextMenu.
     */ 
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

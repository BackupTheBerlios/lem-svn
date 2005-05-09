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
 *
 * @author sjr
 */
public class StateMachineNode extends AbstractDescriptionNode {
    
    StateMachine machine = null;
    
    /** Creates a new instance of StateMachineNode */
    public StateMachineNode( StateMachine m ) {
        this.machine = m;
                
        if( m.getStateList() == null ) return;
        
        DefaultMutableTreeNode stateLevel = new DefaultMutableTreeNode( "States" );
        DefaultMutableTreeNode transitionLevel = new DefaultMutableTreeNode( "Transitions" );
        
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
    
    public String toString() {
        
        return "State machine";
    }
    
    public String getDescription()
    {
        if (machine.getDescription() != null)
            return trim(machine.getDescription());
        else 
            return "" ; 
    }
    
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

/*
 * StateMachineNode.java
 *
 * Created on 27 April 2005, 17:34
 */

package verifier;

import java.awt.Color;
import java.util.Iterator;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
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
        return machine.getDescription();
    }

     public StyledDocument getStyledDocument() {
        StyledDocument doc = new StyledDocument() ;
        SimpleAttributeSet s = new SimpleAttributeSet();
        StyleConstants.setFontFamily(s, "Times New Roman");
        StyleConstants.setFontSize(s, 14);
        StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s, Color.blue);       
        StyledElement element = new StyledElement(toString()+"\n" , s) ;
        doc.addStyle(element) ; 
        
        SimpleAttributeSet s1 = new SimpleAttributeSet();        
        StyleConstants.setFontFamily(s1, "Times New Roman");
        StyleConstants.setFontSize(s1, 14);
        StyleConstants.setBold(s1, false);
        StyleConstants.setForeground(s1, Color.black);       
        StyledElement element1 = new StyledElement(getDescription() , s1) ;
        doc.addStyle(element1) ; 
        
        return doc ;
    }
}

/*
 * TransitionNode.java
 *
 * Created on 2 May 2005, 17:57
 */

package verifier;

import javax.swing.JPopupMenu;
import metamodel.Transition;

/**
 *
 * @author SHukuBOy
 */
public class TransitionNode extends AbstractDescriptionNode {
    
    Transition transition ; 
    String name ; 
    
    /** Creates a new instance of TransitionNode */
    public TransitionNode( Transition Transition , String name ) {
        this.transition = Transition ; 
        this.name = name ; 
    }
    
    public String toString()  {
        return name ; 
    }
    
    public String getDescription()  { 
        if (transition.getDescription() != null)
            return trim(transition.getDescription()) ;
        else
            return "" ;
    }
    
        public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
    
}

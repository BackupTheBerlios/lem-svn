/*
 * TransitionNode.java
 *
 * Created on 2 May 2005, 17:57
 */

package verifier;

import javax.swing.JPopupMenu;
import metamodel.Transition;

/**
 * Tree node appearing inside a StateMachineNode. For graphically representing 
 * and "holding" a LEM Transition object. Has no children.
 * @author SHukuBOy
 */
public class TransitionNode extends AbstractDescriptionNode {
    
    Transition transition ; 
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

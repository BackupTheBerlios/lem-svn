/*
 * StateNode.java
 *
 * Created on 2 May 2005, 17:57
 */

package verifier;

import javax.swing.JPopupMenu;
import metamodel.State;

/**
 *
 * @author SHukuBOy
 */
public class StateNode extends AbstractDescriptionNode {
    
    State state ; 
    /** Creates a new instance of StateNode */
    public StateNode( State state ) {
        this.state = state ; 
    }
    
    public String toString()  {
        return "State : " + state.getName() ; 
    }
    
    public String getDescription()  {
        if (state.getDescription() != null)
            return trim(state.getDescription()) ; 
        else 
            return "" ; 
    }
    
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
    
    
}

/*
 * StateNode.java
 *
 * Created on 2 May 2005, 17:57
 */

package verifier;

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
        return state.getDescription() ; 
    }
    
    
}

/*
 * TransitionNode.java
 *
 * Created on 2 May 2005, 17:57
 */

package verifier;

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
        return transition.getDescription() ; 
    }
    
    
}

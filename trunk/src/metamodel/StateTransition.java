/*
 * StateTransition.java
 *
 * Created on September 25, 2004, 8:02 AM
 */

package metamodel;

/**
 * A StateTransition occurs between a non deletion "from" state and a "to" state.
 * The "to" state may be either a non deletion or a deletion state. 
 *
 * This is an associative class supporting metamodel relationship R505
 *
 * @author  smr
 */
public class StateTransition extends Transition {
    
    /** the state from which this transition occurs [R505] */
    private NonDeletionState fromState = null;
    
    /** Creates a new instance of StateTransition */
    public StateTransition() {
    }
    
    /**
     * A transition occurs between a "from" state and a "to" state.
     *
     * @return the State from which this transistion is directed. Returns null
     * this is an "initialising" transition.
     */
    public State getFromState() {
        return fromState;
    }
    
    /**
     * A transition occurs between a "from" state and a "to" state.
     *
     * @param aState from which this transistion is directed. 
     */
    public void setFromState( NonDeletionState aState ) {
        fromState = aState;
    }
    
}

/*
 * InitialisingTransition.java
 *
 * Created on September 25, 2004, 7:32 AM
 */

package metamodel;

/**
 * A transition from the creation pseudo state to some other state
 * @author  smr
 */
public class InitialisingTransition extends Transition {
    
    /** Creates a new instance of InitialisingTransition */
    public InitialisingTransition() {
    }
    
    /**
     * An InitialisingTransition occurs between the "creation pseudo state"
     * and a "to" state.
     *
     * @return null as this is an "initialising" transition.
     */
    public State getFromState() {
        return null;
    }
    
}

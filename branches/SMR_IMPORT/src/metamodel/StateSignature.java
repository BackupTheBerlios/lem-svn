/*
 * StateSignature.java
 *
 * Created on September 25, 2004, 12:58 PM
 */

package metamodel;

/**
 * The parameter signature for a state. These parameters must be supplied to
 * the state procedure by any event which causes a transition this state.
 *
 * @author  smr
 */
public class StateSignature extends Signature {
    
    /** The state to which this signature belongs */
    private State state = null;
    
    /** Creates a new instance of StateSignature */
    public StateSignature() {
    }

    /**
     * Return the State associated with this StateSignature
     *
     * @return the State associated with this StateSignature
     */
    public State getState() {
        return state;
    }

    /**
     * Set the State associated with this StateSignature
     *
     * @param aState associated with this StateSignature
     */
    public void setState(State aState) {
        this.state = aState;
    }
    
    /** 
     * Return the domain to which this signature belongs
     *
     * @return the domain to which this signature belongs
     */
    public Domain getDomain(){
        return state.getSignature().getDomain();
    }
}

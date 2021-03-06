/*
 * Procedure.java
 *
 * Created on October 7, 2004, 6:49 AM
 */

package metamodel;

/**
 * A procedure is a collection of actions executed upon entry to a state.
 *
 * @author  smr
 */
public class Procedure {
    
    /** the state to which this procuedure belongs */
    private State state = null;
    
    
    /** Creates a new instance of Procedure */
    public Procedure() {
    }

    /**
     * Return the state to which this procedure belongs
     * 
     * @return the state to which this procedure belongs
     */
    public State getState() {
        return state;
    }

    /**
     * Set the state to which this procedure belongs
     * 
     * @param theState to which this procedure belongs
     */
    public void setState(State theState) {
        this.state = theState;
    }
    
}

/*
 * DeletionState.java
 *
 * Created on September 25, 2004, 7:54 AM
 */

package metamodel;

/**
 * A Deletion State is a final state for a state machine which automatically leads to the
 * eventual distruction of the instance.
 *
 * @author  smr
 */
public class DeletionState extends State {
    
    /** Creates a new instance of DeletionState */
    public DeletionState() {
    }
    
}

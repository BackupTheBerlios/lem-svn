/*
 * Procedure.java
 *
 * Created on October 7, 2004, 6:49 AM
 */

package metamodel;

import java.util.Iterator;
import runtime.Context;

/**
 * A procedure is a collection of actions executed upon entry to a state.
 *
 * @author  smr
 */
public class Procedure {
    
    /** the state to which this procuedure belongs */
    private State state = null;
    
    /** A list of actions which are executed by this procedure.
     * May be empty, but non-null.
     */
    java.util.LinkedList actions = null;
    
    /** Creates a new instance of Procedure */
    public Procedure() {
        actions = new java.util.LinkedList();
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
    
    /**
     * Append the given action to the list of actions executed
     * by this procedure.
     *
     * @param a The action to append
     */
    public void addAction( Action a ) {
        actions.add(a);
    }
    
    /**
     * Executes this Procedure in the given Context
     * @param c The context in which this procedure should execute
     */
    public void execute( Context c ) {
        Action a;
        
        Iterator i = actions.iterator();
        
        while( i.hasNext() ) {
            a = (Action)i.next();
            
            a.execute(c);
        }
    }
    
}

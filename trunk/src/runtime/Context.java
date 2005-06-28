/*
 * Context.java
 *
 * Created on 17 May 2005, 14:38
 */

package runtime;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Every Action in LEM executes in some sort of "context".
 * @author u4128551
 */
public class Context {
    /** 
     * A collection of all runtime objects known to this context
     */
    Collection objectList = new ArrayList();
    
    /**
     * A collection of all variables local to the context
     *
     * @todo Should this be a HashMap?
     */
    Collection localVariables = null;
    
    /**
     * A collection of LemEventListener instances interested in 
     * receiving notification of events occuring within this context.
     */
    Collection listeners = new ArrayList();

    /**
     * This context's parent.
     */
    Context parentContext = null;

    /**
     * The nullary Context constructor. Creates a new Context with no objects,
     * no listeners and a null list of local variables
     */
    public Context() {
    }
    
    /**
     * Creates a new instance of Context. Contexts are hierarchical,
     * so the parent context is passed in as a parameter.
     * @param inContext the parent context
     */
    public Context(Context inContext) {
        parentContext = inContext;
    }
    /**
     * Adds the given object to this context
     * @param inObject the object to add to the context
     */
    public void addObject(runtime.Object inObject) {
        objectList.add(inObject);
    }
    
    /**
     * Returns the list of objects visible in this context
     * @return the objects in this context
     */
    public Collection getObjects() {
        return objectList;
    }
    
    /**
     * Returns the list of local variables in this context
     * @return the local variables in this context
     */
    public Collection getLocalVariables() {
        return localVariables;
    }
    
    /**
     * Tells this context that it is about to finish. The context should check
     * all visible objects for participation in all relevant associations,
     * etc.
     */
    public void finish() {
    }
    
    
    /**
     * Adds the given LemEventListener to the list of listeners. This listener
     * will now receive LemEvent objects representing events that occur within this
     * context
     */
    public void addLemEventListener( LemEventListener l ) {
        listeners.add(l);
    }
    
    /**
     * Remove the given LemEventListener from the list of listeners. This listener
     * will receive no more LemEvents
     */
    public void removeLemEventListener( LemEventListener l ) {
        listeners.remove(l);
    }
    
    /**
     * Returns the collection of all LemEventListener objects registered with this context
     */
    public Collection getLemEventListeners() {
        return listeners;
    }
}

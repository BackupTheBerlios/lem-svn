/*
 * Context.java
 *
 * Created on 17 May 2005, 14:38
 */

package runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

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
    HashMap variableList = new HashMap();
    
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
     * A collection of AssociationInstances known to this context.
     */
    HashMap associationInstances = new HashMap();

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
        listeners.addAll( inContext.getLemEventListeners() );
    }

    /**
     * Adds the given object to this context
     * @param inObject the object to add to the context
     */
    public void addObject(runtime.Object inObject) {
        objectList.add(inObject);
    }

    /**
     * Adds the given collection of objects to this context
     * @param inObjects the objects to add to the context
     */
    public void addObjects(Collection inObjects) {
        objectList.add(inObjects);
    }
      
    /**
     * Gets the named variable from this context or any parent contexts.
     *
     * @param name the name of the variable
     * @return the variable, or null if there is no variable with that name
     */
    public Variable getVariable( String name ) {
        Variable v = (Variable)variableList.get( name );
        
        if( v == null && parentContext != null ) {
            // Search parent contexts
            v = parentContext.getVariable( name );
        }
        
        return v;
    }
    
    /**
     * Adds the given variable to this context with the given identifying name.
     * 
     * @param name the identifier of the variable
     * @param variable the variable to be added
     */
    public void addVariable( String name, Variable variable ) {
        variableList.put( name, variable );
    }
    
    /**
     * Tells this context that it is about to finish. The context should check
     * all visible objects for participation in all relevant associations,
     * etc.
     */
    public void finish() {
	if (parentContext != null) {
		parentContext.addObjects(objectList);
	}
    }
    
    /**
     * Adds the given LemEventListener to the list of listeners. This listener
     * will now receive LemEvent objects representing events that occur within this
     * context
     * @param l the listener to add
     */
    public void addLemEventListener( LemEventListener l ) {
        listeners.add(l);
    }
    
    /**
     * Remove the given LemEventListener from the list of listeners. This listener
     * will receive no more LemEvents
     * @param l the listener to remove
     */
    public void removeLemEventListener( LemEventListener l ) {
        listeners.remove(l);
    }
    
    /**
     * Returns the collection of all LemEventListener objects registered with this context
     * @return the list of listeners
     */
    public Collection getLemEventListeners() {
        return listeners;
    }

    /** 
     * Returns the mapping between Association and AssociationInstances.
     *
     * @return the HashMap
     */
    public HashMap getAssociationInstances() {
        return associationInstances;
    }

    /**
     * Add the given AssociationInstance to this context.
     *
     * @param a the AssociationInstance to add
     */
    public void addAssociationInstance( AssociationInstance a ) {
        associationInstances.put( a.getAssociation(), a );
    }
}

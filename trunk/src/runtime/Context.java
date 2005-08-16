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
    HashMap removedAssociations = new HashMap();
    
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
     * Returns the parent context, or null if this is the root context.
     * @returns the parent context.
     */
    public Context getParent() {
	    return parentContext;
    }
    
    /**
     * Adds the given object to this context
     * @param inObject the object to add to the context
     */
    public synchronized void addObject(runtime.Object inObject) {
        objectList.add(inObject);
    }

    /**
     * Adds the given collection of objects to this context
     * @param inObjects the objects to add to the context
     */
    public synchronized void addObjects(Collection inObjects) throws LemRuntimeException {
        objectList.addAll(inObjects);
	if (parentContext != null && objectList.size() > 128) {
		throw new LemRuntimeException("Too many objects on stack.");
	}
    }

    /**
     * Returns the list of objects in this context
     * @returns the list of objects.
     */
    public Collection getObjectList() {
	    return objectList;
    }
    
    /**
     * Gets the named variable from this context or any parent contexts.
     *
     * @param name the name of the variable
     * @return the variable, or null if there is no variable with that name
     */
    public synchronized Variable getVariable( String name ) {
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
    public synchronized void addVariable( String name, Variable variable ) {
        variableList.put( name, variable );
    }
    
    /**
     * Tells this context that it is about to finish. The context should check
     * all visible objects for participation in all relevant associations,
     * etc.
     */
    public void finish() throws LemRuntimeException {
	if (parentContext != null) {
		parentContext.addObjects(objectList);
                parentContext.addAssociationInstance(associationInstances);
                parentContext.removeAssociationInstance(removedAssociations);
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
        if(!associationInstances.containsKey(a.getAssociation()))
            associationInstances.put(a.getAssociation(), new LinkedList());
        
        a.getActiveInstance().addAssociationInstance(a);
        a.getPassiveInstance().addAssociationInstance(a);
        ((LinkedList)associationInstances.get(a.getAssociation())).add(a);
        
    }
    
    public void addAssociationInstance(HashMap inAssociation) {
        //associationInstances.putAll(inAssociation);
        java.util.Iterator i = inAssociation.values().iterator();
        while(i.hasNext()) {
            Collection c = (Collection)i.next();
            java.util.Iterator j = c.iterator();
            while(j.hasNext()) {
                addAssociationInstance((AssociationInstance)j.next());
            }
        }
    }   

    public void removeAssociationInstance( AssociationInstance a ) { 
        if(associationInstances.containsKey(a.getAssociation()))
            ((LinkedList)associationInstances.get(a.getAssociation())).remove(a);
                
        if(!removedAssociations.containsKey(a.getAssociation()))
            removedAssociations.put(a.getAssociation(), new LinkedList());
        
        ((LinkedList)removedAssociations.get(a.getAssociation())).add(a);     
        a.getActiveInstance().removeAssociationInstance(a);
        a.getPassiveInstance().removeAssociationInstance(a);
    }
    
    public void removeAssociationInstance(HashMap inAssociation) {
        java.util.Iterator i = inAssociation.values().iterator();
        while(i.hasNext()) {
            Collection c = (Collection)i.next();
            java.util.Iterator j = c.iterator();
            while(j.hasNext()) {
                removeAssociationInstance((AssociationInstance)j.next());
            }  
        }
    }
    
    public boolean containsAssociationInstance(AssociationInstance a) {
        if(associationInstances.containsKey(a.getAssociation()))
            if(((LinkedList)associationInstances.get(a.getAssociation())).contains(a))
                return true;
        
        if(parentContext != null)
            return parentContext.containsAssociationInstance(a);
        
        return false;
    }
}

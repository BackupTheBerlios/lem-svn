/*
 * Context.java
 *
 * Copyright (C) 2005 James Ring
 *
 * This program is free software; you can redistribute it and/or  
 * modify it under the terms of the GNU General Public License  
 * as published by the Free Software Foundation; either version 2  
 * of the License, or (at your option) any later version.  
 *  
 * This program is distributed in the hope that it will be useful,  
 * but WITHOUT ANY WARRANTY; without even the implied warranty of  
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
 * GNU General Public License for more details.  
 * 
 * You should have received a copy of the GNU General Public License  
 * along with this program; if not, write to the Free Software  
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,  
 * USA. 
 */

package runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Every Action in LEM executes in some sort of "context".
 * @author u4128551
 */
public abstract class Context {
    /**
     * A collection of LemEventListener instances interested in 
     * receiving notification of events occuring within this context.
     */
    private Collection listeners = new ArrayList();

    /**
     * A collection of AssociationInstances known to this context.
     */
    private HashMap associationInstances = new HashMap();
    private HashMap removedAssociations = new HashMap();

    /**
     * This context's parent.
     */
    protected Context parentContext;

    /**
     * Creates a new instance of Context. Contexts are hierarchical,
     * so the parent context is passed in as a parameter.
     * @param inContext the parent context
     */
    protected Context(Context inContext) {
        parentContext = inContext;
	if (inContext != null)
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
    public void addObject(runtime.Object inObject) {
        parentContext.addObject(inObject);
    }

    /**
     * Adds the given object to this context
     * @param inObject the object to add to the context
     */
    public void delObject(runtime.Object o) throws LemRuntimeException {
	parentContext.delObject(o);
    }

    /**
     * Adds the given collection of objects to this context
     * @param inObjects the objects to add to the context
     */
    public void addObjects(Collection inObjects) throws LemRuntimeException {
	parentContext.addObjects(inObjects);
    }

    /**
     * Gets the named variable from this context or any parent contexts.
     *
     * @param name the name of the variable
     * @return the variable, or null if there is no variable with that name
     */
    public Variable getVariable(String name) {
	    return null;
    }
    
    /**
     * Adds the given variable to this context with the given identifying name.
     * 
     * @param name the identifier of the variable
     * @param variable the variable to be added
     */
    public void addVariable(String name, Variable variable) {
	    throw new Error("addVariable not implemented");
    }
    
    /**
     * Tells this context that it is about to finish. The context should check
     * all visible objects for participation in all relevant associations,
     * etc.
     */
    public void finish() throws LemRuntimeException {}
    
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
    
    public AssociationInstance containsAssociationInstance(AssociationInstance a) {
        if(associationInstances.containsKey(a.getAssociation())) {
            int index = ( (LinkedList)associationInstances.get(a.getAssociation()) ).indexOf(a);
            if(index != -1)
                return (AssociationInstance)( (LinkedList)associationInstances.get(a.getAssociation()) ).get(index);
        }
        if(parentContext != null)
            return parentContext.containsAssociationInstance(a);
        
        return null;
    }
}

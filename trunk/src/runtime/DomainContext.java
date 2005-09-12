/*
 * DomainContext.java
 *
 * Created on June 17, 2005, 12:52 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * A domain context represents the state of a particular instance of a LEM
 * Domain.
 *
 * @author sjr
 */
public class DomainContext extends Context {
    public Debug debugObject;

    /** 
     * A collection of all runtime objects known to this context
     */
    private Collection objectList = new ArrayList();

    /** Creates a new instance of DomainContext */
    public DomainContext() {
	    super(null);
	    debugObject = new Debug(this);
    }

    /**
     * Adds the given object to this context
     * @param inObject the object to add to the context
     */
    public synchronized void addObject(runtime.Object inObject) {
        objectList.add(inObject);
    }

    /**
     * Adds the given object to this context
     * @param inObject the object to add to the context
     */
    public synchronized void delObject(runtime.Object o) throws LemRuntimeException {
        if (!objectList.remove(o)) {
		if (parentContext == null)
			throw new LemRuntimeException("Tried to delete non-existant object");
		parentContext.delObject(o);
	}
    }

    /**
     * Adds the given collection of objects to this context
     * @param inObjects the objects to add to the context
     */
    public synchronized void addObjects(Collection inObjects) throws LemRuntimeException {
	    objectList.addAll(inObjects);
    }
    
    /**
     * Returns the list of objects in this context
     * @returns the list of objects.
     */
    public Collection getObjectList() {
	    return objectList;
    }
}

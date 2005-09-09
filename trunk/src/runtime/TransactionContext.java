/*
 * TransactionContext.java
 */

package runtime;

import java.util.Collection;
import java.util.ArrayList;

/**
 *
 * @author npiggin
 */
public class TransactionContext extends LocalContext {
    /** 
     * A collection of all runtime objects known to this context
     */
    private Collection objectList = new ArrayList();

   
    /** Creates a new instance of TransactionContext */
    public TransactionContext(Context inContext) {
        super(inContext);
    }

    public void finish() throws LemRuntimeException {
	if (parentContext != null)
		parentContext.addObjects(objectList);
    }

    /**
     * Adds the given object to this context
     * @param inObject the object to add to the context
     */
    public void addObject(runtime.Object inObject) {
        objectList.add(inObject);
    }

    /**
     * Adds the given object to this context
     * @param inObject the object to add to the context
     */
    public void delObject(runtime.Object o) throws LemRuntimeException {
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
    public void addObjects(Collection inObjects) throws LemRuntimeException {
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

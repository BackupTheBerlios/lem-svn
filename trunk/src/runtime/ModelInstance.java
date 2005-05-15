/*
 * ModelInstance.java
 *
 * Created on 15 May 2005, 17:56
 */

package runtime;

import java.util.ArrayList;

/**
 * This class represents an instance of a xtUML model.
 * @author u3957053
 */
public class ModelInstance {
    
    ArrayList objectPool = null;
    
    /** Creates a new instance of ModelInstance */
    public ModelInstance() {
        objectPool = new ArrayList();
    }
    
    /**
     * Adds an object to the object pool
     * @param o The object to insert into the pool
     */ 
    public void addObject( runtime.Object o ) {
        objectPool.add( o );
    }
}

/*
 * Instance.java
 *
 * Created on 10 May 2005, 17:44
 */

package runtime;

import metamodel.State;
import metamodel.Class;

/**
 *
 * @author u4128551
 */
public class Instance {
    State currentState = null;
    Class instanceOfClass = null;
    /** Creates a new instance of Instance */
    public Instance(Class inClass) {
        instanceOfClass = inClass;
    }
    public Instance(Class inClass, State inState) {
        instanceOfClass = inClass;
        currentState = inState;
    }
    
}

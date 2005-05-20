/*
 * Object.java
 *
 * Created on 10 May 2005, 17:43
 */

package runtime;

import metamodel.DataType;
    
/**
 *
 * @author u4128551
 */
public class Object {
    InstanceAttribute attributes[] = null;
    metamodel.Class classes[] = null;
    
    /** Creates a new instance of Object */
    public Object(metamodel.Class [] inClasses) {
        classes=inClasses;
    }

    public InstanceAttribute getAttribute(String attributeName) {
        //insert code here :)
        return attributes[0];
    }
}

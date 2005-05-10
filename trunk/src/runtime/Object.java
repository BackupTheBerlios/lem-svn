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
    InstanceAttribute Attributes[] = null;
    Class Classes[] = null;
    /** Creates a new instance of Object */
    public Object(Class [] inClasses) {
        Classes=inClasses;
    }
    public InstanceAttribute getAttribute(String AttributeName)
    {
        //insert code here :)
        return Attributes[0];
    }
}

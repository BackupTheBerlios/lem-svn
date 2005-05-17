/*
 * Context.java
 *
 * Created on 17 May 2005, 14:38
 */

package runtime;

import java.util.ArrayList;

/**
 *
 * @author u4128551
 */
public class Context {
    ArrayList objectList = null;
    ArrayList localVariables = null;
    Context parentContext = null;
    /** Creates a new instance of Context */
    public Context(Context inContext) {
        parentContext = inContext;
    }
    public void addObject(runtime.Object inObject)
    {}
    public ArrayList getObjects()
    {
        return objectList;
    }
    public ArrayList getLocalVariables()
    {}
    public void finish()
    {}
}

/*
 * Context.java
 *
 * Created on 17 May 2005, 14:38
 */

package runtime;

import java.util.ArrayList;

/**
 * Every Action in LEM executes in some sort of "context".
 * @author u4128551
 */
public class Context {
    ArrayList objectList = new ArrayList();
    ArrayList localVariables = null;
    Context parentContext = null;

    /**
     * Creates a new instance of Context. Contexts are hierarchical,
     * so the parent context is passed in as a parameter.
     * @param inContext the parent context
     */
    public Context(Context inContext) {
        parentContext = inContext;
    }
    /**
     * Adds the given object to this context
     * @param inObject the object to add to the context
     */
    public void addObject(runtime.Object inObject) {
        objectList.add(inObject);
    }
    
    /**
     * Returns the list of objects visible in this context
     * @return the objects in this context
     */
    public ArrayList getObjects() {
        return objectList;
    }
    
    /**
     * Returns the list of local variables in this context
     * @return the local variables in this context
     */
    public ArrayList getLocalVariables() {
        return localVariables;
    }
    
    /**
     * Tells this context that it is about to finish. The context should check
     * all visible objects for participation in all relevant associations,
     * etc.
     */
    public void finish() {
    }
}

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
public class ModelInstance extends Context {
    public ModelInstance() {
        super( null );
    }
    
    public ArrayList getLocalVariables() {
        return null;
    }
    
    public void finish() {
    }
}

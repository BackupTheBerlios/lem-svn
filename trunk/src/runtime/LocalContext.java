/*
 * LocalContext.java
 *
 * Created on 17 May 2005, 14:47
 */

package runtime;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

/**
 *
 * @author u4128551
 */
public class LocalContext extends Context {
    /**
     * A collection of all variables local to the context
     */
    private List variableList = new LinkedList();

    /**
     * A map of all variables local to the context
     */
    private HashMap variableMap = new HashMap();
   
    /** Creates a new instance of LocalContext */
    public LocalContext(Context inContext) {
        super(inContext);
    }
 
    /**
     * Gets the named variable from this context or any parent contexts.
     *
     * @param name the name of the variable
     * @return the variable, or null if there is no variable with that name
     */
    public Variable getVariable( String name ) {
        Variable v = (Variable)variableMap.get( name );
        
        if( v == null && parentContext != null ) {
            // Search parent contexts
            v = parentContext.getVariable( name );
        }
        
        return v;
    }
    
    /**
     * Adds the given variable to this context with the given identifying name.
     * 
     * @param name the identifier of the variable
     * @param variable the variable to be added
     */
    public void addVariable( String name, Variable variable ) {
        variableMap.put( name, variable );
	variableList.add( variable );
    }
    
    public List getVariableList() {
	return variableList;
    }
}

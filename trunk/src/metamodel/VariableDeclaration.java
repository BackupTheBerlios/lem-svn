/*
 * VariableDeclaration.java
 */

package metamodel;

/**
 *
 * @author npiggin
 */
public class VariableDeclaration extends Action {
    
    /**
     * The name of the variable being referenced.
     */
    private String variableName;
    
    /** The type of the variable declaration. */
    private DataType variableType;
    
    /** 
     * Creates a new instance of VariableDeclaration. 
     * 
     * @param variableName the name of variable to be declared
     */
    public VariableDeclaration( DataType type, String name ) {
	variableName = name;
	variableType = type;
    }

    /**
     * Gets the name of the variable declared.
     * @return the name
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Gets the type of the variable declared.
     * @return the type
     */
    public DataType getVariableType() {
        return variableType;
    }
}

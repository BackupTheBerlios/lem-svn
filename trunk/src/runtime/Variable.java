/*
 * Variable.java
 *
 * Created on 10 May 2005, 17:47
 */

package runtime;

import metamodel.Attribute;
import metamodel.DataType;

/**
 * This class represents an instance of an Attribute.
 * @author u4128551
 */
public class Variable {
    /**
     * The value associated with this Variable
     */
    java.lang.Object value = null;
//    DataType type = null;
    /**
     * The type of this variable
     */
    DataType attribute = null;
    
    /**
     * Creates a new instance of Variable given the type
     *
     * @param t the type of this variable
     */
    public Variable(DataType t) {
        attribute = t;
    }
    
    /**
     * Sets the value of this attribute instance
     *
     * @param value the new value of the attribute instance
     */
    public void setValue(java.lang.Object value) {
        this.value = value;
    }
    
    /**
     * Gets the value associated with this attribute instance
     *
     * @return the value associated with this attribute instance
     */
    public java.lang.Object getValue(){
        return value;
    }
    
    public DataType getType() {
        return attribute;
    }
}

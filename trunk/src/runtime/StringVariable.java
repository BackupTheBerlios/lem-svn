/*
 * StringVariable.java
 *
 * Created on July 6, 2005, 5:48 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

import metamodel.DataType;
import metamodel.StringType;

/**
 *
 * @author sjr
 */
public class StringVariable extends Variable {

    String value;
    
    /** Creates a new instance of StringVariable */
    public StringVariable( String value ) throws LemRuntimeException {
        this.value = value;
    }
    
    public void setValue( java.lang.Object o ) {
        this.value = (String)o;
    }
    
    public java.lang.Object getValue() {
        return value;
    }
    
    public DataType getType() {
        return StringType.getInstance();
    }
    
    public Variable add( Variable b ) throws LemRuntimeException {
        return new StringVariable( value + b.getValue().toString() );
    }
}

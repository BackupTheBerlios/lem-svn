/*
 * ObjectReferenceVariable.java
 *
 * Created on July 6, 2005, 6:13 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

import metamodel.DataType;
import metamodel.ObjectReferenceType;

/**
 *
 * @author sjr
 */
public class ObjectReferenceVariable extends Variable {
    
    runtime.Object value;
    
    /** Creates a new instance of ObjectReferenceVariable */
    public ObjectReferenceVariable( runtime.Object o ) throws LemRuntimeException {
        setValue( o );
    }
    
    public void setValue( java.lang.Object o ) {
        this.value = (runtime.Object)o;
    }
    
    public java.lang.Object getValue() {
        return value;
    }
    
    public DataType getType() {
        return ObjectReferenceType.getInstance();
    }
}

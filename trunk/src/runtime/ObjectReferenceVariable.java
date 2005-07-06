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

/**
 *
 * @author sjr
 */
public class ObjectReferenceVariable extends Variable {
    
    /** Creates a new instance of ObjectReferenceVariable */
    public ObjectReferenceVariable( String value ) throws LemRuntimeException {
        setValue( null );
    }
    
    public void setValue( java.lang.Object o ) {
//        this.value = (Boolean)o;
    }
    
    public java.lang.Object getValue() {
        return null;
    }
}

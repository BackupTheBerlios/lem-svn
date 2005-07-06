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

/**
 *
 * @author sjr
 */
public class StringVariable extends Variable {

    String value;
    
    /** Creates a new instance of StringVariable */
    public StringVariable( String value ) throws LemRuntimeException {
        if( value == null || !value.matches( "^\".*\"$" )) {
            throw new LemRuntimeException( "Malformed value '" + value +"' passed to StringVariable constructor" );
        }
        
        this.value = value;
    }
    
    public void setValue( java.lang.Object o ) {
        this.value = (String)o;
    }
    
    public java.lang.Object getValue() {
        return value;
    }
    
}

/*
 * BooleanVariable.java
 *
 * Created on July 6, 2005, 6:11 PM
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
public class BooleanVariable extends Variable {
    
    /** Creates a new instance of BooleanVariable */
    public BooleanVariable( String value ) throws LemRuntimeException {
        setValue( new Boolean( value ));
    }
}

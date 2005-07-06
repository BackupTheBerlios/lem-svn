/*
 * NumericVariable.java
 *
 * Created on July 6, 2005, 5:43 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

import java.math.BigDecimal;

/**
 *
 * @author sjr
 */
public class NumericVariable extends Variable {
    
    /** Creates a new instance of NumericVariable */
    public NumericVariable( String value ) throws LemRuntimeException {
        try {
            setValue( new BigDecimal( value ));
        } catch( NumberFormatException nfe ) {
            throw new LemRuntimeException( "NumberFormatException while creating new NumericVariable" );
        }
    }
}

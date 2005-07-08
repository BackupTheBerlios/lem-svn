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

import metamodel.BooleanType;
import metamodel.DataType;

/**
 * Encapsulates a Java Boolean object.
 * @author sjr
 */
public class BooleanVariable extends Variable {
    
    protected Boolean value;
    
    /** Creates a new instance of BooleanVariable */
    public BooleanVariable( String value ) throws LemRuntimeException {
        setValue( new Boolean( value ));
    }
    
    public BooleanVariable( boolean value ) throws LemRuntimeException {
        setValue( new Boolean( value ));
    }
    
    public void setValue( Boolean value ) {
        this.value = value;
    }
    
    public void setValue( java.lang.Object o ) {
        this.value = (Boolean)o;
    }
    
    public java.lang.Object getValue() {
        return value;
    }
    
    public DataType getType() {
        return BooleanType.getInstance();
    }
    
    public Variable logicalNot() throws LemRuntimeException {
        return new BooleanVariable( !value.booleanValue() );
    }
    
    public Variable logicalAnd( Variable right ) throws LemRuntimeException {
        if( !right.getCoreDataType().equals( BooleanType.getInstance() )) {
            throw new LemRuntimeException( "Unsupported operation logicalAnd(boolean, " + right.getCoreDataType().getName() + ")" );
        }
        
        return new BooleanVariable( value.booleanValue() && ((Boolean)right.getValue()).booleanValue() );
    }
    
    public Variable logicalOr( Variable right ) throws LemRuntimeException {
        if( !right.getCoreDataType().equals( BooleanType.getInstance() )) {
            throw new LemRuntimeException( "Unsupported operation logicalAnd(boolean, " + right.getCoreDataType().getName() + ")" );
        }
        
        return new BooleanVariable( value.booleanValue() || ((Boolean)right.getValue()).booleanValue() );
    }
}

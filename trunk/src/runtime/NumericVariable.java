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
import metamodel.CoreDataType;
import metamodel.NumericType;
import metamodel.StringType;

/**
 *
 * @author sjr
 */
public class NumericVariable extends Variable {
    
    BigDecimal value;
    
    /** Creates a new instance of NumericVariable */
    public NumericVariable( String value ) throws LemRuntimeException {
        try {
            setValue( new BigDecimal( value ));
        } catch( NumberFormatException nfe ) {
            throw new LemRuntimeException( "NumberFormatException while creating new NumericVariable" );
        }
    }
    
    public NumericVariable( BigDecimal value ) {
        this.value = value;
        setType( NumericType.getInstance() );
    }
    
    public Variable add( Variable right ) throws LemRuntimeException {
        CoreDataType rightType = right.getCoreDataType();
        if( StringType.getInstance().equals( rightType )) {
            // Concatenate the left and the right
            return new StringVariable( value.toString() + right.getValue().toString() );
        } else if( NumericType.getInstance().equals( rightType )) {
            return new NumericVariable( value.add( (BigDecimal)right.getValue() ));
        }
        
        throw new LemRuntimeException( "Operation add(numeric, " + right.getType().getName() + "' not supported" );
    }

    public Variable subtract( Variable right ) throws LemRuntimeException {
        CoreDataType rightType = right.getCoreDataType();
        if( StringType.getInstance().equals( rightType )) {
            // Concatenate the left and the right
            return new StringVariable( value.toString() + right.getValue().toString() );
        } else if( NumericType.getInstance().equals( rightType )) {
            return new NumericVariable( value.subtract( (BigDecimal)right.getValue() ));
        }
        
        throw new LemRuntimeException( "Operation subtract(numeric, " + right.getType().getName() + "' not supported" );
    }
    
    public Variable multiply( Variable right ) throws LemRuntimeException {
        if( NumericType.getInstance().equals( right.getCoreDataType() )) {
            return new NumericVariable( value.multiply( (BigDecimal)right.getValue() ));
        }
        
        throw new LemRuntimeException( "Operation multiply(numeric, " + right.getType().getName() + "' not supported" );
    }
    
    public Variable divide( Variable right ) throws LemRuntimeException {
        if( NumericType.getInstance().equals( right.getCoreDataType() )) {
            try {
                return new NumericVariable( value.divide( (BigDecimal)right.getValue(), 10, BigDecimal.ROUND_HALF_EVEN ));
            } catch( ArithmeticException e ) {
                throw new LemRuntimeException( "Error in division: " + e.getMessage() );
            }
        }
        
        throw new LemRuntimeException( "Operation divide(numeric, " + right.getType().getName() + "' not supported" );
    }
    
    public Variable notEqual(Variable b) throws LemRuntimeException {
        return equal( b ).logicalNot();
    }
    
    public Variable mod(Variable b) throws LemRuntimeException {
        if( !NumericType.getInstance().equals( b.getCoreDataType() ))
            throw new LemRuntimeException( "Operation mod(numeric, " + b.getType().getName() + "' not supported" );
        BigDecimal right = (BigDecimal)b.getValue();
        
        return new NumericVariable( value.remainder( right ));
    }
    
    private int compare( Variable v ) throws LemRuntimeException {
        if( !NumericType.getInstance().equals( v.getCoreDataType() ))
            throw new LemRuntimeException( "Operation compare(numeric, " + v.getType().getName() + "' not supported" );
        
        BigDecimal right = (BigDecimal)v.getValue();
        
        return value.compareTo( right );
    }

    public Variable equal( Variable b ) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == 0 )
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }
    
    public Variable lessThanOrEqual(Variable b) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == 0 || cmp == -1 ) 
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }
    
    public Variable lessThan(Variable b) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == -1 ) 
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }
    
    public Variable greaterThanOrEqual(Variable b) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == 0 || cmp == 1 ) 
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }
    
    public Variable greaterThan(Variable b) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == 1 ) 
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }
    
    public Variable negation() throws LemRuntimeException {
        return new NumericVariable( value.negate() );
    }
    
    public void setValue( BigDecimal value ) {
        this.value = value;
    }
    
    public void setValue( java.lang.Object o ) {
        this.value = (BigDecimal)o;
    }
    
    public java.lang.Object getValue() {
        return value;
    }
    
}


package runtime;

import java.math.BigDecimal;
import metamodel.CoreDataType;
import metamodel.DataType;
import metamodel.NumericType;
import metamodel.StringType;

/**
 * This class represents instances of LEM NumericTypes. Operations on this NumericVariable
 * should respect any constraints imposed by the variable's DataType. For example, if the
 * DataType is a DomainSpecificDataType, then precision and range restrictions may be imposed.
 * 
 * NumericTypes, like other instances of Variable, are (usually) immutable. That is, operations
 * such as add() and subtract() will result in the creation of new Variable instances.
 * @author sjr
 */
public class NumericVariable extends Variable {
    
    BigDecimal value;
    
    /**
     * Creates a new instance of NumericVariable.
     * @param value the String representing the value of this numeric variable
     * @throws runtime.LemRuntimeException if <code>value</code> does not represent a valid Java BigDecimal when passed to BigDecimal's constructor
     * @see API for java.math.BigDecimal
     */
    public NumericVariable( String value ) throws LemRuntimeException {
        try {
            setValue( new BigDecimal( value ));
            setType( NumericType.getInstance() );
        } catch( NumberFormatException nfe ) {
            throw new LemRuntimeException( "NumberFormatException while creating new NumericVariable" );
        }
    }
    
    /**
     * Create a new instance of NumericVariable using the given BigDecimal as a value.
     * @param value the BigDecimal to be used as a value
     */
    public NumericVariable( BigDecimal value ) {
        this.value = value;
        setType( NumericType.getInstance() );
    }
    
    /**
     * Create a new NumericVariable with the given DataType. This type is usually
     * (but not necessarily) a DomainSpecificDataType specifying constraints on the
     * value of this NumericVariable.
     * @param type the type of this variable
     */
    public NumericVariable( DataType type ) {
        this.value = new BigDecimal( "0" );
        setType( type );
    }
    
    /**
     * Create a new instance of NumericVariable with the given type and value.
     * @param type the type, usually (but not necessarily) a DomainSpecificDataType
     * @param value the value to be assumed by this new variable
     */
    public NumericVariable( DataType type, BigDecimal value ) {
        this.value = value;
        setType( type );
    }
    
    /**
     * Create a new instance of NumericVariable with the given type and value.
     * @param type the type, usually (but not necessarily) a DomainSpecificDataType
     * @param value the value to be assumed by this variable
     * @throws runtime.LemRuntimeException if the value String does not represent a valid Java BigDecimal
     * @see the java.math.BigDecimal API
     */
    public NumericVariable( DataType type, String value ) throws LemRuntimeException {
        try {
            this.value = new BigDecimal( value );
            setType( type );
        } catch( NumberFormatException nfe ) {
            System.err.println( "Invalid string passed to NumericVariable constructor: " + nfe.getMessage() );
        }
    }
    
    /**
     * Adds this NumericVariable to the given Variable. If <code>right</code> is a 
     * StringType, then return a new StringVariable which is represents the String
     * given by the Java statement:
     * 
     * <code>value.toString() + (String)right.getValue()</code>
     * 
     * That is, the String representation of the BigDecimal <code>value</code> is prepended
     * to the String of the StringVariable on the right.
     * 
     * If <code>right</code> is a NumericType, then return a new NumericVariable 
     * representing the BigDecimal sum of the two variables.
     * @param right the variable to add
     * @throws runtime.LemRuntimeException if <code>right</code> is not a NumericType or StringType
     * @return a new Variable
     */
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

    /**
     * Returns a new variable representing the difference between <code>value</code> and the
     * BigDecimal value of the <code>right</code> NumericType.
     * @param right the variable to subtract
     * @throws runtime.LemRuntimeException if <code>right</code> is not a NumericType
     * @return a new NumericVariable representing the difference between this variable and <code>right</code>
     */
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
    
    /**
     * Returns a new NumericVariable representing the product of this NumericVariable and
     * the NumericVariable <code>right</code>.
     * @param right the variable with which to multiply
     * @throws runtime.LemRuntimeException if <code>right</code> is not a NumericType
     * @return a new NumericVariable representing the BigDecimal product of this and <code>right</code>
     */
    public Variable multiply( Variable right ) throws LemRuntimeException {
        if( NumericType.getInstance().equals( right.getCoreDataType() )) {
            return new NumericVariable( value.multiply( (BigDecimal)right.getValue() ));
        }
        
        throw new LemRuntimeException( "Operation multiply(numeric, " + right.getType().getName() + "' not supported" );
    }
    
    /**
     * Returns a new NumericVariable representing the quotient of this NumericVariable and
     * the NumericVariable <code>right</code>.
     * @param right the variable by which to divide
     * @throws runtime.LemRuntimeException if <code>right</code> is not a NumericType
     * @return a new NumericVariable representing the BigDecimal quotient of this and <code>right</code>
     */
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
    
    /**
     * Returns equal( b ).logicalNot().
     *
     * @param b the Variable with which to compare
     * @throws runtime.LemRuntimeException if the call to equal() generates an exception
     * @return the BooleanVariable representing the truth of this comparison
     * @see NumericVariable#equal( Variable )
     */
    public Variable notEqual(Variable b) throws LemRuntimeException {
        return equal( b ).logicalNot();
    }
    
    /**
     * Returns the remainder of dividing this variable by the NumericVariable <code>b</code>.
     *
     * @param b the NumericVariable by which to divide
     * @throws runtime.LemRuntimeException if <code>b</code> is not a NumericVariable
     * @return a new NumericVariable which contains the result of the BigDecimal.remainder()
     * operation applied to this and <code>b</code>
     * @see the java.math.BigDecimal remainder() API call
     */
    public Variable mod(Variable b) throws LemRuntimeException {
        if( !NumericType.getInstance().equals( b.getCoreDataType() ))
            throw new LemRuntimeException( "Operation mod(numeric, " + b.getType().getName() + "' not supported" );
        BigDecimal right = (BigDecimal)b.getValue();
        BigDecimal rem = value.remainder( right );
        return new NumericVariable( rem );
    }
    
    /**
     * Apply the BigDecimal.compareTo() method to this and <code>v</code>.
     *
     * @param v the NumericVariable with which to compare
     * @throws runtime.LemRuntimeException if <code>v</code> is not a NumericVariable
     * @return the result of the call to compareTo()
     */
    private int compare( Variable v ) throws LemRuntimeException {
        if( !NumericType.getInstance().equals( v.getCoreDataType() ))
            throw new LemRuntimeException( "Operation compare(numeric, " + v.getType().getName() + "' not supported" );
        
        BigDecimal right = (BigDecimal)v.getValue();
        
        return value.compareTo( right );
    }

    /**
     * Returns a new BooleanVariable representing whether this NumericValue
     * represents the same value as the given NumericValue.
     *
     * <b>NOTE:</b> This method is subject to the oddities of the BigDecimal.compareTo() call.
     * You are advised to be sure of its semantics.
     * @param b the NumericVariable with which to compare 
     * @throws runtime.LemRuntimeException if <code>b</code> is not a NumericVariable
     * @return a new BooleanVariable representing the truth of the equality
     */
    public Variable equal( Variable b ) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == 0 )
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }

    /**
     * Returns a new BooleanVariable representing whether this NumericValue
     * represents the same a lesser or equal value when compared to the given NumericValue.
     *
     * <b>NOTE:</b> This method is subject to the oddities of the BigDecimal.compareTo() call.
     * You are advised to be sure of its semantics.
     * @param b the NumericVariable with which to compare 
     * @throws runtime.LemRuntimeException if <code>b</code> is not a NumericVariable
     * @return a new BooleanVariable representing the truth of the comparison
     */
    public Variable lessThanOrEqual(Variable b) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == 0 || cmp == -1 ) 
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }

    /**
     * Returns a new BooleanVariable representing whether this NumericValue
     * represents the same a lesser value when compared to the given NumericValue.
     *
     * <b>NOTE:</b> This method is subject to the oddities of the BigDecimal.compareTo() call.
     * You are advised to be sure of its semantics.
     * @param b the NumericVariable with which to compare 
     * @throws runtime.LemRuntimeException if <code>b</code> is not a NumericVariable
     * @return a new BooleanVariable representing the truth of the comparison
     */
    public Variable lessThan(Variable b) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == -1 ) 
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }
    
    /**
     * Returns a new BooleanVariable representing whether this NumericValue
     * represents the same an equal or greater value when compared to the given NumericValue.
     *
     * <b>NOTE:</b> This method is subject to the oddities of the BigDecimal.compareTo() call.
     * You are advised to be sure of its semantics.
     * @param b the NumericVariable with which to compare 
     * @throws runtime.LemRuntimeException if <code>b</code> is not a NumericVariable
     * @return a new BooleanVariable representing the truth of the comparison
     */
    public Variable greaterThanOrEqual(Variable b) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == 0 || cmp == 1 ) 
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }
    
    /**
     * Returns a new BooleanVariable representing whether this NumericValue
     * represents a greater value when compared to the given NumericValue.
     *
     * <b>NOTE:</b> This method is subject to the oddities of the BigDecimal.compareTo() call.
     * You are advised to be sure of its semantics.
     * @param b the NumericVariable with which to compare 
     * @throws runtime.LemRuntimeException if <code>b</code> is not a NumericVariable
     * @return a new BooleanVariable representing the truth of the comparison
     */
    public Variable greaterThan(Variable b) throws LemRuntimeException {
        int cmp = compare( b );
        
        if( cmp == 1 ) 
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }
    
    /**
     * Returns a new NumericVariable representing the negation (multiplication by -1) of this NumericVariable
     * @throws runtime.LemRuntimeException 
     * @return a new NumericVariable representing the negation of this NumericVariable
     */
    public Variable negation() throws LemRuntimeException {
        return new NumericVariable( value.negate() );
    }
        
    /**
     * Sets the value of this NumericVariable to the given java.lang.Object. The 
     * object is unconditionally cast to a BigDecimal, so it is up to the caller to 
     * ensure that the given Object is a BigDecimal.
     *
     * @param o the new BigDecimal value
     */
    public void setValue( java.lang.Object o ) {
        this.value = (BigDecimal)o;
    }
    
    /**
     * Returns the BigDecimal value as a java.lang.Object.
     *
     * @return the value of this NumericVariable
     */
    public java.lang.Object getValue() {
        return value;
    }
    
}

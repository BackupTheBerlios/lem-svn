/*
 * Variable.java
 *
 * Created on 10 May 2005, 17:47
 */

package runtime;
import metamodel.CoreDataType;
import metamodel.DataType;
import metamodel.*;
import metamodel.DomainSpecificDataType;

/**
 * This class represents an instance of an getType().
 * @author u4128551
 */
public abstract class Variable {
    
    protected DataType type = null;
    
    public Variable() {
    }
    
    /**
     * Gets the value associated with this getType() instance
     *
     * @return the value associated with this getType() instance
     */
    public abstract java.lang.Object getValue() throws LemRuntimeException;

    /**
     * Set the value of this variable
     *
     * @param Object representing the value of this variable.
     */
    public abstract void setValue( java.lang.Object o ) throws LemRuntimeException;
    
    /**
     * Gets the datatype of this variable.
     *
     * @return the DataType associated with this variable
     */
    public DataType getType() {
        return type;
    }
    
    /**
     * sets the type of variable to the specified type
     *
     * @param d type of the variable
     */
    public void setType(DataType d) {
        type = d ;
    }
    
    /** Gets the CoreDataType of this variable.
     *@return the CoreDataType associated with this variable
     */
    public CoreDataType getCoreDataType() {
        DataType type = getType();
        if( type instanceof DomainSpecificDataType ) {
            return ((DomainSpecificDataType)type).getCoreDataType();
        }
        
        return (CoreDataType)type;
    }
    
    /*Mathematical and Logical operations defined on variables */
    
    public Variable add( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (add) in type " + getType().getName() );
    }
    
    public Variable subtract( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (subtract) in type " + getType().getName() );
    }
    
    public Variable multiply( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (multiply) in type " + getType().getName() );
    }
    
    public Variable divide( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (divide) in type " + getType().getName() );
    }
    
    public Variable exp( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (exp) in type " + getType().getName() );
    }
    
    public Variable equal( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (equal) in type " + getType().getName() );
    }
    
    public Variable lessThan( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (lessThan) in type " + getType().getName() );
    }
    
    public Variable lessThanOrEqual( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (lessThanOrEqual) in type " + getType().getName() );
    }
    
    public Variable greaterThan( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (greaterThan) in type " + getType().getName() );
    }
    
    public Variable greaterThanOrEqual( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (greaterThanOrEqual) in type " + getType().getName() );
    }
    
    public Variable mod( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (mod) in type " + getType().getName() );
    }
    
    public Variable notEqual( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (notEqual) in type " + getType().getName() );
    }
    
    public Variable logicalAnd( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (logicalAnd) in type " + getType().getName() );
    }
    
    public Variable logicalOr( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (logicalOr) in type " + getType().getName() );
    }
    
    public Variable logicalNot() throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported unary operation (logicalNot) in type " + getType().getName() );
    }
    
    public Variable negation() throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported unary operation(negation) in type " + getType().getName() );
    }
}

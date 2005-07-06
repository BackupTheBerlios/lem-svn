/*
 * Variable.java
 *
 * Created on 10 May 2005, 17:47
 */

package runtime;
import metamodel.CoreDataType;
import metamodel.DataType;
import metamodel.DomainSpecificDataType;

/**
 * This class represents an instance of an Attribute.
 * @author u4128551
 */
public abstract class Variable {

//    DataType type = null;
    /**
     * The type of this variable
     */
    DataType attribute = null;
    
    public Variable() {
    }
    
    /**
     * Creates a new instance of Variable given the type
     *
     * @param t the type of this variable
     */
    public Variable(DataType t) {
        attribute = t;
    }
    
    /**
     * Gets the value associated with this attribute instance
     *
     * @return the value associated with this attribute instance
     */
    public abstract java.lang.Object getValue();
    
    public DataType getType() {
        return attribute;
    }
    
    public CoreDataType getCoreDataType() {
        if( attribute instanceof DomainSpecificDataType ) {
            return ((DomainSpecificDataType)attribute).getCoreDataType();
        }
        
        return (CoreDataType)attribute;
    }
    
    public void setType( DataType t ) {
        attribute = t;
    }
    
    public abstract void setValue( java.lang.Object o );
    
    public Variable add( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (add) in type " + attribute.getName() );
    }
    
    public Variable subtract( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (subtract) in type " + attribute.getName() );
    }
    
    public Variable multiply( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (multiply) in type " + attribute.getName() );
    }
    
    public Variable divide( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (divide) in type " + attribute.getName() );
    }
   
    public Variable exp( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (exp) in type " + attribute.getName() );
    }
    
    public Variable equal( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (equal) in type " + attribute.getName() );
    }
    
    public Variable lessThan( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (lessThan) in type " + attribute.getName() );
    }
    
    public Variable lessThanOrEqual( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (lessThanOrEqual) in type " + attribute.getName() );
    }
    
    public Variable greaterThan( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (greaterThan) in type " + attribute.getName() );
    }
    
    public Variable greaterThanOrEqual( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (greaterThanOrEqual) in type " + attribute.getName() );
    }
    
    public Variable mod( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (mod) in type " + attribute.getName() );
    }
    
    public Variable notEqual( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (notEqual) in type " + attribute.getName() );
    }
    
    public Variable logicalAnd( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (logicalAnd) in type " + attribute.getName() );
    }
    
    public Variable logicalOr( Variable b ) throws LemRuntimeException {
        throw new LemRuntimeException( "Unsupported operation (logicalOr) in type " + attribute.getName() );
    }
}


package runtime;

import metamodel.BooleanType;
import metamodel.CoreDataType;
import metamodel.DataType;
import metamodel.DomainSpecificDataType;
import metamodel.NumericType;
import metamodel.ObjectReferenceType;
import metamodel.StringType;

/**
 * The VariableFactory is responsible for creating new subtypes of the Variable
 * class given the desired DataType and value for the new Variable.
 *
 * @author sjr
 */
public class VariableFactory {
    
    /** Creates a new instance of VariableFactory */
    private VariableFactory() {
    }
    
    static Variable newVariable( DataType type, String value ) throws LemRuntimeException {
        Variable v = null;
        DataType c = type;
        
        if( type instanceof DomainSpecificDataType ) {
            c = ((DomainSpecificDataType)type).getCoreDataType();
        }
        
        if( c.equals( StringType.getInstance() )) {
            v = new StringVariable( value );
        } else if( c.equals( NumericType.getInstance() )) {
            v = new NumericVariable( value );
        } else if( c.equals( BooleanType.getInstance() )) {
            v = new BooleanVariable( value );
        } else if( c.equals( ObjectReferenceType.getInstance() )) {
            v = new ObjectReferenceVariable( value );
        } else {
            throw new LemRuntimeException( "VariableFactory for type '" + type.getName() + "' unimplemented" );
        }
        
        v.setType( type );
        return v;
    }
    
    static Variable newVariable( DataType type ) throws LemRuntimeException {
        Variable v = null;
        DataType c = type;
        
        if( type instanceof DomainSpecificDataType ) {
            c = ((DomainSpecificDataType)type).getCoreDataType();
        }
        
        if( c.equals( StringType.getInstance() )) {
            v = new StringVariable( "\"\"" );
        } else if( c.equals( NumericType.getInstance() )) {
            v = new NumericVariable( "0" );
        } else if( c.equals( BooleanType.getInstance() )) {
            v = new BooleanVariable( "true" );
        } else if( c.equals( ObjectReferenceType.getInstance() )) {
            v = new ObjectReferenceVariable( null );
        } else {
            throw new LemRuntimeException( "VariableFactory for type '" + type.getName() + "' unimplemented" );
        }
        
        v.setType( type );
        return v;
    }
}

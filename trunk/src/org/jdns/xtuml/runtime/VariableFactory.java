
package org.jdns.xtuml.runtime;

import org.jdns.xtuml.metamodel.BooleanType;
import org.jdns.xtuml.metamodel.ArbitraryIdType;
import org.jdns.xtuml.metamodel.CoreDataType;
import org.jdns.xtuml.metamodel.DataType;
import org.jdns.xtuml.metamodel.DateType;
import org.jdns.xtuml.metamodel.DomainSpecificDataType;
import org.jdns.xtuml.metamodel.NumericType;
import org.jdns.xtuml.metamodel.ObjectReferenceType;
import org.jdns.xtuml.metamodel.StringType;
import org.jdns.xtuml.metamodel.SetType;

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
    
    public static Variable newVariable( DataType type, java.lang.Object value ) throws LemRuntimeException {
        Variable v = null;
        DataType c = type;
        
        if( type instanceof DomainSpecificDataType ) {
            c = ((DomainSpecificDataType)type).getCoreDataType();
        }
        
        if( c.equals( StringType.getInstance() )) {
            v = new StringVariable( (String)value );
        } else if( c.equals( ArbitraryIdType.getInstance() )) {
            return new ArbitraryIdVariable();
        } else if( c.equals( NumericType.getInstance() )) {
            return new NumericVariable( type, (String)value );
        } else if( c.equals( BooleanType.getInstance() )) {
            v = new BooleanVariable( (String)value );
        } else if( c.equals( DateType.getInstance() )) {
            v = new DateVariable((String)value);           
        } else if( c.equals( ObjectReferenceType.getInstance() )) {
	    if (value != null && !((String)value).equals("null"))
	    	throw new Error("Tried to create objref with non-NULL value");
            v = new ObjectReferenceVariable(null);
        } else if( c.equals( SetType.getInstance() )) {
	    if (value != null) {
		    throw new LemRuntimeException( "VariableFactory unable to create SetVariable with non-null value" );
	    }
            v = new SetVariable( null );
	} else {
            throw new LemRuntimeException( "VariableFactory for type '" + type.getName() + "' unimplemented" );
        }
        
        v.setType( type );
        return v;
    }
    
    public static Variable newVariable( DataType type ) throws LemRuntimeException {
        Variable v = null;
        DataType c = type;
        
        if( type instanceof DomainSpecificDataType ) {
            c = ((DomainSpecificDataType)type).getCoreDataType();
        }
        
        if( c.equals( StringType.getInstance() )) {
            v = new StringVariable( "\"\"" );
        } else if( c.equals( ArbitraryIdType.getInstance() )) {
            return new ArbitraryIdVariable();
        } else if( c.equals( NumericType.getInstance() )) {
            v = new NumericVariable( "0" );
        } else if( c.equals( BooleanType.getInstance() )) {
            v = new BooleanVariable( "true" );
        } else if( c.equals(DateType.getInstance() )) {
            v = new DateVariable();                
        } else if( c.equals( ObjectReferenceType.getInstance() )) {
            v = new ObjectReferenceVariable( null );
        } else {
            throw new LemRuntimeException( "VariableFactory for type '" + type.getName() + "' unimplemented" );
        }
        
        v.setType( type );
        return v;
    }
}

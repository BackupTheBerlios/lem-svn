/*
 * CoreDataType.java
 *
 * Created on January 31, 2004, 12:17 PM
 */

package metamodel;
import java.util.*;

/**
 * Superclass of all core data types
 *
 * @author  smr
 */
public abstract class CoreDataType implements DataType {
    
    /** a symbol table of CoreDataTypes */
    private static HashMap types = new HashMap();
    
    /** Name of this CoreDataType */
    protected String name = "";
    
    /** Creates a new instance of CoreDataType */
    public CoreDataType() {
    }
    
        
    /** register the single instance of each subclass */
    static {
        register( ArbitraryIdType.getInstance() );
        register( BooleanType.getInstance() );
        register( NumericType.getInstance() );
        register( DateType.getInstance() );
        register( IntegerType.getInstance() );
        register( RealType.getInstance() );
        register( StringType.getInstance() );
        register( TimestampType.getInstance() );
        
    }
    
    /** Getter for property name.
     * @return Value of property name.
     *
     */
    public java.lang.String getName() {
        return name;
    }
    
    /**
     * Register the specified data type as a valid CoreDataType
     *
     * @param theType to be registered
     */
    protected static void register( CoreDataType theType ) {
        types.put( theType.getName(), theType );
    }
    
    /**
     * Return the name CoreDataType
     *
     * @param name of the CoreDataType
     * @return the CoreDataType matching the name
     * @throws LemException if the specified CoreDataType does not exist
     */
    public static CoreDataType findByName( String name ) throws LemException {
        
        CoreDataType result = (CoreDataType) types.get( name );
        if ( null == result ) {
            throw new LemException( "'" + name + "' is not a basic type" );
        }
        
        return result;
    }
    
    /**
     * Return this CoreDataType 
     *
     * @return the this CoreDataType 
     */
    public CoreDataType getCoreDataType() {
        return this;
    }
}

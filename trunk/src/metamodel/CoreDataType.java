/* 
 * CoreDataType.java
 *
 * Copyright (C) 2005 Steven Michael Ring
 *
 * This program is free software; you can redistribute it and/or  
 * modify it under the terms of the GNU General Public License  
 * as published by the Free Software Foundation; either version 2  
 * of the License, or (at your option) any later version.  
 *  
 * This program is distributed in the hope that it will be useful,  
 * but WITHOUT ANY WARRANTY; without even the implied warranty of  
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
 * GNU General Public License for more details.  
 * 
 * You should have received a copy of the GNU General Public License  
 * along with this program; if not, write to the Free Software  
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,  
 * USA. 
 */

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
        register( ObjectReferenceType.getInstance() );
	register( SetType.getInstance() );
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

/* 
 * DomainSpecificDataType.java
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
 * DataType.java
 *
 * Created on January 31, 2004, 9:40 AM
 */

package metamodel;

import java.util.*;

/**
 * Represents a non-primitive data type.
 *
 * @author  smr
 */
public class DomainSpecificDataType extends DomainElement implements DataType, DescribedEntity {
    
    /** Name of this type */
    private String name = "";
    
    /** the CoreDataType used by this DomainSpecificDataType */
    private CoreDataType coreType = null;
    
    /** the description of the class */
    private String description = "";
    
    /** list of members for an enumeration */
    private ArrayList enumeration = null;
    
    /** the units of measure for this type */
    private String units = "";
    
    /** the precision of this type */
    private double precision = 1.0;
    
    /** the length of this data type */
    private int length = 0;
    
    /** an optional pattern match for this type */
    private String pattern = null;
    
    /** an option range constraint */
    private Range range = null;
  
    
    /** Creates a new instance of DataType */
    public DomainSpecificDataType() {
    }
    
    /** Getter for property name.
     * @return Value of property name.
     *
     */
    public java.lang.String getName() {
        return name;
    }
    
    /** Setter for property name.
     * @param name New value of property name.
     *
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    /** Getter for property coreType.
     * @return Value of property coreType.
     *
     */
    public CoreDataType getCoreType() {
        return coreType;
    }
    
    /** Setter for property coreType.
     * @param coreType New value of property coreType.
     *
     */
    public void setCoreType(CoreDataType coreType) {
        this.coreType = coreType;
    }
    
      
    /** Getter for property description.
     * @return Value of property description.
     *
     */
    public java.lang.String getDescription() {
        return description;
    }
    
    /** Setter for property description.
     * @param description New value of property description.
     *
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }
    
    /**
     * Return true if this type is an enumeration of identifiers
     *
     * @return true if this type is an enumeration of identifiers,
     * false otherwise.
     */
    public boolean isEnumeration() {
        return enumeration != null;
    }
    
    /**
     * Add the supplied identifier to the enumeration
     *
     * @param anIdentifier to be added to the enumeration
     * @throws LemException if the identifier is already in the list
     */
    public void addIdentifier( String anIdentifier ) throws LemException {
        
        // if this is the first identifier,
        // create a new ArrayList to house the enumeration
        
        if ( enumeration == null )
            enumeration = new ArrayList();
        
        // make sure that the supplied identifier is not already in the enumeration
        
        for ( Iterator it = enumeration.iterator(); it.hasNext(); ) {
            String ident = (String) it.next();
            if ( ident.equals( anIdentifier )) 
                throw new LemException( ident + " already exists in enumeration");
        }
        
        // all is ok so add the identifier
        
        enumeration.add( anIdentifier );
    }
    
    /**
     * Return the index of the supplied identifier
     * 
     * @param theIdentifier whose index is to be returned
     * @return the index of the supplied identifier ( -1 if it is not present)
     * @throws LemException if this is not an enumeration type
     */
    public int getIndex( String theIdentifier ) throws LemException {
        
        // this must be an enumeration for this method to make sense
        
        if ( ! isEnumeration() )
            throw new LemException( "DomainSpecificDataType " + name + " is not an enumeration");
        
        for ( int i = 0; i < enumeration.size(); i++ ) {
            String ident = (String) enumeration.get( i );
            if ( ident.equals( theIdentifier ))
                return i;
        }
        
        // not found
        
        return -1;
    }
    
    /**
     * Return the units specification for this data type
     * 
     * @return the units specification for this data type
     */

    public String getUnits() {
        return units;
    }

    /**
     * Set the units specification for this data type
     * 
     * @param units for this data type
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * Return the precision specification for this data type
     * 
     * @return the precision specification for this data type
     */

    public double getPrecision() {
        return precision;
    }

    /**
     * Set the precision specification for this data type
     * 
     * @param precision for this data type
     */
    public void setPrecision(double precision) {
        this.precision = precision;
    }

    /**
     * Return the length specification for this data type
     * 
     * @return the length specification for this data type
     */
    public int getLength() {
        return length;
    }

    /**
     * Set the length specification for this data type
     * 
     * @param length for this data type
     */
    public void setLength(int length) {
        this.length = length;
    }
    
    /**
     * Return the pattern specification for this data type
     * 
     * @return the pattern specification for this data type
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Set the pattern specification for this data type
     * 
     * @param pattern for this data type
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Return the range specification for this data type
     * 
     * @return the range specification for this data type
     */
    public Range getRange() {
        return range;
    }

    /**
     * Set the range specification for this data type
     * 
     * @param range for this data type
     */
    public void setRange(Range range) {
        this.range = range;
    }
    
    /**
     * Return the CoreDataType associated with this DomainSpecificDataType
     *
     * @return the CoreDataType associated with this DomainSpecificDataType
     */
    public CoreDataType getCoreDataType() {
        return this.getCoreType();
    }
      
}

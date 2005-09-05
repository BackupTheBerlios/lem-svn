/* 
 * Domain.java
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
 * Domain.java
 *
 * Created on December 6, 2003, 4:31 PM
 */

package metamodel;
import java.util.*;

/**
 * Represents an xtUML domain comprising types, classes, associations and other xtUML elements
 *
 * @author  smr
 */
public class Domain implements DescribedEntity {
    
    /** the name of the Domain */
    private String name = "";
    
    /** Description of the Domain */
    private String description = "";
    
    /** A collection of subsystems */
    private HashMap subsystems = new HashMap();
    
    /** Reference classes by number */
    private Vector numberedClasses = new Vector();
    
    /** A collection of classes */
    private HashMap classes = new HashMap();
    
    /** Collection of data types declared for this domain */
    private HashMap types = new HashMap();
    
    /** A Collection of Relationships. Impements R121 */
    private HashMap relationships = new HashMap();
    
    /** A Map of Scenario names to Scenario objects */
    private Map scenarios = new HashMap();
    /** 
     * Creates a new instance of Domain 
     */
    public Domain(){
        
        // create a "default" subsytem
        
        Subsystem defaultSS = new Subsystem();
        defaultSS.setDomain( this );
        defaultSS.setDescription( "Default subsystem automatically provided by LEM" );
        defaultSS.setName( "Default" );
        
        try {
            this.add( defaultSS );
        } catch ( LemException e ) {
            // this cannot happen, it is a new domain
        }
    }
    
    /** Getter for property associations.
     * @return Value of property associations.
     *
     */
    public java.util.HashMap getRelationships() {
        return relationships;
    }
    
    /** Getter for property classes.
     * @return Value of property classes.
     *
     */
    public java.util.HashMap getClasses() {
        return classes;
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
    
    /**
     * Add a class to the domain
     * @param theClass to be added to the domain
     * @throws LemException in the case where the Class has already been added to this Domain
     */
    public void add( metamodel.Class theClass ) throws LemException {
        
        String key = theClass.getName();
        if ( null != classes.get( key ) ) {
            throw new LemException( "Class " + key + " already exists" );
        }
        
        // all is ok, so add the class collection
        
        theClass.setDomain( this );
        classes.put( key, theClass );
        
        // check external references from class
        
        // theClass.checkAttributes();
    }
    
       
    /**
     * Add a Subsystem to the domain
     * @param theSubsystem to be added to the domain
     * @throws LemException in the case where the Subsystem has already been added to this Domain
     */
    public void add( Subsystem theSubsystem ) throws LemException {
        
        String key = theSubsystem.getName();
        if ( null != subsystems.get( key ) ) {
            throw new LemException( "Subsystem " + key + " already exists in domain " + name );
        }
        
        // all is ok, so add the class collection
        
        theSubsystem.setDomain( this );
        subsystems.put( key, theSubsystem );
        
        // add the subsystem's classes to the domain
        
    //    for ( Iterator it = theSubsystem.getClasses().values().iterator(); it.hasNext();  ) {
    //        Class theClass = (Class) it.next();
    //        this.add( theClass );
    //    }
    }
    
    /**
     * Return the specified subsystem
     *
     * @param name of subsystem to return
     * @return the specified subsystem or null if it does not exist
     */
    public Subsystem getSubsystem( String name ) {
        return (Subsystem) subsystems.get( name );
    }
    
    /**
     * Add a DomainSpecificDataType to the domain
     * @param theType the type to be added
     * @throws LemException in the case where the DomainSpecificDataType already exists in this Domain
     */
    public void add( DomainSpecificDataType theType ) throws LemException {
        
        String key = theType.getName();
        if ( null != types.get( key ) ) {
            throw new LemException( "Type " + key + " already exists in domain " + name );
        }
        
        types.put( key, theType );
        theType.setDomain( this );
    }
    
     /**
     * Add a Relationship to the domain
     * @param theRelationship to be added to the domain
     * @throws LemException in the event of an error (eg Duplicate assocation)
     */
    public void add( Relationship theRelationship ) throws LemException {
        
        String key = theRelationship.getName();
        if ( null != relationships.get( key ) ) {
            throw new LemException( "Relationship " + key + " already exists in domain " + name);
        }
        
        // all is ok, so add the association collection
        
        theRelationship.setDomain( this );
        relationships.put( key, theRelationship );
    }
    
    /**
     * Add the given Scenario to the domain
     *
     * @param scenario the Scenario
     * @throws LemException in the event of a duplicate scenario name
     */
    public void add( Scenario scenario ) throws LemException {
        String key = scenario.getName();
        if( scenarios.get( key ) != null )
            throw new LemException( "Scenario " + key + " already exists in domain " + name);
        
        scenarios.put( key, scenario ); 
    }
    
    /**
     * Get all scenarios defined for this domain.
     *  
     * @return the Map of Scenario names to Scenario objects
     */
    public Map getScenarios() {
        return scenarios;
    }

    /**
     * Get the named scenario from the domain, or null if it does not exist.
     *
     * @return the named scenario
     */
    public Scenario getScenario( String name ) {
	return (Scenario)scenarios.get( name );
    }
	
    
    
    /**
     * Retrieve a class given its name 
     *
     * @param name of the class to be retrieved
     * @return the Class with the specified number or null if it does not exist
     */
    public Class getClass( String name ) {
        
        return (Class) classes.get( name );
    }
    
    /**
     * Retrieve a relationship given its name 
     *
     * @param name of the relationship to be retrieved
     * @return the relationship with the specified name or null if it does not exist
     */
    public Relationship getRelationship( String name ) {
        
        return (Relationship) relationships.get( name );
    }
    
    /** Getter for property subsystems.
     * @return Value of property subsystems.
     *
     */
    public java.util.HashMap getSubsystems() {
        return subsystems;
    }
    
    /** Setter for property subsystems.
     * @param subsystems New value of property subsystems.
     *
     */
    public void setSubsystems(java.util.HashMap subsystems) {
        this.subsystems = subsystems;
    }
 
    /** Getter for property types.
     * @return Value of property types.
     *
     */
    public java.util.HashMap getTypes() {
        return types;
    }
    
    /** Setter for property types.
     * @param types New value of property types.
     *
     */
    public void setTypes(java.util.HashMap types) {
        this.types = types;
    }
    
    /**
     * Return the named DomainSpecificDataType
     *
     * @param typeName of the DomainSpecificDataType
     * @return the DomainSpecificDataType matching the name or null if it
     * does not exist
     */
    public DomainSpecificDataType getType( String typeName ) {
        
        DomainSpecificDataType result = (DomainSpecificDataType) types.get( typeName );
                
        return result;
    }
    
        
    /**
     * Return the named DomainSpecificDataType
     * @param typeName of the DomainSpecificDataType
     * @return the DomainSpecificDataType matching the name
     * @throws LemException if the specified DomainSpecificDataType does not exist
     * @todo Should this really throw an exception in the case where the type can't be found?
     */
    public DomainSpecificDataType findTypeByName( String typeName ) throws LemException {
        
        DomainSpecificDataType result = (DomainSpecificDataType) types.get( typeName );
     
        // TODO: Should this really throw an exception in the case where the type can't be found?
        // I don't think types not found would be an exceptional circumstance
        
        if ( null == result ) {
            throw new LemException( " Type '" + typeName + "' is not defined in domain '" + name + "'" );
        }
        
        return result;
    }
    
    /**
     * Find the named class  or create a new one. The method support forward references to classes
     *
     * @param className to be found or created
     * @param ref the source reference for this enquiry
     * @return the class (either existing or newly created)
     */
    public Class findOrCreate( String className, SourceReference ref ) {
        
        Class result = findOrCreate( className );
        result.setFirstReferenced( ref );
        
        return result;
        
    }
    
        
    /**
     * Find the named class  or create a new one. 
     *
     * @param className to be found or created
     * @return the class (either existing or newly created)
     */
    public Class findOrCreate( String className ) {
        
        Class result = (Class) classes.get( className );
        if ( null == result ) {
            result = new Class( this, className );
            numberedClasses.add( result );
            result.setClassNumber( numberedClasses.size() - 1 );
            try {
                this.add( result );
            } catch ( LemException e ) {
                // cannot happen in this context
                e.printStackTrace();
                System.exit( 99 );
            }
        }
        
        return result;
        
    }
    
    /**
     * Retrieve a class given its number
     * @param classNumber of the class to be retrieved
     * @throws LemException if the class does not exist
     * @return the Class with the specified number
     */
    public Class getClassByNumber( int classNumber ) throws LemException {
        
        Class result = null;
        try {
            result = (Class) numberedClasses.get( classNumber );
        } catch ( Exception e ) {
            result = null;
        }
        
        if ( null == result ) 
            throw new LemException( "Class number " + classNumber + " is not defined" );
        else 
            return result;
    }
}

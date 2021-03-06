/*
 * Class.java
 *
 * Created on December 6, 2003, 4:43 PM
 */

package metamodel;
import java.util.*;

/**
 * Represents an xtUML Class
 *
 * @author  smr
 */
public class Class extends DomainElement implements SubsystemElement, DescribedEntity {

    
    /** the id assigned to this class */
    private int classNumber;
    
    /** the name of the class */
    private String name = "";
    
    /** the abbreviated name of the class (defaults to name) */
    private String abbreviation = null;
    
    /** identifiers for this class. There may be zero, one or many */
    private ArrayList identifiers = new ArrayList();

    /** store the Association if this is an AssociationClass */
    private Association association = null;
    
    /** the Subsystem to which this class belongs */
    private Subsystem subsystem = null;
    
    /** attributes of this class */
    private HashMap attributes = new HashMap();
    
    /** generalisation roles in which this class participates */
    private HashMap generalisationRoles = new HashMap();
    
    /**
     * The events to which this class responds.
     * This excludes non-public (self directed) events delared in the LEM "behaviour" block.
     * This attribute supports metamodel relationship R901.
     */
    private HashMap events = new HashMap();
    
    /**
     * The state machine associated with this class [R501]
     */
    private StateMachine stateMachine = null;
   
    
    
    /**
     * Creates a new instance of Class given a Domain and name
     *
     */
    public Class( ) {
    }
    
    /**
     * Creates a new instance of Class given a Domain and name
     *
     * @param domain in which this class is defined
     * @param name of the class
     */
    public Class( Domain domain, String name ) {
        this.domain = domain;
        this.name = name;
    }
    

        
    
    /** Getter for property abbreviation.
     * @return Value of property abbreviation.
     *
     */
    public java.lang.String getAbbreviation() {
        if ( null != abbreviation )
            return abbreviation;
        else
            return name;
    }
    
    /** Setter for property abbreviation.
     * @param abbreviation New value of property abbreviation.
     *
     */
    public void setAbbreviation(java.lang.String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
    /** Getter for property classNumber.
     * @return Value of property classNumber.
     *
     */
    public int getClassNumber() {
        return classNumber;
    }
    
    /** Setter for property classNumber.
     * @param classNumber New value of property classNumber.
     *
     */
    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
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
    
    /** Getter for property association.
     * @return Value of property association.
     *
     */
    public Association getAssociation() {
        return association;
    }
    
    /** Setter for property association. Warning, use
     * Association method setAssociationClass to assign the 
     * association class.
     *
     * @param association New value of property association.
     *
     */
    protected void setAssociation(Association association) {
        this.association = association;
    }
    
    /**
     * Reports if this is an Association Class. In xtUML an Associaton Class
     * acts to formalise and specific Association. In all other respects
     * an Association Class is a normal class
     *
     * @return true if this is an Association Class, false otherwise
     */
    
    public boolean isAssociationClass() {
        
        return ( null != association );
    }
    
    /**
     * returns the Subsytem to which this SubsystemElement belongs
     *
     * @return theSubsystem to which this SubsystemElement belongs
     *
     */
    public Subsystem getSubsystem() {
        return subsystem;
    }
    
    /**
     * set the Subsytem to which this SubsystemElement belongs
     *
     * @param theSubsystem to which this SubsystemElement belongs
     *
     */
    public void setSubsystem(Subsystem theSubsystem) {
        subsystem = theSubsystem;
        this.setDomain( subsystem.getDomain() );
    }
    
    /** Getter for property attributes.
     * @return Value of property attributes.
     *
     */
    public java.util.HashMap getAttributes() {
        return attributes;
    }
    
    /** Setter for property attributes.
     * @param attributes New value of property attributes.
     *
     */
    public void setAttributes(java.util.HashMap attributes) {
        this.attributes = attributes;
    }
    
    /** 
     * Add the specified attribute to this class
     * 
     * @param theAttribute to add
     * @throws LemException if the supplied attribute name is not unique or is unspecified
     */
    public void add( Attribute theAttribute ) throws LemException {
        
        String attributeName = theAttribute.getName();
        if ( attributeName == null || attributeName.equals( "" ) ) {
            throw new LemException( "empty attribute name" );
        }
        
        if ( null != attributes.get( attributeName ) ) {
            throw new LemException( "Duplicate attribute definition. '" + attributeName + "' already exists in class '" + name + "'"  );
        }
        
        // all OK, so add it
        
        attributes.put( attributeName, theAttribute );
    }
    
    /**
     * Return the specified attribute
     *
     * @param name of attribute to be return
     * @return the required attribute or null if it does not exist
     */
    public Attribute getAttribute( String name ) {
        return (Attribute) attributes.get( name );
    }
    
    /** Check the DomainSpecificAttributes to ensure they have been declared
     *
     * @throws LemException if undeclared domain attributes are referenced
     */
    protected void checkAttributes() throws LemException {
        
        for ( Iterator it = attributes.values().iterator(); it.hasNext(); ) {
            Attribute attribute = (Attribute) it.next();
            
            DataType type = attribute.getType();
            if ( type instanceof DomainSpecificDataType ) { 
                DomainSpecificDataType dst = domain.findTypeByName( type.getName() );
                attribute.setType( dst );
            }
        }
    }

    /**
     * Return the StateMachine associated with this Class.
     *
     * @return the StateMachine associated with this Class. May be null if the
     * class is inactive.
     */
    public StateMachine getStateMachine() {
        return stateMachine;
    }

    /**
     * Make this an active class by setting the StateMachine. The supplied StateMachine
     * will provide the behaviour of this class.
     *
     * @param aStateMachine to represent the behaviour of this Class. 
     */
    public void setStateMachine(StateMachine aStateMachine) {
        this.stateMachine=aStateMachine;
        aStateMachine.setDomainClass( this );
    }
    
    /**
     * Return true if this is an active class otherwise false. A class is regarded as
     * Active if it has behaviour (defined by a StateMachine).
     *
     * @return true if this is an active class otherwise false
     */
    public boolean isActive() {
        return ( stateMachine != null );
    }
    
    /**
     * Return an array of the events define in this class. 
     *
     * This method will return on;y public events declared in the class body. It will
     * not include privately scoped self-directed events declared in the behaviour block.
     * 
     * @return an array of the public events in this class
     */
    public Event [] getEvents() {
        
        Event [] result = new Event[ events.size() ];
        
        int i = 0;
        for ( Iterator it = events.values().iterator(); it.hasNext()  ; i++ )
            result[ i ] = (Event) it.next();
            
        return result;
    }
    
    /**
     * Find and return an a event given the event name. All known events (including those
     * declared is superclasses are searched)
     * 
     * @param eventName the name of the event to return
     * @return the named event or null if the event is not know to the class
     * @todo search the superclasses
     */
    public Event getEvent( String eventName ) {
        
        return (Event) events.get( eventName );
        
    }
    
    /**
     * Add a new event to this class
     *
     * @param anEvent to be added to this class
     */
    public void add( Event anEvent ) {
        events.put( anEvent.getName(), anEvent );
    }
    
    /**
     * Add a GeneralisationRole to this class
     *
     * @param aGeneralisationRole to be added
     */
    public void add( GeneralisationRole aGeneralisationRole ) {
        generalisationRoles.put( aGeneralisationRole, aGeneralisationRole );
    }
    
    /**
     * Return the "abstract" status of this class. A class is abstract if 
     * it particpates as a superclass in any generalisation heirarchy.
     *
     * @return true if this is a superclass in one or more generalisations
     */
    
    public boolean isAbstract() {
        
        for ( Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
            GeneralisationRole role = (GeneralisationRole) it.next();
            if ( role instanceof SuperclassRole )
                return true;
        }
        
        return false;
    }
    
    /** 
     * Return a collection of all attributes defined for this class. These include
     * attributes defined in this class and those defined in the superclasses of 
     * relationships in which the class participates as a sub class.
     *
     * @return a collection of all attributes defined for this class. The returned
     * HashMap contains values which are Attribute instances. Each key consists of the 
     * attributes class name and attribute name separated by a dot.
     */
    public HashMap getAllAttributes() {
        
        HashMap result = new HashMap();
        
        // add the local attributes
        
        for (Iterator it = attributes.values().iterator(); it.hasNext(); ) {
            Attribute att = (Attribute) it.next();
            result.put( this.getName() + "." + att.getName(), att );
        }
        
        // now add the attributes defined in the superclass of each generalisation
        
        for (Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
            GeneralisationRole role = (GeneralisationRole) it.next();
            if ( role instanceof SubclassRole ) {
                metamodel.Class superClass = role.getGeneralisation().getSuperClassRole().getParticipant();
                HashMap superclassAttributes = superClass.getAllAttributes();
                result.putAll( superclassAttributes );
            }
        }
        
        return result;
       
    }
    
    /**
     * Return a collection of all generalisations in which this class participates. These include
     * the generalisations in which this class directly participates as well as all those
     * involving this class's parents.
     *
     * * @return a collection of all generalisations in which this class participates. The returned
     * HashMap contains values which are Generalisation instances. Each key consists of the 
     * Generalisation name.
     */
    public HashMap getAllGeneralisations() {
        
        HashMap result = new HashMap();
        
        // add the local generalisation
        
        for (Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
            GeneralisationRole role = (GeneralisationRole) it.next();
            Generalisation g = role.getGeneralisation();
            result.put( g.getName(), g );
        }
        
        // now add the generalisations involving the superclass of each generalisation
        
        for (Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
            GeneralisationRole role = (GeneralisationRole) it.next();
            if ( role instanceof SubclassRole ) {
                metamodel.Class superClass = role.getGeneralisation().getSuperClassRole().getParticipant();
                HashMap superclassGeneralisations = superClass.getAllGeneralisations();
                result.putAll( superclassGeneralisations );
            }
        }
        
        return result;
       
    }
    
    
    /**
     * Create and return a new Identifier for this class
     *
     * @return A new Identifier for this class
     */
    public Identifier createNewIdentifier() {
        
        Identifier id = new Identifier();
        
        identifiers.add( id );
        id.setNumber( identifiers.size() );
        id.setDomainClass( this );
        
        return id;
    }
    
    /**
     * Return an ArrayList containing the Identifiers of this class
     *
     * @return an ArrayList containing the Identifiers of this class
     */
    public ArrayList getIdentifiers() {
        return identifiers;
    }
    
    /**
     * Return the first duplicate of the supplied identifier in this class.
     * This method is used during model construction to verify that no
     * identifiers are duplicated within the class.
     *
     * @param anIdentifier to be tested
     * @return the first duplicate of the supplied identifier 
     */
    public Identifier getFirstDuplicate( Identifier anIdentifier ) {
        
        
        int n = identifiers.size();
        int j = identifiers.indexOf( anIdentifier );
        
        if ( j < 0 )
            return null;
        
        Identifier id = null;
        for ( int i = 0; i < n; i++ ) {
            if ( i == j )
                continue;
            id = (Identifier) identifiers.get( i );
            if ( anIdentifier.equals( id )) 
                return id;
        }
        
        // no duplicates found
        
        return null;
    }
    
    /** 
     * Return a HashMap of all the generalisation roles for which this class
     * is a direct participant.
     *
     * @return the the generalisation roles for which this class
     * is a direct participant
     */
    public HashMap getGeneralisationRoles() {
        return generalisationRoles;
    }
    
    /**
     * Return a HashMap containing all generalisations in which this class directly participates. 
     *
     * @return a HashMap containing all generalisations in which this class directly participates
     * Each key consists of the Generalisation name.
     */
    public HashMap getGeneralisations() {
        
        HashMap result = new HashMap();
        
        // add the local generalisation
        
        for (Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
            GeneralisationRole role = (GeneralisationRole) it.next();
            Generalisation g = role.getGeneralisation();
            result.put( g.getName(), g );
        }
                
        return result;
       
    }
    
    /**
     * Returns all of the Generalisations in which this class participates
     * directly as a superclass.
     *
     * @returns an ArrayList containing all generalisations in which 
     * this class directly participates as a superclass
     */
    public HashMap getSuperclassParticipation()  {
        
        HashMap result = new HashMap();
        
        // add the local generalisation
        
        for (Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
            GeneralisationRole role = (GeneralisationRole) it.next();
            if ( role instanceof SuperclassRole ) {
                Generalisation g = role.getGeneralisation();
                result.put( g.getName(), g );
            }
        }
                
        return result;
       
    }

    /**
     * Returns all of the Generalisations in which this class participates
     * directly as a subclass.
     *
     * @returns an ArrayList containing all generalisations in which 
     * this class directly participates as a subclass
     */
    public HashMap getSubclassParticipation()  {
        
        HashMap result = new HashMap();
        
        // add the local generalisation
        
        for (Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
            GeneralisationRole role = (GeneralisationRole) it.next();
            if ( role instanceof SubclassRole ) {
                Generalisation g = role.getGeneralisation();
                result.put( g.getName(), g );
            }
        }
                
        return result;
       
    }
}

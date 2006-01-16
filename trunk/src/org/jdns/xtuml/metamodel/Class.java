/*
 * Class.java
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
 * Class.java
 *
 * Created on December 6, 2003, 4:43 PM
 */

package org.jdns.xtuml.metamodel;
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
	private List identifiers = new ArrayList();
	
	/** store the Association if this is an AssociationClass */
	private Association association = null;
	
	/** the Subsystem to which this class belongs */
	private Subsystem subsystem = null;
	
	/** attributes of this class */
	private Map attributes = new HashMap();
	
	/** generalisation roles in which this class participates */
	private Map generalisationRoles = new HashMap();
	
	/** Associations in which this class participates */
	private Map associations = new HashMap();
	
	/**
	 * The events to which this class responds.
	 * This excludes non-public (self directed) events delared in the LEM "behaviour" block.
	 * This attribute supports metamodel relationship R901.
	 */
	private Map events = new HashMap();
	
	/**
	 * The state machine associated with this class [R501]
	 */
	private StateMachine stateMachine = null;
	
	/**
	 * a list store association's names which have a assciation class
	 */
	private List list = new ArrayList();
	
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
	public Map getAttributes() {
		return attributes;
	}
	
	/** Setter for property attributes.
	 * @param attributes New value of property attributes.
	 *
	 */
	public void setAttributes(Map attributes) {
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
	public Collection getEvents() {
		return events.values();
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
	 * Add the given Association to this class. This class is considered to
	 * participate somehow in the Association.
	 *
	 * @param a the Association to be added
	 */
	public void add( Association a ) {
		associations.put( a.getName(), a );
	}
	
	/**
	 * Gets the Associations in which this class participates.
	 *
	 * @return the Map of association name to Associations
	 */
	public Map getAssociations() {
		return associations;
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
	 * Collection contains values which are Attribute instances. Each key consists of the
	 * attributes class name and attribute name separated by a dot.
	 */
	public Collection getAllAttributes() {
		
//        HashMap result = new HashMap();
//
//        // add the local attributes
//
//        for (Iterator it = attributes.values().iterator(); it.hasNext(); ) {
//            Attribute att = (Attribute) it.next();
//            result.put( this.getName() + "." + att.getName(), att );
//        }
//
//        // now add the attributes defined in the superclass of each generalisation
//
//        for (Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
//            GeneralisationRole role = (GeneralisationRole) it.next();
//            if ( role instanceof SubclassRole ) {
//                metamodel.Class superClass = role.getGeneralisation().getSuperClassRole().getParticipant();
//                HashMap superclassAttributes = superClass.getAllAttributes();
//                result.putAll( superclassAttributes );
//            }
//        }
		
		return attributes.values() ;
		
	}
	
	/**
	 * Return a collection of all generalisations in which this class participates as a subclass. These include
	 * the generalisations in which this class directly participates as well as all those
	 * involving this class's parents.
	 * @return all generalisations in which this class participates, including participation by parent and grandparent classes
	 * @todo This method is misnamed: it returns only Generalisations in which this class participates as a subclass
	 */
	public Map getAllGeneralisations() {
		
		HashMap result = new HashMap();
		
		// add the local generalisation
		
		for (Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
			GeneralisationRole role = (GeneralisationRole) it.next();
			if( role instanceof SuperclassRole ) continue;
			Generalisation g = role.getGeneralisation();
			result.put( g.getName(), g );
		}
		
		// now add the generalisations involving the superclass of each generalisation
		
		for (Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
			GeneralisationRole role = (GeneralisationRole) it.next();
			if ( role instanceof SubclassRole ) {
				org.jdns.xtuml.metamodel.Class superClass = role.getGeneralisation().getSuperClassRole().getParticipant();
				Map superclassGeneralisations = superClass.getAllGeneralisations();
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
		
		Identifier ident = new Identifier();
		
		identifiers.add( ident );
		ident.setNumber( identifiers.size() );
		ident.setDomainClass( this );
		
		return ident;
	}
	
	/**
	 * Return an ArrayList containing the Identifiers of this class
	 *
	 * @return an ArrayList containing the Identifiers of this class
	 */
	public List getIdentifiers() {
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
		
		Identifier ident = null;
		for ( int i = 0; i < n; i++ ) {
			if ( i == j )
				continue;
			ident = (Identifier) identifiers.get( i );
			if ( anIdentifier.equals( ident ))
				return ident;
		}
		
		// no duplicates found
		
		return null;
	}
	
	/**
	 * Return a Map of all the generalisation roles for which this class
	 * is a direct participant.
	 *
	 * @return the the generalisation roles for which this class
	 * is a direct participant
	 */
	public Map getGeneralisationRoles() {
		return generalisationRoles;
	}
	
	/**
	 * Return a Map containing all generalisations in which this class directly participates.
	 *
	 * @return a Map containing all generalisations in which this class directly participates
	 * Each key consists of the Generalisation name.
	 */
	public Map getGeneralisations() {
		
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
	 * @return an ArrayList containing all generalisations in which
	 * this class directly participates as a superclass
	 */
	public Map getSuperclassParticipation()  {
		
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
	 * @return an ArrayList containing all generalisations in which
	 * this class directly participates as a subclass
	 */
	public Map getSubclassParticipation() {
		
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
	
	
	/**
	 * Return UMLGraph string  of this class, including relationships and attributes
	 *
	 * @param isSelected a flag to indicate whether this class is be selected
	 * @Return UMLGraph string  of this class, including relationships and attributes
	 */
	public String dumpUMLGraph(boolean isSelected) {
		
		StringBuffer strBuf = new StringBuffer( );
		Collection associationList;
		
		// for all non-AssociationClasses
		if (!associations.isEmpty() || !generalisationRoles.isEmpty()) {
			// append ParticipatingClass option
			strBuf.append("/**\n");
			
			// set color for selected class
			if (isSelected)
				strBuf.append(" *  @opt nodefillcolor \"#FFFF99\"\n");
			
			// append associations for a class
			for ( Iterator it = associations.values().iterator(); it.hasNext(); ) {
				Association asso = (Association) it.next();
				// if this association have not be printed
				if (org.jdns.xtuml.verifier.ClassWriter.getAssociationList().contains(asso)) {
					// decide which side Multiplicity belongs to
					if (this.name == asso.getPassivePerspective().
							getAttachedClassRole().getParticipant().getName()) {
						strBuf.append(" *  @assoc " +
								asso.getActivePerspective().getMultiplicity().getSymbolic() + " " +
								asso.getName() + " " +
								asso.getPassivePerspective().getMultiplicity().getSymbolic() + " ");
					} else {
						strBuf.append(" *  @assoc " +
								asso.getPassivePerspective().getMultiplicity().getSymbolic() + " " +
								asso.getName() + " " +
								asso.getActivePerspective().getMultiplicity().getSymbolic() + " ");
					}
					// append the name of other participant in this association
					if (this.name == asso.getParticipants()[0].getName()) {
						strBuf.append(asso.getParticipants()[1].getName() + "\n");
					} else {
						strBuf.append(asso.getParticipants()[0].getName() + "\n");
					}
					
					// if this association contains Asscociation Class
					if (asso.getAssociationClassRole()!=null)  {
						list.add(asso);
					}
					org.jdns.xtuml.verifier.ClassWriter.removeAssociation(asso);
				}
			} //end for
			
			//append generalisations for a class
			for ( Iterator it = generalisationRoles.values().iterator(); it.hasNext(); ) {
				GeneralisationRole generalisationRole = (GeneralisationRole) it.next();
				if (this.name != generalisationRole.getGeneralisation().getSuperclass().getName())
					strBuf.append(" *  @extends " + generalisationRole.getGeneralisation().getSuperclass().getName() + "\n");
			}
			strBuf.append(" */\n");
			
			
			// append ParticipantingClass
			strBuf.append("class " + this.name + " {\n");
			// append atributes
			for ( Iterator it = attributes.values().iterator(); it.hasNext(); ) {
				Attribute attribute = (Attribute) it.next();
				strBuf.append("   " + attribute.getType().getName() +  " " + attribute.getName() + ";\n");
			}
			strBuf.append("}\n\n");
		} // end if
		
		// UMLGraph code for all AssociationClasses in selected domain is appended
		// after code for all ParticipantingClasses have been generated.
		
		// Since all associations contain AssociationClass have already be stored in
		// variable list, AssociationClasses can be find easily.
		for (Iterator lit = list.listIterator();lit.hasNext();)  {
			// get association
			Association asso = (Association) lit.next();
			// get AssociationClass from association
			Class associationClass = asso.getAssociationClassRole().getAssociationClass();
			// append  AssociationClass option
			strBuf.append("/**\n");
			// set color for this class
			if (org.jdns.xtuml.verifier.ClassWriter.getSelectedClassList().contains(associationClass))
				strBuf.append(" *  @opt nodefillcolor \"#FFFF99\"\n");
			// add name of the association that it belongs to
			strBuf.append(" *  @tagvalue " + "Association " +  asso.getName() + "\n*/\n" );
			// add the name of the AssociationClass
			strBuf.append("class " + associationClass.getName() + " {\n");
			
			// append atributes
			for ( Iterator it = associationClass.attributes.values().iterator(); it.hasNext(); ) {
				Attribute attribute = (Attribute) it.next();
				strBuf.append("   " + attribute.getType().getName() +  " " + attribute.getName() + ";\n");
			}
			strBuf.append("}\n\n");
			
		} // end for
		list.clear();
		return strBuf.toString();
	}
	
}

/*
 * BuilderPass1.java
 *
 * Created on February 1, 2004, 2:59 AM
 */

package verifier;
import metamodel.*;
import parser.*;

/**
 * Populates the basic object graph representing the subject model and builds a map between
 * the parse tree (returned by the LemParser) and the metamodel instances. This map is used in later passes of the
 * parse tree.
 *
 * This class implements the Visitor pattern. Each node in the parse tree is "visited" by executing the
 * corresponding <emph>visit</emph> method in this class.
 *
 * @author  smr
 */
public class BuilderPass1 extends Visitor {
    
    /** Creates a new instance of BuilderPass1 */
    public BuilderPass1() {
    }

    /**
     * Returns the identifier represented by the given Node.
     *
     * @param identifierNode the node which represents the identifier. This
     * parameter must be of type LEMIdentifier
     *
     * @return the String identifier represented by the Node
     */
    private String getIdentifier( Node identifierNode ) throws LemException {
	return (String)(visit( (LEMIdentifier)identifierNode, null ));
    }

    /**
     * Returns the end identifier represented by the given Node.
     *
     * @param endIdentifierNode the node which represents the end identifier. This
     * parameter must be of type LEMEndIdentifier
     *
     * @return the String end identifier represented by the Node
     */
    private String getEndIdentifier( Node identifierNode ) throws LemException {
	if( identifierNode.jjtGetNumChildren() == 0 ) {
	    return null;
	}

	return (String)(visit( (LEMIdentifier)identifierNode.jjtGetChild( 0 ), null ));
    }


    public Object visit(LEMModelDeclaration node, Object data) throws LemException {
	Model m = new Model();

        super.visit( node, m );

	String name = getIdentifier(node.jjtGetChild( 0 ));
	String endName = getEndIdentifier( node.jjtGetChild( node.jjtGetNumChildren() - 1 ));
	checkIdentity( name, endName, node.getLastToken() );
	m.setName( name );

	return m;
    }
    /**
     * Returns a bridge when given a LEMBridgeDeclaration
     * Adds a bridge to the model
     *
     * @author James Greenhalgh u3964614
     * @throws LemRuntimeException if invalid parameters are passed
     * @return a bridge
     */
    public Object visit(LEMBridgeDeclaration node, Object data) throws LemException {
        Bridge b = new Bridge();
        super.visit( node, b );
        String bridgeName = getIdentifier( node.jjtGetChild( 0 ));
        String bridgeEndName = getEndIdentifier( node.jjtGetChild( 2));
        checkIdentity( bridgeName, bridgeEndName, node.getLastToken());
        b.setId( bridgeName );
        Model m = (Model)data;
        m.add(b);
        
        return b;
    }
    public Object visit(LEMIdentifier node, Object data) throws LemException {
    	return node.getFirstToken().image;
    }

    public Object visit(LEMEndIdentifier node, Object data) throws LemException {
        if( node.jjtGetNumChildren() > 0 ) {
	    return visit((LEMIdentifier)node.jjtGetChild( 0 ), null);
	}

	return null;
    }

    /**
     * Process a Domain declaration by creating a Domain instance for use by this visitor
     *
     * @todo Do we do domain uniqueness checking here?
     */
    public Object visit(LEMDomainDeclaration node, Object data) throws LemException {
        
        // create the new domain to be constructed by this visitor
        
        Domain domain = new Domain();
	String name = getIdentifier( node.jjtGetChild( 0 ));
	String endName = getEndIdentifier( node.jjtGetChild( node.jjtGetNumChildren() - 1));
	checkIdentity( name, endName, node.getLastToken() );
	domain.setName( name );

        getMapper().add( node, domain );
        super.visit( node, domain );
        
        // insert the newly constructed domain into the model
        
        Model model = (Model) data;
        model.add( domain );
        
        return domain;
        
    }
    
    /**
    * Process a Subsystem declaration by creating a Subsystem instance for use by this visitor
    *
    * @todo Do we do subsystem uniqueness checking here?
    */
    public Object visit(LEMSubSystemDeclaration node, Object data) throws LemException {
        Domain domain = (Domain) data;
        Subsystem subsystem = new Subsystem();
	String name = getIdentifier( node.jjtGetChild( 0 ));
	String endName = getEndIdentifier( node.jjtGetChild( node.jjtGetNumChildren() - 1 ));
	checkIdentity( name, endName, node.getLastToken() );
	subsystem.setName( name );

	System.err.println( "Got subsystem name '" + name + "'" );
	
        subsystem.setDomain( domain );
        getMapper().add( node, subsystem );
        super.visit( node, subsystem );

        // insert the newly constructed subsystem into the domain
        domain.add( subsystem );

        return data;

    }

    /**
     * Process a Class declaration by creating a Class instance for use by this visitor.
     *
     * A class is declared in one of two contexts, in a bare sub-system context or
     * as an assocation class. The data passed to this method is used to determine the context
     */
    public Object visit(LEMClassDeclaration node, Object data) throws LemException {
        
        Subsystem subsystem = null;
        Association association = null;
        
        
        // sort out the context
        
        if ( data instanceof Subsystem ) {
            subsystem = (Subsystem) data;
        } else {
            association = (Association) data;
            subsystem = association.getSubsystem();
        }
        
        // now create a new class as a placeholder
        
        metamodel.Class theClass = new metamodel.Class();
       	String name = getIdentifier( node.jjtGetChild( 0 ));
	String endName = getEndIdentifier( node.jjtGetChild( node.jjtGetNumChildren() - 1 ));
	checkIdentity( name, endName, node.getLastToken() );
        theClass.setName(name);
        
        getMapper().add( node, theClass );
        
        // set its subsystem to allow the child elements to get context
        
        theClass.setSubsystem( subsystem );
        
        super.visit( node, theClass );
        
        // check the uniqueness constraint
        
        Domain domain = subsystem.getDomain();
        if ( null != domain.getClass( name ) ) {
            throw new LemException(
                    "Class " + name + " is already defined.",
                    node.getFirstToken(),
                    "LEM_E_01013" );
            
        }
        
        subsystem.add( theClass );
        
        // if this is an association class, then record its presence
        
        if ( null != association ) {
            AssociationClassRole acr = new AssociationClassRole( theClass, association );
            association.setAssociationClassRole( acr );
        }
        
        
        return data;
        
    }
    
    /**
     * Process a Type declaration by creating a DomainSpecificDataType instance for use by this visitor
     */
    public Object visit(LEMTypeDeclaration node, Object data) throws LemException {
        
        // create the new domain to be constructed by this visitor
        
        DomainSpecificDataType type = new DomainSpecificDataType();
        getMapper().add( node, type );
        super.visit( node, type );
        
        // insert the newly constructed type into the domain
        
        Domain domain = (Domain) data;
        
        // check that the type is unique
        
        
        if ( null != domain.getType( type.getName() )) {
            throw new LemException(
                    "Type " + type.getName() + " is already defined",
                    node.getFirstToken(),
                    "LEM_E_01005" );
        }
        
        // add the type to the domain
        
        domain.add( type );
        
        return data;
        
    }
    
    
    /**
     * Process the identifier for a type
     */
    public Object visit(LEMTypeIdentifier node, Object data)   throws LemException {
        super.visit( node, data );
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        String name =  node.getFirstToken().image;
        type.setName( name );
        
        return data;
        
    }
    
    /**
     * Process a basic data type (also known as a CoreDataType)
     */
    public Object visit(LEMPrimitiveType node, Object data)   throws LemException {
        super.visit( node, data );
        
        // lookup the matching CoreDataType
        
        String name =  node.getFirstToken().image;
        CoreDataType coreType = CoreDataType.findByName( name );
        
        // Test for DomainSpecificDataType declaration...
        
        if ( data instanceof DomainSpecificDataType ) {
            
            DomainSpecificDataType type = (DomainSpecificDataType) data;
            type.setCoreType( coreType );
            
        } else if ( data instanceof Attribute ){
            
            Attribute attribute = (Attribute) data;
            attribute.setType( coreType );
            
        } else if ( data instanceof Parameter ){
            
            Parameter parameter = (Parameter) data;
            parameter.setType( coreType );
        
	} else if ( data instanceof LEMVariableDeclaration ) {
		/* Hack, see LEMVariableDeclaration */
        } else {
            
            // must be an error
            
            throw new LemException( "did not expect " + data.getClass().getName() + " instance associated with basic type " + name );
        }
        
        return data;
        
    }
    
    
    
    /**
     * Process an attribute declaration
     */
    public Object visit(LEMBaseAttribute node, Object data) throws LemException {
        
        // create the Attribure to be constructed by this visitor
        
        metamodel.Class theClass = (metamodel.Class) data;
        BaseAttribute attribute = new BaseAttribute();
        getMapper().add( node, attribute );
        attribute.setDomainClass( theClass );
        super.visit( node, attribute );
        
        // insert the newly constructed attribute into the class
        
        theClass.add( attribute );
        
        return data;
        
    }
    
    
    /**
     * Process an attribute declaration
     */
    public Object visit(LEMReferentialAttribute node, Object data) throws LemException {
        
        // create the Attribure to be constructed by this visitor
        
        metamodel.Class theClass = (metamodel.Class) data;
        ReferentialAttribute attribute = new ReferentialAttribute();
        getMapper().add( node, attribute );
        attribute.setDomainClass( theClass );
        super.visit( node, attribute );
        
        // insert the newly constructed attribute into the class
        
        theClass.add( attribute );
        
        return data;
        
    }
    
    
    /**
     * Process an attribute identifier
     */
    public Object visit(LEMAttributeIdentifier node, Object data)   throws LemException {
        super.visit( node, data );
        
        if( data instanceof Attribute ) {
            Attribute attribute = (Attribute) data;
            String name =  node.getFirstToken().image;
            attribute.setName( name );
        }
        
        return data;
        
    }
    
    
    /**
     * Process a DomainSpecificType. This is a reference to a previously specified
     * domain specific data type. So we create a proxy that will be replaced by the real
     * DomainSpecificDataType once its name is known.
     *
     * The resolution of the actual domain specific data type is deferred to the
     * second pass.
     *
     */
    public Object visit(LEMDomainSpecificType node, Object data)   throws LemException {
        super.visit( node, data );
        
        
        // create a proxy data type as a placeholder
        // until the Attribure/Parameter can be checked
        
        String name =  node.getFirstToken().image;
        DomainSpecificDataType type = new DomainSpecificDataType();
        type.setName( name );
        
        if ( data instanceof Attribute ) {
            
            Attribute attribute = (Attribute) data;
            attribute.setType( type );
            
        } else if ( data instanceof Parameter ) {
            
            Parameter parameter = (Parameter) data;
            parameter.setType( type );
            
        } else {
            
            // did not expect this type
            
            throw new LemException( "did not expect " + data.getClass().getName() + " instance as data associated with DomainSpecificType " + name );
        }
        
        return data;
        
    }
    
    
    /**
     * Process  Description text
     */
    public Object visit(LEMDescription node, Object data)   throws LemException {
        super.visit( node, data );
        
        DescribedEntity de = (DescribedEntity) data;
        String description =  node.getFirstToken().image;
        de.setDescription( description );
        
        return data;
        
    }
    /**
     * Process an Generalisation
     */
    public Object visit(LEMGeneralisation node, Object data) throws LemException {
        
        // create the Generalisation instance
        
        Generalisation generalisation = new Generalisation();
        getMapper().add( node, generalisation );
        Subsystem subSystem = (Subsystem) data;
        generalisation.setSubsystem( subSystem );        
        
        String startName = getIdentifier( node.jjtGetChild( 0 ));
        String endName = getEndIdentifier( node.jjtGetChild( node.jjtGetNumChildren() - 1 ));
        checkIdentity( startName, endName, node.getFirstToken() );
        
        if( !startName.matches( "R(\\d)+" )) 
            throw new LemException( "Association identifier not in correct format", node.getFirstToken(), "TODO" );

        generalisation.setName( Integer.parseInt( startName.substring( 1 )));
        
        // now visit its components
        
        super.visit( node, generalisation );
        
        // register the generalisation in the domain
        
        Domain domain = subSystem.getDomain();
        
        // check that the generalisation does not exist
        
        if ( null != domain.getRelationship( generalisation.getName() )) {
            throw new LemException(
                    "Relation " + generalisation.getName() + " is already defined",
                    node.getFirstToken(),
                    "LEM_E_01016" );
        }
        
        // add it
        
        domain.add( generalisation );
        
        
        return data;
    }
    
    
    
    /**
     * Process the superclass identifier in a Generalisation
     */
    public Object visit(LEMSuperClassIdentifier node, Object data) throws LemException {
        
        // defer processing until the next pass
        
        super.visit( node, data );
        return data;
    }
    
    
    /**
     * Process the subclass identifier in a Generalisation
     */
    public Object visit(LEMSubClassIdentifier node, Object data) throws LemException {
        
        // defer processing until the next pass
        
        super.visit( node, data );
        return data;
    }
    
    /**
     * Process an Association declaration
     */
    public Object visit(LEMAssociation node, Object data) throws LemException {
        
        Subsystem subSystem = (Subsystem) data;
        Association association = new Association();
        getMapper().add( node, association );
        association.setSubsystem( subSystem );
        
        String startName = getIdentifier( node.jjtGetChild( 0 ));
        String endName = getEndIdentifier( node.jjtGetChild( node.jjtGetNumChildren() - 1 ));
        checkIdentity( startName, endName, node.getFirstToken() );
        
        if( !startName.matches( "R(\\d)+" )) 
            throw new LemException( "Association identifier not in correct format", node.getFirstToken(), "TODO" );
        
        association.setName(Integer.parseInt( startName.substring(1) ));
        
        super.visit( node, association );
        
        // register the association in the domain
        
        Domain domain = subSystem.getDomain();
        
        // check that the association does not exist
        
        if ( null != domain.getRelationship( association.getName() )) {
            throw new LemException(
                    "Relation " + association.getName() + " is already defined",
                    node.getFirstToken(),
                    "LEM_E_01016" );
        }
        
        // add it
        
        domain.add( association );
        
        return data;
    }
    
    /**
     * Process an IdentifierDeclaration
     */
    public Object visit(LEMIdentifierDeclaration node, Object data) throws LemException {
        
        // create the Identifier to be constructed by this visitor
        
        metamodel.Class theClass = (metamodel.Class) data;
        Identifier id = theClass.createNewIdentifier();
        
        getMapper().add( node, id );
        super.visit( node, id );
        
        return data;
    }
    
    /**
     * Process an ActivePerspective
     */
    public Object visit(LEMActivePerspective node, Object data) throws LemException {
        
        // start of new ActivePerspective
        
        Association association = (Association) data;
        ActivePerspective perspective = new ActivePerspective();
        getMapper().add( node, perspective );
        association.setActivePerspective( perspective );
        
        super.visit( node, perspective );
        
        return data;
    }
    
    
    /**
     * Process an PassivePerspective
     */
    public Object visit(LEMPassivePerspective node, Object data) throws LemException {
        
        // start of new ActivePerspective
        
        Association association = (Association) data;
        PassivePerspective perspective = new PassivePerspective();
        getMapper().add( node, perspective );
        association.setPassivePerspective( perspective );
        
        super.visit( node, perspective );
        
        return data;
    }
    
    
    /**
     * Process an ActiveSubject reference. This node might be visited in the
     * context of either an Active or Passive perspective. Work on this node
     * is deferred to the second pass where all domain classes are known
     */
    public Object visit(LEMActiveSubject node, Object data) throws LemException {
        
        super.visit( node, data );
        return data;
        
    }
    
    
    /**
     * Process an ActiveObject reference. This node might be visited in the
     * context of either an Active or Passive perspective. Work on this node
     * is deferred to the second pass where all domain classes are known
     */
    public Object visit(LEMActiveObject node, Object data) throws LemException {
        
        super.visit( node, data );
        return data;
    }
    
    
    /**
     * Process a "verb clause" related to an association perspective
     */
    
    public Object visit(LEMActiveVerbClause node, Object data)  throws LemException {
        super.visit( node, data );
        
        Perspective perspective = (Perspective) data;
        String clause =  node.getFirstToken().image;
        perspective.setVerbClause( new VerbClause( clause ) );
        return data;
    }
    
    
    /**
     * Process a "verb clause" related to an association perspective
     */
    
    public Object visit(LEMPassiveVerbClause node, Object data)  throws LemException {
        super.visit( node, data );
        
        Perspective perspective = (Perspective) data;
        String clause =  node.getFirstToken().image;
        perspective.setVerbClause( new VerbClause( clause ) );
        return data;
    }
    
    
    /**
     * Process a "multiplicity" related to an association
     */
    
    public Object visit(LEMMultiplicity node, Object data)  throws LemException {
        super.visit( node, data );
        
        Perspective perspective = (Perspective) data;
        String multiplicity =  node.getFirstToken().image;
        perspective.setMultiplicity( Multiplicity.fromString( multiplicity ));
        return data;
    }
    
    
    /**
     * Check that the supplied identifiers are identical
     */
    private void checkIdentity( String name1, String name2, Token token )   throws LemException {
        if ( name2 == null ) return;
        if ( name1 != null && name1.equals(name2) ) return;
        
        throw new LemException(
                "Expecting identifier '" + name1 + "' got '" + name2 + "'",
                token,
                "LEM_E_01001" );
    }
    
    
    /**
     * Return the source refernce of the supplied node
     *
     * @param node found in the source
     * @return the source refernce of the supplied node
     */
    private SourceReference getSourceRef( SimpleNode node ) {
        
        int lineNo = node.getFirstToken().beginLine;
        int columnNo = node.getFirstToken().beginColumn;
        
        return new SourceReference( "", lineNo, columnNo );
    }
    
    
    /**
     * Process an event declaration. An event declaration may be made in the context of a class
     * (public event) or in the context of a State Machine (private or self directed event).
     */
    public Object visit(LEMEventDeclaration node, Object data) throws metamodel.LemException {
        
        metamodel.Class theClass = null;
        StateMachine stateMachine = null;
        
        if ( data instanceof metamodel.Class ) {
            theClass = (metamodel.Class) data;
        } else {
            stateMachine = (StateMachine) data;
            theClass = stateMachine.getDomainClass();
        }
        
        // create the Event to be constructed by this visitor
        
        Event event = new Event();
        getMapper().add( node, event );
        event.setDomainClass( theClass );
        event.setName( getIdentifier( node.jjtGetChild( 0 )));
        
        
        super.visit( node, event );
        
        // check constraints
        
        if ( data instanceof metamodel.Class ) {
            if ( null != theClass.getEvent( event.getName() )) {
                throw new LemException(
                        "Event " + event.getName() + " is already defined",
                        node.getFirstToken(),
                        "LEM_E_01007" );
                
            } else {
                
                // insert the newly constructed event into the class
                
                theClass.add( event );
            }
        } else {
            if ( null != stateMachine.getEvent( event.getName() )) {
                throw new LemException(
                        "Event " + event.getName() + " is already defined",
                        node.getFirstToken(),
                        "LEM_E_01007" );
                
            } else {
                
                // insert the newly constructed event into the class
                
                stateMachine.add( event );
            }
            
        }
        
        return data;
        
    }
    
    /**
     * Create a signature for the current event
     */
    public Object visit(LEMEventSignature node, Object data) throws metamodel.LemException {
        
        EventSignature eventSignature = new EventSignature();
        getMapper().add( node, eventSignature );
        
        Event event = (Event) data;
        event.setSignature( eventSignature );
        
        super.visit( node, eventSignature );
        
        return data;
    }
    
    
    /**
     * Create a new state machine to capture class behaviour
     */
    public Object visit(LEMBehaviour node, Object data) throws metamodel.LemException {
        
        metamodel.Class domainClass = (metamodel.Class) data;
        
        StateMachine stateMachine = new StateMachine();
        getMapper().add( node, stateMachine );
        domainClass.setStateMachine( stateMachine );
        
        super.visit( node, stateMachine );
        
        // add state machine to this class
        
        
        
        
        return data;
    }
    
    /**
     * Process a state declaration
     */
    public Object visit(LEMStateDeclaration node, Object data) throws metamodel.LemException {
        
        State state = null;
        
        if ( node.getFirstToken().image.equals( "deletion" ) ) {
            state = new DeletionState();
        } else {
            state = new NonDeletionState();
        }
        
        String name = getIdentifier( node.jjtGetChild(0));
        String endName = getEndIdentifier( node.jjtGetChild( node.jjtGetNumChildren() - 1 ));
        checkIdentity(name, endName, node.getLastToken());
        state.setName( name );
        
        getMapper().add( node, state );
        StateMachine stateMachine = (StateMachine) data;
        state.setStateMachine(stateMachine);
        
        // TODO: Hack until we can modify LEMDescription to be more like LEMIdentifier
        visit( (LEMDescription)node.jjtGetChild(1), state );
        
        Procedure p = (Procedure)node.jjtGetChild(3).jjtAccept( this, null );
        state.setProcedure( p );
        p.setState(state);
        
        StateSignature s = (StateSignature) node.jjtGetChild(2).jjtAccept( this, null );
        
        // add the state to the state machine
        
        if ( null != stateMachine.getState( state.getName() )) {
            throw new LemException(
                    "State " + state.getName() + " is already defined.",
                    node.getFirstToken(),
                    "LEM_E_01009" );
            
        } else {
            stateMachine.add( state );
        }
        
        return data;
    }
    
    public Object visit (LEMProcedure node, Object data) throws metamodel.LemException {
	Procedure p = new Procedure();
	getMapper().add(node, p);

        super.visit( node, p );
        
	return p;
    }

    /**
     * Process a variable declaration. Temporary hack.
     */
    public Object visit (LEMVariableDeclaration node, Object data) throws metamodel.LemException {
	    /* Hack to keep LEMPrimitiveType running (see LEMPrimitiveType) */
	    super.visit (node, node);

	    return data;
    }
    
    /**
     * Create a signature for the current state
     */
    public Object visit(LEMStateSignature node, Object data) throws metamodel.LemException {
        
        StateSignature stateSignature = new StateSignature();
        
        super.visit( node, stateSignature );
        
        return stateSignature;
    }
    
    /**
     * A parameter is always declared within the context of a Signature so
     * our task is to simply construct the parameter declaration and
     * add it to the Signature. We much ensure that the name of the parameter
     * is unique with the signature
     */
    public Object visit(LEMParameterDeclaration node, Object data) throws metamodel.LemException {
        
        Signature signature = (Signature) data;
        Parameter parameter = new Parameter();
        getMapper().add( node, parameter );
        
        super.visit( node, parameter );
        
	String name = getIdentifier(node.jjtGetChild( 0 ));
	parameter.setName(name);

        // parameter name must be unique within the signature
        
	/* todo: fix this test
        if ( null != signature.getParameter( parameter.getName() )) {
            throw new LemException(
                    "Parameter " + parameter.getName() + " is already defined.",
                    node.getFirstToken(),
                    "LEM_E_01008" );
            
        }
	*/

        signature.add( parameter );
        
        return data;
    }
    
    /**
     * Process a enumeration list associate with a domain specific data type
     */
    public Object visit(LEMEnumeratedList node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        type.setCoreType( CoreDataType.findByName( "integer" ));
        super.visit( node, data );
        
        
        return data;
    }
    
    /**
     * Process a date type associate with a domain specific data type
     */
    public Object visit(LEMDateTypeSpecification node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        type.setCoreType( CoreDataType.findByName( "date" ));
        super.visit( node, data );
        
        
        return data;
    }
    
     /**
     * Process an ObjectReferenceType associate with a domain specific data type
     */
    public Object visit(LEMObjectReferenceTypeSpecification node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        type.setCoreType( CoreDataType.findByName( "objref" ));
        super.visit( node, data );
        
        return data;
    }
    
   
    /**
     * Process a NumericType associate with a domain specific data type
     */
    public Object visit(LEMNumericTypeSpecification node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        type.setCoreType( CoreDataType.findByName( "numeric" ));
        super.visit( node, data );
        
        
        return data;
    }
    
    /**
     * Process a string type associated with a domain specific data type
     */
    public Object visit(LEMStringTypeSpecification node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        type.setCoreType( CoreDataType.findByName( "string" ));
        super.visit( node, data );
        
        
        return data;
    }
    
    /**
     * Process an arbitrary_id type associated with a domain specific data type
     */
    public Object visit(LEMArbitraryIdTypeSpecification node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        type.setCoreType( CoreDataType.findByName( "arbitrary_id" ));
        super.visit( node, data );
        
        
        return data;
    }
    
    /**
     * Add an enumeration member to a DomainSpecificDataType
     * Check to make sure the member does not already exist
     */
    public Object visit(LEMEnumerationMember node, Object data) throws metamodel.LemException {
        
        super.visit( node, data );
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        String identifier = node.getFirstToken().image;
        
        try {
            type.addIdentifier( identifier );
        } catch ( LemException e ) {
            throw new LemException(
                    "Duplicate name: " + identifier + " already exists in enumeration.",
                    node.getFirstToken(),
                    "LEM_E_01006" );
        }
        
        return data;
    }
    
    /**
     * Process a length specification in a DomainSpecificDataType
     */
    public Object visit(LEMLengthSpec node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        super.visit( node, data );
        
        String lenSpec = node.getLastToken().image;
        int len = 0;
        
        try {
            len = Integer.parseInt( lenSpec );
            type.setLength( len );
        } catch ( NumberFormatException e ) {
            throw new LemException(
                    "Integer required in length specification",
                    node.getLastToken(),
                    "LEM_E_01024" );
        }
        
        
        return data;
    }
    
    /**
     * Process a pattern specification in a DomainSpecificDataType
     */
    public Object visit(LEMPatternSpec node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        super.visit( node, data );
        
        String pattern = node.getLastToken().image;
        type.setPattern( pattern );
        
        
        return data;
    }
    
    /**
     * Process a units specification in a DomainSpecificDataType
     */
    public Object visit(LEMUnitsSpec node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        super.visit( node, data );
        
        String units = node.getLastToken().image;
        type.setUnits( units );
        
        
        return data;
    }
    
    /**
     * Process a precision specification in a DomainSpecificDataType
     */
    public Object visit(LEMPrecisionSpec node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        super.visit( node, data );
        
        String precisionSpec = node.getLastToken().image;
        double precision = 0;
        
        try {
            precision = Double.parseDouble( precisionSpec );
            type.setPrecision( precision );
        } catch ( NumberFormatException e ) {
            throw new LemException(
                    "Bad number format.",
                    node.getLastToken(),
                    "LEM_E_01025" );
        }
        
        
        return data;
    }
    
    /**
     * Process a precision specification in a DomainSpecificDataType
     */
    public Object visit(LEMRangeSpec node, Object data) throws metamodel.LemException {
        
        DomainSpecificDataType type = (DomainSpecificDataType) data;
        Range range = new Range();
        getMapper().add( node, range );
        type.setRange( range );
        
	/* TODO: need to get the result of children expressions, and
	 * do validity checking, setLowValue, setHighValue, etc.
	 */
 
	/* TODO: also, can probably do the following which has been *cut*
	 * from pass 2
	 *
        if ( ! range.isValid() ) {
            throw new LemException(
                    "Range specification is invalid",
                    node.getLastToken(),
                    "LEM_E_01028" );
        }*/
        
        super.visit( node, range );
        
        return data;
    }
}

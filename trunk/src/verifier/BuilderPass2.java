/*
 * BuilderPass2.java
 *
 * Created on September 26, 2004, 4:23 PM
 */


package verifier;
import java.util.Collection;
import java.util.Vector;
import metamodel.*;
import parser.*;

/**
 * The second pass of the parse tree adds further infomation to the metamodel object
 * graph, resolves some references and allows basic validation of the metamodel to be
 * performed.
 *
 * This class implements the Visitor pattern. Each node in the parse tree is "visited" by executing the
 * corresponding <i>visit</i> method in this class.
 *
 * @author  smr
 */
public class BuilderPass2 extends Visitor {
    
    private Model currentModel = null;
    
    /**
     * A reference to the domain in which we're currently visiting nodes
     */
    private Domain currentDomain = null;
    
    /**
     * Creates a new instance of ModelFitout
     *
     * @param aMapper from a previous BuilderPass2
     */
    public BuilderPass2( Model model, Mapper aMapper ) {
        currentModel = model;
        super.setMapper( aMapper );
    }
    
    public Object visit( LEMModelDeclaration node, Object data ) throws LemException {
        super.visit(node, currentModel);
        return null;
    }
    
    public Object visit( LEMDomainDeclaration node, Object data ) throws LemException {
        currentDomain = (Domain)(getMapper().getObject(node));
        
        super.visit(node, data);
        return data;
    }
    
    /**
     * Process an Generalisation
     */
    public Object visit(LEMGeneralisation node, Object data) throws LemException {
        
        // retrieve the generalisation from the mapper
        
        Generalisation generalisation = (Generalisation) mapper.getObject( node );
        
        // now visit its components
        
        super.visit( node, generalisation );
        
        // do something to register the generalisation in the domain
        
        
        return data;
    }
    
    public Object visit( LEMProcedure node, Object data ) throws LemException {
        Procedure p = (Procedure)getMapper().getObject(node);
        
        super.visit( node, p );
        return p;
    }
    
    public Object visit( LEMClassList node, Object data ) throws LemException {
        Vector v = new Vector();
        
        for( int i = 0; i < node.jjtGetNumChildren(); i++ ) {
            String className = getIdentifier( node.jjtGetChild(i));
            metamodel.Class c = currentDomain.getClass(className);
            
            if( c == null ) {
                throw new LemException(
                        "Class " + c + " does not exist.",
                        node.getFirstToken(),
                        "LEM_E_01017" );
            }
            
            v.add( c );
            
        }
        
        return v;
    }
    
    public Object visit( LEMObjectCreation node, Object data ) throws LemException {
        CreateAction a = new CreateAction();
        Collection c = (Collection)visit( (LEMClassList)node.jjtGetChild(0), null);
        
        
        a.setClasses(c);
        Procedure p = (Procedure)data;
        p.addAction( a );
        
        
        return a;
    }
    
    
    /**
     * Process the superclass identifier in a Generalisation
     *
     * This node can exist in the context of a generalisation or is a specialisation list
     */
    public Object visit(LEMSuperClassIdentifier node, Object data) throws LemException {
        
        super.visit( node, data );
        
        if ( data instanceof Generalisation ) {
            
            // we are processing a Generalisation, so recover it and the domain
            
            Generalisation generalisation = (Generalisation) data;
            Domain domain = generalisation.getDomain();
            
            // find the class being refered to, or create a new class
            
            String superclassName =  node.getFirstToken().image;
            metamodel.Class superclass = domain.getClass( superclassName );
            
            // now create the Generalisation role that has been defined
            
            SuperclassRole role = new SuperclassRole( superclass, generalisation );
            generalisation.setSuperClassRole( role );
        }
        
        // all done
        
        return data;
    }
    
    
    /**
     * Process the subclass identifier in a Generalisation
     */
    public Object visit(LEMSubClassIdentifier node, Object data) throws LemException {
        
        super.visit( node, data );
        
        // we are processing a Generalisation, so recover it and the domain
        
        Generalisation generalisation = (Generalisation) data;
        Domain domain = generalisation.getDomain();
        
        // find the class being refered to, or create a new class
        
        String subclassName =  node.getFirstToken().image;
        metamodel.Class subclass = domain.getClass( subclassName );
        
        // must exist
        
        if ( null == subclass ) {
            throw new LemException(
                    "Class " + subclassName + " does not exist.",
                    node.getFirstToken(),
                    "LEM_E_01015" );
        }
        
        // now create the Generalisation role that has been defined
        
        SubclassRole role = new SubclassRole( subclass, generalisation );
        generalisation.add( role );
        
        // all done
        
        return data;
    }
    
    
    /**
     * Process an ActivePerspective
     */
    public Object visit(LEMActivePerspective node, Object data) throws LemException {
        
        // recover the active perspective
        
        ActivePerspective perspective = (ActivePerspective) getMapper().getObject( node );
        
        super.visit( node, perspective );
        
        return data;
    }
    
    /**
     * Process a PassivePerspective
     */
    public Object visit(LEMPassivePerspective node, Object data) throws LemException {
        
        // recover the passive perspective
        
        PassivePerspective perspective = (PassivePerspective) getMapper().getObject( node );
        
        super.visit( node, perspective );
        
        return data;
    }
    
    /**
     * Process an ActiveSubject reference. This node might be visited in the
     * context of either an Active or Passive perspective
     */
    public Object visit(LEMActiveSubject node, Object data) throws LemException {
        
        if ( data instanceof ActivePerspective ) {
            
            // start of new ActivePerspective
            
            ActivePerspective perspective = (ActivePerspective) data;
            Association association = perspective.getAssociation();
            
            
            // get the participating class
            
            Domain domain = association.getSubsystem().getDomain();
            String name =  node.getFirstToken().image;
            
            metamodel.Class subjectClass = domain.getClass( name );
            if ( null == subjectClass ) {
                throw new LemException(
                        "Class " + subjectClass + " does not exist.",
                        node.getFirstToken(),
                        "LEM_E_01017" );
            }
            
            // create a ParticipatingClassRole to represent the classes involvement in this
            // association
            
            ParticipatingClassRole pcr = new ParticipatingClassRole( subjectClass, association );
            getMapper().add( node, pcr );
            perspective.setAttachedClassRole( pcr );
            
            // add the perspective to this association
            
            perspective.setAssociation( association );
            association.setActivePerspective( perspective );
            
        }
        
        super.visit( node, data );
        return data;
        
    }
    
    
    /**
     * Process an ActiveObject reference. This node might be visited in the
     * context of either an Active or Passive perspective
     */
    public Object visit(LEMActiveObject node, Object data) throws LemException {
        
        if ( data instanceof PassivePerspective ) {
            
            // start of new ActivePerspective
            
            PassivePerspective perspective = (PassivePerspective) data;
            Association association = perspective.getAssociation();
            
            
            // get the participating class
            
            Domain domain = association.getSubsystem().getDomain();
            String name =  node.getFirstToken().image;
            metamodel.Class objectClass = domain.getClass( name );
            if ( null == objectClass ) {
                throw new LemException(
                        "Class " + objectClass + " does not exist.",
                        node.getFirstToken(),
                        "LEM_E_01020" );
            }
            
            // create a ParticipatingClassRole to represent the classes involvement in this
            // association
            
            ParticipatingClassRole pcr = new ParticipatingClassRole( objectClass, association );
            perspective.setAttachedClassRole( pcr );
            
            // add the perspective to this association
            
            perspective.setAssociation( association );
            association.setPassivePerspective( perspective );
        }
        
        super.visit( node, data );
        return data;
    }
    
    /**
     * A parameter is always declared within the context of a Signature so
     * our task is to simply construct the parameter declaration and
     * add it to the Signature. We much ensure that the name of the parameter
     * is unique with the signature
     */
    public Object visit(LEMParameterDeclaration node, Object data) throws metamodel.LemException {
        
        Parameter parameter = (Parameter) getMapper().getObject( node );
        super.visit( node, parameter );
        
        
        
        return data;
    }
    
    
    /**
     * Process an attribute declaration
     */
    public Object visit(LEMBaseAttribute node, Object data) throws LemException {
        
        Attribute attribute = (Attribute) getMapper().getObject( node );
        super.visit( node, attribute );
        
        return data;
        
    }
    
    /**
     * Check that the named Domain Sepcific Type is actually in the domain.
     * We can now discard the proxy object.
     */
    public Object visit(LEMDomainSpecificType node, Object data)   throws LemException {
        super.visit( node, data );
        
        DomainSpecificDataType dsdt = null;
        String name = null;
        Domain domain = null;
        
        // recover the type name from the proxy
        
        if ( data instanceof Attribute ) {
            
            Attribute attribute = (Attribute) data;
            domain = attribute.getDomainClass().getDomain();
            dsdt = ( DomainSpecificDataType ) attribute.getType();
            name = dsdt.getName();
            
        } else {
            Parameter parameter = ( Parameter ) data;
            domain = parameter.getDomain();
            dsdt = ( DomainSpecificDataType ) parameter.getType();
            name = dsdt.getName();
        }
        
        // see is the real type exists in the domain
        
        DomainSpecificDataType type = domain.getType( name );
        if ( null == type ) {
            throw new LemException(
                    "Domain specific data type " + name + " does not exist.",
                    node.getFirstToken(),
                    "LEM_E_01023" );
        }
        
        // set the type and discard the proxy
        
        if ( data instanceof Attribute ) {
            
            Attribute attribute = (Attribute) data;
            attribute.setType( type );
            
        } else {
            
            Parameter parameter = ( Parameter ) data;
            parameter.setType( type );
            
        }
        
        return data;
        
    }
    
    
    
    /**
     * Process an event declaration. An event declaration may be made in the context of a class
     * (public event) or in the context of a State Machine (private or self directed event).
     */
    public Object visit(LEMEventDeclaration node, Object data) throws metamodel.LemException {
        
        // recover the event
        
        Event event = (Event) getMapper().getObject( node );
        super.visit( node, event );
        
        return data;
    }
    
    /**
     * Process an IdentifierDeclaration
     */
    public Object visit(LEMIdentifierDeclaration node, Object data) throws LemException {
        
        // retreve the Identifier to be constructed by this visitor
        
        Identifier id = (Identifier) getMapper().getObject( node );
        
        super.visit( node, id );
        
        return data;
    }
    
    /**
     * Process an IdentifyingAttribute
     */
    public Object visit(LEMIdentifyingAttribute node, Object data) throws LemException {
        
        super.visit( node, data );
        
        // this method is called in the context of an existing identifier
        
        Identifier id = (Identifier) data;
        String attributeName =  node.getFirstToken().image;
        
        // check that the attribute exists
        
        metamodel.Class domainClass = id.getDomainClass();
        Attribute attribute = domainClass.getAttribute( attributeName );
        if ( null == attribute ) {
            throw new LemException(
                    "Attribute " + attributeName + " is not defined in class " + domainClass.getName(),
                    node.getLastToken(),
                    "LEM_E_01035" );
        }
        
        // add the attribute and handle any exception
        
        try {
            id.addAttribute( attributeName );
            
        } catch ( Exception e ) {
            throw new LemException(
                    e.getMessage(),
                    node.getLastToken(),
                    "LEM_E_01036" );
        }
        
        return data;
    }
    
    public Object visit(LEMIdentifier node, Object data) {
        return node.getFirstToken().image;
    }
    
    /**
     * Returns the identifier represented by the given Node.
     *
     * @param identifierNode the node which represents the identifier. This
     * parameter must be of type LEMIdentifier
     *
     * @return the String identifier represented by the Node
     * @todo This is code duplicated from BuilderPass1. Perhaps the Visitor
     * class should implement this?
     */
    private String getIdentifier( Node identifierNode ) throws LemException {
        return (String)(visit( (LEMIdentifier)identifierNode, null ));
    }
}

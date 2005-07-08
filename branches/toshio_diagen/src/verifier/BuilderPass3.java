/*
 * BuilderPass3.java
 *
 * Created on September 26, 2004, 4:23 PM
 */


package verifier;
import metamodel.*;
import parser.*;

/**
 * The third (and final) pass of the parse tree resolves remaining references
 * and allows final validation of action language and other constructs to be
 * performed.
 *
 * This class implements the Visitor pattern. Each node in the parse tree is "visited" by executing the
 * corresponding <emph>visit</emph> method in this class.
 *
 * @author  smr
 */
public class BuilderPass3 extends Visitor {

    private Model currentModel = null;
    
    /**
     * Creates a new instance of ModelFitout
     *
     * @param aMapper from a previous BuilderPass3
     */
    public BuilderPass3( Model model, Mapper aMapper ) {
	currentModel = model;
        super.setMapper( aMapper );
    }

    public Object visit( LEMModelDeclaration node, Object data ) throws LemException {
	    super.visit(node, currentModel);
	    return null;
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
        
        if ( data instanceof PassivePerspective ) {
            
            // we are finalising a pespectve
            // get the context
            
            PassivePerspective perspective = (PassivePerspective) data;
            Association association = perspective.getAssociation();
            Domain domain = association.getDomain();
            ParticipatingClassRole pcr = perspective.getAttachedClassRole();
            
            // add this class role to the perspective
            
            String name =  node.getFirstToken().image;
            metamodel.Class subjectClass = domain.getClass( name );
            
            // make sure the named class is in the domain
            
            if ( null == subjectClass ) {
                throw new LemException(
                "Class " + subjectClass + " does not exist.",
                node.getFirstToken(),
                "LEM_E_01019" );
            }
            
            // check the the classes in the perspectives match
            
            ActivePerspective ap = association.getActivePerspective();
            metamodel.Class apClass = ap.getAttachedClassRole().getParticipant();
            if ( subjectClass != apClass ) {
                throw new LemException(
                "Expecting " + apClass.getName() + ".",
                node.getFirstToken(),
                "LEM_E_01021" );
                
            }
        }
        
        super.visit( node, data );
        return data;
        
    }
    
    
    /**
     * Process an ActiveObject reference. This node might be visited in the
     * context of either an Active or Passive perspective
     */
    public Object visit(LEMActiveObject node, Object data) throws LemException {
        
        if ( data instanceof ActivePerspective ) {
            
            // we are finalising a pespectve
            // get the context
            
            ActivePerspective perspective = (ActivePerspective) data;
            Association association = perspective.getAssociation();
            Domain domain = association.getDomain();
            ParticipatingClassRole pcr = perspective.getAttachedClassRole();
            
            // make sure the named class is in the domain
            
            String name =  node.getFirstToken().image;
            metamodel.Class objectClass = domain.getClass( name );
            
            if ( null == objectClass ) {
                throw new LemException(
                "Class " + objectClass + " does not exist.",
                node.getFirstToken(),
                "LEM_E_01018" );
            }
            
            // check the the classes in the perspectives match
            
            PassivePerspective pp = association.getPassivePerspective();
            metamodel.Class ppClass = pp.getAttachedClassRole().getParticipant();
            if ( objectClass != ppClass ) {
                throw new LemException(
                "Expecting " + ppClass.getName() + ".",
                node.getFirstToken(),
                "LEM_E_01022" );
                
            }
        }
        
        super.visit( node, data );
        return data;
        
    }
    /**
     * Process a referential attribute declaration
     */
    public Object visit(LEMReferentialAttribute node, Object data) throws LemException {
        
        ReferentialAttribute attribute = (ReferentialAttribute) getMapper().getObject( node );
        super.visit( node, attribute );
        
        return data;
        
    }
    
    /**
     * Process a referential attribute declaration
     */
    public Object visit(LEMAttributeReference node, Object data) throws LemException {
        super.visit( node, data );
        
        ReferentialAttribute attribute = (ReferentialAttribute) data;
        
        // resolved the various parts of the reference
        
        Token token = node.getFirstToken();
        String explicitPerspective = token.image;
        
        if ( explicitPerspective.equals( "active" ) || explicitPerspective.equals( "passive") ) {
            token = token.next;
        } else {
            explicitPerspective = null;
        }
        
        //
        
        Token classNameToken = token;
        String remoteClassName = token.image;
        token = token.next.next;
        Token attributeNameToken = token;
        String remoteAttributeName = token.image;
        token = token.next.next;
        String relationId =  token.image;
        
        
        // check that the association exists
        
        Domain domain = attribute.getDomainClass().getDomain();
        Relationship relation = domain.getRelationship( relationId );
        if ( null == relation ) {
            throw new LemException(
            "Relation " + relationId + " does not exist.",
            node.getLastToken(),
            "LEM_E_01029" );
        }
        
        if ( ! ( relation instanceof Association ) ) {
            throw new LemException(
            relationId + "is a " + relation.getClass().getName(),
            node.getLastToken(),
            "LEM_E_01030" );
        }
        
        Association association = (Association) relation;
        
        // check that the class exists
        
        metamodel.Class referencedClass = domain.getClass( remoteClassName );
        if ( null == referencedClass ) {
            throw new LemException(
            "Class " + remoteClassName + " does not exist.",
            classNameToken,
            "LEM_E_01031" );
        }
        
        
        
        // and the attribute is defined in that class
        
        Attribute referencedAttribute = referencedClass.getAttribute( remoteAttributeName );
        if ( null == referencedAttribute ) {
            throw new LemException(
            "Attribute " + remoteAttributeName + " does not exist in class " + remoteClassName,
            attributeNameToken,
            "LEM_E_01032" );
        }
        
        // sort out which AssociationRole is being traversed
        
        AssociationRole traversed = null;
        
        // must also be a perspective
        
        Perspective perspective = null;
        
        // firstly, if the association is reflexive, the specified perspective should be used
        
        if ( association.isReflexive() ) {
            
            if ( null == explicitPerspective ) {
                throw new LemException(
                relationId + " is reflexive and an explicit perspective (active | passive) has not been specified",
                node.getFirstToken(),
                "LEM_E_01033" );
            }
            
            if ( explicitPerspective.equals( "active" ) )
                perspective = association.getActivePerspective();
            else
                perspective = association.getPassivePerspective();
            
            traversed = perspective.getAttachedClassRole();
            
            
        } else {
            
            // so its a non-reflexive association...
            // the perspective must be the one not associated with the current class
            
            // we must not reference the current  class
            
            if ( referencedClass == attribute.getDomainClass() ) {
                throw new LemException(
                "Class " + remoteClassName + " references this class",
                classNameToken,
                "LEM_E_01038" );
            }
            
            // make sure the perspective was not explicitly defined
            
            if ( null != explicitPerspective ) {
                throw new LemException(
                "Explicit perspective is redundant. " + relationId + " is not reflexive",
                node.getFirstToken(),
                "LEM_E_01034" );
            }
            
            
            // now determine the perspective in use by choosing the perspective not
            // involving the current class
            
            perspective = association.getActivePerspective();
            if ( perspective.getDomainClass() == attribute.getDomainClass() )
                perspective = association.getPassivePerspective();
            
            // now choose the appropriate AssociationRole using the referenced class
            
            if ( referencedClass ==  perspective.getDomainClass() ) {
                traversed = perspective.getAttachedClassRole();
            } else {
                traversed = association.getAssociationClassRole();
            }
            
        }
        
        // check that we have got a valid relationship to traverse
        
        if ( traversed == null ) {
            throw new LemException(
            "Class " + remoteClassName + " is not a participant in association "+ relationId,
            node.getFirstToken(),
            "LEM_E_01037" );
        }
        
        // the perspective in use must have a multiplicity of at most 1
        
        if ( perspective.getMultiplicity().isMultivalued() ) {
            
            // report error - navigation yields multiple values
            
            String activePassive = "active";
            if ( perspective instanceof PassivePerspective )
                activePassive = "passive";
            
            throw new LemException(
            "Navigation across the " + activePassive + " perspective of association " + relationId + " (" + perspective.getVerbClause().toString() + 
            "), yields " + perspective.getMultiplicity().toString() + " values",
            node.getFirstToken(),
            "LEM_E_01040" );
        }
        
        // set the referenced attribute
        
        attribute.setReferenced( referencedAttribute );
        attribute.setTraversedPerspective( traversed );
        
        return data;
    }
    
    /**
     * Process an IdentifierDeclaration
     */
    public Object visit(LEMIdentifierDeclaration node, Object data) throws LemException {
        
        // retreve the Identifier to be constructed by this visitor
        
        Identifier id = (Identifier) getMapper().getObject( node );
        
        super.visit( node, id );
        
        // make sure that this identifier is not duplicated in this class
        
        metamodel.Class domainClass = id.getDomainClass();
        
        
        Identifier dupId = domainClass.getFirstDuplicate( id );
        
        if ( null != dupId ) {
            throw new LemException(
            "This identifier (" + id.getName() + ") is duplicated in the class by identifier: " + dupId.getName(),
            node.getFirstToken(),
            "LEM_E_01039" );
        }
        return data;
    }
    
}

/*
 * BuilderPass3.java
 *
 * Created on September 26, 2004, 4:23 PM
  *
 * Copyright (C) 2004 Steven Ring
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

package org.jdns.xtuml.verifier;
import org.jdns.xtuml.metamodel.ActivePerspective;
import org.jdns.xtuml.metamodel.Association;
import org.jdns.xtuml.metamodel.AssociationRole;
import org.jdns.xtuml.metamodel.Attribute;
import org.jdns.xtuml.metamodel.Domain;
import org.jdns.xtuml.metamodel.Identifier;
import org.jdns.xtuml.metamodel.LemException;
import org.jdns.xtuml.metamodel.Model;
import org.jdns.xtuml.metamodel.ParticipatingClassRole;
import org.jdns.xtuml.metamodel.PassivePerspective;
import org.jdns.xtuml.metamodel.Perspective;
import org.jdns.xtuml.metamodel.ReferentialAttribute;
import org.jdns.xtuml.metamodel.Relationship;
import org.jdns.xtuml.parser.LEMActiveObject;
import org.jdns.xtuml.parser.LEMActivePerspective;
import org.jdns.xtuml.parser.LEMActiveSubject;
import org.jdns.xtuml.parser.LEMAttributeReference;
import org.jdns.xtuml.parser.LEMIdentifierDeclaration;
import org.jdns.xtuml.parser.LEMModelDeclaration;
import org.jdns.xtuml.parser.LEMPassivePerspective;
import org.jdns.xtuml.parser.LEMReferentialAttribute;
import org.jdns.xtuml.parser.Token;

/**
 * The third (and final) pass of the parse tree resolves remaining references
 * and allows final validation of action language and other constructs to be
 * performed.
 *
 * This class implements the Visitor pattern. Each node in the parse tree is "visited" by executing the
 * corresponding <emph>visit</emph> method in this class.
 *
 * @author  Steven Michael Ring
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
     * context of either an Active or Passive perspective. We are only interested
     * in the ActivePerspective in order to check the symmetry of the association
     */
    public Object visit(LEMActiveSubject node, Object data) throws LemException {
        
        if ( data instanceof ActivePerspective ) {
            
            // we are finalising a pespectve
            // get the context
            
            ActivePerspective perspective = (ActivePerspective) data;
            Association association = perspective.getAssociation();
            Domain domain = association.getDomain();
            ParticipatingClassRole pcr = perspective.getAttachedClassRole();
            
            // get the details for the ActiveSubject class
            
            String name =  node.getFirstToken().image;
            org.jdns.xtuml.metamodel.Class subjectClass = domain.getClass( name );
            
            // make sure the named class is in the domain
            
            if ( null == subjectClass ) {
                throw new LemException(
                "Class " + name + " does not exist.",
                node.getFirstToken(),
                "LEM_E_01019" );
            }
            
            // check the the classes in the perspectives match
            
            PassivePerspective pp = association.getPassivePerspective();
            org.jdns.xtuml.metamodel.Class ppClass = pp.getAttachedClassRole().getParticipant();
            if ( subjectClass != ppClass ) {
                throw new LemException(
                "Expecting '" + ppClass.getName() + "' as ActiveSubject in active perspective of association " + association.getName(),
                node.getFirstToken(),
                "LEM_E_01021" );
                
            }
        }
        
        super.visit( node, data );
        return data;
        
    }
    
    /**
     * Process an ActiveObject reference. This node might be visited in the
     * context of either an Active or Passive perspective. We are only interested
     * in the PassivePerspective in order to check the symmetry of the association
     */
    public Object visit(LEMActiveObject node, Object data) throws LemException {
        
        if ( data instanceof PassivePerspective ) {
            
            // we are finalising a pespectve
            // get the context
            
            PassivePerspective perspective = (PassivePerspective) data;
            Association association = perspective.getAssociation();
            Domain domain = association.getDomain();
            ParticipatingClassRole pcr = perspective.getAttachedClassRole();
            
            // get the details for the ActiveObject class
            
            String name =  node.getFirstToken().image;
            org.jdns.xtuml.metamodel.Class objectClass = domain.getClass( name );
            
            // make sure the named class is in the domain
            
            if ( null == objectClass ) {
                throw new LemException(
                "Class " + name + " does not exist.",
                node.getFirstToken(),
                "LEM_E_01019" );
            }
            
            // check the the classes in the perspectives match
            
            ActivePerspective ap = association.getActivePerspective();
            org.jdns.xtuml.metamodel.Class apClass = ap.getAttachedClassRole().getParticipant();
            if ( objectClass != apClass ) {
                throw new LemException(
                "Expecting '" + apClass.getName() + "' as ActiveObject in passive perspective of association " + association.getName(),
                node.getFirstToken(),
                "LEM_E_01021" );
                
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
        
        org.jdns.xtuml.metamodel.Class referencedClass = domain.getClass( remoteClassName );
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
            
        }
        
        // check that we have got a valid relationship to traverse
        
        if ( perspective == null ) {
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
        attribute.setTraversedPerspective( perspective );
        
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
        
        org.jdns.xtuml.metamodel.Class domainClass = id.getDomainClass();
        
        
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

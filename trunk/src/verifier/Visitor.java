 /*
  * Code generated by src/util/generateVisitor.pl  ... DO NOT EDIT this file
 */

package verifier;
import parser.*;
/**
 * A generic visitor for the LemParser
 */

public class Visitor implements LemParserVisitor { 

        
    /** a Mapper to maintain a working map between SimpleNodes and Metamodel object */
    protected Mapper mapper = new Mapper();
    

    private void logVisit( SimpleNode node, Object data ) {
        Token t1 = node.getFirstToken(); 
        Token t2 = node.getLastToken();
        System.err.print( "Visited " + node.getClass().getName() + " token=" + t1.image ); 
        if ( t1==t2)
            System.err.println( "" );
        else
            System.err.println( " to token=" + t2.image );
        
    }


    /**
     * Get the Mapper object from this visitor
     * 
     *  the Mapper object maintained by this visitor
     */
    public Mapper getMapper() {
        return mapper;
    }



    /**
     * Set the Mapper object for this visitor
     * 
     *  the Mapper object to be maintained by this visitor
     */
    public void setMapper( Mapper aMapper) {
        mapper = aMapper;
    }
    
	  public Object visit(SimpleNode node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMModelDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMEndIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMModelBody node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMBridgeDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMBridgeBody node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMBridgeIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMBridgeEndIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMIdentifierDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMIdentifyingAttribute node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAttributeDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMBaseAttribute node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMReferentialAttribute node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMDerivedAttribute node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAttributeCalculationProcedure node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMReferredClass node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMReferredAttribute node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMReferredAssociation node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAttributeReference node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMLiteral node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAttributeIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMDomainSpecificType node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMDomainDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMTypeDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMPrimitiveType node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMTypeSpecification node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMStringTypeSpecification node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMDateTypeSpecification node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMBooleanTypeSpecification node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMArbitraryIdTypeSpecification node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMLengthSpec node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMPatternSpec node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMNumericTypeSpecification node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMUnitsSpec node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMRangeSpec node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMPrecisionSpec node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMPrecisionValue node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMEnumeratedList node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMEnumerationMember node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMTypeIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMSubSystemDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMDescription node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMSubSystemBody node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMClassDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMSpecialisationList node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMRelationship node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAssociation node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMActivePerspective node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMPassivePerspective node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMActiveSubject node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMActiveObject node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMActiveVerbClause node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMPassiveVerbClause node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMVerbClause node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMClassBody node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMBehaviour node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMSelfEventDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMGeneralisation node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMGeneralisationBody node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMRelationshipIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMRelationshipEndIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMSuperClassIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMSubClassIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAssociationClassDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMMultiplicity node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMEventDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMEventIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMStateSignature node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMEventSignature node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMSignature node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMParameterDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMParameterIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMStateDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMStateIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMStateEndIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMTransitionDeclaration node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMFromStateIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMToStateIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMTransitionEventIdentifier node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMProcedure node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMActionBlock node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAction node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMObjectReference node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAttributeInstanceReference node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMObjectCreation node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMObjectDeletion node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMRValue node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMLValue node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAttributeAssignment node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMIfStatement node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMElseIfPart node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMElsePart node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMWhileStatement node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMExpression node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMRelation node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMRelational node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMSimpleExpression node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMUnary node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMAdding node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMTerm node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMMultiplying node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMFactor node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMPrimary node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
	  public Object visit(LEMParenthesizedPrimary node, Object data) throws metamodel.LemException { 
		logVisit( node, data );
		node.childrenAccept( this, data );
		return data;
	}
}

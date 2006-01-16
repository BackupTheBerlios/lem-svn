/*
 * BuilderPass2.java
 *
 * Copyright (C) 2004 Steven Ring
 * Copyright (C) 2005 James Ring
 * Copyright (C) 2005 Nick Piggin
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

package verifier;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Vector;
import org.jdns.xtuml.metamodel.Action;
import org.jdns.xtuml.metamodel.ActionBlock;
import org.jdns.xtuml.metamodel.ActivePerspective;
import org.jdns.xtuml.metamodel.AssignmentAction;
import org.jdns.xtuml.metamodel.Association;
import org.jdns.xtuml.metamodel.Attribute;
import org.jdns.xtuml.metamodel.BinaryOperation;
import org.jdns.xtuml.metamodel.BooleanType;
import org.jdns.xtuml.metamodel.BreakStatement;
import org.jdns.xtuml.metamodel.CancelAction;
import org.jdns.xtuml.metamodel.ConditionalAlternative;
import org.jdns.xtuml.metamodel.CoreDataType;
import org.jdns.xtuml.metamodel.CreateAction;
import org.jdns.xtuml.metamodel.CreationTransaction;
import org.jdns.xtuml.metamodel.DataType;
import org.jdns.xtuml.metamodel.DeleteAction;
import org.jdns.xtuml.metamodel.Domain;
import org.jdns.xtuml.metamodel.DomainSpecificDataType;
import org.jdns.xtuml.metamodel.Event;
import org.jdns.xtuml.metamodel.Expression;
import org.jdns.xtuml.metamodel.ForStatement;
import org.jdns.xtuml.metamodel.Generalisation;
import org.jdns.xtuml.metamodel.GenerateAction;
import org.jdns.xtuml.metamodel.Identifier;
import org.jdns.xtuml.metamodel.IfStatement;
import org.jdns.xtuml.metamodel.InitialisingTransition;
import org.jdns.xtuml.metamodel.LemException;
import org.jdns.xtuml.metamodel.Literal;
import org.jdns.xtuml.metamodel.Model;
import org.jdns.xtuml.metamodel.NonDeletionState;
import org.jdns.xtuml.metamodel.NumericType;
import org.jdns.xtuml.metamodel.ObjectReferenceType;
import org.jdns.xtuml.metamodel.Parameter;
import org.jdns.xtuml.metamodel.PassivePerspective;
import org.jdns.xtuml.metamodel.PrintAction;
import org.jdns.xtuml.metamodel.Procedure;
import org.jdns.xtuml.metamodel.RelateAction;
import org.jdns.xtuml.metamodel.RelatedToOperation;
import org.jdns.xtuml.metamodel.Relationship;
import org.jdns.xtuml.metamodel.ReturnAction;
import org.jdns.xtuml.metamodel.Scenario;
import org.jdns.xtuml.metamodel.SelectExpression;
import org.jdns.xtuml.metamodel.State;
import org.jdns.xtuml.metamodel.StateMachine;
import org.jdns.xtuml.metamodel.StateTransition;
import org.jdns.xtuml.metamodel.StringType;
import org.jdns.xtuml.metamodel.SubclassRole;
import org.jdns.xtuml.metamodel.SuperclassRole;
import org.jdns.xtuml.metamodel.Transition;
import org.jdns.xtuml.metamodel.UnaryOperation;
import org.jdns.xtuml.metamodel.UnrelateAction;
import org.jdns.xtuml.metamodel.VariableDeclaration;
import org.jdns.xtuml.metamodel.VariableReference;
import org.jdns.xtuml.metamodel.WhileStatement;
import org.jdns.xtuml.parser.LEMAction;
import org.jdns.xtuml.parser.LEMActionBlock;
import org.jdns.xtuml.parser.LEMActionList;
import org.jdns.xtuml.parser.LEMActiveObject;
import org.jdns.xtuml.parser.LEMActivePerspective;
import org.jdns.xtuml.parser.LEMActiveSubject;
import org.jdns.xtuml.parser.LEMAdding;
import org.jdns.xtuml.parser.LEMAndRelation;
import org.jdns.xtuml.parser.LEMAttributeAssignment;
import org.jdns.xtuml.parser.LEMBaseAttribute;
import org.jdns.xtuml.parser.LEMBehaviour;
import org.jdns.xtuml.parser.LEMBreak;
import org.jdns.xtuml.parser.LEMClassDeclaration;
import org.jdns.xtuml.parser.LEMClassList;
import org.jdns.xtuml.parser.LEMCreationTransaction;
import org.jdns.xtuml.parser.LEMDescription;
import org.jdns.xtuml.parser.LEMDomainDeclaration;
import org.jdns.xtuml.parser.LEMDomainSpecificType;
import org.jdns.xtuml.parser.LEMElseIfPart;
import org.jdns.xtuml.parser.LEMElsePart;
import org.jdns.xtuml.parser.LEMEventCancel;
import org.jdns.xtuml.parser.LEMEventDeclaration;
import org.jdns.xtuml.parser.LEMEventGeneration;
import org.jdns.xtuml.parser.LEMExpression;
import org.jdns.xtuml.parser.LEMFactor;
import org.jdns.xtuml.parser.LEMForStatement;
import org.jdns.xtuml.parser.LEMGeneralisation;
import org.jdns.xtuml.parser.LEMIdentifier;
import org.jdns.xtuml.parser.LEMIdentifierDeclaration;
import org.jdns.xtuml.parser.LEMIdentifyingAttribute;
import org.jdns.xtuml.parser.LEMIfStatement;
import org.jdns.xtuml.parser.LEMLValue;
import org.jdns.xtuml.parser.LEMLinkCreation;
import org.jdns.xtuml.parser.LEMLinkDeletion;
import org.jdns.xtuml.parser.LEMLinkObjectCreation;
import org.jdns.xtuml.parser.LEMLiteral;
import org.jdns.xtuml.parser.LEMLogicalAnd;
import org.jdns.xtuml.parser.LEMLogicalOr;
import org.jdns.xtuml.parser.LEMModelDeclaration;
import org.jdns.xtuml.parser.LEMMultiplying;
import org.jdns.xtuml.parser.LEMObjectCreation;
import org.jdns.xtuml.parser.LEMObjectDeletion;
import org.jdns.xtuml.parser.LEMObjectReference;
import org.jdns.xtuml.parser.LEMOptionalParameterList;
import org.jdns.xtuml.parser.LEMParameterDeclaration;
import org.jdns.xtuml.parser.LEMParameterList;
import org.jdns.xtuml.parser.LEMParenthesizedPrimary;
import org.jdns.xtuml.parser.LEMPassivePerspective;
import org.jdns.xtuml.parser.LEMPrimary;
import org.jdns.xtuml.parser.LEMPrimitiveType;
import org.jdns.xtuml.parser.LEMPrintAction;
import org.jdns.xtuml.parser.LEMProcedure;
import org.jdns.xtuml.parser.LEMRelatedTo;
import org.jdns.xtuml.parser.LEMRelation;
import org.jdns.xtuml.parser.LEMRelational;
import org.jdns.xtuml.parser.LEMRelationshipReference;
import org.jdns.xtuml.parser.LEMReturn;
import org.jdns.xtuml.parser.LEMScenarioDeclaration;
import org.jdns.xtuml.parser.LEMSelectMultiplicity;
import org.jdns.xtuml.parser.LEMSelectStatement;
import org.jdns.xtuml.parser.LEMSimpleExpression;
import org.jdns.xtuml.parser.LEMSubClassIdentifier;
import org.jdns.xtuml.parser.LEMSuperClassIdentifier;
import org.jdns.xtuml.parser.LEMTerm;
import org.jdns.xtuml.parser.LEMTransitionDeclaration;
import org.jdns.xtuml.parser.LEMType;
import org.jdns.xtuml.parser.LEMUnary;
import org.jdns.xtuml.parser.LEMUnaryOperator;
import org.jdns.xtuml.parser.LEMVariableDeclaration;
import org.jdns.xtuml.parser.LEMVariableDeclarationList;
import org.jdns.xtuml.parser.LEMVariableReference;
import org.jdns.xtuml.parser.LEMWhereClause;
import org.jdns.xtuml.parser.LEMWhileStatement;
import org.jdns.xtuml.parser.LEMWithDelay;
import org.jdns.xtuml.parser.LemParserConstants;
import org.jdns.xtuml.parser.Node;
import org.jdns.xtuml.parser.SimpleNode;

/**
 * The second pass of the parse tree adds further infomation to the org.jdns.xtuml.metamodel object
 * graph, resolves some references and allows basic validation of the org.jdns.xtuml.metamodel to be
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
	 * A reference to the class in which we're currently visiting nodes
	 */
	private org.jdns.xtuml.metamodel.Class currentClass = null;

	/**
	  * A reference to the action block in which we're currently visiting nodes
	  */
	private ActionBlock currentBlock = null;

	/**
	 * flag to indicate if we are in a scenario or a procedure
	 */
	private boolean inScenario = false;

	/**
	 * flag to indicate if we are in a select statement
	 */
	private boolean inSelect = false;

	/**
	 * Creates a new instance of ModelFitout
	 *
	 * @param aMapper from a previous BuilderPass2
	 */
	public BuilderPass2( Model model, Mapper aMapper ) {
		currentModel = model;
		super.setMapper( aMapper );
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMModelDeclaration node, Object data ) throws LemException {
		super.visit( node, currentModel );
		return null;
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMDomainDeclaration node, Object data ) throws LemException {
		currentDomain = ( Domain ) ( getMapper().getObject( node ) );

		// I don't believe that any children of DomainDeclaration are actually
		// handled here (in BuilderPass2), so removing the next line shouldn't
		// have any effect
		// super.visit(node, data);

		for ( int i = 2; i < node.jjtGetNumChildren(); i++ ) {
			Object o = node.jjtGetChild( i ).jjtAccept( this, null );
			if ( o instanceof Scenario )
				currentDomain.add( ( Scenario ) o );
		}

		return data;
	}

	public Object visit( LEMScenarioDeclaration node, Object data ) throws LemException {
		Scenario s = new Scenario();
		inScenario = true;
		s.setName( getIdentifier( node.jjtGetChild( 0 ) ) );
		s.setDescription( ( String ) node.jjtGetChild( 1 ).jjtAccept( this, null ) );
		s.setActionBlock( ( ActionBlock ) node.jjtGetChild( 2 ).jjtAccept( this, null ) );
		inScenario = false;
		return s;
	}

	/**
	 * Process an Generalisation
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMGeneralisation node, Object data ) throws LemException {

		// retrieve the generalisation from the mapper

		Generalisation generalisation = ( Generalisation ) mapper.getObject( node );

		// now visit its components

		super.visit( node, generalisation );

		// do something to register the generalisation in the domain


		return data;
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */

	public Object visit( LEMVariableDeclarationList node, Object data ) throws LemException {
		LinkedList decls = new LinkedList();

		for ( int i = 0; i < node.jjtGetNumChildren(); i++ ) {
			decls.add( node.jjtGetChild( i ).jjtAccept( this, null ) );
		}

		return decls;
	}


	public Object visit( LEMVariableDeclaration node, Object data ) throws LemException {
		String typeName = ( String ) node.jjtGetChild( 0 ).jjtAccept( this, null );
		DataType type = CoreDataType.findByName( typeName );
		String varName = ( String ) node.jjtGetChild( 1 ).jjtAccept( this, null );
		VariableDeclaration v = new VariableDeclaration( type, varName );
		return v;
	}

	public Object visit( LEMSelectStatement node, Object data ) throws LemException {
		int multiplicity = ( ( Integer ) node.jjtGetChild( 0 ).jjtAccept( this, null ) ).intValue();
		String fromClassName = getIdentifier( node.jjtGetChild( 1 ) );
		String lastToken = node.getLastToken().toString() ;
		// adds selected to the list of valid variables
		inSelect = true;
		Expression condition = ( Expression ) node.jjtGetChild( 2 ).jjtAccept( this, data );
		// selected is no longer valid after here
		inSelect = false;
		org.jdns.xtuml.metamodel.Class fromClass = getClass( fromClassName );

		if ( fromClass == null )
			throw new LemException( "Class '" + fromClassName + "' not defined.",node.getFirstToken(), "LEM_E_01044" );

		SelectExpression se = new SelectExpression( multiplicity, fromClass, condition );
		getMapper().add( node, se );

		return se;
	}

	public Object visit( LEMSelectMultiplicity node, Object data ) throws LemException {
		switch ( node.getFirstToken().kind ) {
		case LemParserConstants.ONE:
			return new Integer( SelectExpression.MULTIPLICITY_ONE );
		case LemParserConstants.ANY:
			return new Integer( SelectExpression.MULTIPLICITY_ANY );
		case LemParserConstants.ALL:
			return new Integer( SelectExpression.MULTIPLICITY_ALL );
		}

		return null;
	}

	public Object visit( LEMWhereClause node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() > 0 )
			return node.jjtGetChild( 0 ).jjtAccept( this, null );

		return null;
	}

	public Object visit( LEMProcedure node, Object data ) throws LemException {
		// Fetch the Procedure object created in the first pass
		Procedure p = ( Procedure ) getMapper().getObject( node );

		if ( node.jjtGetNumChildren() > 0 )
			p.setActionBlock( ( ActionBlock ) node.jjtGetChild( 0 ).jjtAccept( this, data ) );
		else
			p.setActionBlock( new ActionBlock() );

		return p;
	}

	public Object visit( LEMActionList node, Object data ) throws LemException {
		LinkedList actions = new LinkedList();
		boolean encounteredBreak = false;

		for ( int i = 0; i < node.jjtGetNumChildren(); i++ ) {
			if ( encounteredBreak )
				throw new LemException( "Unreachable statement",node.getFirstToken(), "LEM_E_01045" );
			Object o = node.jjtGetChild( i ).jjtAccept( this, data );

			if ( o != null && o instanceof Action ) {
				actions.add( o );
				if ( o instanceof BreakStatement ) {
					encounteredBreak = true;
				}
			}
		}
		return actions;
	}

	public Object visit( LEMAction node, Object data ) throws LemException {
		Object o = node.jjtGetChild( 0 ).jjtAccept( this, data );

		if ( o == null || !( o instanceof Action ) )
			System.err.println( node.jjtGetChild( 0 ).getClass().getName()
			                    + " returns a null or non-Action object" );

		return o;
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMActionBlock node, Object data ) throws LemException {
		//        ActionBlock a = (ActionBlock)getMapper().getObject(node);
		ActionBlock a = new ActionBlock();

		// set the ActionBlock we are visiting as the current block
		if ( currentBlock != null )
			a.setParent( currentBlock );
		currentBlock = a;

		// visit the variable declaration within this block
		LinkedList varDecls = ( LinkedList ) node.jjtGetChild( 0 ).jjtAccept( this, data );
		for ( int i = 0; i < varDecls.size(); i++ ) {
			VariableDeclaration v = ( VariableDeclaration ) varDecls.get( i );
			if ( !a.addVariableDeclaration( v ) )
				throw new LemException( "Double declaration of variable: " + v.getVariableName(),node.getFirstToken(), "LEM_E_01046" );
		}

		// visit the actions within this block
		LinkedList stmts = ( LinkedList ) node.jjtGetChild( 1 ).jjtAccept( this, data );

		// reset the current block to the parent so that all local variables used
		// within this block is no longer valid
		if ( a.getParent() != null )
			currentBlock = a.getParent();

		for ( int i = 0; i < stmts.size(); i++ ) {
			a.addAction( ( Action ) stmts.get( i ) );
		}

		//        super.visit( node, a );
		return a;
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMClassList node, Object data ) throws LemException {
		Vector v = new Vector();

		for ( int i = 0; i < node.jjtGetNumChildren(); i++ ) {
			String className = getIdentifier( node.jjtGetChild( i ) );
			org.jdns.xtuml.metamodel.Class c = currentDomain.getClass( className );

			if ( c == null ) {
				throw new LemException(
				    "Class " + c + " does not exist.",
				    node.getFirstToken(),
				    "LEM_E_01017" );
			}

			v.add( c );

		}

		return v;
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMUnaryOperator node, Object data ) throws LemException {
		switch ( node.getFirstToken().kind ) {
		case LemParserConstants.LNOT:
			return new UnaryOperation( UnaryOperation.LOGICAL_NOT );
		case LemParserConstants.MINUS:
			return new UnaryOperation( UnaryOperation.NEGATION );
		}

		throw new LemException( "Encountered unknown operator '" + node.getFirstToken().image + "'",node.getFirstToken(), "LEM_E_01047" );
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMUnary node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() == 2 ) {
			UnaryOperation o = ( UnaryOperation ) ( node.jjtGetChild( 0 ).jjtAccept( this, null ) );
			Expression e = ( Expression ) ( node.jjtGetChild( 1 ).jjtAccept( this, null ) );

			if ( o == null )
				return e;
			o.setOperand( e );
			return o;

		} else {
			return node.jjtGetChild( 0 ).jjtAccept( this, null );
		}
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMFactor node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() == 1 ) {
			return node.jjtGetChild( 0 ).jjtAccept( this, null );
		}

		BinaryOperation o = new BinaryOperation( BinaryOperation.EXPONENTIATION );
		o.setLeft( ( Expression ) node.jjtGetChild( 0 ).jjtAccept( this, null ) );
		o.setRight( ( Expression ) node.jjtGetChild( 1 ).jjtAccept( this, null ) );

		return o;
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMPrimary node, Object data ) throws LemException {
		return node.jjtGetChild( 0 ).jjtAccept( this, null );
	}

	public Object visit( LEMPrintAction node, Object data ) throws LemException {
		return new PrintAction( (Expression)node.jjtGetChild( 0 ).jjtAccept( this, null ));
	}
	
	/**
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMObjectCreation node, Object data ) throws LemException {
		CreateAction a = new CreateAction();
		getMapper().add( node, a );

		Collection c = ( Collection ) ( node.jjtGetChild( 0 ).jjtAccept( this, null ) );

		a.setClasses( c );

		return a;
	}

	/**
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMObjectDeletion node, Object data ) throws LemException {
		DeleteAction a = new DeleteAction();
		getMapper().add( node, a );

		VariableReference vr = ( VariableReference ) node.jjtGetChild( 0 ).jjtAccept( this, null );

		a.setVariable( vr );

		return a;
	}

	public Object visit( LEMOptionalParameterList node, Object data ) throws LemException {
		if( node.jjtGetNumChildren() > 0 ) 
			return node.jjtGetChild( 0 ).jjtAccept( this, null );
		return null;
	}
	
	/**
	 * Returns a LinkedList of Expressions as parameters.
	 */
	public Object visit( LEMParameterList node, Object data ) throws LemException {
		LinkedList parameters = null;
		if ( node.jjtGetNumChildren() > 0 ) {
			parameters = new LinkedList();
			for ( int i = 0; i < node.jjtGetNumChildren(); i++ ) {
				Expression p = ( Expression ) node.jjtGetChild( i ).jjtAccept( this, null );
				parameters.add( p );
			}
		}
		return parameters;
	}

	public Object visit( LEMEventCancel node, Object data ) throws LemException {
		CancelAction a = new CancelAction();
		getMapper().add( node, a );

		String eventName = getIdentifier( node.jjtGetChild( 0 ) );
		a.setEventName( eventName );

		return a;
	}

	public Object visit( LEMEventGeneration node, Object data ) throws LemException {
		GenerateAction a = new GenerateAction();
		getMapper().add( node, a );

		String eventName = getIdentifier( node.jjtGetChild( 0 ) );
		a.setEventName( eventName );

		LinkedList p = ( LinkedList ) node.jjtGetChild( 1 ).jjtAccept( this, null );
		a.setParameters( p );

		VariableReference vr = ( VariableReference ) node.jjtGetChild( 2 ).jjtAccept( this, null );
		a.setTarget( vr );

		org.jdns.xtuml.metamodel.Expression delay = ( org.jdns.xtuml.metamodel.Expression ) node.jjtGetChild( 3 ).jjtAccept( this, null );
		a.setDelayTime ( delay ) ;

		return a;
	}

	public Object visit( LEMWithDelay node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() > 0 )
			return node.jjtGetChild( 0 ).jjtAccept( this, null );

		return null;
	}


	public Object visit( LEMLinkCreation node, Object data ) throws LemException {
		RelateAction a = new RelateAction();
		VariableReference active = ( VariableReference ) node.jjtGetChild( 0 ).jjtAccept( this, null );
		VariableReference passive = ( VariableReference ) node.jjtGetChild( 1 ).jjtAccept( this, null );
		Collection association = ( Collection ) node.jjtGetChild( 2 ).jjtAccept( this, null );
		String linkObjectName = null;

		// if there is the optional creating LinkObject clause at the end
		if ( node.jjtGetNumChildren() == 4 ) {
			linkObjectName = ( String ) node.jjtGetChild( 3 ).jjtAccept( this, null );
		}
		java.util.Iterator i = association.iterator();
		String assocName = ( String ) i.next();
		Relationship r = currentDomain.getRelationship( assocName );

		if ( !( r instanceof Association ) ) {
			throw new LemException( "Relationship "
			                        + assocName
			                        + " is not an Association", node.getFirstToken(), "LEM_E_01041" );
		}

		Association ra = ( Association ) r;

		// if there is an optional verb clause specified

		if ( association.size() == 2 ) {
			String verbclause = ( String ) i.next();
			if ( ra.getActivePerspective().getVerbClause().toString().equals( verbclause ) ) {
				a.setActiveObjectName( active.getVariableName() );
				a.setPassiveObjectName( passive.getVariableName() );
				a.setVerbClause( true );
			} else if ( ra.getPassivePerspective().getVerbClause().toString().equals( verbclause ) ) {
				a.setPassiveObjectName( active.getVariableName() );
				a.setActiveObjectName( passive.getVariableName() );
				a.setVerbClause( true );
			} else {
				throw new LemException( "Invalid verb clause \"" + verbclause + "\"",node.getFirstToken(), "LEM_E_01048" );
			}
		} else {
			a.setActiveObjectName( active.getVariableName() );
			a.setPassiveObjectName( passive.getVariableName() );
		}
		a.setAssociation( ra );
		if ( linkObjectName != null )
			a.setLinkObjectName( linkObjectName );
		return a;
	}

	public Object visit( LEMRelationshipReference node, Object data ) throws LemException {
		LinkedList ll = new LinkedList();
		String assocName = ( String ) node.jjtGetChild( 0 ).jjtAccept( this, null );
		ll.add( assocName );
		if ( node.jjtGetNumChildren() == 2 ) {
			String verb = node.getLastToken().image;
			ll.add( verb );
		}

		return ll;
	}

	public Object visit( LEMLinkDeletion node, Object data ) throws LemException {
		UnrelateAction a = new UnrelateAction();
		VariableReference active = ( VariableReference ) node.jjtGetChild( 0 ).jjtAccept( this, null );
		VariableReference passive = ( VariableReference ) node.jjtGetChild( 1 ).jjtAccept( this, null );
		String assocName = (String) node.jjtGetChild( 2 ).jjtAccept( this, null );

		Relationship r = currentDomain.getRelationship( assocName );
		if ( !( r instanceof Association ) ) {
			System.err.println( assocName + " is invalid." );
			throw new LemException( "Relationship "
			                        + assocName
			                        + " is not an Association", node.getFirstToken(), "LEM_E_01041" );
		}

		Association ra = ( Association ) r;

		a.setActiveObjectName( active.getVariableName() );
		a.setPassiveObjectName( passive.getVariableName() );
		a.setAssociationClassReference( assocName );
		a.setAssociation( ra );

		return a;
	}

	public Object visit( LEMLinkObjectCreation node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() != 1 )
			return null;
		return node.jjtGetChild( 0 ).jjtAccept( this, null );
	}

	public Object visit( LEMReturn node, Object data ) throws LemException {

		ReturnAction a = new ReturnAction();

		if ( node.jjtGetNumChildren() == 1 ) {
			a.setExpression( ( Expression ) node.jjtGetChild( 0 ).jjtAccept( this, null ) );
		}

		return a;
	}

	public Object visit( LEMAttributeAssignment node, Object data ) throws LemException {
		AssignmentAction a = new AssignmentAction();

		VariableReference r = ( VariableReference ) ( node.jjtGetChild( 0 ).jjtAccept( this, null ) );

		Expression e = ( Expression ) ( node.jjtGetChild( 1 ).jjtAccept( this, data ) );

		a.setVariableReference( r );
		a.setExpression( e );

		return a;
	}

	public Object visit( LEMWhileStatement node, Object data ) throws LemException {
		WhileStatement w = new WhileStatement();

		w.setCondition( ( Expression ) node.jjtGetChild( 0 ).jjtAccept( this, null ) );
		w.setBlock( ( ActionBlock ) node.jjtGetChild( 1 ).jjtAccept( this, null ) );
		return w;
	}

	public Object visit( LEMForStatement node, Object data ) throws LemException {
		ForStatement f = new ForStatement();
		String selectVariable = getIdentifier( node.jjtGetChild( 0 ) ) ;
		f.setSelectVariable( selectVariable );
//		VariableDeclaration selected = new VariableDeclaration( CoreDataType.findByName( "objref" ), selectVariable );
//		if ( !currentBlock.addVariableDeclaration( selected ) )
//			throw new LemException( "Double declaration of variable: " + selectVariable );
		f.setSetExpression( ( Expression ) node.jjtGetChild( 1 ).jjtAccept( this, null ) );
		f.setBlock( ( ActionBlock ) node.jjtGetChild( 2 ).jjtAccept( this, null ) );
//		currentBlock.removeVariableDeclaration( selected );
		return f;
	}

	public Object visit( LEMCreationTransaction node, Object data ) throws LemException {
		CreationTransaction ct = new CreationTransaction();
		ct.setBlock((ActionBlock)node.jjtGetChild(0).jjtAccept(this, null));
		return ct;
	}


	public Object visit( LEMBreak node, Object data ) throws LemException {
		if ( !hasLoopAncestorNode( node ) )
			throw new LemException( "break outside loop",node.getFirstToken(), "LEM_E_01049" );
		return new BreakStatement();
	}

	public Object visit( LEMIfStatement node, Object data ) throws LemException {
		IfStatement theIf = new IfStatement();
		Expression cond = ( Expression ) node.jjtGetChild( 0 ).jjtAccept( this, null );
		ActionBlock ab = ( ActionBlock ) node.jjtGetChild( 1 ).jjtAccept( this, null );
		theIf.addConditionalAlternative( cond, ab );

		for ( int i = 2; i < node.jjtGetNumChildren(); i++ ) {
			theIf.addConditionalAlternative( ( ConditionalAlternative ) node.jjtGetChild( i ).jjtAccept( this, null ) );
		}

		return theIf;
	}

	public Object visit( LEMElseIfPart node, Object data ) throws LemException {
		Expression cond = ( Expression ) node.jjtGetChild( 0 ).jjtAccept( this, null );
		ActionBlock ab = ( ActionBlock ) node.jjtGetChild( 1 ).jjtAccept( this, null );

		return new ConditionalAlternative( cond, ab );
	}

	public Object visit( LEMElsePart node, Object data ) throws LemException {
		ActionBlock ab = ( ActionBlock ) node.jjtGetChild( 0 ).jjtAccept( this, null );

		return new ConditionalAlternative( null, ab );
	}

	public Object visit( LEMLValue node, Object data ) throws LemException {
		return node.jjtGetChild( 0 ).jjtAccept( this, null );
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMExpression node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() == 1 ) {
			return node.jjtGetChild( 0 ).jjtAccept( this, null );
		} else {
			return listToTree( node, node.jjtGetNumChildren() - 1 );
		}
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMAndRelation node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() == 1 ) {
			return node.jjtGetChild( 0 ).jjtAccept( this, null );
		} else {
			return listToTree( node, node.jjtGetNumChildren() - 1 );
		}
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMRelation node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() == 1 ) {
			return node.jjtGetChild( 0 ).jjtAccept( this, null );
		} else {
			return listToTree( node, node.jjtGetNumChildren() - 1 );
		}
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMRelatedTo node, Object data ) throws LemException {
		RelatedToOperation o = null ;
		if ( node.jjtGetNumChildren() != 3 ) {
			throw new LemException( "Related To is a ternary operator,\n Use:  Related To class1 Across R1 ", node.getFirstToken(), "LEM_E_01050" ) ;
		} else {
			String relatedClassName = getIdentifier( node.jjtGetChild( 0 ) );
			org.jdns.xtuml.metamodel.Class relatedClass = getClass( relatedClassName );
			String relationshipName = getIdentifier( node.jjtGetChild( 1 ) );
			Relationship r = currentDomain.getRelationship( relationshipName );
			o = new RelatedToOperation ( relatedClass, r ) ;
		}
		return o ;
	}


	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMSimpleExpression node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() == 1 ) {
			return node.jjtGetChild( 0 ).jjtAccept( this, null );
		} else {
			return listToTree( node, node.jjtGetNumChildren() - 1 );
		}
	}

	/**
	 * Returns a binary tree representing the left-associative list of terms represented
	 * by the children of the given SimpleNode. For example.
	 *
	 * 2 + 3 + 5 is translated into
	 *
	 *        +
	 *      +   5
	 *    2   3
	 */
	protected Expression listToTree( SimpleNode n, int index ) throws LemException {
		if ( index == 0 )
			return ( Expression ) ( n.jjtGetChild( index ).jjtAccept( this, null ) );

		Expression right = ( Expression ) ( n.jjtGetChild( index ).jjtAccept( this, null ) );
		BinaryOperation o = ( BinaryOperation ) ( n.jjtGetChild( index - 1 ).jjtAccept( this, null ) );
		Expression left = listToTree( n, index - 2 );

		o.setLeft( left );
		o.setRight( right );
		return o;
	}

	public Object visit( LEMParenthesizedPrimary node, Object data ) throws LemException {
		return node.jjtGetChild( 0 ).jjtAccept( this, null );
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMTerm node, Object data ) throws LemException {
		if ( node.jjtGetNumChildren() == 1 ) {
			return node.jjtGetChild( 0 ).jjtAccept( this, null );
		} else {
			return listToTree( node, node.jjtGetNumChildren() - 1 );
		}
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMAdding node, Object data ) throws LemException {
		switch ( node.getFirstToken().kind ) {
		case LemParserConstants.PLUS:
			return new BinaryOperation( BinaryOperation.ADDITION );
		case LemParserConstants.MINUS:
			return new BinaryOperation( BinaryOperation.SUBTRACTION );
		}

		throw new LemException( "Unknown operator '" + node.getFirstToken() + "' encountered", node.getFirstToken(), "LEM_E_01047" );
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMLogicalOr node, Object data ) throws LemException {
		return new BinaryOperation( BinaryOperation.LOGICAL_OR );
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMLogicalAnd node, Object data ) throws LemException {
		return new BinaryOperation( BinaryOperation.LOGICAL_AND );
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMRelational node, Object data ) throws LemException {
		switch ( node.getFirstToken().kind ) {
		case LemParserConstants.EQUAL:
			return new BinaryOperation( BinaryOperation.EQUAL );
		case LemParserConstants.NE:
			return new BinaryOperation( BinaryOperation.NOT_EQUAL );
		case LemParserConstants.LT:
			return new BinaryOperation( BinaryOperation.LESS_THAN );
		case LemParserConstants.LE:
			return new BinaryOperation( BinaryOperation.LESS_THAN_OR_EQUAL );
		case LemParserConstants.GT:
			return new BinaryOperation( BinaryOperation.GREATER_THAN );
		case LemParserConstants.GE:
			return new BinaryOperation( BinaryOperation.GREATER_THAN_OR_EQUAL );
		}

		throw new LemException( "Unknown operator '" + node.getFirstToken() + "' encountered", node.getFirstToken(), "LEM_E_01047" );
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMMultiplying node, Object data ) throws LemException {
		switch ( node.getFirstToken().kind ) {
		case LemParserConstants.MULTIPLY:
			return new BinaryOperation( BinaryOperation.MULTIPLICATION );
		case LemParserConstants.DIVIDE:
			return new BinaryOperation( BinaryOperation.DIVISION );
		case LemParserConstants.MOD:
			return new BinaryOperation( BinaryOperation.MODULO );
		}

		throw new LemException( "Unknown operator '" + node.getFirstToken() + "' encountered", node.getFirstToken(), "LEM_E_01047" );
	}

	/**
	 *
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMObjectReference node, Object data ) throws LemException {
		/*
		String objectName = node.getFirstToken().image;
		if ( inScenario && objectName.equals( "self" ) )
			throw new LemException( "Undefined use of self in Scenario" );
		if ( !objectName.equals( "self" ) && !( objectName.equals( "selected" ) && hasSelectAncestorNode( node ) ) ) {
			if ( !currentBlock.isValidVariable( objectName ) )
				throw new LemException( "Undeclared object " + objectName );
			else {
				if ( !( currentBlock.getVariableType( objectName ) instanceof ObjectReferenceType ) )
					throw new LemException( objectName + " is not an object reference" );
			}
		}
		*/
		return new VariableReference( node.getFirstToken().image );
	}

	/**
	 * Visit a LEMVariableReference. Return the instance of org.jdns.xtuml.metamodel.VariableReference
	 * which represents this LEMVariableReferenceNode.
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */

    	public Object visit( LEMVariableReference node, Object data ) throws LemException {
        	if( node.jjtGetNumChildren() == 0) {
	    		// 'self' reference.
	            	if(inScenario)
	            	    throw new LemException("Undefined use of self in Scenario", node.getFirstToken(), "LEM_E_01051" );
			    return new VariableReference( "self" );  
		} else if( node.jjtGetNumChildren() == 1 ) {
	            	// bare variable reference
	            	String variableName = getIdentifier(node.jjtGetChild(0));
			
/* TODO: need to fix static variable checking
            	if(!currentBlock.isValidVariable(variableName)) {
            	    if((currentClass.getAttribute(variableName) != null) && !(inScenario)) {
            	        return new VariableReference("self", variableName);
            	    }else if(!(variableName.equals("selected") && hasSelectAncestorNode(node)))
            	        throw new LemException("Undeclared variable "+variableName, node.getFirstToken(), "LEM_E_01052" );
          	  } 
*/
			return new VariableReference(variableName);
        	} else if( node.jjtGetNumChildren() == 2 ) {
      	  	    // object.variable-style reference, eg. "publisher.name"
        	    VariableReference obj = (VariableReference)visit( (LEMObjectReference)node.jjtGetChild(0), null );
        	    String variableName = getIdentifier( node.jjtGetChild(1));      
        	    return new VariableReference( obj.getVariableName(), variableName );
        	}

        	throw new LemException( "LEMVariableReference has neither 1 nor 2 children", node.getFirstToken(), "LEM_E_01053"  );

    	}

	/**
	 * Visit a LEMLiteral node. Return the instance of org.jdns.xtuml.metamodel.Literal representing
	 * this literal.
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMLiteral node, Object data ) throws LemException {
		DataType t = null;
		switch ( node.getFirstToken().kind ) {
		case LemParserConstants.DECIMAL_LITERAL:
		case LemParserConstants.REAL_LITERAL:
			//            case LemParserConstants.FLOATING_POINT_LITERAL:
			t = NumericType.getInstance();
			break;
		case LemParserConstants.BOOLEAN_LITERAL:
			t = BooleanType.getInstance();
			break;
		case LemParserConstants.STRING_LITERAL:
			t = StringType.getInstance();
			break;
		case LemParserConstants.NULL:
			// TODO: NullType
			t = ObjectReferenceType.getInstance();
			break;
		default:
			throw new LemException( "LEMLiteral unknown type" );
		}

		return new Literal( t, node.getFirstToken().image );
	}

	/**
	 * Visit LEMClassDeclaration and pass this class down to children.
	 */
	public Object visit( LEMClassDeclaration node, Object data ) throws LemException {

		org.jdns.xtuml.metamodel.Class c = ( org.jdns.xtuml.metamodel.Class ) mapper.getObject( node );
		currentClass = c;
		super.visit( node, c );

		return c;
	}

	/**
	 * Process the superclass identifier in a Generalisation
	 *
	 * This node can exist in the context of a generalisation or is a specialisation list
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMSuperClassIdentifier node, Object data ) throws LemException {

		super.visit( node, data );

		if ( data instanceof Generalisation ) {

			// we are processing a Generalisation, so recover it and the domain

			Generalisation generalisation = ( Generalisation ) data;
			Domain domain = generalisation.getDomain();

			// find the class being refered to, or create a new class

			String superclassName = node.getFirstToken().image;
			org.jdns.xtuml.metamodel.Class superclass = domain.getClass( superclassName );

			// now create the Generalisation role that has been defined

			SuperclassRole role = new SuperclassRole( superclass, generalisation );
			generalisation.setSuperClassRole( role );
		}

		// all done

		return data;
	}

	/**
	 * Process the subclass identifier in a Generalisation
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMSubClassIdentifier node, Object data ) throws LemException {

		super.visit( node, data );

		// we are processing a Generalisation, so recover it and the domain

		Generalisation generalisation = ( Generalisation ) data;
		Domain domain = generalisation.getDomain();

		// find the class being refered to, or create a new class

		String subclassName = node.getFirstToken().image;
		org.jdns.xtuml.metamodel.Class subclass = domain.getClass( subclassName );

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
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMActivePerspective node, Object data ) throws LemException {

		// recover the active perspective

		ActivePerspective perspective = ( ActivePerspective ) getMapper().getObject( node );

		super.visit( node, perspective );

		return data;
	}

	/**
	 * Process a PassivePerspective
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMPassivePerspective node, Object data ) throws LemException {

		// recover the passive perspective

		PassivePerspective perspective = ( PassivePerspective ) getMapper().getObject( node );

		super.visit( node, perspective );

		return data;
	}

	/**
	 * Process an ActiveSubject reference. This node might be visited in the
	 * context of either an Active or Passive perspective
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMActiveSubject node, Object data ) throws LemException {

		super.visit( node, data );
		return data;

	}


	/**
	 * Process an ActiveObject reference. This node might be visited in the
	 * context of either an Active or Passive perspective
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMActiveObject node, Object data ) throws LemException {

		super.visit( node, data );
		return data;
	}

	/**
	 * A parameter is always declared within the context of a Signature so
	 * our task is to simply construct the parameter declaration and
	 * add it to the Signature. We much ensure that the name of the parameter
	 * is unique with the signature
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMParameterDeclaration node, Object data ) throws org.jdns.xtuml.metamodel.LemException {

		Parameter parameter = ( Parameter ) getMapper().getObject( node );
		super.visit( node, parameter );



		return data;
	}


	/**
	 * Process an attribute declaration
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMBaseAttribute node, Object data ) throws LemException {
		Attribute attribute = ( Attribute ) getMapper().getObject( node );
		super.visit( node, attribute );

		return data;

	}

	/**
	 * Check that the named Domain Sepcific Type is actually in the domain.
	 * We can now discard the proxy object.
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMDomainSpecificType node, Object data ) throws LemException {
		super.visit( node, data );

		DomainSpecificDataType dsdt = null;
		String name = null;
		Domain domain = null;

		// recover the type name from the proxy

		if ( data instanceof Attribute ) {

			Attribute attribute = ( Attribute ) data;
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
			Attribute attribute = ( Attribute ) data;
			attribute.setType( type );
		} else {
			Parameter parameter = ( Parameter ) data;
			parameter.setType( type );
		}

		return data;
	}

	/**
	 * Process an IdentifierDeclaration
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMIdentifierDeclaration node, Object data ) throws LemException {

		// retreve the Identifier to be constructed by this visitor

		Identifier id = ( Identifier ) getMapper().getObject( node );

		super.visit( node, id );

		return data;
	}

	/**
	 * Process an IdentifyingAttribute
	 * @param node
	 * @param data
	 * @throws org.jdns.xtuml.metamodel.LemException
	 * @return
	 */
	public Object visit( LEMIdentifyingAttribute node, Object data ) throws LemException {

		super.visit( node, data );

		// this method is called in the context of an existing identifier

		Identifier id = ( Identifier ) data;
		String attributeName = node.getFirstToken().image;

		// check that the attribute exists

		org.jdns.xtuml.metamodel.Class domainClass = id.getDomainClass();
		Attribute attribute = domainClass.getAttribute( attributeName );
		if ( null == attribute ) {
			throw new LemException(
			    "Attribute " + attributeName + " is not defined in class " + domainClass.getName(),
			    node.getLastToken(),
			    "LEM_E_01035" );
		}

		// TODO: check if the attribute is a referential attribute, it must be navigated via a "mandatory"
		// association perspective

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

	/**
	 *
	 * @param node
	 * @param data
	 * @return
	 */
	public Object visit( LEMIdentifier node, Object data ) {
		return node.getFirstToken().image;
	}

	public Object visit( LEMType node, Object data ) throws LemException {
		return node.jjtGetChild( 0 ).jjtAccept( this, null );
	}

	public Object visit( LEMPrimitiveType node, Object data ) {
		return node.getFirstToken().image;
	}

	/**
	 * Process an event declaration. An event declaration may be made in the context of a class
	 * (public event) or in the context of a State Machine (private or self directed event).
	 */
	public Object visit( LEMEventDeclaration node, Object data ) throws LemException {
		Event event = ( Event ) getMapper().getObject( node );
		currentClass.getStateMachine().add( event );

		return event;
	}

	public Object visit( LEMBehaviour node, Object data ) throws LemException {
		StateMachine m = ( StateMachine ) getMapper().getObject( node );

		for ( int i = 1; i < node.jjtGetNumChildren(); i++ ) {
			Object o = node.jjtGetChild( i ).jjtAccept( this, null );

			if ( o instanceof Transition )
				m.add( ( Transition ) o );
			else
				System.err.println( "Warning: Does visit("
				                    + node.jjtGetChild( i ).getClass().getName()
				                    + ") still uses old-style side effects?" );
		}

		return m;
	}

	public Object visit( LEMTransitionDeclaration node, Object data ) throws LemException {
		Transition t;

		if ( node.jjtGetNumChildren() == 4 ) {
			StateTransition st = new StateTransition();
			String fromStateName = getIdentifier( node.jjtGetChild( 2 ) );
			String toStateName = getIdentifier( node.jjtGetChild( 3 ) );

			State fromState = getState( fromStateName );
			State toState = getState( toStateName );

			if ( fromState == null )
				throw new LemException( "No such state '" + fromStateName + "' in class '"
				                        + currentClass.getName() + "'", node.getFirstToken(), "LEM_E_01010"  );
			if ( toState == null )
				throw new LemException( "No such state '" + toStateName + "' in class '"
				                        + currentClass.getName() + "'", node.getFirstToken(), "LEM_E_01011"  );

			if ( !( fromState instanceof NonDeletionState ) )
				throw new LemException( "State '" + fromStateName + "' is not a non-deletion state", node.getFirstToken(), "LEM_E_01012"  );

			st.setFromState( ( NonDeletionState ) fromState );
			st.setToState( toState );

			t = st;
		} else {
			// Only 3 children, must be from the creation state
			InitialisingTransition i = new InitialisingTransition();

			String toStateName = getIdentifier( node.jjtGetChild( 2 ) );
			State toState = getState( toStateName );

			if ( toState == null )
				throw new LemException( "No such state '" + toStateName + "' in class '"
				                        + currentClass.getName() + "'", node.getFirstToken(), "LEM_E_01011"  );

			i.setToState( toState );

			t = i;
		}

		// Why bother doing all the above if the event can't be found??
		String eventName = getIdentifier( node.jjtGetChild( 0 ) );
		Event e = getEvent( eventName );

		if ( e == null )
			throw new LemException( "Event '" + eventName + "' not found", node.getFirstToken(), "LEM_E_01054"  );

		t.setEvent( e );
		t.setDescription( getIdentifier( node.jjtGetChild( 1 ) ) );

		return t;

	}

	/**
	 * Return the String description represented by the given LEMDescription node.
	 * 
	 * @todo The LemParser.jjt specifies an optional token. JavaCC will return the 
	 * next token along if no string is specified! This is an evil dirty hack until
	 * the grammar can be fixed.
	 */
	public Object visit( LEMDescription node, Object data ) {
		return node.getFirstToken().kind == LemParserConstants.STRING_LITERAL
		       ? node.getFirstToken().image
		       : null;
	}

	protected org.jdns.xtuml.metamodel.Class getClass( String className ) {
		return currentDomain.getClass( className );
	}

	protected State getState( String stateName ) {
		StateMachine m = currentClass.getStateMachine();

		if ( m != null )
			return m.getState( stateName );

		return null;
	}

	protected Event getEvent( String eventName ) {
		StateMachine m = currentClass.getStateMachine();

		if ( m != null )
			return m.getEvent( eventName );

		return null;
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
		return ( String ) identifierNode.jjtAccept( this, null );
	}

	/**
	 * returns true if the any of the ancestor node of the given node is a for or while loop
	 *
	 * @param myNode the node which we are interested in.
	 *
	 * @return true if ancestor is a for or while loop
	 */
	private boolean hasLoopAncestorNode( Node myNode ) {
		Node parentNode = myNode.jjtGetParent();
		if ( ( parentNode instanceof LEMWhileStatement ) || ( parentNode instanceof LEMForStatement ) )
			return true;
		if ( parentNode instanceof LEMProcedure )
			return false;
		else
			return hasLoopAncestorNode( parentNode );
	}

	private boolean hasSelectAncestorNode( Node myNode ) {
		Node parentNode = myNode.jjtGetParent();
		if ( parentNode instanceof LEMSelectStatement )
			return true;
		if ( parentNode instanceof LEMProcedure )
			return false;
		else
			return hasSelectAncestorNode( parentNode );
	}
}

/*
 * InterpreterTest.java
 * JUnit based test
 *
 * Created on 4 July 2005, 21:36
 */

package runtime;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import metamodel.VariableDeclaration;
import metamodel.AssignmentAction;
import metamodel.LemException;
import metamodel.Model;
import metamodel.Procedure;
import metamodel.Action;
import metamodel.CreateAction;
import parser.ParseException;
import tools.Lem;

/**
 *
 * @author sjr
 */
public class InterpreterTest extends junit.framework.TestCase {
    
    public InterpreterTest(String testName) {
        super(testName);
    }
    
    /*
    public void testExecuteAssignmentAction() {
        Lem l = new Lem();
        Model m = null;
        
        try {
            m = l.parse( new FileInputStream( "regression/tests/AssociativityTest.lem" ));
        } catch( FileNotFoundException fnfe ) {
            fail( "Could not find model file " + fnfe.getMessage() );
        } catch( IOException e ) {
            fail( "Could not read model file: " + e.getMessage() );
        } catch( ParseException e ) {
            fail( "Could not parse model file: " + e.getMessage() );
        } catch( LemException e ) {
            fail( "Some LEMException occurred: " + e.getMessage() );
        }
        
	VariableDeclaration v = (VariableDeclaration)(m.getDomain("TestDomain")
	.getClass("A")
	.getStateMachine()
	.getState("a")
	.getProcedure()
	.getActionBlock()
	.getVariableDeclarations()
	.getFirst());
	
        // Grab the AssignmentAction
        AssignmentAction a = (AssignmentAction)(m.getDomain("TestDomain")
        .getClass("A")
        .getStateMachine()
        .getState("a")
        .getProcedure()
	.getActionBlock()
        .getActions()
        .getFirst());
        
        DomainContext c = new DomainContext();
        Interpreter i = new Interpreter(null);
        
        try {
	    instantiateVariable(v, c);

            Variable v = i.executeAssignmentAction(a, c);
            System.out.println( "The answer is " + v.getValue() );
            BigDecimal val = (BigDecimal)v.getValue();
            assertEquals( "The result is 2.1 + 3.2 + 4.3 = 9.6", true, v.getValue().equals( new BigDecimal( "9.6" )));
        } catch( LemRuntimeException e ) {
            fail( "Runtime exception while executing test: " + e.getMessage() );
        }
        
    }
    */

    public void testEvaluation() {
        Lem l = new Lem();
        Model m = null;
        
        try {
            m = l.parse( new FileInputStream( "regression/tests/AssociativityTest.lem" ));
        } catch( FileNotFoundException fnfe ) {
            fail( "Could not find model file " + fnfe.getMessage() );
        } catch( IOException e ) {
            fail( "Could not read model file: " + e.getMessage() );
        } catch( ParseException e ) {
            fail( "Could not parse model file: " + e.getMessage() );
        } catch( LemException e ) {
            fail( "Some LEMException occurred: " + e.getMessage() );
        }
        
        Procedure p = m.getDomain("TestDomain")
        .getClass("A")
        .getStateMachine()
        .getState("evalTest")
        .getProcedure();
        
        DomainContext c = new DomainContext();
        Interpreter i = new Interpreter(null);
        
        try {
            i.interpret( p, c );
        } catch( LemRuntimeException e ) {
            fail( "Some LemRuntimeException occurred: " + e.getMessage() );
        }
    }
 
    public void testSignalGeneration() {
        Lem l = new Lem();
        Model m = null;
        
        try {
            m = l.parse( new FileInputStream( "regression/tests/SignalTest.lem" ));
        } catch( FileNotFoundException fnfe ) {
            fail( "Could not find model file " + fnfe.getMessage() );
        } catch( IOException e ) {
            fail( "Could not read model file: " + e.getMessage() );
        } catch( ParseException e ) {
            fail( "Could not parse model file: " + e.getMessage() );
        } catch( LemException e ) {
            fail( "Some LEMException occurred: " + e.getMessage() );
        }
        
        CreateAction create = (CreateAction)m.getDomain("TestDomain")
        .getClass("main")
        .getStateMachine()
        .getState("sigInit")
        .getProcedure()
	.getActionBlock()
	.getActions()
	.getFirst();

	Procedure mainProc = m.getDomain("TestDomain")
        .getClass("main")
        .getStateMachine()
        .getState("sigTest")
        .getProcedure();
        
        DomainContext c = new DomainContext();
        Interpreter i = new Interpreter(null);
        
        try {
            runtime.Object obj = i.executeCreateAction( create, c );
	    i = new Interpreter(obj);
	    i.interpret(mainProc, c);
        } catch( LemRuntimeException e ) {
            fail( "Some LemRuntimeException occurred: " + e.getMessage() );
        }
    }
    

    public void testExecuteVariableDeclaration() {
        Lem l = new Lem();
        Model m = null;
        
        try {
            m = l.parse( new FileInputStream( "regression/tests/AssociativityTest.lem" ));
        } catch( FileNotFoundException fnfe ) {
            fail( "Could not find model file " + fnfe.getMessage() );
        } catch( IOException e ) {
            fail( "Could not read model file: " + e.getMessage() );
        } catch( ParseException e ) {
            fail( "Could not parse model file: " + e.getMessage() );
        } catch( LemException e ) {
            fail( "Some LEMException occurred: " + e.getMessage() );
        }
        
        Procedure p = m.getDomain("TestDomain")
        .getClass("A")
        .getStateMachine()
        .getState("varDeclTest")
        .getProcedure();
        
        DomainContext c = new DomainContext();
        Interpreter i = new Interpreter(null);
        
        try {
            i.interpret( p, c );
        } catch( LemRuntimeException e ) {
            fail( "Some LemRuntimeException occurred: " + e.getMessage() );
        }
    }
    
    public void testAssociations() {
        Lem l = new Lem();
        Model m = null;
        
        try {
            m = l.parse( new FileInputStream( "regression/tests/AssociationTest.lem" ));
        } catch( FileNotFoundException fnfe ) {
            fail( "Could not find model file " + fnfe.getMessage() );
        } catch( IOException e ) {
            fail( "Could not read model file: " + e.getMessage() );
        } catch( ParseException e ) {
            fail( "Could not parse model file: " + e.getMessage() );
        } catch( LemException e ) {
            fail( "Some LEMException occurred: " + e.getMessage() );
        }
        
        metamodel.Class a = m.getDomain("TestDomain").getClass("A");
        metamodel.Class b = m.getDomain("TestDomain").getClass("B");
        metamodel.Class c = m.getDomain("TestDomain").getClass("C");
        
        assertEquals( "Class A participates in 1 association", 1, a.getAssociations().size() );
        assertEquals( "Class B participates in 1 association", 1, b.getAssociations().size() );
        assertEquals( "Class C participates in 1 (reflexive) association", 1, c.getAssociations().size() );
    }
            
}

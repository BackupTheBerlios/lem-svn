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

    public void testSelect() {
        Lem l = new Lem();
        Model m = null;
        
        try {
            m = l.parse( new FileInputStream( "regression/tests/SelectTest.lem" ));
        } catch( FileNotFoundException fnfe ) {
            fail( "Could not find model file " + fnfe.getMessage() );
        } catch( IOException e ) {
            fail( "Could not read model file: " + e.getMessage() );
        } catch( ParseException e ) {
            fail( "Could not parse model file: " + e.getMessage() );
        } catch( LemException e ) {
            fail( "Some LEMException occurred: " + e.getMessage() );
        }
        
	Procedure mainProc = m.getDomain("TestDomain")
        .getClass("TestClass")
        .getStateMachine()
        .getState("TestState")
        .getProcedure();
        
        DomainContext c = new DomainContext();
        Interpreter i = new Interpreter(null);
        
        try {
	    i.interpret(mainProc, c);
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
       
    public void testLinkCreation() {
        Lem l = new Lem();
        Model m = null;
      
        try {
            m = l.parse( new FileInputStream( "regression/tests/LinkCreationTest.lem" ));
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
        .getClass("TestClass")
        .getStateMachine()
        .getState("createTestClass")
        .getProcedure()
	.getActionBlock()
	.getActions()
	.getFirst();

	Procedure mainProc = m.getDomain("TestDomain")
        .getClass("TestClass")
        .getStateMachine()
        .getState("relateTestClass")
        .getProcedure();
        
        DomainContext c = new DomainContext();
        Interpreter i = new Interpreter(null);
        int k=0;
        try {
            runtime.Object obj = i.executeCreateAction( create, c );
	    i = new Interpreter(obj);
	    i.interpret(mainProc, c);
        } catch( LemRuntimeException e ) {
            fail( "Some LemRuntimeException occurred: " + e.getMessage() );
        }
        
        java.util.Iterator j = c.getAssociationInstances().values().iterator();
        AssociationInstance a = (AssociationInstance)((java.util.LinkedList)j.next()).getFirst();
        Instance active = a.getActiveInstance();
        Instance passive = a.getPassiveInstance(); 
        int count=0;
        j = c.getAssociationInstances().values().iterator();
        while(j.hasNext()) {
            count += ((java.util.LinkedList)j.next()).size();
        }
        assertEquals("An association instance has been added", 2, count);
        assertEquals("Active perspective instance is of class A", "A", active.getInstanceClass().getName());        
        assertEquals("Passive perspective instance is of class B", "B", passive.getInstanceClass().getName());
        Instance linkobject = (Instance)a.getLinkObjectInstance().getInstances().iterator().next();
        assertEquals("Link object instance is of class D", "D", linkobject.getInstanceClass().getName());
        AssociationInstance aI_active = (AssociationInstance)active.getAssociationInstances(a.getAssociation()).iterator().next();
        assertEquals("Instance of class A has the correct association instance", a, aI_active);
        AssociationInstance aI_passive = (AssociationInstance)passive.getAssociationInstances(a.getAssociation()).iterator().next();
        assertEquals("Instance of class A has the correct association instance", a, aI_passive);        
        
        
	mainProc = m.getDomain("TestDomain")
        .getClass("TestClass")
        .getStateMachine()
        .getState("relateTestClass2")
        .getProcedure();
        
        c = new DomainContext();
        i = new Interpreter(null);
        try {
            runtime.Object obj = i.executeCreateAction( create, c );
	    i = new Interpreter(obj);
	    i.interpret(mainProc, c);
        } catch( LemRuntimeException e ) {
            
            fail( "Testing for creation of association that already exist.\n" + "Some LemRuntimeException occurred: " + e.getMessage() );
        }     
    }    
    
    public void testLinkDeletion() {
        Lem l = new Lem();
        Model m = null;
      
        try {
            m = l.parse( new FileInputStream( "regression/tests/LinkDeletionTest.lem" ));
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
        .getClass("TestClass")
        .getStateMachine()
        .getState("createTestClass")
        .getProcedure()
	.getActionBlock()
	.getActions()
	.getFirst();

	Procedure mainProc = m.getDomain("TestDomain")
        .getClass("TestClass")
        .getStateMachine()
        .getState("unrelateTestClass")
        .getProcedure();
        
        DomainContext c = new DomainContext();
        Interpreter i = new Interpreter(null);
        int k=0;
        try {
            runtime.Object obj = i.executeCreateAction( create, c );
	    i = new Interpreter(obj);
	    i.interpret(mainProc, c);
        } catch( LemRuntimeException e ) {
            fail( "Testing for deletion of association that exist.\n"+"Some LemRuntimeException occurred: " + e.getMessage() );
        }
        int count=0;
        java.util.Iterator j = c.getAssociationInstances().values().iterator();
        while(j.hasNext()) {
            count += ((java.util.LinkedList)j.next()).size();
        }
        assertEquals("Test result: An association instance has been removed", 0, count);
        
        c = new DomainContext();
        i = new Interpreter(null);
	mainProc = m.getDomain("TestDomain")
        .getClass("TestClass")
        .getStateMachine()
        .getState("unrelateTestClass2")
        .getProcedure();        
        try {
            runtime.Object obj = i.executeCreateAction( create, c );
	    i = new Interpreter(obj);
	    i.interpret(mainProc, c);
        } catch( LemRuntimeException e ) {
            fail( "Testing for deletion of association that does not exist.\n" +"Some LemRuntimeException occurred: " + e.getMessage() );
        }    
    }        
}

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
import metamodel.AssignmentAction;
import metamodel.LemException;
import metamodel.Model;
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
        
        // Grab the AssignmentAction
        AssignmentAction a = (AssignmentAction)(m.getDomain("TestDomain")
            .getClass("A")
            .getStateMachine()
            .getState("a")
            .getProcedure()
            .getActions()
            .getFirst());
        
        DomainContext c = new DomainContext();
        Interpreter i = new Interpreter();
        
        try { 
            Variable v = i.executeAssignmentAction(a, c);
            BigDecimal val = (BigDecimal)v.getValue();
            System.out.println( "The answer is " + val );
            assertEquals( "The result is 2 + 3 + 4 = 9", true, val.equals( new BigDecimal( "9.6" )));
        } catch( LemRuntimeException e ) {
            fail( "Runtime exception while executing test: " + e.getMessage() );
        }
        
    }
}

/*
 * AssignmentActionTest.java
 * JUnit based test
 *
 * Created on July 3, 2005, 10:59 PM
 */

package metamodel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jdns.xtuml.metamodel.AssignmentAction;
import org.jdns.xtuml.metamodel.BinaryOperation;
import org.jdns.xtuml.metamodel.Expression;
import org.jdns.xtuml.metamodel.LemException;
import org.jdns.xtuml.metamodel.Literal;
import org.jdns.xtuml.metamodel.Model;
import parser.ParseException;
import org.jdns.xtuml.tools.Lem;

/**
 *
 * @author sjr
 */
public class AssignmentActionTest extends junit.framework.TestCase {
    
    public AssignmentActionTest(String testName) {
        super(testName);
    }
    
    public void testAssociativity() {
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
        
        AssignmentAction a = (AssignmentAction)m.getDomain("TestDomain")
            .getClass("A")
            .getStateMachine()
            .getState("a")
            .getProcedure()
            .getActions()
            .getFirst();
        
        /* We expect 
         *          +
         *        +  4
         *       2 3
         */
        Expression e1 = a.getExpression(); 
        assertEquals( "root node is a binary operation", true, e1 instanceof BinaryOperation );
        
        Expression e2 = ((BinaryOperation)e1).getLeft();
        assertEquals( "left node is a binary operation", true, e2 instanceof BinaryOperation );
        
        Expression e3 = ((BinaryOperation)e2).getLeft();
        assertEquals( "left node is a literal", true, e3 instanceof Literal );
    
        assertEquals( "leftmost literal is 2", true, ((Literal)e3).getValue().equals("2.1"));
        
        Expression e4 = ((BinaryOperation)e2).getRight();
        assertEquals( "'middle' literal is 3", true, ((Literal)e4).getValue().equals("3.2"));
        
        Expression e5 = ((BinaryOperation)e1).getRight();
        assertEquals( "right node is a literal", true, e5 instanceof Literal);
        assertEquals( "rightmost literal is 4", true, ((Literal)e5).getValue().equals("4.3"));
        
        
        
    }
}

/*
 * EventTest.java
 * JUnit based test
 *
 * Created on 28 July 2005
 */

package metamodel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import metamodel.AssignmentAction;
import metamodel.LemException;
import metamodel.Model;
import metamodel.Procedure;
import parser.ParseException;
import tools.Lem;

/**
 *
 * @author npiggin
 */
public class EventTest extends junit.framework.TestCase {
    
    public EventTest(String testName) {
        super(testName);
    }
    
    public void testEvent() {
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
    }
}

package metamodel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import org.jdns.xtuml.metamodel.VariableDeclaration;
import org.jdns.xtuml.metamodel.AssignmentAction;
import org.jdns.xtuml.metamodel.LemException;
import org.jdns.xtuml.metamodel.Model;
import org.jdns.xtuml.metamodel.Procedure;
import org.jdns.xtuml.metamodel.Action;
import org.jdns.xtuml.metamodel.CreateAction;
import org.jdns.xtuml.metamodel.Domain;
import org.jdns.xtuml.metamodel.Scenario;
import parser.ParseException;
import org.jdns.xtuml.tools.Lem;

/**
 *
 * @author sjr
 */
public class ScenarioTest extends junit.framework.TestCase {
    
    public ScenarioTest(String testName) {
        super(testName);
    }
    
    /**
     * Tests the loading of a model with a single simple scenario
     */
    public void testLoadScenario() {
        Lem l = new Lem();
        Model m = null;
        
        try {
            m = l.parse( new FileInputStream( "regression/tests/ScenarioTest.lem" ));
        } catch( FileNotFoundException fnfe ) {
            fail( "Could not find model file " + fnfe.getMessage() );
        } catch( IOException e ) {
            fail( "Could not read model file: " + e.getMessage() );
        } catch( ParseException e ) {
            fail( "Could not parse model file: " + e.getMessage() );
        } catch( LemException e ) {
            fail( "Some LEMException occurred: " + e.getMessage() );
        }
        
	Domain d = m.getDomain( "TestDomain" );
	assertEquals( "Test domain has 1 scenario", 1, d.getScenarios().size() );
	Scenario s = (Scenario)d.getScenarios().get( "TestScenario" );
	assertEquals( "Test scenario is not null", true, s != null );
    }
}

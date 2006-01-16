/*
 * ClassTest.java
 * JUnit based test
 *
 * Created on June 15, 2005, 8:04 PM
 */

package metamodel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.jdns.xtuml.metamodel.LemException;
import org.jdns.xtuml.metamodel.Model;
import parser.ParseException;
import java.util.*;
import junit.framework.TestCase;
import tools.Lem;

/**
 *
 * @author sjr
 */
public class ClassTest extends TestCase {
    
    public ClassTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
    }

    protected void tearDown() throws Exception {
    }
    
    public void testGetAllGeneralisations() {
        Lem l = new Lem();
        Model m = null;
        
        try {
            m = l.parse( new FileInputStream( "regression/tests/ParentTestComplex.lem" ));
        } catch( FileNotFoundException fnfe ) {
            fail( "Could not find model file " + fnfe.getMessage() );
        } catch( IOException e ) {
            fail( "Could not read model file: " + e.getMessage() );
        } catch( ParseException e ) {
            fail( "Could not parse model file: " + e.getMessage() );
        } catch( LemException e ) {
            fail( "Some LEMException occurred: " + e.getMessage() );
        }
        
        HashMap classes = m.getDomain("TestDomain").getClasses();
        HashMap gens = new HashMap();
        
        org.jdns.xtuml.metamodel.Class a = (org.jdns.xtuml.metamodel.Class)classes.get( "A" );
        org.jdns.xtuml.metamodel.Class b = (org.jdns.xtuml.metamodel.Class)classes.get( "B" );
        org.jdns.xtuml.metamodel.Class c = (org.jdns.xtuml.metamodel.Class)classes.get( "C" );
        org.jdns.xtuml.metamodel.Class d = (org.jdns.xtuml.metamodel.Class)classes.get( "D" );
        org.jdns.xtuml.metamodel.Class e = (org.jdns.xtuml.metamodel.Class)classes.get( "E" );
        org.jdns.xtuml.metamodel.Class f = (org.jdns.xtuml.metamodel.Class)classes.get( "F" );
        org.jdns.xtuml.metamodel.Class g = (org.jdns.xtuml.metamodel.Class)classes.get( "G" );
        org.jdns.xtuml.metamodel.Class h = (org.jdns.xtuml.metamodel.Class)classes.get( "H" );
        org.jdns.xtuml.metamodel.Class i = (org.jdns.xtuml.metamodel.Class)classes.get( "I" );
        
        assertEquals( "A participates as a subclass in 0 generalisations", 0, a.getAllGeneralisations().size() );
        gens = b.getAllGeneralisations();
        assertEquals( "B participates only in R1", true, gens.size() == 1 && gens.get("R1") != null );
        gens.clear();
        gens = i.getAllGeneralisations();
        assertEquals( "I participates in R3 and R4", true, gens.size() == 2 && gens.get("R3") != null && gens.get( "R4" ) != null );
        gens.clear();
        gens = c.getAllGeneralisations();
        assertEquals( "C participates in R1 only", true, gens.size() == 1 && gens.get( "R1" ) != null );
    }
    
}

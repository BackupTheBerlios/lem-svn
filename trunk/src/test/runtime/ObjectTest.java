/*
 * ObjectTest.java
 * JUnit based test
 *
 * Created on June 15, 2005, 2:16 PM
 */

package runtime;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import metamodel.LemException;
import metamodel.Model;
import parser.ParseException;
import tools.Lem;

/**
 *
 * @author sjr
 */
public class ObjectTest extends junit.framework.TestCase {
    
    public ObjectTest(String testName) {
        super(testName);
    }
    
    protected void setUp() {
    }
    
    /**
     * Please refer to the regression/tests/ParentTest.lem
     */
    public void testValidClassesSimple() {
        Lem l = new Lem();
        Model m = null;
        
        try {
            m = l.parse( new FileInputStream( "regression/tests/ParentTest.lem" ));
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
        HashMap parents = new HashMap();
        
        metamodel.Class a = (metamodel.Class)classes.get( "A" );
        metamodel.Class b = (metamodel.Class)classes.get( "B" );
        metamodel.Class c = (metamodel.Class)classes.get( "C" );
        metamodel.Class d = (metamodel.Class)classes.get( "D" );
        metamodel.Class e = (metamodel.Class)classes.get( "E" );
        
        ArrayList classList = new ArrayList();
        classList.add( b );
        
        assertEquals( "B is a valid object in and of itself", true, runtime.Object.validClasses( classList ));
        
        classList.add( e );
        assertEquals( "B,E participate in the same hierarchy", false, runtime.Object.validClasses( classList ));
        
        classList.add( a );
        assertEquals( "A is abstract", true, a.isAbstract() );
        
        classList.clear();
        classList.add( b );
        classList.add( d );
        classList.add( e );
        assertEquals( "B, D and E participate in the same hierarchy", false, runtime.Object.validClasses( classList ));
    }
}

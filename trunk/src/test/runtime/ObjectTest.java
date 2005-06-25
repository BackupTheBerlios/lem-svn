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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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
    
    /**
     * Please refer to the regression/tests/ParentTest.lem
     */
    public void testValidClassesComplex() {
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
        HashMap parents = new HashMap();
        
        metamodel.Class a = (metamodel.Class)classes.get( "A" );
        metamodel.Class b = (metamodel.Class)classes.get( "B" );
        metamodel.Class c = (metamodel.Class)classes.get( "C" );
        metamodel.Class d = (metamodel.Class)classes.get( "D" );
        metamodel.Class e = (metamodel.Class)classes.get( "E" );
        metamodel.Class f = (metamodel.Class)classes.get( "F" );
        metamodel.Class g = (metamodel.Class)classes.get( "G" );
        metamodel.Class h = (metamodel.Class)classes.get( "H" );
        metamodel.Class i = (metamodel.Class)classes.get( "I" );
        
        ArrayList classList = new ArrayList();
        classList.add( b );
        assertEquals( "B is a valid object in and of itself", true, runtime.Object.validClasses( classList ));
        classList.add( h );
        assertEquals( "B,H is a valid object in and of itself", true, runtime.Object.validClasses( classList ));
        classList.add( f );
        assertEquals( "F,H participate in the same hierarchy", false, runtime.Object.validClasses( classList ));
    }
    
    public void testInstantiate() {
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
        HashMap parents = new HashMap();
        
        metamodel.Class a = (metamodel.Class)classes.get( "A" );
        metamodel.Class b = (metamodel.Class)classes.get( "B" );
        metamodel.Class c = (metamodel.Class)classes.get( "C" );
        metamodel.Class d = (metamodel.Class)classes.get( "D" );
        metamodel.Class e = (metamodel.Class)classes.get( "E" );
        metamodel.Class f = (metamodel.Class)classes.get( "F" );
        metamodel.Class g = (metamodel.Class)classes.get( "G" );
        metamodel.Class h = (metamodel.Class)classes.get( "H" );
        metamodel.Class i = (metamodel.Class)classes.get( "I" );
        
        ArrayList classList = new ArrayList();
        
        /** Test instantiating b */
        classList.add( b );
        try {
            runtime.Object o = new Object( classList );
            Collection instances = o.getInstances();
            
            assertEquals( "Should be two instances", 2, instances.size() );
            Iterator it = instances.iterator();
            
            assertEquals( "The first instance should be of type 'A'", a, ((runtime.Instance)it.next()).getInstanceClass() );
            assertEquals( "The second instance should be of type 'B'", b, ((runtime.Instance)it.next()).getInstanceClass() );
            
        } catch( LemRuntimeException ex ) {
            fail( "Failed to instantiate B: " + ex.getMessage() );
        }
        
        classList.clear();
        
        /** Test creating a "I, D". */
        classList.add( i );
        classList.add( d );
        try {
            runtime.Object o = new Object( classList );
            Collection instances = o.getInstances();
            
            assertEquals( "Should be 5 instances", 5, instances.size() );
            Iterator it = instances.iterator();

        } catch( LemRuntimeException ex ) {
            fail( "Failed to instantiate I,D: " + ex.getMessage() );
        }
    }
}

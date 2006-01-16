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
import org.jdns.xtuml.metamodel.LemException;
import org.jdns.xtuml.metamodel.Model;
import org.jdns.xtuml.runtime.LemRuntimeException;
import org.jdns.xtuml.runtime.Object;
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
        
        org.jdns.xtuml.metamodel.Class a = (org.jdns.xtuml.metamodel.Class)classes.get( "A" );
        org.jdns.xtuml.metamodel.Class b = (org.jdns.xtuml.metamodel.Class)classes.get( "B" );
        org.jdns.xtuml.metamodel.Class c = (org.jdns.xtuml.metamodel.Class)classes.get( "C" );
        org.jdns.xtuml.metamodel.Class d = (org.jdns.xtuml.metamodel.Class)classes.get( "D" );
        org.jdns.xtuml.metamodel.Class e = (org.jdns.xtuml.metamodel.Class)classes.get( "E" );
        
        ArrayList classList = new ArrayList();
        classList.add( b );
        
        assertEquals( "B is a valid object in and of itself", true, org.jdns.xtuml.runtime.Object.validClasses( classList ));
        
        classList.add( e );
        assertEquals( "B,E participate in the same hierarchy", false, org.jdns.xtuml.runtime.Object.validClasses( classList ));
        
        classList.add( a );
        assertEquals( "A is abstract", true, a.isAbstract() );
        
        classList.clear();
        classList.add( b );
        classList.add( d );
        classList.add( e );
        assertEquals( "B, D and E participate in the same hierarchy", false, org.jdns.xtuml.runtime.Object.validClasses( classList ));
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
        
        org.jdns.xtuml.metamodel.Class a = (org.jdns.xtuml.metamodel.Class)classes.get( "A" );
        org.jdns.xtuml.metamodel.Class b = (org.jdns.xtuml.metamodel.Class)classes.get( "B" );
        org.jdns.xtuml.metamodel.Class c = (org.jdns.xtuml.metamodel.Class)classes.get( "C" );
        org.jdns.xtuml.metamodel.Class d = (org.jdns.xtuml.metamodel.Class)classes.get( "D" );
        org.jdns.xtuml.metamodel.Class e = (org.jdns.xtuml.metamodel.Class)classes.get( "E" );
        org.jdns.xtuml.metamodel.Class f = (org.jdns.xtuml.metamodel.Class)classes.get( "F" );
        org.jdns.xtuml.metamodel.Class g = (org.jdns.xtuml.metamodel.Class)classes.get( "G" );
        org.jdns.xtuml.metamodel.Class h = (org.jdns.xtuml.metamodel.Class)classes.get( "H" );
        org.jdns.xtuml.metamodel.Class i = (org.jdns.xtuml.metamodel.Class)classes.get( "I" );
        
        ArrayList classList = new ArrayList();
        classList.add( b );
        assertEquals( "B is a valid object in and of itself", true, org.jdns.xtuml.runtime.Object.validClasses( classList ));
        classList.add( h );
        assertEquals( "B,H is a valid object in and of itself", true, org.jdns.xtuml.runtime.Object.validClasses( classList ));
        classList.add( f );
        assertEquals( "F,H participate in the same hierarchy", false, org.jdns.xtuml.runtime.Object.validClasses( classList ));
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
        
        org.jdns.xtuml.metamodel.Class a = (org.jdns.xtuml.metamodel.Class)classes.get( "A" );
        org.jdns.xtuml.metamodel.Class b = (org.jdns.xtuml.metamodel.Class)classes.get( "B" );
        org.jdns.xtuml.metamodel.Class c = (org.jdns.xtuml.metamodel.Class)classes.get( "C" );
        org.jdns.xtuml.metamodel.Class d = (org.jdns.xtuml.metamodel.Class)classes.get( "D" );
        org.jdns.xtuml.metamodel.Class e = (org.jdns.xtuml.metamodel.Class)classes.get( "E" );
        org.jdns.xtuml.metamodel.Class f = (org.jdns.xtuml.metamodel.Class)classes.get( "F" );
        org.jdns.xtuml.metamodel.Class g = (org.jdns.xtuml.metamodel.Class)classes.get( "G" );
        org.jdns.xtuml.metamodel.Class h = (org.jdns.xtuml.metamodel.Class)classes.get( "H" );
        org.jdns.xtuml.metamodel.Class i = (org.jdns.xtuml.metamodel.Class)classes.get( "I" );
        
        ArrayList classList = new ArrayList();
        
        /** Test instantiating b */
        classList.add( b );
        try {
            org.jdns.xtuml.runtime.Object o = new Object( classList );
            Collection instances = o.getInstances();
            
            assertEquals( "Should be two instances", 2, instances.size() );
            Iterator it = instances.iterator();
            
            assertEquals( "The first instance should be of type 'A'", a, ((org.jdns.xtuml.runtime.Instance)it.next()).getInstanceClass() );
            assertEquals( "The second instance should be of type 'B'", b, ((org.jdns.xtuml.runtime.Instance)it.next()).getInstanceClass() );
            
        } catch( LemRuntimeException ex ) {
            fail( "Failed to instantiate B: " + ex.getMessage() );
        }
        
        classList.clear();
        
        /** Test creating a "I, D". */
        classList.add( i );
        classList.add( d );
        try {
            org.jdns.xtuml.runtime.Object o = new Object( classList );
            Collection instances = o.getInstances();
            
            assertEquals( "Should be 5 instances", 5, instances.size() );
            Iterator it = instances.iterator();

        } catch( LemRuntimeException ex ) {
            fail( "Failed to instantiate I,D: " + ex.getMessage() );
        }
    }
}

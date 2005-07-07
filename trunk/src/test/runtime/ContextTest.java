/*
 * ContextTest.java
 * JUnit based test
 *
 * Created on 4 July 2005, 20:40
 */

package runtime;

import metamodel.StringType;
import metamodel.DataType;
import runtime.VariableFactory;

/**
 *
 * @author sjr
 */
public class ContextTest extends junit.framework.TestCase {
    
    public ContextTest(String testName) {
        super(testName);
    }
    
    public void testGetVariable() {
        Context c = new Context();
        Context d = new Context( c );
        
        try {
            Variable v = VariableFactory.newVariable( StringType.getInstance(), "\"\"" );
            c.addLocalVariable( "testVariable", v );
            
            // Now, grab that variable from d
            Variable t = d.getLocalVariable( "testVariable" );
            
            assertEquals( "The two variables should be the same", v, t );
            
        } catch( LemRuntimeException e ) {
            fail( "Caught LemRuntimeException while creating new variable: " + e.getMessage() );
        }
    }
}
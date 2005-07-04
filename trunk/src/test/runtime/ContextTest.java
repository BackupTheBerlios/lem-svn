/*
 * ContextTest.java
 * JUnit based test
 *
 * Created on 4 July 2005, 20:40
 */

package runtime;

import metamodel.StringType;

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
        Variable v = new Variable( StringType.getInstance() );
        
        c.addLocalVariable( "testVariable", v );
        
        // Now, grab that variable from d
        Variable t = d.getLocalVariable( "testVariable" );
        
        assertEquals( "The two variables should be the same", v, t );
    }
}

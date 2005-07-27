/*
 * ArbitraryIdTest.java
 *
 * Created on July 23, 2005, 5:19 PM
 */

/**
 *
 * @author thuanseah
 */
package runtime;

import runtime.ArbitraryIdVariable ;
import runtime.LemRuntimeException ;

public class ArbitraryIdTest extends junit.framework.TestCase{
    
    /** Creates a new instance of ArbitraryIdTest */
    public ArbitraryIdTest(String testName) {
        super(testName);
    }
    
    public void testGetType() {
        try {
            ArbitraryIdVariable id = new ArbitraryIdVariable() ;
            System.out.println(id.getType().getName()) ; 
        } catch( LemRuntimeException e ) {
            fail("Failed Because :" + e.getMessage() );
        }        
    }
    
    public void testGetValue() {
        ArbitraryIdVariable id1 = null ;
        ArbitraryIdVariable id2 = null ;
        try {
            id1 = new ArbitraryIdVariable() ;
            id2 = new ArbitraryIdVariable() ;
            System.out.println(id1.getValue().toString()) ; 
            System.out.println(id2.getValue().toString()) ; 
            assertEquals( "The two arbitrary ids are unique", false, id1.getValue() != id2.getValue());
        } catch( LemRuntimeException e ) {
            fail("Failed Because :" + e.getMessage() );
        }        
    }
}

/*
 * DateVariableTest.java
 * JUnit based test
 *
 * Created on 14 July 2005, 22:34
 */

package test.runtime;

import java.util.Date;
import org.jdns.xtuml.runtime.DateVariable ;
import org.jdns.xtuml.runtime.LemRuntimeException ;
/**
 *
 * @author shuku
 */
public class DateVariableTest extends junit.framework.TestCase {
    
    public DateVariableTest(String testName) {
        super(testName);       
    }
    
    
    public void testGetType() {
        DateVariable d = new DateVariable() ; 
        System.out.println( d.getType().getName())  ; 
    }
    
    public void testGetValue() {        
        DateVariable d = null ;
        try {
            d = new DateVariable("10-12-2030") ;
            Date date = d.getDate() ;
            String theDate = (String) d.getValue() ;
            System.err.println(theDate) ; 
            assertEquals( "DateVariable created and converted correctly", true, theDate.equals("10-12-2030") );
        } catch( LemRuntimeException e ) {
            fail("Failed Because :" + e.getMessage() );
        }
    }          
    
    
}

/*
 * TimestampType.java
 *
 * Created on January 31, 2004, 12:37 PM
 */

package metamodel;
/**
 * A class representing the Timestamp core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class TimestampType extends CoreDataType {
    
    /** singleton instance of TimestampType */
    private static TimestampType instance = null;
    
    /** Creates a new instance of TimestampType */
    private TimestampType() {
        name = "timestamp";
    }
    
    /** 
     * Return the single instance of TimestampType 
     *
     * @return the single instance of TimestampType 
     */
    public static synchronized TimestampType getInstance() {
        
        if ( instance == null )  {
            instance = new TimestampType();
        }
        return instance;
    }
}

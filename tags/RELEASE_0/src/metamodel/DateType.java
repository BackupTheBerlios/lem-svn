/*
 * DateType.java
 *
 * Created on January 31, 2004, 12:34 PM
 */

package metamodel;
/**
 * A class representing the Date core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class DateType extends CoreDataType {
    
    /** singleton instance of DateType */
    private static DateType instance = null;
    
    /** Creates a new instance of DateType */
    private DateType() {
        name = "date";
    }
    
    /** 
     * Return the single instance of DateType 
     *
     * @return the single instance of DateType 
     */
    public static synchronized DateType getInstance() {
        
        if ( instance == null )  {
            instance = new DateType();
        }
        return instance;
    }
}

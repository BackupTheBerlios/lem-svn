/*
 * StringType.java
 *
 * Created on January 31, 2004, 12:27 PM
 */

package metamodel;
/**
 * A class representing the String core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class StringType extends CoreDataType {
    
    /** singleton instance of StringType */
    private static StringType instance = null;
    
    /** Creates a new instance of StringType */
    private StringType() {
        name = "string";
    }
    
    /** 
     * Return the single instance of StringType 
     *
     * @return the single instance of StringType 
     */
    public static synchronized StringType getInstance() {
        
        if ( instance == null )  {
            instance = new StringType();
        }
        return instance;
    }
}

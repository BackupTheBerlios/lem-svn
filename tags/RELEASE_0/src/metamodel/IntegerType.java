/*
 * IntegerType.java
 *
 * Created on January 31, 2004, 12:30 PM
 */

package metamodel;
/**
 * A class representing the Integer core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class IntegerType extends CoreDataType {
    
    /** singleton instance of IntegerType */
    private static IntegerType instance = null;
    
    /** Creates a new instance of BooleanType */
    private IntegerType() {
        name = "integer";
    }
    
    /** 
     * Return the single instance of IntegerType 
     *
     * @return the single instance of IntegerType 
     */
    public static synchronized IntegerType getInstance() {
        
        if ( instance == null )  {
            instance = new IntegerType();
        }
        return instance;
    }
}


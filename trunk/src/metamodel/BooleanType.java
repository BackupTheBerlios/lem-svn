/*
 * BooleanType.java
 *
 * Created on January 31, 2004, 12:20 PM
 */

package metamodel;

/**
 * A class representing the Boolean core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class BooleanType extends CoreDataType {
    
    /** singleton instance of BooleanType */
    private static BooleanType instance = null;
    
    /** Creates a new instance of BooleanType */
    private BooleanType() {
        name = "boolean";
    }
   
    /** 
     * Return the single instance of BooleanType 
     *
     * @return the single instance of BooleanType 
     */
    public static synchronized BooleanType getInstance() {
        
        if ( instance == null )  {
            instance = new BooleanType();
        }
        return instance;
    }
}

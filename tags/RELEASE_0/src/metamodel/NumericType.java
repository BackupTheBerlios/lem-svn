/*
 * NumericType.java
 *
 * Created on November 6, 2004, 4:49 PM
 */

package metamodel;
/**
 * A class representing the Numeric core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class NumericType extends CoreDataType {
    
    /** singleton instance of NumericType */
    private static NumericType instance = null;
    
    /** Creates a new instance of NumericType */
    private NumericType() {
        name = "numeric";
    }
    
    /** 
     * Return the single instance of NumericType 
     *
     * @return the single instance of NumericType 
     */
    public static synchronized NumericType getInstance() {
        
        if ( instance == null )  {
            instance = new NumericType();
        }
        return instance;
    }
}


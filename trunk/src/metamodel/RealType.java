/*
 * RealType.java
 *
 * Created on January 31, 2004, 12:32 PM
 */

package metamodel;
/**
 * A class representing the Real core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class RealType extends CoreDataType {
    
    /** singleton instance of RealType */
    private static RealType instance = null;
    
    /** Creates a new instance of RealType */
    private RealType() {
        name = "real";
    }

    
    /** 
     * Return the single instance of RealType 
     *
     * @return the single instance of RealType 
     */
    public static synchronized RealType getInstance() {
        
        if ( instance == null )  {
            instance = new RealType();
        }
        return instance;
    }
}

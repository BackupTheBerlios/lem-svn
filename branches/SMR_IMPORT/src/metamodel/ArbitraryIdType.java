/*
 * ArbitraryIdType.java
 *
 * Created on January 31, 2004, 12:39 PM
 */


package metamodel;
/**
 * A class representing the ArbitaryId core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class ArbitraryIdType extends CoreDataType {
    
    /** singleton instance of ArbitraryIdType */
    private static ArbitraryIdType instance = null;
    
    /** Creates a new instance of ArbitraryIdType */
    private ArbitraryIdType() {
        name = "arbitrary_id";
    }
    
    /** 
     * Return the single instance of ArbitraryIdType 
     *
     * @return the single instance of ArbitraryIdType 
     */
    public static synchronized ArbitraryIdType getInstance() {
        
        if ( instance == null )  {
            instance = new ArbitraryIdType();
        }
        return instance;
    }
}

/*
 * ObjectReference.java
 *
 * Created on May 30, 2005
 */


package metamodel;
/**
 * A class representing the ObjectReference core data type. This class is a singleton. 
 *
 * @author  u3293115
 */
public class ObjectReferenceType extends CoreDataType {
    
    /** singleton instance of ArbitraryIdType */
    private static ObjectReferenceType instance = null;
    
    /** Creates a new instance of ObjectReferenceType */
    private ObjectReferenceType() {
        name = "objref";
    }
    
    /** 
     * Return the single instance of ObjectReferenceType 
     *
     * @return the single instance of ObjectReferenceType 
     */
    public static synchronized ObjectReferenceType getInstance() {
        
        if ( instance == null )  {
            instance = new ObjectReferenceType();
        }
        return instance;
    }
}

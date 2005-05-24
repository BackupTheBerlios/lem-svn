/*
 * DataType.java
 *
 * Created on January 31, 2004, 12:09 PM
 */

package metamodel;
/**
 * Defines a data type
 *
 * @author  smr
 */
public interface DataType {
    
    /**
     * Return the name of the DataType
     * @return the name of the datatype
     */
    public String getName();
    
    
    
    /**
     * Return the CoreDataType associated with this DataItem
     *
     * @return the CoreDataType associated with this DataItem
     */
    public abstract CoreDataType getCoreDataType();
    
}

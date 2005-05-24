/*
 * MetaModelException.java
 *
 * Created on January 2, 2005, 3:09 PM
 */

package metamodel;

/**
 * Instances of this class are thrown when various "semantic" errors occur. For example,
 * adding an attribute of type "double" to a class when no type "double" is defined in the domain.
 * @author smr
 */
public class MetaModelException extends Exception {
    
    /** Creates a new instance of MetaModelException */
    public MetaModelException() {
        super();
    }
    
    /** 
     * Creates a new exception based on the supplied message 
     *
     * @param message describing the exception
     */
    public MetaModelException( String message ) {
        super( message );
    }
    
}

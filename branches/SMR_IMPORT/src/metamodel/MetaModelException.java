/*
 * MetaModelException.java
 *
 * Created on January 2, 2005, 3:09 PM
 */

package metamodel;

/**
 *
 * @author  smr
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

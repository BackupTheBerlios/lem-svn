/*
 * SubclassRole.java
 *
 * Created on April 25, 2004, 4:31 PM
 */

package metamodel;
/**
 * Represents a classes participation in a Generalisation as a subclass class
 *
 * @author  smr
 */
public class SubclassRole extends GeneralisationRole {
    
    /** 
     * Creates a new instance of SubclassRole using supplied attributes
     *
     * @param participantClass for this GeneralisationRole
     * @param aGeneralisation in which the class is participating
     */
    public SubclassRole( Class participantClass, Generalisation aGeneralisation ) {
        
        super( participantClass, aGeneralisation );
    }
    
}

/*
 * SuperclassRole.java
 *
 * Created on April 25, 2004, 4:28 PM
 */

package metamodel;
/**
 * Represents a classes participation in a Generalisation as a super class
 *
 * @author  smr
 */
public class SuperclassRole extends GeneralisationRole {
    
    /** 
     * Creates a new instance of SuperclassRole using supplied attributes
     *
     * @param participantClass for this GeneralisationRole
     * @param aGeneralisation in which the class is participating
     */
    public SuperclassRole( Class participantClass, Generalisation aGeneralisation ) {
        
        super( participantClass, aGeneralisation );
    }
    
}

/*
 * ParticipatingClassRole.java
 *
 * Created on April 26, 2004, 2:48 PM
 */

package metamodel;
import java.util.*;

/**
 * Represents one class in a binary association
 * @author  smr
 */
public class ParticipatingClassRole extends AssociationRole {
    
    /** the perspectives to which this Participating class role is attached
     * the is a maximum of two (for a binary reflexive assocaiton)
     */
    private Vector perspectives = new Vector();
    
    /** 
     * Creates a new instance of ParticipatingClassRole using supplied attributes
     *
     * @param participantClass for this ParticipatingClassRole
     * @param anAssociation in which the class is participating
     */
    public ParticipatingClassRole( Class participantClass, Association anAssociation ) {
        
        super( participantClass, anAssociation );
    }
    
    /**
     * Add the supplied perspective to this ParticipatingClassRole
     *
     * @param aPerspective to add
     * @throws LemException if the maximum number of perspectives is exceeded
     */
    public void add( Perspective aPerspective ) throws LemException {
        
        if ( perspectives.size() > 1 ) {
            throw new LemException( "Too many perspectives for class " + getClass().getName() + 
                                        " in Association " + getAssociation().getName() );
        }
        
        perspectives.add( aPerspective );
    }
    
}

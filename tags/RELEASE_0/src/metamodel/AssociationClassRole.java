/*
 * AssociationClassRole.java
 *
 * Created on May 1, 2004, 3:06 PM
 */

package metamodel;
/**
 * Instances of AssociationClassRole represent the relationship between
 * an association and the corresponding Association Class.
 *
 * @author  smr
 */
public class AssociationClassRole extends AssociationRole {
    

    
    /** 
     * Creates a new instance of AssociationClassRole using supplied attributes
     *
     * @param participantClass for this AssociationClassRole
     * @param anAssociation in which the class is participating
     */
    public AssociationClassRole( Class participantClass, Association anAssociation ) {
        
        super( participantClass, anAssociation);
    }
    
    /** Getter for property associationClass.
     * @return Value of property associationClass.
     *
     */
    public Class getAssociationClass() {
        return getParticipant();
    }

    
}

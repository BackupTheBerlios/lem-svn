/*
 * AssociationRole.java
 *
 * Created on April 26, 2004, 2:41 PM
 */

package metamodel;
/**
 * Representats the participation of a class in an Association relationship
 *
 * @author  smr
 */
public abstract class AssociationRole {

    /** the class participating in the association relationship */
    private Class participant = null;
    
    /** the generalisation in which the class has a role */
    private Association association = null;
    
    /** 
     * Creates a new instance of AssociationRole using supplied attributes
     *
     * @param participantClass for this AssociationRole
     * @param anAssociation in which the class is participating
     */
    public AssociationRole( Class participantClass, Association anAssociation ) {
        
        participant = participantClass;
        association = anAssociation;
    }
    
    /** Getter for property association.
     * @return Value of property association.
     *
     */
    public Association getAssociation() {
        return association;
    }

    
    /** Getter for property participant.
     * @return Value of property participant.
     *
     */
    public Class getParticipant() {
        return participant;
    }
}

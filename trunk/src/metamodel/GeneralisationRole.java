/*
 * GeneralisationRole.java
 *
 * Created on April 25, 2004, 4:08 PM
 */

package metamodel;
/**
 * Representats the participation of a class in a Generalisation relationship
 *
 * @author  smr
 */
public abstract class GeneralisationRole {
    
    /** the class participating in the generalisation relationship */
    private Class participant = null;
    
    /** the generalisation in which the class has a role */
    private Generalisation generalisation = null;
    
    /** 
     * Creates a new instance of GeneralisationRole using supplied attributes
     *
     * @param participantClass for this GeneralisationRole
     * @param aGeneralisation in which the class is participating
     */
    public GeneralisationRole( Class participantClass, Generalisation aGeneralisation ) {
        
        participant = participantClass;
        generalisation = aGeneralisation;
    }
    
    /** Getter for property generalisation.
     * @return Value of property generalisation.
     *
     */
    public Generalisation getGeneralisation() {
        return generalisation;
    }

    
    /** Getter for property participant.
     * @return Value of property participant.
     *
     */
    public Class getParticipant() {
        return participant;
    }

}

/*
 * Generalisation.java
 *
 * Created on April 25, 2004, 3:25 PM
 */

package metamodel;
import java.util.HashMap;
/**
 * Represents a Generalisation/Specialisation relationship
 *
 * @author  smr
 */
public class Generalisation extends Relationship {
    
    /** the superclass in this Generalisation */
    private SuperclassRole superclassRole = null;
    
    /** 
     * The participants in this Generalisations.
     * These will be instances of GeneralisationRole subclasses
     * 
     * There must be one, and only one, instance of SuperclassRole
     * and there must be two or more instances of SubclassRole
     */
    private HashMap participants = new HashMap();
    
    /** Creates a new instance of Generalisation */
    public Generalisation() {
    }
    
    /**
     * Add a subclass to the list of subclasses
     *
     * @param theSubclassRole to be added
     * @throws XtUmlException if the subclass already exists
     */
    public void add( SubclassRole theSubclassRole ) throws LemException {
        
        Object key = theSubclassRole.getParticipant().getName();
        
        if ( participants.containsKey( key ) ) {
            throw new LemException( "Class " + key + " already included in Generalisation " + getName() );
        } else {
            participants.put( key, theSubclassRole );
            theSubclassRole.getParticipant().add( theSubclassRole );
        }
    }
    
    /** Getter for property superClassRole.
     * @return Value of property superClassRole.
     *
     */
    public SuperclassRole getSuperClassRole() {
        return superclassRole;
    }
    
    /** Setter for property superClassRole.
     * @param aSuperclassRole New value of property superClassRole.
     *
     */
    public void setSuperClassRole(SuperclassRole aSuperclassRole) {
        
        // remove any existing superclass
        
        if ( null != superclassRole ) {
            participants.remove( superclassRole.getParticipant().getName() );
        }
        
        // set the new superClass
        
        this.superclassRole = aSuperclassRole;
        
        // and add it to the list of participants
        
        participants.put( this.superclassRole.getParticipant().getName(), this.superclassRole );
        superclassRole.getParticipant().add( superclassRole );
    }
    
    /**
     * Return the superclass of this generalisation
     *
     * @return the superclass of this generalisation
     */
    public metamodel.Class getSuperclass() {
        return superclassRole.getParticipant();
    }
    
}

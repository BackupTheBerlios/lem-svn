/*
 * Association.java
 *
 * Created on December 6, 2003, 5:33 PM
 */


package metamodel;

import java.util.*;

/**
 * Represents an xtUML assocation
 * @author  smr
 */
public class Association extends Relationship {
    
    /** the active perspective of this Association */
    private ActivePerspective activePerspective = null;
    
    /** the passive perspective of this Association */
    private PassivePerspective passivePerspective = null;
    
    /** an Association Class is represented by an AssociationClassRole */
    private AssociationClassRole associationClassRole = null;
    
       
    /** Getter for property activePerspective.
     * @return Value of property activePerspective.
     *
     */
    public ActivePerspective getActivePerspective() {
        return activePerspective;
    }    
    
    /** Setter for property activePerspective.
     * @param activePerspective New value of property activePerspective.
     *
     */
    public void setActivePerspective(ActivePerspective activePerspective) {
        this.activePerspective = activePerspective;
        activePerspective.setAssociation( this );
    }    
    
    /** Getter for property associationClassRole.
     * @return Value of property associationClassRole.
     *
     */
    public AssociationClassRole getAssociationClassRole() {
        return associationClassRole;
    }    
    
    /** Setter for property associationClassRole.
     * @param associationClassRole New value of property associationClassRole.
     *
     */
    public void setAssociationClassRole(AssociationClassRole associationClassRole) {
        this.associationClassRole = associationClassRole;
    }    

    /** Getter for property passivePerspective.
     * @return Value of property passivePerspective.
     *
     */
    public PassivePerspective getPassivePerspective() {
        return passivePerspective;
    }
    
    /** Setter for property passivePerspective.
     * @param passivePerspective New value of property passivePerspective.
     *
     */
    public void setPassivePerspective(PassivePerspective passivePerspective) {
        this.passivePerspective = passivePerspective;
        passivePerspective.setAssociation( this );
    }
    
    /**
     * Return true if this is a reflexive association. A reflexsive association involves
     * the same class at each perspective.
     *
     * @return true if this is a reflexive association
     */
    public boolean isReflexive() {
        
        return activePerspective.getDomainClass() == passivePerspective.getDomainClass();
    }
    
    
    /**
     * Return an array of direct participants in this association. The direct participants
     * is/are the perspective class/es (end point class/es) and any association class.
     *
     * @return an array of direct participants in this association
     */
    public Class [] getParticipants() {
        
        int count = 1;
        
        if ( ! isReflexive() )
            count++;
        if ( null != associationClassRole )
            count++;
        
        Class [] result = new Class[ count ];
        count = 0;
        result[ count ] = activePerspective.getDomainClass();
        if ( activePerspective.getDomainClass() != passivePerspective.getDomainClass() ) {
            count++;
            result[ count ] = passivePerspective.getDomainClass();
        }
        if ( null != associationClassRole ) {
            count++;
            result[ count ] = associationClassRole.getParticipant();
        }
        
        
        return result;
     }
    
    
    
}

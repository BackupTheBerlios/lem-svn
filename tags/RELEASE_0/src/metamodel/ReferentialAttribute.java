/*
 * ReferentialAttribute.java
 *
 * Created on November 8, 2004, 11:42 PM
 */

package metamodel;

/**
 * A reference to a data attribute contained in a related class. Referential attributes
 * are used in identity specifications and may also be referenced in action language.
 *
 * @author  smr
 */
public class ReferentialAttribute extends Attribute {
    
    
    /** An association role is traversed to gain access to the required attribute */
    private AssociationRole traversed = null;
    
    /** The attribute referenced */
    private Attribute referenced = null;
   
    /** Creates a new instance of ReferentialAttribute */
    public ReferentialAttribute() {
    }

    /**
     * Return the AssociationRole traversed to gain access to the remote attribute
     *
     * @return the AssociationRole traversed to gain access to the remote attribute
     */
    public AssociationRole getTraversed() {
        return traversed;
    }

    /**
     * Set the AssociationRole traversed to gain access to the remote attribute
     *
     * @param traversed - the AssociationRole traversed to gain access to the remote attribute
     */
    public void setTraversedPerspective(AssociationRole traversed) {
        this.traversed = traversed;
    }
    
    /**
     * Return the attribute referenced by thie ReferentialAttribute
     *
     * @return the attribute referenced by thie ReferentialAttribute
     */
    public Attribute getReferenced() {
        return referenced;
    }

    /**
     * set the attribute referenced by thie ReferentialAttribute
     *
     * @param referenced - the attribute referenced by thie ReferentialAttribute
     */
    public void setReferenced(Attribute referenced) {
        this.referenced = referenced;
    }
    
    /** Getter for property type.
     * @return Value of property type.
     *
     */
    public DataType getType() {
        return referenced.getType();
    }
    
    /**
     * Return the CoreDataType associated with this Referential attribute
     *
     * @return the CoreDataType associated with this Referential attribute
     */
    public CoreDataType getCoreDataType() {
        return referenced.getCoreDataType();
    }
    
    
}

/*
 * Attribute.java
 *
 * Created on January 31, 2004, 3:23 PM
 */
package metamodel;

import java.util.*;

/**
 * Represents the attribute of a class
 *
 * @author  smr
 */
public abstract class Attribute extends DataItem {
    
    /** the identifiers to which this attribute supplies a value */
    protected ArrayList identifiers = new ArrayList();
    
  
    /** Creates a new instance of Attribute */
    public Attribute() {
    }
    
    
    /** 
     * Return an ArrayList containing the Identifiers in which this 
     * attribute participates. The ArrayList may be empty if this
     * attribute does not supply a value to any identifier.
     *
     * @return an ArrayList containing the Identifiers in which this 
     * attribute participates.
     */
    public ArrayList getIdentifiers() {
        return identifiers;
    }
    
    /**
     * Add the supplied identifer to the list of identifiers to which this
     * attribute contributes a value.
     *
     * @param theIdentifer to which this attribute supplies a value
     */
    public void addIdentifier( Identifier theIdentifier ) {
        identifiers.add( theIdentifier );
    }
}

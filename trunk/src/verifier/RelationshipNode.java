/*
 * RelationshipNode.java
 *
 * Created on 27 April 2005, 17:28
 */

package verifier;

import metamodel.Relationship;

/**
 *
 * @author sjr
 */
public class RelationshipNode extends AbstractDescriptionNode{
    
    Relationship relationship = null;
    
    /** Creates a new instance of RelationshipNode */
    public RelationshipNode( metamodel.Relationship r ) {
        this.relationship = r;
    }
    
    public String toString() {
        return "Relationship " + relationship.getName(); 
    }
    
    public String getDescription()
    {
        return relationship.getDescription();
    }
}

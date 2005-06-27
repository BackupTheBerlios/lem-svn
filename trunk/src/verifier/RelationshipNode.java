/*
 * RelationshipNode.java
 *
 * Created on 27 April 2005, 17:28
 */

package verifier;

import javax.swing.JPopupMenu;
import metamodel.Relationship;

/**
 * Tree node appearing inside a DomainNode. For graphically representing and 
 * "holding" a LEM Relationship object. Has no children.
 * @author sjr
 */
public class RelationshipNode extends AbstractDescriptionNode{
    
    Relationship relationship = null;
    
    /**
     * Creates a new instance of RelationshipNode. 
     * @param r LEM Relationship object to be contained.
     */
    public RelationshipNode( metamodel.Relationship r ) {
        this.relationship = r;
    }
    /**
    * Returns name property of LEM Relationship object.
    * @return the Relationship name.
    */    
    public String toString() {
        return "Relationship " + relationship.getName(); 
    }
        /**
    * Returns the description property of the LEM Relationship object.
    * @return the Relationship description.
    */
    public String getDescription()
    {
        if (relationship.getDescription() != null)
            return trim(relationship.getDescription());
        else 
            return "" ; 
    }
    /**
    * Returns the ContextMenu based on the LEM Relationship object.
    * @return the Relationship ContextMenu.
    */    
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

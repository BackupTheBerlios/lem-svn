/*
 * AttributeNode.java
 *
 * Created on 27 April 2005, 17:20
 */

package verifier;

import javax.swing.JPopupMenu;
import metamodel.Attribute;

/**
 * Tree node for representing and "holding" LEM attribute objects. Has no children.
 * @author sjr
 */
public class AttributeNode extends AbstractDescriptionNode {
    
    Attribute attribute = null;
    
    /** Creates a new instance of AttributeNode */
    public AttributeNode( Attribute a ) {
        this.attribute = a;
    }
    /** Returns the name of the attribute followed by the type of the attribute */
    public String toString() {
        return attribute.getName() + " : " + attribute.getType().getName();
    }
    /** 
     * Returns the description of the attribute, will return an empty string if
     * description is null.
     */
    public String getDescription() {        
        if (attribute.getDescription() != null)
            return trim(attribute.getDescription() );        
        else
            return "" ;                         
    }
    /**
     * Creates and returns a JPopupMenu based on the specified attribute.
     */
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

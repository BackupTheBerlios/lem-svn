/*
 * AttributeNode.java
 *
 * Created on 27 April 2005, 17:20
 */

package verifier;

import javax.swing.JPopupMenu;
import metamodel.Attribute;

/**
 *
 * @author sjr
 */
public class AttributeNode extends AbstractDescriptionNode {
    
    Attribute attribute = null;
    
    /** Creates a new instance of AttributeNode */
    public AttributeNode( Attribute a ) {
        this.attribute = a;
    }
    
    public String toString() {
        return attribute.getName() + " : " + attribute.getType().getName();
    }
 
    public String getDescription() {        
        if (attribute.getDescription() != null)
            return trim(attribute.getDescription() );        
        else
            return "" ;                         
    }
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

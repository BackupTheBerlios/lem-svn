/*
 * AttributeNode.java
 *
 * Created on 27 April 2005, 17:20
 */

package verifier;

import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.Attribute;

/**
 *
 * @author sjr
 */
public class AttributeNode extends DefaultMutableTreeNode {
    
    Attribute attribute = null;
    
    /** Creates a new instance of AttributeNode */
    public AttributeNode( Attribute a ) {
        this.attribute = a;
    }
    
    public String toString() {
        return attribute.getName() + " : " + attribute.getType().getName();
    }
    
}

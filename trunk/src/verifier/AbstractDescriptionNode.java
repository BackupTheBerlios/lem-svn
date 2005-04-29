/*
 * AbstractDescriptionNode.java
 *
 * Created on 29 April 2005, 13:22
 */

package verifier;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author David Gavin
 */
public abstract class AbstractDescriptionNode extends DefaultMutableTreeNode{
    
    /** Creates a new instance of AbstractDescriptionNode */
    public AbstractDescriptionNode() {
    }
    
    public abstract String getDescription();
    
    
}

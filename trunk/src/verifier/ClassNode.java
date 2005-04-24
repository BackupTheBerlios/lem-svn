/*
 * ClassNode.java
 *
 * Created on April 24, 2005, 3:54 PM
 */

package verifier;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author sjr
 */
public class ClassNode extends DefaultMutableTreeNode {
    
    metamodel.Class thisClass;
    
    /** Creates a new instance of ClassNode */
    public ClassNode( metamodel.Class c ) {
        this.thisClass = c;
    }
    
    public String toString() {
        return "Class " + thisClass.getName();
    }
}

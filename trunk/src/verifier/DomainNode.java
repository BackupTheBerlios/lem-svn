/*
 * DomainNode.java
 *
 * Created on April 24, 2005, 3:46 PM
 */

package verifier;

import java.util.Iterator;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.Domain;

/**
 *
 * @author sjr
 */
public class DomainNode extends DefaultMutableTreeNode {
    
    Domain domain;
    
    /** Creates a new instance of DomainNode */
    public DomainNode( Domain d ) {
        this.domain = d;
        
        Iterator i = d.getClasses().values().iterator();
        while( i.hasNext() ) {
            add( new ClassNode( (metamodel.Class)i.next() ));
        }
    }
    
    public String toString() {
        return "Domain " + domain.getName();
    }
}

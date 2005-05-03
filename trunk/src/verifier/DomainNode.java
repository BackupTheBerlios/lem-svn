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
public class DomainNode extends AbstractDescriptionNode {
    
    Domain domain;
    
    /** Creates a new instance of DomainNode */
    public DomainNode( Domain d ) {
        this.domain = d;
        
        DefaultMutableTreeNode classLevel = new DefaultMutableTreeNode( "Classes" );
        
        Iterator i = d.getClasses().values().iterator();
        while( i.hasNext() ) {
            classLevel.add( new ClassNode( (metamodel.Class)i.next() ));
        }
        
        add( classLevel );
        
        
        DefaultMutableTreeNode relationshipLevel = new DefaultMutableTreeNode( "Relationships" );
        
        i = d.getRelationships().values().iterator();
        while( i.hasNext() ) {
            relationshipLevel.add( new RelationshipNode( (metamodel.Relationship) i.next() ));
        }
        
        add( relationshipLevel );
        
        //add( new DefaultMutableTreeNode("Hello, world" ));
    }
    
    public String toString() {
        return "Domain " + domain.getName();
    }
    
    public String getDescription(){
        return domain.getDescription();
    }
    

}

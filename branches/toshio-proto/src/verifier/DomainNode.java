/*
 * DomainNode.java
 *
 * Created on April 24, 2005, 3:46 PM
 */

package verifier;

import java.util.Iterator;
import javax.swing.JPopupMenu;
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
        
        //DefaultMutableTreeNode subsystemLevel = new DefaultMutableTreeNode( "Subsystems" );
        
        Iterator i = d.getSubsystems().values().iterator();
        while( i.hasNext() ) {
            add( new SubsystemNode( (metamodel.Subsystem)i.next() ));
        }
        
        DefaultMutableTreeNode relationshipLevel = new DefaultMutableTreeNode( "Relationships" );
        i = d.getRelationships().values().iterator();
        while( i.hasNext() ) {
            relationshipLevel.add( new RelationshipNode( (metamodel.Relationship)i.next() ));
        }
        
        add( relationshipLevel );
    }
    
    public String toString() {
        return "Domain " + domain.getName();
    }
    
    public String getDescription(){
        if (domain.getDescription() != null)
            return trim(domain.getDescription());
        else
            return "" ;
    }
    
    public JPopupMenu getContextMenu() {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

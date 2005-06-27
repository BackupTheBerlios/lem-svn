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
 * Tree node appearing inside a ModelTreeNode. For graphically representing and 
 * "holding" LEM Domain objects. Has SubsystemNode and RelationshipNode children.
 * @author sjr
 */
public class DomainNode extends AbstractDescriptionNode {
	
	Domain domain;
	
	/** 
         * Creates a new instance of DomainNode. Creates SubsystemNode branches 
         * based on LEM Domain object and lists all RelationshipNodes of Domain 
         * under a default tree node named relationshipLevel.
         * @param d the LEM Domain object.
         */
	public DomainNode( Domain d ) {
		this.domain = d;
		
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
	/**
         * Returns name property of LEM Domain object.
         * @return the Domain name.
         */
	public String toString() {
		return "Domain " + domain.getName();
	}
	/**
         * Returns the description property of the LEM Domain object.
         * @return the Domain description.
         */
	public String getDescription(){
		if (domain.getDescription() != null)
			return trim(domain.getDescription());
		else
			return "" ;
	}
	/**
         * Returns the ContextMenu based on the LEM Domain object.
         * @return the Domain ContextMenu.
         */
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		return ContextMenu;
	}
}

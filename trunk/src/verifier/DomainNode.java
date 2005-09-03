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
        /**The LEM domain object that DomainNode contains*/	
	Domain domain;
	private ModelTreePanel view ; 
	/** 
         * Creates a new instance of DomainNode. Creates SubsystemNode branches 
         * based on LEM Domain object and lists all RelationshipNodes of Domain 
         * under a default tree node named relationshipLevel.
         * @param d the LEM Domain object.
         */
	public DomainNode( Domain d , ModelTreePanel view ) {
		this.domain = d;
		this.view = view ; 
		Iterator i = d.getSubsystems().values().iterator();
		while( i.hasNext() ) {
			add( new SubsystemNode( (metamodel.Subsystem)i.next() ));
		}
		
		
		i = d.getRelationships().values().iterator();
                if(i.hasNext()){
                    DefaultMutableTreeNode relationshipLevel = new DefaultMutableTreeNode( "Relationships" );
                    while( i.hasNext() ) {
    			relationshipLevel.add( new RelationshipNode( (metamodel.Relationship)i.next() ));
                    }
                    add( relationshipLevel );
                }
                
                i = d.getScenarios().values().iterator();
                if(i.hasNext()){
                    DefaultMutableTreeNode scenarioLevel = new DefaultMutableTreeNode( "Scenarios" );
                    while( i.hasNext() ) {
                        ScenarioNode s = new ScenarioNode( (metamodel.Scenario)i.next() ) ;
                        s.setView ( view ) ; 
                        scenarioLevel.add( s );
                    	//scenarioLevel.add( new ScenarioNode( (metamodel.Scenario)i.next() ));
                    }
                    add( scenarioLevel );
                }
	}
        
        public void setView(ModelTreePanel view) {
            this.view = view ; 
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

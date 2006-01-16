/*
 * DomainNode.java
 *
 * Created on April 24, 2005, 3:46 PM
 *
 * Copyright (C) 2005 James Ring
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 */

package org.jdns.xtuml.verifier;

import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdns.xtuml.metamodel.Domain;

/**
 * Tree node appearing inside a ModelTreeNode. For graphically representing and 
 * "holding" LEM Domain objects. Has SubsystemNode and RelationshipNode children.
 * @author James Ring
 */
public class DomainNode extends AbstractDescriptionNode {
        /**The LEM domain object that DomainNode contains*/	
	private Domain domain;
	private Eleminator eleminator ; 
	
	/** 
         * Creates a new instance of DomainNode. Creates SubsystemNode branches 
         * based on LEM Domain object and lists all RelationshipNodes of Domain 
         * under a default tree node named relationshipLevel.
         * @param d the LEM Domain object.
         */
	public DomainNode( Domain d , Eleminator inEleminator ) {
		this.domain = d;
		eleminator = inEleminator ; 
		Iterator i = d.getSubsystems().values().iterator();
		while( i.hasNext() ) {
			add( new SubsystemNode( (org.jdns.xtuml.metamodel.Subsystem)i.next() ));
		}
		
		
		i = d.getRelationships().values().iterator();
                if(i.hasNext()){
                    DefaultMutableTreeNode relationshipLevel = new DefaultMutableTreeNode( "Relationships" );
                    while( i.hasNext() ) {
    			relationshipLevel.add( new RelationshipNode( (org.jdns.xtuml.metamodel.Relationship)i.next() ));
                    }
                    add( relationshipLevel );
                }
                
                i = d.getScenarios().values().iterator();
                if(i.hasNext()){
                    DefaultMutableTreeNode scenarioLevel = new DefaultMutableTreeNode( "Scenarios" );
                    while( i.hasNext() ) {
                        ScenarioNode s = new ScenarioNode( (org.jdns.xtuml.metamodel.Scenario)i.next(), eleminator ) ;                        
                        scenarioLevel.add( s );
                    	//scenarioLevel.add( new ScenarioNode( (metamodel.Scenario)i.next() ));
                    }
                    add( scenarioLevel );
                }
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

/*
 * ClassNode.java
 *
 * Created on April 24, 2005, 3:54 PM
 *
 * Copyright (C) 2005 Steven Ring
 * Copyright (C) 2005 Shokouhmand Torabi
 * Copyright (C) 2005 David Gavin
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

package verifier;

import java.awt.Color;
import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import org.jdns.xtuml.metamodel.StateMachine;
import org.jdns.xtuml.metamodel.Event ;

/**
 * Tree node appearing inside a SubsystemNode. For representing and "holding" a
 * LEM Class object. Has AttributeNode, StateMachineNode and EventsTreeNode as
 * children.
 * @author Steven Ring
 */
public class ClassNode extends AbstractDescriptionNode {
	/**The LEM Class object that ClassNode contains*/
	org.jdns.xtuml.metamodel.Class thisClass;
	/**The StyledDocument object that ClassNode maintains*/
	private StyledDocument attributeListing ;
	
	/**
	 * Creates a new instance of ClassNode. Examines LEM Class object and creates
	 * subtrees based on the objects attributes, state machine and events.
	 * @param c LEM class object to be contained.
	 */
	public ClassNode( org.jdns.xtuml.metamodel.Class c ) {
		this.thisClass = c;
		attributeListing = new StyledDocument() ;		
		Iterator i = c.getAllAttributes().iterator();		
		while( i.hasNext() ) {
			AttributeNode attribute =  new AttributeNode( (org.jdns.xtuml.metamodel.Attribute) i.next(), null , null ) ;
			add( attribute);
			attributeListing.append(attribute.getStyledDocument() ) ;
		}
		
		StateMachine m = thisClass.getStateMachine();
		if( m != null ) {
			add( new StateMachineNode( m ));
		}
		
		Iterator j = thisClass.getEvents().iterator() ;
		
		if( !j.hasNext() )
			return;
		
		DefaultMutableTreeNode eventLevel = new DefaultMutableTreeNode( "Events" );
		
		while ( j.hasNext() ) {
			
			eventLevel.add( new EventNode((Event)j.next()) ) ;
		}
		add( eventLevel );		
	}
	/**
	 * Returns Name property of LEM Class object.
	 * @return the name of the Class.
	 */
	public String toString() {
		return "Class " + thisClass.getName();
	}
	/**
	 * Returns Description property of LEM Class object.
	 * @return the description of the Class.
	 */
	public String getDescription(){
		if (thisClass.getDescription() != null )
			return trim(thisClass.getDescription());
		else
			return "" ;
	}
	/**
	 * Returns StyledDocument object containing preformatted LEM Class description,
	 * followed by the list of attributes that the Class has.
	 * @return the StyledDocument of the ClassNode.
	 */
	public StyledDocument getStyledDocument() {
		StyledDocument doc = new StyledDocument() ;
		SimpleAttributeSet s = new SimpleAttributeSet();
		StyleConstants.setFontFamily(s, "Times New Roman");
		StyleConstants.setFontSize(s, 14);
		StyleConstants.setBold(s, true);
		StyleConstants.setForeground(s, Color.blue);
		StyledElement element = new StyledElement(toString()+"\n" , s) ;
		doc.addStyle(element) ;
		
		SimpleAttributeSet s1 = new SimpleAttributeSet();
		StyleConstants.setFontFamily(s1, "Times New Roman");
		StyleConstants.setFontSize(s1, 14);
		StyleConstants.setBold(s1, false);
		StyleConstants.setForeground(s1, Color.black);
		StyledElement element1 = new StyledElement(getDescription() , s1) ;
		doc.addStyle(element1) ;
		
		StyledElement element2 = new StyledElement("\n-------------- Attributes ----------------\n" , s1) ;
		doc.addStyle(element2) ;
		
		doc.append(attributeListing) ;
		return doc ;
	}
	/**
	 * Creates and returns a JPopupMenu based on the specified Class.
	 * @return the ContextMenu of the ClassNode.
	 */
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		return ContextMenu;
	}
}

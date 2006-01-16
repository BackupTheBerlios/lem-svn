/*
 * InstanceNode.java
 *
 * Created on September 9, 2005, 2:51 PM
 *
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
import org.jdns.xtuml.metamodel.Association;
import org.jdns.xtuml.metamodel.Attribute;
import org.jdns.xtuml.metamodel.State;
import org.jdns.xtuml.metamodel.StateMachine;
import runtime.Instance;

/**
 * Tree node appearing appearing inside an ObjectNode. For graphically
 * representing and "holding" LEM Instance objects. Has ContextAssociationNode,
 * AttributeNode and ContextStateMachineNode as a children.
 * @author Shokouhmand Torabi
 */
public class InstanceNode extends AbstractDescriptionNode {
	
        /**The LoggerFrame that InstanceNode contains.*/
	private LoggerFrame frame ;
        /**The LEM Instance that InstanceNode contains*/
	private Instance thisInstance ;
	
	/** Creates a new instance of InstanceNode. Examines the LEM Instance object
         * and creates subtrees based on the Attributes, Associations and State Machines.
         *@param instance the LEM instance to be contained.
         *@param fram the associated logger.
         */
	public InstanceNode(Instance instance , LoggerFrame frame) {
		this.frame = frame ;
		this.thisInstance = instance ;
		
		// add attributes to the instances subtree ;
		DefaultMutableTreeNode attributesLevel = new DefaultMutableTreeNode( "Attributes" ) ;
		Iterator i = thisInstance.getInstanceClass().getAllAttributes().iterator() ;
		if ( i.hasNext() ) {
			add( attributesLevel ) ;
			while ( i.hasNext() ) {
				attributesLevel.add( new AttributeNode( (Attribute)i.next(), thisInstance , frame )) ; 
			}			
		}
                DefaultMutableTreeNode associationsLevel = new DefaultMutableTreeNode( "Association" ) ;
		Iterator j = thisInstance.getInstanceClass().getAssociations().values().iterator() ; 
                if ( j.hasNext() ) {
			add ( associationsLevel ) ; 
			while ( j.hasNext() ) {
				associationsLevel.add( new ContextAssociationNode( thisInstance, (Association)j.next() , frame )) ; 
			}			
		}
                StateMachine m = thisInstance.getInstanceClass().getStateMachine();
		if( m != null ) {
                    State currentState = thisInstance.getCurrentState();
			add( new ContextStateMachineNode( m, currentState, frame ));
		}
	}
	
	/**
	 * Returns name property of the Instance followed by it's Id
	 * @return the Instance name and Id
	 */
	public String toString() {
		return thisInstance.getInstanceClass().getName() + " : " + thisInstance.getInstanceInObject().getObjectId() ;
	}
	/**
	 * Returns the description property of the Instance.
	 * @return the instance description.
	 */
	public String getDescription(){
		return "" ; //trim(thisInstance.getDescription());
	}
	/**
	 * Returns the ContextMenu based on the Instance.
	 * @return the Instance ContextMenu.
	 */
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		return ContextMenu;
	}
        
        /**
	 * Returns StyledDocument object containing preformatted LEM Instance description,
	 * followed by the list of attributes that the Instance has.
	 * @return the StyledDocument of the InstanceNode.
	 */
	
	public StyledDocument getDynamicDescription() {
		StyledDocument doc = new StyledDocument() ;
		Iterator i = thisInstance.getInstanceClass().getAllAttributes().iterator() ;
		if ( i.hasNext() ) {
			SimpleAttributeSet s2 = new SimpleAttributeSet();
			StyleConstants.setFontFamily(s2, "Times New Roman");
			StyleConstants.setFontSize(s2, 14);
			StyleConstants.setBold(s2, true);
			StyleConstants.setForeground(s2, Color.black);
			StyledElement element2 = new StyledElement( "List of Attributes\n" , s2) ;
			doc.addStyle( element2 ) ;
			
			while (i.hasNext()) {
				
				Attribute attribute = (Attribute)i.next() ;
				AttributeNode attributeNode = new AttributeNode( attribute, thisInstance , null ) ;
				StyledDocument styledDoc1 = attributeNode.getStyledDocument() ;
				StyledDocument styledDoc2 = attributeNode.getDynamicDescription() ;
				doc.addStyle( StyledElement.getTab() ) ; 
				doc.addStyle( StyledElement.getTab() ) ; 
				doc.append(styledDoc1) ;
				doc.addStyle( StyledElement.getTab() ) ; 
				doc.addStyle( StyledElement.getTab() ) ; 
				doc.append(styledDoc2) ;
			}
		}
		return doc;
	}
	
}

/*
 * AttributeNode.java
 *
 * Created on 27 April 2005, 17:20
 *
 * Copyright (C) 2005 James Ring
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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import org.jdns.xtuml.metamodel.Attribute;
import runtime.Instance ;
import runtime.Variable;
import runtime.VariableFactory;

/**
 * Tree node appearing appearing inside a ClassNode. For graphically
 * representing and "holding" LEM attribute objects. Has no children.
 *
 * @author James Ring
 */
public class AttributeNode extends AbstractDescriptionNode {
	/**The LEM attribute that AttributeNode contains.*/
	private Attribute attribute = null;
	private LoggerFrame frame = null ;
	/** instance to which this attribute belongs **/
	private Instance instance = null;

	/** 
	 * Creates a new instance of AttributeNode
	 * @param a the LEM attribute object.
	 */
	public AttributeNode( Attribute a , Instance instance, LoggerFrame frame ) {
		this.attribute = a;
		this.frame = frame ;
		this.instance = instance ;
	}

	/**
	 * Returns the name of the attribute followed by the type of the attribute
	 * @return the name of the attribute.
	 */
	public String toString() {
		return attribute.getName() + " : " + attribute.getType().getName();
	}

	/**
	 * Returns the description of the attribute, will return an empty string if
	 * description is null.
	 * @return the Description
	 */
	public String getDescription() {
		//if (attribute.getDescription() != null)
		//	return trim(attribute.getDescription() );
		//else
		return "" ;
	}
	/**
	 * Creates and returns a JPopupMenu based on the specified attribute.
	 * @return the ContextMenu.
	 */
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		
		ContextMenu.add("Descriptions") ;
		JMenuItem changeValue = new JMenuItem();
		changeValue.setText("Change Value.");
		changeValue.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				String input = JOptionPane.showInputDialog( frame , "Change Value", "New Value:",
						JOptionPane.QUESTION_MESSAGE );
				if ( input != null ) {
					try {
						Variable v = instance.getInstanceInObject().getAttribute(attribute.getName()) ;
						//DataType type = v.getType() ;
						//Interpreter interpreter = new Interpreter( null ) ;
						//Expression e = new Expression() ;
						Variable newVariable = VariableFactory.newVariable( v.getType() );
						//JOptionPane.showMessageDialog( frame , "Delayed Self Signal " + signalId + " Was generated to the object", "Success!",
						//		JOptionPane.INFORMATION_MESSAGE );
					} catch( Exception e) {
						JOptionPane.showMessageDialog( frame , "Exception Happened : " + e.getMessage() , "Success!",
								JOptionPane.ERROR_MESSAGE );
					}
				}
			}
		});
		ContextMenu.add(changeValue);
		
		
		return ContextMenu;
	}
	
	/**
	* Creates and returns a Styled Document containing the name of the specified attribute.
	* @return the Styled Document
	*/
	public StyledDocument getDynamicDescription() {
		StyledDocument doc = new StyledDocument() ;
		Variable v = instance.getInstanceInObject().getAttribute(attribute.getName()) ;
		String value = v.getValue().toString() ;
		SimpleAttributeSet s = new SimpleAttributeSet();
		StyleConstants.setFontFamily(s, "Times New Roman");
		StyleConstants.setFontSize(s, 14);
		StyleConstants.setBold(s, true);
		StyleConstants.setForeground(s, Color.red);
		StyledElement element = new StyledElement( "Current Value :" + value  , s) ;
		doc.addStyle(element) ;
		return doc;
	}
	
}

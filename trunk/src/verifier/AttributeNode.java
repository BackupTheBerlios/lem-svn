/*
 * AttributeNode.java
 *
 * Created on 27 April 2005, 17:20
 */

package verifier;
import java.awt.Color;
import javax.swing.JPopupMenu;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import metamodel.Attribute;
import runtime.Instance ; 
import runtime.Variable;

/**
 * Tree node appearing appearing inside a ClassNode. For graphically
 * representing and "holding" LEM attribute objects. Has no children.
 * @author sjr
 */
public class AttributeNode extends AbstractDescriptionNode {
	/**The LEM attribute that AttributeNode contains.*/
	private Attribute attribute = null;
	private LoggerFrame frame = null ;
	/** instance to which this attribute belongs **/
	private Instance instance = null;
	/** Creates a new instance of AttributeNode
	 * @param a the LEM attribute object.
	 */
	public AttributeNode( Attribute a , Instance instance, LoggerFrame frame ) {
		this.attribute = a;
		this.frame = frame ;
		this.instance = instance ; 
	}
	/** Returns the name of the attribute followed by the type of the attribute
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
		return ContextMenu;
	}
	
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

/*
 * InstanceNode.java
 *
 * Created on September 9, 2005, 2:51 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package verifier;
import java.awt.Color;
import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.Attribute;
import runtime.Instance;

/**
 *
 * @author u3958283
 */
public class InstanceNode extends AbstractDescriptionNode {
	
	private LoggerFrame frame ; 
	private Instance thisInstance ;	
	
	/** Creates a new instance of InstanceNode */
	public InstanceNode(Instance instance , LoggerFrame frame) {
		this.frame = frame ; 
		this.thisInstance = instance ; 
		// add attributes to the instances subtree ;
		DefaultMutableTreeNode attributesLevel = new DefaultMutableTreeNode( "Attributes" ) ;
		Iterator i = thisInstance.getInstanceClass().getAllAttributes().iterator() ; 
		if ( i.hasNext() ) {
			add ( attributesLevel ) ; 
			while ( i.hasNext() ) {
				attributesLevel.add( new AttributeNode( (Attribute)i.next() , frame )) ; 
			}			
		}		
	}
	
	/**
	 * Returns name property of the Instance
	 * @return the Instance name.
	 */
	public String toString() {
		return thisInstance.getInstanceClass().getName() + " : " + thisInstance.getInstanceInObject().getObjectId() ;
	}
	/**
	 * Returns the description property of the Instance.
	 * @return the Object description.
	 */
	public String getDescription(){
		return "" ; //trim(thisInstance.getDescription());
	}
	/**
	 * Returns the ContextMenu based on the Scenario.
	 * @return the Scenario ContextMenu.
	 */
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		return ContextMenu;
	}	
	
	public StyledDocument getDynamicDescription() {
		StyledDocument doc = new StyledDocument() ;
		Iterator i = thisInstance.getInstanceClass().getAllAttributes().iterator() ;
		while (i.hasNext()) {
			SimpleAttributeSet s = new SimpleAttributeSet();
			StyleConstants.setFontFamily(s, "Times New Roman");
			StyleConstants.setFontSize(s, 14);
			StyleConstants.setBold(s, true);
			StyleConstants.setForeground(s, Color.black);
			StyledElement element = new StyledElement( "\t\tList of Attributes\n" , s) ;
			Attribute attribute = (Attribute)i.next() ; 
			AttributeNode attributeNode = new AttributeNode( attribute, null ) ;
			StyledDocument styledDoc = attributeNode.getDynamicDescription() ; 
			doc.append(styledDoc) ; 
		}
		return doc;
	}
	
}

/*
 * StyledElement.java
 *
 * Created on 1 May 2005, 21:19
 */

package verifier;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * StyledElement specifies the content and attributes (font, colour, size ,etc)
 * of a piece of text.  This StyledElement will then belong to a StyledDocument.
 * @author SHukuBOy
 * @see StyledDocument
 */
public class StyledElement {
	
	/** attributes of the given piece of text e.g. font, size,colour. */
	private SimpleAttributeSet attribute ;
	/** piece of text inside the element */
	private String content ;
	
	/** Creates a new instance of DisplayElement */
	public StyledElement(String content, SimpleAttributeSet attribute) {
		this.attribute = attribute ;
		this.content = content;
	}
	
	/**Returns the attributes of the given element in form of a SimpleAttributeSet
	 *@return SimpleAttributeSet attributes of the element, e.g. font, size, colour and etc.
	 */
	public SimpleAttributeSet getAttributeSet() {
		return attribute ;
	}
	
	/**String content of the given element
	 *return String the content of the given element */
	public String getContent() {
		return content ;
	}
	
	public static StyledElement getTab() {
		SimpleAttributeSet s = new SimpleAttributeSet();
		StyleConstants.setFontFamily(s, "Times New Roman");
		StyleConstants.setFontSize(s, 14);
		StyleConstants.setBold(s, true);
		StyleConstants.setForeground(s, Color.black);
		return new StyledElement( "\t" , s) ;
	}
}

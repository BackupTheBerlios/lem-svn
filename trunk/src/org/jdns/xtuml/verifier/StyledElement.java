/*
 * StyledElement.java
 *
 * Created on 1 May 2005, 21:19
 *
 * Copyright (C) 2005 Shokouhmand Torabi
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

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

/**
 *
 * StyledElement specifies the content and attributes (font, colour, size ,etc)
 * of a piece of text.  This StyledElement will then belong to a StyledDocument.
 * @author Shokouhmand Torabi
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

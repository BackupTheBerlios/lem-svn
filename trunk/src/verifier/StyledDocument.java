/*
 * DisplayElement.java
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

package verifier;

import java.util.ArrayList;

/**
 * DisplayDocument specifies the content and attributes (font, colour, size ,etc)
 * of a document, made of pieces of rich text format.
 * @author Shokouhmand Torabi
 */
public class StyledDocument {
	
	/** An ArrayList containing StyledElement objects which all together
	 *form the text and formating of the StyledDocument*/
	private ArrayList elements;
	
	/** Creates a new instance of DisplayDocument */
	public StyledDocument() {
		elements = new ArrayList() ;
	}
	
	/** returns the number of StyledElements in the document
	 *@return number of StyledElements attached to the StyledDocument*/
	public int getLength() {
		return elements.size();
	}
	
	/**Adds an StyledElement to the StyledDocument
	 *@param element the Styled element to be attached to end of current
	 *Styled Document*/
	public void addStyle(StyledElement element) {
		elements.add(element) ;
	}
	
	/**
	 * Returns a StyledElement specified by the index parameter, or null if the
	 * given index does not exist.
	 * @param index index of the returned StyledElement
	 * @return A StyledElement corresponding to the given index, or null if given index doesn't exit
	 */
	public StyledElement getStyledElement(int index) {
		if (index <= elements.size() )
			return (StyledElement) elements.get(index);
		else {
			StyledElement s = null ;
			return s;
		}
	}
	
	/**Appends a whole StyledDocument to the end of the current document,
	 *by adding all the elements of the latter to the end of the former.
	 *@param doc The StyledDocument to add to the end of current document.
	 */
	public void append(StyledDocument doc) {
		if (doc != null ) {
			for (int i = 0 ; i < doc.getLength() ; i++) {
				elements.add( (StyledElement) doc.getStyledElement(i)) ;
			}
		}
	}
	
}

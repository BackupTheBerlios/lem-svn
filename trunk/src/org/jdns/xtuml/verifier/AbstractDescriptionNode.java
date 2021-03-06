/*
 * AbstractDescriptionNode.java
 *
 * Created on 29 April 2005, 13:22
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

package org.jdns.xtuml.verifier;

import java.awt.Color;
import java.util.StringTokenizer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Tree node appearing inside a ModelTreePanel or another tree node. Has methods 
 * for returning a preformatted StyledDocument. Has method for returning a context
 * menu.
 * @author David Gavin 
 */
public abstract class AbstractDescriptionNode extends DefaultMutableTreeNode{
    
    /** Creates a new instance of AbstractDescriptionNode */
    public AbstractDescriptionNode() {
    }
    /**
     * To be implemented by all children. Will typically return the String 
     * description of the LEM object held by the particular node.
     * @return The Description
     */
    public abstract String getDescription();
    
	public StyledDocument getDynamicDescription() {
		return null ; 
	}
    /**
     * Takes empty extra white spaces out of String
     * but preserves the NewLines after end of sentences.
     * @param text the text to trim
     * @return the trimmed text
     */
    public String trim(String text) {
        String tmp = "" , trimmed = "" ;        
        String[] words = text.split("\n");
        for (int i = 0 ; i < words.length; i++) {
            if ( words[i].trim().length() > 0 && words[i].trim().charAt( words[i].trim().length() - 1 ) == '.')
                tmp += words[i] + " <LEM_DESC_LINE_BREAK> " ;
            else
                tmp += words[i];
        }
        StringTokenizer st = new StringTokenizer(tmp) ;
        while (st.hasMoreElements()) {
            String t = st.nextToken();
            if (t.compareTo("<LEM_DESC_LINE_BREAK>") == 0)
                trimmed += "\n\n" ;
            else
                trimmed += t.trim() + " ";
        }
        return trimmed ;
    }
    /**
     * Returns StyledDocument object containing preformatted LEM object description.
     * @return The StyledDocument object.
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
        String Description;
        if(getDescription()==""||getDescription()==null){
            Description="" ;//"<No Description>";
        } else {
            Description=getDescription();
        }
        
        StyledElement element1 = new StyledElement(Description , s1) ;
        
        doc.addStyle(element1) ;
        return doc ;
    }
    /**
     * Returns dynamically built contextmenu. Each context menu will be built based
     * on the LEM object that the node contains. 
     * @return The Context Menu
     */
    public abstract javax.swing.JPopupMenu getContextMenu();
    
}

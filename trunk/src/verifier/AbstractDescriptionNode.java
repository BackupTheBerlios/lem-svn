/*
 * AbstractDescriptionNode.java
 *
 * Created on 29 April 2005, 13:22
 */

package verifier;

import java.awt.Color;
import java.util.StringTokenizer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author David Gavin
 */
public abstract class AbstractDescriptionNode extends DefaultMutableTreeNode{
    
    /** Creates a new instance of AbstractDescriptionNode */
    public AbstractDescriptionNode() {
    }
    
    public abstract String getDescription();
    
    /**
     * Takes empty extra white spaces out of String
     * but preserves the NewLines after end of sentences. */
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
            Description="<No Description>";
        } else {
            Description=getDescription();
        }
        
        StyledElement element1 = new StyledElement(Description , s1) ;
        
        doc.addStyle(element1) ;
        return doc ;
    }
}

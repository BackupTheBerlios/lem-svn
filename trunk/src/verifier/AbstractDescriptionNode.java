/*
 * AbstractDescriptionNode.java
 *
 * Created on 29 April 2005, 13:22
 */

package verifier;

import java.awt.Color;
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
        }
        else
        {
            Description=getDescription();
        }
        
        StyledElement element1 = new StyledElement(Description , s1) ;
        
        doc.addStyle(element1) ;                 
        return doc ;
    }
}

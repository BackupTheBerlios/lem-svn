/*
 * TransitionNode.java
 *
 * Created on 2 May 2005, 17:57
 */

package verifier;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import metamodel.Transition;

/**
 *
 * @author SHukuBOy
 */
public class TransitionNode extends AbstractDescriptionNode {
    
    Transition transition ; 
    String name ; 
    
    /** Creates a new instance of TransitionNode */
    public TransitionNode( Transition Transition , String name ) {
        this.transition = transition ; 
        this.name = name ; 
    }
    
    public String toString()  {
        return name ; 
    }
    
    public String getDescription()  {        
        return transition.getDescription() ; 
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
        StyledElement element1 = new StyledElement(getDescription() , s1) ;
        doc.addStyle(element1) ;                 
        return doc ;
    }
    
}

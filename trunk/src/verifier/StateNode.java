/*
 * StateNode.java
 *
 * Created on 2 May 2005, 17:57
 */

package verifier;

import java.awt.Color;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import metamodel.State;

/**
 *
 * @author SHukuBOy
 */
public class StateNode extends AbstractDescriptionNode {
    
    State state ; 
    /** Creates a new instance of StateNode */
    public StateNode( State state ) {
        this.state = state ; 
    }
    
    public String toString()  {
        return "State : " + state.getName() ; 
    }
    
    public String getDescription()  {        
        return state.getDescription() ; 
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

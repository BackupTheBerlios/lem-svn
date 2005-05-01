/*
 * ModelTreeNode.java
 *
 * Created on April 24, 2005, 3:30 PM
 */

package verifier;

import java.awt.Color;
import java.util.Iterator;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import metamodel.Domain;
import metamodel.Model;

/**
 *
 * @author sjr
 */
public class ModelTreeNode extends AbstractDescriptionNode {
    Model model;
    
    /** Creates a new instance of ModelTreeNode */
    public ModelTreeNode( Model m ) {
        this.model = m;
        Iterator i = m.getDomains().values().iterator();
        
        while( i.hasNext() ) {
            add( new DomainNode( (Domain)i.next() ));
        }
    }
 
    public String toString() {
        return "Model " + model.getName();
    }
    
    public String getDescription(){
        return model.getDescription();
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

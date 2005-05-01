/*
 * ClassNode.java
 *
 * Created on April 24, 2005, 3:54 PM
 */

package verifier;

import java.awt.Color;
import java.util.Iterator;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import metamodel.StateMachine;

/**
 *
 * @author sjr
 */
public class ClassNode extends AbstractDescriptionNode {
    
    metamodel.Class thisClass;
    
    /** Creates a new instance of ClassNode */
    public ClassNode( metamodel.Class c ) {
        this.thisClass = c;
        
        Iterator i = c.getAllAttributes().values().iterator();
        
        while( i.hasNext() ) {
            add( new AttributeNode( (metamodel.Attribute) i.next() ));
        }
        
        StateMachine m = thisClass.getStateMachine();
        if( m != null ) {
            add( new StateMachineNode( m ));
        }
    }
    
    public String toString() {
        return "Class " + thisClass.getName();
    }
    
    public String getDescription(){
        return thisClass.getDescription();
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

/*
 * DomainNode.java
 *
 * Created on April 24, 2005, 3:46 PM
 */

package verifier;

import java.awt.Color;
import java.util.Iterator;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.Domain;

/**
 *
 * @author sjr
 */
public class DomainNode extends AbstractDescriptionNode {
    
    Domain domain;
    
    /** Creates a new instance of DomainNode */
    public DomainNode( Domain d ) {
        this.domain = d;
        
        DefaultMutableTreeNode classLevel = new DefaultMutableTreeNode( "Classes" );
        
        Iterator i = d.getClasses().values().iterator();
        while( i.hasNext() ) {
            classLevel.add( new ClassNode( (metamodel.Class)i.next() ));
        }
        
        add( classLevel );
        
        
        DefaultMutableTreeNode relationshipLevel = new DefaultMutableTreeNode( "Relationships" );
        
        i = d.getRelationships().values().iterator();
        while( i.hasNext() ) {
            relationshipLevel.add( new RelationshipNode( (metamodel.Relationship) i.next() ));
        }
        
        add( relationshipLevel );
        
        //add( new DefaultMutableTreeNode("Hello, world" ));
    }
    
    public String toString() {
        return "Domain " + domain.getName();
    }
    
    public String getDescription(){
        return domain.getDescription();
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

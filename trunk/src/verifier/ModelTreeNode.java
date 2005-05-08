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
        return trim(model.getDescription());
    }
    
}

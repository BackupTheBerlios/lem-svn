/*
 * ModelTreeNode.java
 *
 * Created on April 24, 2005, 3:30 PM
 */

package verifier;

import java.util.Iterator;
import javax.swing.JPopupMenu;
import metamodel.Domain;
import metamodel.Model;

/**
 * Tree node appearing inside a ModelTreePanel. For graphically representing and 
 * "holding" LEM Model objects. Has DomainNode as a child.
 * @author sjr
 */
public class ModelTreeNode extends AbstractDescriptionNode {
    Model model;
    
    /** 
    * Creates a new instance of ModelTreeNode. Creates DomainNode branches 
    * based on LEM Model object.
    * @param m the LEM Model object.
    */
    public ModelTreeNode( Model m ) {
        this.model = m;
        Iterator i = m.getDomains().values().iterator();
        
        while( i.hasNext() ) {
            add( new DomainNode( (Domain)i.next() ));
        }
    }
    /**
    * Returns name property of LEM Model object.
    * @return the Model name.
    */ 
    public String toString() {
        return "Model " + model.getName();
    }
    /**
    * Returns the description property of the LEM Model object.
    * @return the Model description.
    */    
    public String getDescription(){
        return trim(model.getDescription());
    }
    /**
    * Returns the ContextMenu based on the LEM Model object.
    * @return the Model ContextMenu.
    */
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

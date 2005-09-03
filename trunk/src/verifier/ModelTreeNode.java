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
    /**LEM Model object that ModelTreeNode contains*/
    Model model;
    private ModelTreePanel view ;
    /**
     * Creates a new instance of ModelTreeNode. Creates DomainNode branches
     * based on LEM Model object.
     * @param m the LEM Model object.
     */
    public ModelTreeNode( Model m , ModelTreePanel view ) {
        this.model = m;
        this.view = view ; 
        Iterator i = m.getDomains().values().iterator();
        
        while( i.hasNext() ) {
            DomainNode d = new DomainNode((Domain)i.next() , view) ;
            //d.setView(view) ;
            add( d ) ;
            //add( new DomainNode( (Domain)i.next() ));
        }
    }
    
    public void setView(ModelTreePanel view) {
        this.view = view ;
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
    public JPopupMenu getContextMenu() {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

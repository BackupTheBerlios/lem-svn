/* SubsystemNode.java
 *
 * Created on 21 May 2005, 16:42
 *
 * $Revision$
 * $Date$
 */

package verifier;

import java.util.Iterator;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import metamodel.Domain;
import metamodel.Subsystem;

/**
 * Tree node appearing inside a DomainNode. For graphically representing and 
 * "holding" a LEM Subsystem object. Has ClassNodes as children.
 * @author sjr
 */
public class SubsystemNode extends AbstractDescriptionNode {
	Subsystem subsystem;
	/** 
         * Creates a new instance of SubsystemNode. Creates sublevel named 
         * classLevel for containing ClassNodes. Creates ClassNodes based on LEM
         * Class objects in LEM Subsystem object. If no Class objects in LEM 
         * Subsystem object then classLevel will display message "[No Classes]". 
         * @param s LEM Subsystem object to be contained.
         */
	public SubsystemNode( Subsystem s ) {
		this.subsystem = s;
		
		if (s.getClasses().size() == 0) {
			DefaultMutableTreeNode classLevel = new DefaultMutableTreeNode( "[No Classes]" );
			add( classLevel );
		} else {
			DefaultMutableTreeNode classLevel = new DefaultMutableTreeNode( "Classes" );
			
			Iterator i = s.getClasses().values().iterator();
			while (i.hasNext()) {
				classLevel.add( new ClassNode( (metamodel.Class)i.next() ));
			}
			
			add( classLevel );
		}
	}
    /**
     * Returns name property of LEM Subsystem object.
     * @return the Subsystem name.
     */ 
    public String toString() {
	return "Subsystem " + subsystem.getName();
    }
    /**
     * Returns the description property of the LEM Subsystem object.
     * @return the Subsystem description.
     */	
    public String getDescription() {
	if (subsystem.getDescription() != null)
		return trim(subsystem.getDescription());
	else
		return "" ;
    }
    /**
     * Returns the ContextMenu based on the LEM Subsystem object.
     * @return the Subsystem ContextMenu.
     */	
    public JPopupMenu getContextMenu() {
        JPopupMenu ContextMenu = new JPopupMenu();
	return ContextMenu;
    }
}


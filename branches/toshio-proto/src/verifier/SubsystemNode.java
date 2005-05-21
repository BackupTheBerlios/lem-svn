/*
 * SubsystemNode.java
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

public class SubsystemNode extends AbstractDescriptionNode {
	
	Subsystem subsystem;
	
	/** Creates a new instance of SubsystemNode */
	public SubsystemNode( Subsystem s ) {
		this.subsystem = s;
		
		DefaultMutableTreeNode classLevel = new DefaultMutableTreeNode( "Classes" );
		
		Iterator i = s.getClasses().values().iterator();
		while (i.hasNext()) {
			classLevel.add( new ClassNode( (metamodel.Class)i.next() ));
		}
		
		add( classLevel );
	}
	
	public String toString() {
		return "Subsystem " + subsystem.getName();
	}
	
	public String getDescription() {
		if (subsystem.getDescription() != null)
			return trim(subsystem.getDescription());
		else
			return "" ;
	}
	
	public JPopupMenu getContextMenu() {
		JPopupMenu ContextMenu = new JPopupMenu();
		return ContextMenu;
	}
}
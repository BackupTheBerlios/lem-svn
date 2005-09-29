/* SubsystemNode.java
 *
 * Created on 21 May 2005, 16:42
 *
 * Copyright (C) 2005 James Ring
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
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
 * @author James Ring
 */
public class SubsystemNode extends AbstractDescriptionNode {
        /**The LEM Subsystem object that SubsystemNode contains */
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


/*
 * ModelTreeNode.java
 *
 * Created on April 24, 2005, 3:30 PM
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
import org.jdns.xtuml.metamodel.Domain;
import org.jdns.xtuml.metamodel.Model;

/**
 * Tree node appearing inside a ModelTreePanel. For graphically representing and
 * "holding" LEM Model objects. Has DomainNode as a child.
 * @author James Ring
 */
public class ModelTreeNode extends AbstractDescriptionNode {
    /**LEM Model object that ModelTreeNode contains*/
    private Model model;
    private Eleminator eleminator;
    /**
     * Creates a new instance of ModelTreeNode. Creates DomainNode branches
     * based on LEM Model object.
     * @param m the LEM Model object.
     */
    public ModelTreeNode( Model m , Eleminator inEleminator ) {
        model = m;
        eleminator = inEleminator; 
        Iterator i = m.getDomains().values().iterator();
        while( i.hasNext() ) {
            DomainNode d = new DomainNode((Domain)i.next() , eleminator) ;
            add( d ) ;
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
    public JPopupMenu getContextMenu() {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

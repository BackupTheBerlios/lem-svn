/*
 * StateNode.java
 *
 * Created on 2 May 2005, 17:57
 *
 * Copyright (C) 2005 Shokouhmand Torabi
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

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.jdns.xtuml.metamodel.State;
import runtime.DomainContext;
import runtime.Interpreter;
import runtime.LemRuntimeException;

/**
 * Tree node appearing inside a StateMachineNode. For graphically representing
 * and "holding" a LEM State object. Has no children.
 *
 * @author Shokouhmand Torabi
 */
public class StateNode extends AbstractDescriptionNode {
    /**The LEM State object that StateNode contains.*/
    State state ;
    /**
     * Creates a new instance of StateNode.
     * @param state LEM State object to be contained.
     */
    public StateNode( State state ) {
        this.state = state ;
    }
    /**
     * Returns name property of LEM State object.
     * @return the State name.
     */
    public String toString()  {
        return "State : " + state.getName() ;
    }
    /**
     * Returns the description property of the LEM State object.
     * @return the State description.
     */
    public String getDescription()  {
        if (state.getDescription() != null)
            return trim(state.getDescription()) ;
        else
            return "" ;
    }
    /**
     * Returns the ContextMenu based on the LEM State object.
     * @return the State ContextMenu.
     */
    public JPopupMenu getContextMenu() {
        JPopupMenu contextMenu = new JPopupMenu();
        JMenuItem executeProcedure = new JMenuItem();
        executeProcedure.setText("Execute");
        executeProcedure.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executeProcedure();
            }
        });
        contextMenu.add(executeProcedure);
        return contextMenu;
    }
    
    /**
     * Executes the associated procedure
     */
    
    public void executeProcedure() {
	/**
	 * @todo: remove the ability to execute an arbitrary procedure.
	 * Program entry must only be through a scenario
	 */
        org.jdns.xtuml.metamodel.Procedure p = this.state.getProcedure();
        runtime.DomainContext d = new DomainContext();
        ConsoleLogger c = new ConsoleLogger(d);
	/* Not executing in the context of an object! */
        runtime.Interpreter i = new Interpreter(null);
    }
}

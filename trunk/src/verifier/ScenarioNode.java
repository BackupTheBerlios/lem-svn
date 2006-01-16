/*
 * ScenarioNode.java
 *
 * Created on 4 August 2005, 00:42
 *
 * Copyright (C) 2005 David Gavin
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

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import org.jdns.xtuml.metamodel.Scenario;
import org.jdns.xtuml.runtime.DomainContext;
import org.jdns.xtuml.runtime.Interpreter;
import org.jdns.xtuml.runtime.LemRuntimeException;
import javax.swing.JPanel ;
/**
 *
 * @author David Gavin
 */
public class ScenarioNode extends AbstractDescriptionNode  {
	private Scenario scenario;
	private Eleminator eleminator;
	/** Creates a new instance of ScenarioNode */
	public ScenarioNode(Scenario scenario, Eleminator inEleminator) {
		this.scenario = scenario;
                eleminator = inEleminator;
	}
	
	/**
	 * Returns name property of LEM State object.
	 * @return the State name.
	 */
	public String toString()  {
		return "Scenario : " + scenario.getName() ;
	}
	
	
	/**
	 * Returns the description property of the LEM State object.
	 * @return the State description.
	 */
	public String getDescription()  {
		if (scenario.getDescription() != null)
			return trim(scenario.getDescription()) ;
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
				LoggerFrame loggerWindow = new LoggerFrame(scenario, eleminator);
				loggerWindow.setVisible(true);
				loggerWindow.setBounds(0,0,640,480);
			}
		});
		contextMenu.add(executeProcedure);
		return contextMenu;
	}
		
}

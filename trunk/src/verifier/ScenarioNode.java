/*
 * ScenarioNode.java
 *
 * Created on 4 August 2005, 00:42
 */
package verifier;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import metamodel.Scenario;
import runtime.DomainContext;
import runtime.Interpreter;
import runtime.LemRuntimeException;
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
				loggerWindow.startExecutor();
			}
		});
		contextMenu.add(executeProcedure);
		return contextMenu;
	}
		
}

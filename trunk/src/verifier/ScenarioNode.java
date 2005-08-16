/*
 * ScenarioNode.java
 *
 * Created on 4 August 2005, 00:42
 */
package verifier;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import metamodel.Scenario;
import runtime.DomainContext;
import runtime.Interpreter;
import runtime.LemRuntimeException;
/**
 *
 * @author David Gavin
 */
public class ScenarioNode extends AbstractDescriptionNode  {
   Scenario scenario; 
    /** Creates a new instance of ScenarioNode */
    public ScenarioNode(Scenario scenario) {
        this.scenario = scenario;
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
                executeScenario();
            }
        });
        contextMenu.add(executeProcedure);
        return contextMenu;
    }
    public void executeScenario() {
        runtime.DomainContext d = new DomainContext();
        ConsoleLogger c = new ConsoleLogger(d);
        runtime.Interpreter i = new Interpreter(null);
        try{
            i.interpret(scenario,  d);
        } catch(LemRuntimeException e) {
            System.out.println(e);
        }
    }
    
}

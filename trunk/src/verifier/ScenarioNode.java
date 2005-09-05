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
    Scenario scenario;
    private ModelTreePanel view ;
    /** Creates a new instance of ScenarioNode */
    public ScenarioNode(Scenario scenario) {
        this.scenario = scenario;
    }
    
    public void setView(ModelTreePanel view) {
        this.view = view ;
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
//                (new Thread() {
//                    public void run() {
//                        executeScenario();
//                    }
//
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
                // view.integrateLogger( executeScenario() ) ;
                Thread scenarioExecuter = new Thread () {
                    public void run() {
                        runtime.DomainContext d = new DomainContext();
                        runtime.Interpreter i = new Interpreter(null);
						ScenarioExecuter se = new ScenarioExecuter( loggerPanel(d) , i, scenario) ;                         
						view.integrateLogger( se );						
						executeScenario( d , i) ; 
                    }
                } ;
                scenarioExecuter.start() ;                                                               
            }
        });
        contextMenu.add(executeProcedure);
        return contextMenu;
    }
    
    public void executeScenario(  runtime.Context d, Interpreter i) {
        //runtime.InstanceInterpreter i = new runtime.InstanceInterpreter() ;                        
        try{
            i.interpret(scenario,  d);
        } catch(LemRuntimeException e) {
            System.out.println(e);
        }
   }
    
    public JContextLoggerPanel loggerPanel( DomainContext d) {
        //JDialog dlg = new JDialog();                
        ConsoleLogger c = new ConsoleLogger(d);        
        JContextLoggerPanel p = new JContextLoggerPanel( d );               
        //dlg.getContentPane().setLayout( new BorderLayout() );
        //dlg.getContentPane().add( p, BorderLayout.CENTER );
        //dlg.setVisible( true );               
        return p ;
    }
    
}

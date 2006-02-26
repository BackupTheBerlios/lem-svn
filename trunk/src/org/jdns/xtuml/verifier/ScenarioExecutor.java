/*
 * ScenarioExecutor.java
 *
 * Created on September 16, 2005, 5:05 PM
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

package org.jdns.xtuml.verifier;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import org.jdns.xtuml.metamodel.Scenario;
import org.jdns.xtuml.runtime.DomainContext;
import org.jdns.xtuml.runtime.Interpreter;
import org.jdns.xtuml.runtime.LemRuntimeException;

/**
 * Displays an executing scenario on the GUI.
 * Has methods: refresh tree, get scenario name, kill scenario, get context tree,
 * get description pane.
 *
 * @author Shokouhmand Torabi
 */
public class ScenarioExecutor extends javax.swing.JPanel implements Runnable {
	private Interpreter interpreter;
	private Scenario scenario;
	private DomainContext context ;
	/** gui components **/
	private LoggerFrame frame ;
	private ConsoleLogger consoleLogger;
	
	/** Creates new form BeanForm */
	public ScenarioExecutor() {
		initComponents();
	}
	
        /**
         * Initalise the executor and associate a scenario and logger.
         * @param scenario The associated scenario
         * @param frame The associated logger frame
         */
	public void init(Scenario scenario, LoggerFrame frame) {
		this.scenario = scenario ;
		this.frame = frame ;
		interpreter = new Interpreter(null);
		context = new DomainContext();
		consoleLogger = new ConsoleLogger(context);
		debugControlPanel.init(context, scenario , this) ; 
		loggerPanel.init(context);
		contextTree.init(context, frame ) ;
		contextTree.setDescriptionPane( descriptionPanel) ;
		horizontalSplit.setLeftComponent(treeScroller) ; 
		verticalSplit2.setLeftComponent( descriptionPaneScroller ) ; 
		//verticalSplit2.setRightComponent( loggerPanel) ; 
	}
	
        /**
         * Execute the associated scenario
         */
	public void run() {
		try {
			context.getDebugObject().runModel();
			interpreter.interpret(scenario, context);
		} catch(LemRuntimeException e) {
			System.out.println(e);
		}
		
	}
	
        /**
         * Refresh the context tree according to the state of the model.
         */
	public void refreshTree() {
		//this.removeAll() ;
		treeScroller.remove( contextTree ) ;
		ContextTree newTree = new ContextTree();
		newTree.init(context,  frame ) ;
		treeScroller.setViewportView(newTree) ; 
		//this.setAutoscrolls(true) ;
		//JScrollPane treeScroll = new JScrollPane(newTree) ;
		//treeScroll.setAutoscrolls(true ) ;
		//verticalSplit.setRightComponent( horizontalSplit1 ) ;
		//add(verticalSplit, java.awt.BorderLayout.CENTER);
		updateUI() ;
	}
	
        /**
         * @return The current scenario name
         */
	public String getScenarioName() {
		return scenario.getName();
	}
        
        /**
         * Stop the execution of the current scenario.
         */
	public void killScenario()	{
		context.getDebugObject().stopModel();
		
	}
	
        /**
         * @return The context tree for the scenario
         */
	public ContextTree getContextTree() {
		return contextTree;
	}
        
        /**
         * @return The description pane for the context tree
         */
	public JTextPane getDescriptionPane() {
		return descriptionPanel;
	}
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        horizontalSplit = new javax.swing.JSplitPane();
        verticalSplit = new javax.swing.JSplitPane();
        debugControlPanel = new DebugControlPanel();
        verticalSplit2 = new javax.swing.JSplitPane();
        descriptionPaneScroller = new javax.swing.JScrollPane();
        descriptionPanel = new javax.swing.JTextPane();
        loggerPanel = new LoggerPanel();
        treeScroller = new javax.swing.JScrollPane();
        contextTree = new ContextTree();

        setLayout(new java.awt.BorderLayout());

        setMaximumSize(null);
        setMinimumSize(null);
        setPreferredSize(null);
        horizontalSplit.setMinimumSize(new java.awt.Dimension(800, 600));
        horizontalSplit.setPreferredSize(new java.awt.Dimension(1024, 768));
        verticalSplit.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        verticalSplit.setMinimumSize(new java.awt.Dimension(650, 600));
        verticalSplit.setPreferredSize(new java.awt.Dimension(874, 768));
        debugControlPanel.setMaximumSize(new java.awt.Dimension(1024, 80));
        debugControlPanel.setMinimumSize(new java.awt.Dimension(650, 40));
        debugControlPanel.setPreferredSize(new java.awt.Dimension(874, 768));
        verticalSplit.setTopComponent(debugControlPanel);

        verticalSplit2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        descriptionPaneScroller.setMinimumSize(new java.awt.Dimension(703, 200));
        descriptionPaneScroller.setPreferredSize(new java.awt.Dimension(703, 300));
        descriptionPanel.setMaximumSize(null);
        descriptionPaneScroller.setViewportView(descriptionPanel);

        verticalSplit2.setTopComponent(descriptionPaneScroller);

        loggerPanel.setMaximumSize(null);
        loggerPanel.setMinimumSize(null);
        loggerPanel.setPreferredSize(null);
        verticalSplit2.setBottomComponent(loggerPanel);

        verticalSplit.setBottomComponent(verticalSplit2);

        horizontalSplit.setRightComponent(verticalSplit);

        treeScroller.setMinimumSize(new java.awt.Dimension(150, 600));
        treeScroller.setPreferredSize(new java.awt.Dimension(150, 600));
        contextTree.setMaximumSize(new java.awt.Dimension(250, 768));
        contextTree.setMinimumSize(new java.awt.Dimension(150, 600));
        contextTree.setPreferredSize(new java.awt.Dimension(150, 768));
        treeScroller.setViewportView(contextTree);

        horizontalSplit.setLeftComponent(treeScroller);

        add(horizontalSplit, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents
	
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ContextTree contextTree;
    private DebugControlPanel debugControlPanel;
    private javax.swing.JScrollPane descriptionPaneScroller;
    private javax.swing.JTextPane descriptionPanel;
    private javax.swing.JSplitPane horizontalSplit;
    private LoggerPanel loggerPanel;
    private javax.swing.JScrollPane treeScroller;
    private javax.swing.JSplitPane verticalSplit;
    private javax.swing.JSplitPane verticalSplit2;
    // End of variables declaration//GEN-END:variables
	
}
/*
 * StateNode.java
 *
 * Created on 2 May 2005, 17:57
 */

package verifier;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import metamodel.State;
import runtime.DomainContext;
import runtime.Interpreter;
import runtime.LemRuntimeException;

/**
 * Tree node appearing inside a StateMachineNode. For graphically representing
 * and "holding" a LEM State object. Has no children.
 * @author SHukuBOy
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
    
    public void executeProcedure() {
        metamodel.Procedure p = this.state.getProcedure();
        runtime.DomainContext d = new DomainContext();
        ConsoleLogger c = new ConsoleLogger(d);
        runtime.Interpreter i = new Interpreter();
        try{
            i.interpret(p, d);
        } catch(LemRuntimeException e) {
            System.out.println(e);
        }
    }
}

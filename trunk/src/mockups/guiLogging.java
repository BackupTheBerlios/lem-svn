/*
 * guiLogging.java
 *
 * Created on 13 July 2005, 01:42
 */
package mockups;

import java.util.Vector;
import metamodel.ActionBlock;
import metamodel.CreateAction;
import metamodel.Procedure;
import metamodel.VariableReference;
import runtime.DomainContext;
import runtime.LemRuntimeException;


/**
 *
 * @author  David Gavin
 */
public class guiLogging extends javax.swing.JFrame {
   Procedure p = new Procedure();
   ActionBlock aB = new ActionBlock();
   CreateAction a = new CreateAction();
   metamodel.Class newClass = new metamodel.Class();
   runtime.DomainContext d = new DomainContext();
   
   runtime.Interpreter I = new runtime.Interpreter();
   metamodel.VariableReference variable = new metamodel.VariableReference("thisClass");
    /** Creates new form guiLogging */
    public guiLogging() {
        initComponents();
        newClass.setName("newClass");
        Vector c = new Vector();
        c.add(newClass);
        a.setClasses(c);
        variable.setObjectName("newThing");
        variable.setVariableName("thisClass");
        a.setVariable(variable);
        aB.addAction(a);
        p.setActionBlock(aB);
    }
     public void ExecuteProcedure() 
    {
       try{
           verifier.ConsoleLogger Con = new verifier.ConsoleLogger(d);
        I.interpret(p, d);
        
        }
        catch(LemRuntimeException e)
        {
            System.out.println(e);
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        getContentPane().setLayout(null);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setName("LoggingPrototype");
        jTextArea1.setEditable(false);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 72, 360, 210);

        jButton1.setText("Generate!");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        getContentPane().add(jButton1);
        jButton1.setBounds(30, 30, 350, 23);

        pack();
    }//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        ExecuteProcedure();
    }//GEN-LAST:event_jButton1MouseClicked
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new guiLogging().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
    
}

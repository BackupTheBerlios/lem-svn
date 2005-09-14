/*
 * guiLogging.java
 *
 * Created on 2 August 2005, 04:37
 */

package mockups;

import java.util.Vector;
import metamodel.ActionBlock;
import metamodel.CreateAction;
import metamodel.Procedure;
import metamodel.Class;
import runtime.DomainContext;
import runtime.Interpreter;
import runtime.LemRuntimeException;
import verifier.JContextLoggerPanel;

/**
 *
 * @author  David Gavin
 * @author  Donna Aloe
 * @author  Simon Franklin
 */
public class guiLogging extends javax.swing.JFrame {
    Vector c = new Vector();
    DomainContext d = new DomainContext();
    Procedure p = new Procedure();
    CreateAction a = new CreateAction();
    ActionBlock aB = new ActionBlock();
    Class newClass = new Class();
    JContextLoggerPanel logger = new JContextLoggerPanel(d);
    /** Creates new form guiLogging */
    public guiLogging() {
        getContentPane().add(logger,java.awt.BorderLayout.CENTER);
        initComponents();
      	setBounds(0,0,640,480);
	setTitle("MockUps");
        
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mockup");
        setName("????");
        jButton1.setText("Generate");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        getContentPane().add(jButton1, java.awt.BorderLayout.NORTH);

        pack();
    }
    // </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        newClass.setName("ARTC");
        Vector c = new Vector();
        c.add(newClass);
        a.setClasses(c);
        aB.addAction(a);
        p.setActionBlock(aB);
        ExecuteProcedure();
        p=new Procedure();
        a = new CreateAction();
        aB = new ActionBlock();
        newClass = new Class();
        newClass.setName("Controller");
        c = new Vector();
        c.add(newClass);
        a.setClasses(c);
        aB.addAction(a);
        p.setActionBlock(aB);
        ExecuteProcedure();
        p=new Procedure();
        a = new CreateAction();
        aB = new ActionBlock();
        newClass = new Class();
        newClass.setName("Plane");
        c = new Vector();
        c.add(newClass);
        c.add(newClass);
        c.add(newClass);
        a.setClasses(c);
        aB.addAction(a);
        p.setActionBlock(aB);
        ExecuteProcedure();
        p=new Procedure();
        a = new CreateAction();
        aB = new ActionBlock();
        newClass = new Class();
    }//GEN-LAST:event_jButton1MouseClicked
    public void ExecuteProcedure()
    {
        
        
	/* Not executing in the context of an object! */
   }
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
    // End of variables declaration//GEN-END:variables
    
}

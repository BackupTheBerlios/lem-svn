/*
 * ModelTreePanel.java
 *
 * Created on April 24, 2005, 3:25 PM
 */

package verifier;

import javax.swing.tree.DefaultTreeModel;
import metamodel.Model;

/**
 *
 * @author  sjr
 */
public class ModelTreePanel extends javax.swing.JPanel {
    
    /** Creates new form ModelTreePanel */
    public ModelTreePanel( Model m ) {
        initComponents();
        
        modelTree.setModel( new DefaultTreeModel( new ModelTreeNode( m )));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        modelTree = new javax.swing.JTree();

        setLayout(new java.awt.BorderLayout());

        add(modelTree, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree modelTree;
    // End of variables declaration//GEN-END:variables
    
}

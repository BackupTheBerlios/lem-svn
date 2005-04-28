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
        treeScrollPane = new javax.swing.JScrollPane();
        SplitPanel = new javax.swing.JSplitPane();
        modelTree = new javax.swing.JTree();
        DescriptionArea = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        SplitPanel.setLeftComponent(modelTree);

        DescriptionArea.setEditable(false);
        SplitPanel.setRightComponent(DescriptionArea);

        treeScrollPane.setViewportView(SplitPanel);

        add(treeScrollPane, java.awt.BorderLayout.CENTER);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea DescriptionArea;
    private javax.swing.JSplitPane SplitPanel;
    private javax.swing.JTree modelTree;
    private javax.swing.JScrollPane treeScrollPane;
    // End of variables declaration//GEN-END:variables
    
}

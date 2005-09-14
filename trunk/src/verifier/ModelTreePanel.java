/*
 * ModelTreePanel.java
 *
 * Created on April 24, 2005, 3:25 PM
 */

package verifier;

import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultTreeSelectionModel;
import metamodel.Model;

/**
 *
 * @author  sjr
 */
public class ModelTreePanel extends javax.swing.JPanel {
	private JPopupMenu contextMenu = new JPopupMenu();
	private Object currentContextObject = null;
	private Eleminator eleminator ;
	
	/** Creates new form ModelTreePanel */
	public ModelTreePanel(Model m, Eleminator inEleminator) {
		eleminator = inEleminator;
		initComponents();
		modelTree.setSelectionModel(new DefaultTreeSelectionModel());
		ModelTreeNode modelTreeNode = new ModelTreeNode( m , eleminator) ;
		modelTree.setModel( new DefaultTreeModel( modelTreeNode ));
		modelTree.setSelectionInterval(0,0);
		if (modelTree.getSelectionPath() != null)
			displayDescription(modelTree.getSelectionPath().getLastPathComponent());
		SplitPanel.setDividerLocation(175);
		CustomTreeRenderer render = new CustomTreeRenderer();
		modelTree.setCellRenderer(render);
	}
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        SplitPanel = new javax.swing.JSplitPane();
        treeScrollPane = new javax.swing.JScrollPane();
        modelTree = new javax.swing.JTree();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionArea = new javax.swing.JTextPane();

        setLayout(new java.awt.BorderLayout());

        SplitPanel.setDividerSize(5);
        modelTree.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                modelTreePropertyChange(evt);
            }
        });
        modelTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelTreeMouseClicked(evt);
            }
        });

        treeScrollPane.setViewportView(modelTree);

        SplitPanel.setLeftComponent(treeScrollPane);

        descriptionArea.setEditable(false);
        descriptionArea.setEditorKit(descriptionArea.getEditorKit());
        descriptionScrollPane.setViewportView(descriptionArea);

        SplitPanel.setRightComponent(descriptionScrollPane);

        add(SplitPanel, java.awt.BorderLayout.CENTER);

    }
    // </editor-fold>//GEN-END:initComponents
	
    private void modelTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelTreeMouseClicked
		
		if (MouseEvent.BUTTON3 == evt.getButton()||MouseEvent.BUTTON2 == evt.getButton()) {
			try{
				Object p = modelTree.getClosestPathForLocation(evt.getX(), evt.getY()).getLastPathComponent();
				if (p instanceof AbstractDescriptionNode){
					AbstractDescriptionNode adn = (AbstractDescriptionNode)p;
					currentContextObject = p;
					contextMenu = adn.getContextMenu();
					JMenuItem desc = new JMenuItem();
					desc.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent evt) {
							DescriptionMenuClicked(evt);
						}
					});
					desc.setText("Description");
					contextMenu.add(desc);
					contextMenu.show( this, evt.getX()-treeScrollPane.getHorizontalScrollBar().getValue(), evt.getY()-treeScrollPane.getVerticalScrollBar().getValue() );
				}
			} catch(Exception e){System.out.println(e);}
		} else {
			displayDescription(modelTree.getSelectionPath().getLastPathComponent());
		}
		
    }//GEN-LAST:event_modelTreeMouseClicked
	private void DescriptionMenuClicked(java.awt.event.ActionEvent evt) {
		displayDescription(currentContextObject);
		contextMenu.setVisible(false);
		currentContextObject=null;
	}
    private void modelTreePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_modelTreePropertyChange
		try{
			displayDescription(modelTree.getSelectionPath().getLastPathComponent());
		} catch(Exception e){}
    }//GEN-LAST:event_modelTreePropertyChange
	public void displayDescription(Object p) {
		StyledDocument doc = null, dynamicDoc = null ;
		try{
			
			if (p instanceof AbstractDescriptionNode){
				AbstractDescriptionNode ADN = (AbstractDescriptionNode)p;
				doc = ADN.getStyledDocument();				
				try {
					descriptionArea.getDocument().remove( 0 , descriptionArea.getDocument().getLength() ) ;
					for ( int i = 0 ; i < doc.getLength() ; i++ ) {
						descriptionArea.getDocument().insertString(descriptionArea.getDocument().getLength() ,doc.getStyledElement(i).getContent() + "\n", doc.getStyledElement(i).getAttributeSet()) ;
					}
				}
				
				catch(Exception e) {}
			}
		}
		
		catch(NullPointerException e) {
		}
		
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane SplitPanel;
    private javax.swing.JTextPane descriptionArea;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTree modelTree;
    private javax.swing.JScrollPane treeScrollPane;
    // End of variables declaration//GEN-END:variables
	
}

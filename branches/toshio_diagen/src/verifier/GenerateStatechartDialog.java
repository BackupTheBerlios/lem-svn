/*
 * GenerateStatechartDialog.java
 *
 * Created on 8 July 2005, 21:38
 */

package verifier;

import java.awt.Component;
import java.awt.Frame;
import java.util.Collection;
import javax.swing.JOptionPane;

/**
 *
 * @author  gen
 */
public class GenerateStatechartDialog extends javax.swing.JDialog {
	
	/**
	 * Handle to the displayed dialog.
	 */
	private static GenerateStatechartDialog dialog = null;
	
	/**
	 * Creates new form GenerateStatechartDialog 
	 *
	 * @param the dialog's parent window
	 * @param modal whether dialog should be modal
	 * @param statechartList the list of statecharts the dialog should display
	 */
	private GenerateStatechartDialog(java.awt.Frame parent, boolean modal, 
			Collection statechartList) {
		super(parent, modal);
		initComponents();
	}
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
  // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    statechartLabel = new javax.swing.JLabel();
    statechartList = new javax.swing.JComboBox();
    jLabel4 = new javax.swing.JLabel();
    browseButton = new javax.swing.JButton();
    destField = new javax.swing.JTextField();
    generateButton = new javax.swing.JButton();
    cancelButton = new javax.swing.JButton();

    getContentPane().setLayout(new java.awt.GridBagLayout());

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Generate Statechart");
    setName("Generate Statechart");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    statechartLabel.setText("Statechart:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    getContentPane().add(statechartLabel, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    getContentPane().add(statechartList, gridBagConstraints);

    jLabel4.setText("Destination:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    getContentPane().add(jLabel4, gridBagConstraints);

    browseButton.setMnemonic('B');
    browseButton.setText("Browse...");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    getContentPane().add(browseButton, gridBagConstraints);

    destField.setText("jTextField1");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(destField, gridBagConstraints);

    generateButton.setMnemonic('G');
    generateButton.setText("Generate");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    getContentPane().add(generateButton, gridBagConstraints);

    cancelButton.setMnemonic('C');
    cancelButton.setText("Cancel");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    getContentPane().add(cancelButton, gridBagConstraints);

    pack();
  }
  // </editor-fold>//GEN-END:initComponents

	private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		// Show the statechart list
		
	}//GEN-LAST:event_formWindowOpened
	
	/**
	 * Display the dialog
	 *
	 * @param parent the component that this dialog belongs do
	 * @param statechartList the list of statecharts the dialog should display
	 */
	public static String showDialog(Component parent, Collection statechartList) {
		Frame frame = JOptionPane.getFrameForComponent( parent );
		//dialog = new GenerateStatechartDialog( frame, true );
		//dialog.setVisible( true );
		return "This is a test";
	}
	
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton browseButton;
  private javax.swing.JButton cancelButton;
  private javax.swing.JTextField destField;
  private javax.swing.JButton generateButton;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel statechartLabel;
  private javax.swing.JComboBox statechartList;
  // End of variables declaration//GEN-END:variables
	
}
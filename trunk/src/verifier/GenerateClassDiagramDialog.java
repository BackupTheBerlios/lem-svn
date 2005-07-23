/*
 * GenerateClassDiagramDialog.java
 *
 * Created on 16 July 2005, 19:00
 */

package verifier;

import java.awt.Component;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import metamodel.Domain;
import metamodel.Model;

/**
 *
 * @author Toshio Nakamura
 * @todo use inheritance?
 */
public class GenerateClassDiagramDialog extends javax.swing.JDialog {
	
	/**
	 * Handle to the displayed dialog.
	 */
	private static GenerateClassDiagramDialog dialog = null;
	
	/**
	 * The model that this dialog is associated with
	 * @todo should this be static?
	 */
	private Model model;
	
	/**
	 * The currently selected Domain
	 */
	private Domain selectedDomain;
	
	/**
	 * The currently selected LEM Class
	 */
	private metamodel.Class selectedClass;
	
	/**
	 * Hardcoded name of dot binary
	 * @todo this is a hack
	 * @todo this needs to be replaced with a Preferences-style solution
	 */
	private static String dotBinary = "/usr/bin/dot";
	
	/** Creates new form GenerateClassDiagramDialog */
	public GenerateClassDiagramDialog(java.awt.Frame parent, boolean modal, Model m) {
		super(parent, modal);
		model = m;
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

    domainLabel = new javax.swing.JLabel();
    destinationLabel = new javax.swing.JLabel();
    filenameField = new javax.swing.JTextField();
    generateButton = new javax.swing.JButton();
    cancelButton = new javax.swing.JButton();
    domainList = new javax.swing.JComboBox();
    classLabel = new javax.swing.JLabel();
    classList = new javax.swing.JComboBox();

    getContentPane().setLayout(new java.awt.GridBagLayout());

    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    domainLabel.setText("Domain:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    getContentPane().add(domainLabel, gridBagConstraints);

    destinationLabel.setText("Output Filename:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    getContentPane().add(destinationLabel, gridBagConstraints);

    filenameField.setText("statechart.png");
    filenameField.setPreferredSize(new java.awt.Dimension(300, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(filenameField, gridBagConstraints);

    generateButton.setMnemonic('G');
    generateButton.setText("Generate");
    generateButton.setActionCommand("generate");
    generateButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        generateButtonActionPerformed(evt);
      }
    });

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    getContentPane().add(generateButton, gridBagConstraints);

    cancelButton.setMnemonic('C');
    cancelButton.setText("Cancel");
    cancelButton.setActionCommand("cancel");
    cancelButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cancelButtonActionPerformed(evt);
      }
    });

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    getContentPane().add(cancelButton, gridBagConstraints);

    domainList.setPreferredSize(new java.awt.Dimension(300, 20));
    domainList.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        domainListActionPerformed(evt);
      }
    });

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
    getContentPane().add(domainList, gridBagConstraints);

    classLabel.setText("Class:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    getContentPane().add(classLabel, gridBagConstraints);

    classList.setPreferredSize(new java.awt.Dimension(300, 20));
    classList.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        classListActionPerformed(evt);
      }
    });

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    getContentPane().add(classList, gridBagConstraints);

    pack();
  }
  // </editor-fold>//GEN-END:initComponents

	private void domainListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_domainListActionPerformed
			// Update the Domain and Class combo boxes
		JComboBox cb = (JComboBox)evt.getSource();
		selectedDomain = model.getDomain( (String)cb.getSelectedItem() );
		updateClassList( selectedDomain );
	}//GEN-LAST:event_domainListActionPerformed

	private void classListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classListActionPerformed
		// Update the selection
		JComboBox cb = (JComboBox)evt.getSource();
		selectedClass = selectedDomain.getClass( (String)cb.getSelectedItem() );
	}//GEN-LAST:event_classListActionPerformed
		
	private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
		if ("generate".equals( evt.getActionCommand() )) {
			// Fail if no filename specified
			if (filenameField.getText() == "") {
				JOptionPane.showMessageDialog( this, "No output filename specified", "Error",
						JOptionPane.ERROR_MESSAGE );
			}
			// 'Normal' case
			else {
				String dotCode = selectedClass.dumpDot();
				DotWriter.dotToPNG( dotCode, filenameField.getText() );
			}
		}
		System.err.println("generateButton complete");
		this.dispose();
	}//GEN-LAST:event_generateButtonActionPerformed
	
	/**
	 * Display the dialog
	 *
	 * @param parent the component that this dialog belongs do
	 * @param model the model for which the dialog is acting as a selector
	 */
	public static void showDialog(Component parent, Model m) {
		Frame frame = JOptionPane.getFrameForComponent( parent );
		dialog = new GenerateClassDiagramDialog( frame, true, m );
		dialog.setVisible( true );
	}
	
	private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
		// Initialise up the Domains combo box
		Iterator domainIter = model.getDomains().values().iterator();
		while (domainIter.hasNext()) {
			domainList.addItem( ((Domain)domainIter.next()).getName() );
		}
		domainList.setSelectedIndex( 0 );
		selectedDomain = model.getDomain( (String)domainList.getSelectedItem() );
		
		// Initialise the Class combo box
		updateClassList( selectedDomain );
	}//GEN-LAST:event_formWindowOpened
	
	/**
	 * Updates the Class combo box to display the child subsystems of a single
	 * parent domain.
	 *
	 * @param parent the parent Domain
	 * @todo only list classes with state machines
	 * @todo this function may not be needed - may be possible to access
	 * statecharts directly
	 * @todo does Java support an 'or else'-style construct?
	 * @todo Recode to use Exceptions?
	 */
	private void updateClassList( Domain parent ) {
		classList.removeAllItems();
		// Handle 'domainless' models
		if (parent == null) {
			classList.addItem( "<None>" );
			classList.setSelectedIndex( 0 );
			selectedClass = null;
		} else {
			Iterator classIter = parent.getClasses().values().iterator();
			// Handle domains with no classes
			if (classIter.hasNext() == false) {
				classList.addItem( "<None>" );
				classList.setSelectedIndex( 0 );
				selectedClass = null;
			} else {
				metamodel.Class c;
				while (classIter.hasNext()) {
					c = (metamodel.Class)classIter.next();
					classList.addItem( c.getName() );
				}
			}
			classList.setSelectedIndex( 0 );
			selectedClass = parent.getClass( (String)classList.getSelectedItem() );
		}
	}
	
	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		if ("cancel".equals( evt.getActionCommand() )) {
			this.dispose();
		}
	}//GEN-LAST:event_cancelButtonActionPerformed
		
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton cancelButton;
  private javax.swing.JLabel classLabel;
  private javax.swing.JComboBox classList;
  private javax.swing.JLabel destinationLabel;
  private javax.swing.JLabel domainLabel;
  private javax.swing.JComboBox domainList;
  private javax.swing.JTextField filenameField;
  private javax.swing.JButton generateButton;
  // End of variables declaration//GEN-END:variables
	
}

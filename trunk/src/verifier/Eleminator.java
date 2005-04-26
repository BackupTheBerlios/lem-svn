/*
 * Eleminator.java
 *
 * Created on April 24, 2005, 11:43 AM
 */

package verifier;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import metamodel.LemException;
import metamodel.Model;
import parser.ParseException;
import tools.Lem;

/**
 *
 * @author  sjr
 */
public class Eleminator extends javax.swing.JFrame {
    
    Vector models = new Vector();
    File workingDirectory = null;
    
    /** Creates new form Eleminator */
    public Eleminator() {
        initComponents();
        setBounds(0,0,640,480);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        desktopPane = new javax.swing.JDesktopPane();
        statusPanel = new javax.swing.JPanel();
        menubar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openItem = new javax.swing.JMenuItem();
        quitItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("eLEMinator");
        getContentPane().add(statusPanel, java.awt.BorderLayout.SOUTH);

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");
        openItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openItem.setMnemonic('O');
        openItem.setText("Open");
        openItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openItemActionPerformed(evt);
            }
        });

        fileMenu.add(openItem);

        quitItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        quitItem.setMnemonic('Q');
        quitItem.setText("Quit");
        quitItem.setToolTipText("Exits the eLEMinator");
        quitItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitItemActionPerformed(evt);
            }
        });

        fileMenu.add(quitItem);

        menubar.add(fileMenu);

        setJMenuBar(menubar);

        pack();
    }//GEN-END:initComponents
    
    private void quitItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitItemActionPerformed
        System.exit( 1 );
    }//GEN-LAST:event_quitItemActionPerformed
    
    private void openItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openItemActionPerformed
        // Present the user with a file chooser
        
        File selectedFile;
        Model m = null;
        
        JFileChooser jfc = new JFileChooser(workingDirectory);
        jfc.setFileFilter( new FileFilter() {
            public boolean accept( File f ) {
                return f.isDirectory() || f.getName().endsWith( ".lem" );
            }
            
            public String getDescription() {
                return "LEM model files (*.lem)";
            }
        } );
        
        jfc.showDialog(this, "Load");
        
        selectedFile = jfc.getSelectedFile();
        if( selectedFile != null ) {
            try {
                m = loadModel( selectedFile );
                if( m != null ) {
                    models.add( loadModel( selectedFile ));
                    JOptionPane.showMessageDialog( this, "Model loaded successfully.", "Success!", 
                            JOptionPane.INFORMATION_MESSAGE );
                }
            } catch( FileNotFoundException fnfe ) {
                JOptionPane.showMessageDialog( this, "Could not open the model file: " + fnfe.getMessage(),
                        "File not found", JOptionPane.ERROR_MESSAGE );
            } catch( ParseException pe ) {
                JOptionPane.showMessageDialog( this, "There was an error in parsing the given model: " + pe.getMessage(),
                        "Parse error", JOptionPane.ERROR_MESSAGE );
              
            } catch( LemException le ) {
                JOptionPane.showMessageDialog( this, "There was an error in parsing the given model: " + le.getMessage(),
                        "Parse error", JOptionPane.ERROR_MESSAGE );                
            } catch( IOException ioe ) {
                JOptionPane.showMessageDialog( this, "An input/output error occured while trying to read the model: " + ioe.getMessage(),
                        "Read error", JOptionPane.ERROR_MESSAGE );                
            }
            workingDirectory = jfc.getSelectedFile().getParentFile();
        }
        
        getContentPane().add( new ModelTreePanel( m ), BorderLayout.CENTER );
    }//GEN-LAST:event_openItemActionPerformed
    
    public Model loadModel( File modelFile ) throws FileNotFoundException, ParseException, LemException, IOException {
        Lem l = new Lem();
        FileInputStream fis = new FileInputStream( modelFile );
        
        return l.parse( fis );
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Eleminator().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenuItem openItem;
    private javax.swing.JMenuItem quitItem;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    
}
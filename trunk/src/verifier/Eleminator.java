/*
 * Eleminator.java
 *
 * Copyright (C) 2005 Steven Michael Ring
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

package verifier;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileFilter;
import metamodel.LemException;
import metamodel.Model;
import parser.ParseException;
import tools.Lem;

import java.io.*;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;



/**
 *
 * @author  sjr
 */
public class Eleminator extends javax.swing.JFrame {
	Vector windows = new Vector();
	Vector models = new Vector();
	Model activeModel = null;
	File workingDirectory = null;
	ModelTreePanel MTP = new ModelTreePanel(new Model());
        int windowCounter = 0;
	
	/** Creates new form Eleminator */
	public Eleminator() {
		getContentPane().add( MTP, BorderLayout.CENTER );
		try{
			String nativeLook = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(nativeLook);
			SwingUtilities.updateComponentTreeUI(this);
		} catch(Exception e){}
		
		try{
			File custDir = new File(System.getProperty("user.home")+"/"+"WorkSpace.dat");
			ObjectInputStream In = new ObjectInputStream(new FileInputStream
					(custDir));
			workingDirectory = new File( ((WorkSpace)In.readObject()).getWorkSpace());
		} catch (Exception ex){
			workingDirectory = new File(System.getProperty("user.home"));}
		
		initComponents();
		LookAndFeelInfo[] li = UIManager.getInstalledLookAndFeels();
		
		for(int i=0;li.length>i;i++){  //For more themes
			
			themeComboBox.addItem(new ThemeItem(li[i].getName(),li[i].getClassName()));
		}
		
		setSize(640, 480);
		setLocationByPlatform( true );
		doLayout();
		setTitle("eLEMinator");
		URL imageURL = getClass().getClassLoader().getResource("verifier/lem.jpg");
		Image lem = Toolkit.getDefaultToolkit().getImage(imageURL);
		setIconImage(lem);
	}
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    private void initComponents() {//GEN-BEGIN:initComponents
        desktopPane = new javax.swing.JDesktopPane();
        statusPanel = new javax.swing.JPanel();
        lemToolBar = new javax.swing.JToolBar();
        themeLabel = new javax.swing.JLabel();
        themeComboBox = new javax.swing.JComboBox();
        menubar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openItem = new javax.swing.JMenuItem();
        importItem = new javax.swing.JMenuItem();
        quitItem = new javax.swing.JMenuItem();
        toolsMenu = new javax.swing.JMenu();
        generateClassDiagramItem = new javax.swing.JMenuItem();
        generateStatechartItem = new javax.swing.JMenuItem();
        windowMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("eLEMinator");
        statusPanel.setMaximumSize(new java.awt.Dimension(10, 10));
        getContentPane().add(statusPanel, java.awt.BorderLayout.SOUTH);

        lemToolBar.setName("Standard");
        themeLabel.setText("Themes");
        lemToolBar.add(themeLabel);

        themeComboBox.setMaximumSize(new java.awt.Dimension(150, 150));
        themeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                themeComboBoxItemStateChanged(evt);
            }
        });

        lemToolBar.add(themeComboBox);

        getContentPane().add(lemToolBar, java.awt.BorderLayout.NORTH);

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

        importItem.setText("Import");
        importItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importItemActionPerformed(evt);
            }
        });

        fileMenu.add(importItem);

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

        toolsMenu.setMnemonic('T');
        toolsMenu.setText("Tools");
        toolsMenu.setToolTipText("");
        generateClassDiagramItem.setMnemonic('C');
        generateClassDiagramItem.setText("Generate Class Diagram...");
        generateClassDiagramItem.setToolTipText("");
        generateClassDiagramItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateClassDiagramItemActionPerformed(evt);
            }
        });

        toolsMenu.add(generateClassDiagramItem);

        generateStatechartItem.setMnemonic('S');
        generateStatechartItem.setText("Generate Statechart...");
        generateStatechartItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateStatechartItemActionPerformed(evt);
            }
        });

        toolsMenu.add(generateStatechartItem);

        menubar.add(toolsMenu);

        windowMenu.setMnemonic('W');
        windowMenu.setText("Window");
        windowMenu.setEnabled(false);
        menubar.add(windowMenu);

        setJMenuBar(menubar);

        pack();
    }//GEN-END:initComponents

	private void generateClassDiagramItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateClassDiagramItemActionPerformed
		GenerateClassDiagramDialog.showDialog( this, activeModel );
	}//GEN-LAST:event_generateClassDiagramItemActionPerformed
		
	private void generateStatechartItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateStatechartItemActionPerformed
		GenerateStatechartDialog.showDialog( this, activeModel );
	}//GEN-LAST:event_generateStatechartItemActionPerformed
	
	private void importItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importItemActionPerformed
		// Display a file chooser
		
		File selectedFile;
		Model m = null;
		JFileChooser jfc = new JFileChooser( workingDirectory );
		jfc.setFileFilter( new FileFilter() {
			public boolean accept( File f ) {
				return f.isDirectory() || f.getName().endsWith( ".xmi" );
			}
			
			public String getDescription() {
				return "XMI UML files (*.xmi)";
			}
		} );
		
		jfc.setCurrentDirectory( workingDirectory );
		
		int retval = jfc.showDialog( this, "Import" );
		
		selectedFile = jfc.getSelectedFile();
		if (selectedFile != null && (retval == JFileChooser.APPROVE_OPTION)) {
			try {
				m = importModel( selectedFile );
				if (m != null) {
					models.add(m);
					activeModel = m;
					JOptionPane.showMessageDialog( this, "Model loaded successfully.", "Success!",
							JOptionPane.INFORMATION_MESSAGE );
					workingDirectory = jfc.getSelectedFile().getParentFile();
					getContentPane().remove(MTP);
					MTP = new ModelTreePanel(m);
					getContentPane().add(MTP, BorderLayout.CENTER);
					try{
						ObjectOutputStream Out = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home")+"/"+"WorkSpace.dat"));
						Out.writeObject(new WorkSpace(workingDirectory.getAbsolutePath()));
						Out.close();
					} catch (Exception ex){
						ex.printStackTrace();
					}
					
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
			} catch( Exception e ) {
				e.printStackTrace();
			}
			
		}
		
		setVisible(true);
	}//GEN-LAST:event_importItemActionPerformed
	
        private void themeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_themeComboBoxItemStateChanged
					try{
						ThemeItem newLook = (ThemeItem)themeComboBox.getSelectedItem();
						UIManager.setLookAndFeel(newLook.getClassName());
						SwingUtilities.updateComponentTreeUI(this);
					} catch(Exception e){}
        }//GEN-LAST:event_themeComboBoxItemStateChanged
				
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
			
			jfc.setCurrentDirectory(workingDirectory);
			
			int retval = jfc.showDialog(this, "Load");
			
			selectedFile = jfc.getSelectedFile();
			if( selectedFile != null && (retval == JFileChooser.APPROVE_OPTION) ) {
				try {
					m = loadModel( selectedFile );
					if( m != null ) {
						models.add( m );
						activeModel = m;
						JOptionPane.showMessageDialog(this, "Model loaded successfully.", "Success!",
								JOptionPane.INFORMATION_MESSAGE );
						workingDirectory = jfc.getSelectedFile().getParentFile();
						getContentPane().remove(MTP);
						MTP = new ModelTreePanel(m);
						getContentPane().add(MTP, BorderLayout.CENTER);
						try{
							ObjectOutputStream Out = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home")+"/"+"WorkSpace.dat"));
							Out.writeObject(new WorkSpace(workingDirectory.getAbsolutePath()));
							Out.close();
						} catch (FileNotFoundException ex){
						}
						
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
				
			}
			
			setVisible(true);
			
/*        metamodel.Procedure p = m.getDomain( "Publications" ).getClass( "Manuscript" ).getStateMachine().getState("Adding").getProcedure();
				runtime.ModelInstance i = new runtime.ModelInstance();
 
				p.execute(i); */
			
			
    }//GEN-LAST:event_openItemActionPerformed
		
		public Model loadModel( File modelFile ) throws FileNotFoundException, ParseException, LemException, IOException {
			Lem l = new Lem();
			FileReader fis = new FileReader( modelFile );
			
			return l.parse( fis );
		}
		
		
		/**
		 * Converts an XMI file into a LEM model.  Note that this functionality is
		 * experimental, and only works in very limited cases.
		 * @param importFile the XMI file to convert
		 * @return a LEM model created from the XMI file
		 */
		public Model importModel( File importFile ) throws TransformerException, TransformerConfigurationException,
				FileNotFoundException, ParseException, LemException, IOException {
			Lem l = new Lem();
			String s = importXMI(importFile);
			return l.parse(new StringReader(s));
		}
		
		
		private String importXMI(File sourceFile) throws FileNotFoundException, TransformerConfigurationException, TransformerException {
			
			BufferedReader styledata;
			StreamSource sourcedata;
			Templates stylesheet;
			Transformer trans;
			
			// Get the stylesheet file stream
			
			
			styledata = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("verifier/xmi2lem.xsl").getFile())));
			sourcedata = new StreamSource(new FileReader(sourceFile));
			
			
			// Initialize Saxon
			TransformerFactory factory = TransformerFactoryImpl.newInstance();
			stylesheet = factory.newTemplates(new StreamSource(styledata));
			
			// Apply the transformation
			trans = stylesheet.newTransformer();
			StringWriter sw = new StringWriter();
			trans.transform(sourcedata, new StreamResult(sw));
			
			//return sw.toString();
			return sw.getBuffer().substring(sw.getBuffer().indexOf("model"));
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
                public void newWindow(JFrame window){
                    JMenuItem showWindow = new JMenuItem();
                    windowMenu.setEnabled(true);
                    showWindow.setText(window.getTitle()+" "+(windowCounter+1));
                    window.setTitle(window.getTitle()+" "+(windowCounter+1));
                    windowCounter++;
                    showWindow.addActionListener(new LEMMenuListener(window));
                    windowMenu.add(showWindow);
                    windows.add(showWindow);
                }
                public void killWindow(JFrame window){
                    for(int i=0; i<windows.size();i++) {
                        if(window.getTitle().matches(((JMenuItem)windows.get(i)).getText())) {
                            windowMenu.remove(((JMenuItem)windows.get(i)));
                            windows.remove(i);
                            break;
                        }
                    }
                    if(windows.isEmpty()){
                        windowMenu.setEnabled(false);
                    }
                }
                
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem generateClassDiagramItem;
    private javax.swing.JMenuItem generateStatechartItem;
    private javax.swing.JMenuItem importItem;
    private javax.swing.JToolBar lemToolBar;
    private javax.swing.JMenuBar menubar;
    private javax.swing.JMenuItem openItem;
    private javax.swing.JMenuItem quitItem;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JComboBox themeComboBox;
    private javax.swing.JLabel themeLabel;
    private javax.swing.JMenu toolsMenu;
    private javax.swing.JMenu windowMenu;
    // End of variables declaration//GEN-END:variables
}

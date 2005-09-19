/*
 * ContextLoggerPanel.java
 *
 * Created on September 16, 2005, 6:04 PM
 */

package verifier;

import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.table.JTableHeader;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import runtime.LemAttributeChangeEvent;
import runtime.LemAttributeReadEvent;
import runtime.LemEventCancellationEvent;
import runtime.LemEventGenerationEvent;
import runtime.LemEventReceivedEvent;
import runtime.LemObjectCreationEvent;
import runtime.LemObjectDeletionEvent;
import runtime.LemRelationshipCreationEvent;
import runtime.LemRelationshipDeletionEvent;
import runtime.LemSelectionEvent;
import runtime.LemStateTransitionEvent;
import metamodel.VariableReference;

/**
 *
 * @author  u3958283
 */
public class ContextLoggerPanel extends javax.swing.JPanel implements runtime.LemEventListener {
	private int counter = 0;
    
    // Attribute set for defult text
    SimpleAttributeSet text = new SimpleAttributeSet();
    SimpleAttributeSet classNames = new SimpleAttributeSet();
    SimpleAttributeSet oId = new SimpleAttributeSet();
    SimpleAttributeSet attName = new SimpleAttributeSet();
    SimpleAttributeSet values = new SimpleAttributeSet();
    //Set up for table model
    TableModel model = new TableModel();
	
	/** Creates new form BeanForm */
	public ContextLoggerPanel() {
		initializeComponents();
	}
	
	public void init(runtime.Context c) {
		
		c.addLemEventListener(this);       
        // Set the data model for the table
        table.setModel(model);      
        model.setTable(table);
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        // Set the mouse listener on the column titles
        header.addMouseListener(new ColumnHeaderListener());
        
        // Disable autoCreateColumnsFromModel otherwise all the column customizations
        // and adjustments will be lost when the model data is sorted
        table.setAutoCreateColumnsFromModel(false);
        
        //Add a tool tip to show sorting
        table.getTableHeader().setToolTipText(
               "Click to specify sorting, Once for Ascending, Twice for Descending");
        
        //Set the column names in the combo box
        for (int i=0; i<model.getColumnCount(); i++){
            colSelection.addItem(model.getColumnName(i));
        }
        
        //Set text in matches combo box
        matches.addItem("Exactly");
        matches.addItem("Contains");
      
        //Defult text formating for log messages
        StyleConstants.setFontFamily(text, "Times New Roman");
        StyleConstants.setFontSize(text, 14);
        StyleConstants.setForeground(text, Color.black);
        StyleConstants.setBold(text,false);
        
        // Attribute set for Object names
        StyleConstants.setFontFamily(classNames, "Times New Roman");
        StyleConstants.setFontSize(classNames, 16);
        StyleConstants.setForeground(classNames, Color.DARK_GRAY);
        StyleConstants.setBold(classNames,true);
        
        // Attribute set for Object Id's
        StyleConstants.setFontFamily(oId, "Times New Roman");
        StyleConstants.setFontSize(oId, 16);
        StyleConstants.setForeground(oId, Color.DARK_GRAY);
        StyleConstants.setBold(oId,true);
        
         // Attribute set for Attribute Names
        StyleConstants.setFontFamily(attName, "Times New Roman");
        StyleConstants.setFontSize(attName, 16);
        StyleConstants.setForeground(attName, Color.DARK_GRAY);
        StyleConstants.setBold(attName,true);
        
        // Attribute set for all other values
        StyleConstants.setFontFamily(values, "Times New Roman");
        StyleConstants.setFontSize(values, 16);
        StyleConstants.setForeground(values, Color.DARK_GRAY);
        StyleConstants.setBold(values,true);		
	}
	
	public void initializeComponents() {
		jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        textField = new javax.swing.JTextField();
        filterButton = new javax.swing.JButton();
        colSelection = new javax.swing.JComboBox();
        matches = new javax.swing.JComboBox();
        clear = new javax.swing.JButton();

        setLayout(null);

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(5);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        textArea.setEditable(false);
        jScrollPane1.setViewportView(textArea);

        jSplitPane1.setLeftComponent(jScrollPane1);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12", "Title 13", "Title 14"
            }
        ));
        jScrollPane2.setViewportView(table);

        jSplitPane1.setRightComponent(jScrollPane2);

        add(jSplitPane1);
        jSplitPane1.setBounds(0, 0, 620, 410);

        textField.setText("Enter keyword");
        add(textField);
        textField.setBounds(220, 410, 120, 30);

        filterButton.setText("Go");
        filterButton.setPreferredSize(new java.awt.Dimension(45, 25));
        filterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filterButtonMouseClicked(evt);
            }
        });

        add(filterButton);
        filterButton.setBounds(480, 410, 60, 30);

        colSelection.setPreferredSize(new java.awt.Dimension(87, 19));
        add(colSelection);
        colSelection.setBounds(350, 410, 120, 30);

        matches.setPreferredSize(new java.awt.Dimension(87, 19));
        add(matches);
        matches.setBounds(90, 410, 120, 30);

        clear.setText("Clear");
        clear.setPreferredSize(new java.awt.Dimension(50, 19));
        clear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clearMousePressed(evt);
            }
        });

        add(clear);
        clear.setBounds(550, 410, 70, 30);
	}
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
	private void initComponents() {
		
	}
	// </editor-fold>//GEN-END:initComponents
	
	private void clearMousePressed(java.awt.event.MouseEvent evt) {                                   
        model.removeFilter();
    }                                  
    /**
     * Called when user clicks on Go to filter according to keyword and column as selected in the text field and
     * combo box.
     *
     */
    private void filterButtonMouseClicked(java.awt.event.MouseEvent evt) {                                          
        int ind;
        String tmp;
        ind = colSelection.getSelectedIndex();
        tmp = textField.getText();
        tmp.toLowerCase();
        int type = matches.getSelectedIndex();
        if ((tmp.trim()).length() == 0){
            model.removeFilter();
        } else {
            model.applyFilter(tmp, ind, type);
        }
    }                                         
    
    /*
     *Adds StyledDocument to the text area
     *@param doc Styled Document to be added to text area
     */
    private void addText(StyledDocument doc){
        try {
            for ( int i = 0 ; i < doc.getLength() ; i++ ) {
                textArea.getDocument().insertString(textArea.getDocument().getLength() ,
                        doc.getStyledElement(i).getContent(), 
                        doc.getStyledElement(i).getAttributeSet());
            }
            
            counter ++;
        } catch(Exception x) {}
    }

    /**
     * Called by the runtime when a CreateAction has been implemented
     * and a new object has been created.
     * The panel displays an object created message listing all the instances created.
     * The table displays the object created message listing all the instances created.
     *
     *@param event The Object Creation event to be logged
     **/
    public synchronized void objectCreated(LemObjectCreationEvent event) {
        StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.objectCreated(event, counter);
        
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Object Created, Object ID: ", text));
        doc.addStyle(new StyledElement(event.getObjectId().toString(), oId));
        doc.addStyle(new StyledElement(" Instance(s): " , text));
        java.util.Collection className = new java.util.LinkedList();
        className = event.getObjectClassName();
        for( Iterator i = event.getObjectClassName().iterator(); i.hasNext(); ) {
            doc.addStyle(new StyledElement(i.next() + " ", classNames));
        }
        doc.addStyle(new StyledElement("\n", text));
        addText(doc);
    }
    
    /**
     * Called by the runtime when reading an attribute's value
     *
     * @param event The event representing the circumstances in which the
     *  attribute is read.
     */
    public synchronized void attributeRead(LemAttributeReadEvent event) {
        StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.attributeRead(event, counter);
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Attribute Read, Object ID: " , text));
        doc.addStyle(new StyledElement(event.getObjectId().toString(), oId));
        doc.addStyle(new StyledElement(" Attribute Name: ",  text));
        doc.addStyle(new StyledElement(event.getAttributeName(), attName)); 
        doc.addStyle(new StyledElement(" Value: " , text));
        doc.addStyle(new StyledElement(event.getValue().toString(), values));
        doc.addStyle(new StyledElement("\n", text));
       addText(doc);
    }
    
    /**
     * Called by the runtime when a DeleteAction has been implemented
     * and an existing has been deleted.
     * The panel displays an object deleted message listing all the
     * instances of the object deleted.
     *
     *@param event The Object Deletion event to be logged
     **/
    public synchronized void objectDeleted(LemObjectDeletionEvent event) {
        StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.objectDeleted(event, counter);
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Object Deleted, Object ID: ", text));
        doc.addStyle(new StyledElement(event.getObjectId().toString(), oId));
        doc.addStyle(new StyledElement("\n", text));
        addText(doc);
    }
    
    /**
     * Called by the runtime when a LemEvent has resulted in a object transitioning
     *  from one state to the next.
     * The panel displays the transition event listing the state the object has moved to.
     *
     *@param event The State transition event to be logged
     **/
    public synchronized void transitionEvent(LemStateTransitionEvent event) {
        StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.transitionEvent(event, counter);
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Transition Event, Object ID: ",text));
        doc.addStyle(new StyledElement(event.getObjectId().toString(), oId));
        doc.addStyle(new StyledElement(" Previous State: ", text));
        doc.addStyle(new StyledElement(event.getFromState(), values));
        doc.addStyle(new StyledElement(" New State: ", text));
        doc.addStyle(new StyledElement(event.getToState(), values));
        doc.addStyle(new StyledElement("\n", text));
      addText(doc);
    }
                    
    /**
     * Called by the runtime when an attribute's value has changed.
     *
     * @param e The event representing the attribute change
     */
    public synchronized void attributeChange(LemAttributeChangeEvent event) {
        StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.attributeChange(event, counter);
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Attribute Changed, Object ID: ", text));
        doc.addStyle(new StyledElement(event.getObjectId().toString(), oId));
        doc.addStyle(new StyledElement(" Attribute Name: ", text));
        doc.addStyle(new StyledElement(event.getAttributeName(), attName));
        doc.addStyle(new StyledElement(" Old value: ", text));
        doc.addStyle(new StyledElement(event.getOldValue().toString(), values));
        doc.addStyle(new StyledElement(" New Value: ", text));
        doc.addStyle(new StyledElement(event.getNewValue().toString(), values));
        doc.addStyle(new StyledElement("\n", text));
        addText(doc);
    }
    
    /**
     * Called by the runtime when an existing relationship is deleted
     *
     * @param event The LemRelationshipDeletionEvent event representing
     * the circumstances in which the relationship has been deleted
     */
    public synchronized void relationshipDeletion(LemRelationshipDeletionEvent event) {
        StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.relationshipDeletion(event, counter);
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Relationship Deleted, Object1 ID: ", text));
        doc.addStyle(new StyledElement(event.getActiveObjectId().toString(),  oId));
        doc.addStyle(new StyledElement(" Object2 ID: ", text));
        doc.addStyle(new StyledElement(event.getPassiveObjectId().toString(), oId));
        doc.addStyle(new StyledElement(" Relationship Label: ", text));
        doc.addStyle(new StyledElement(event.getAssociationLabel(), values));
        doc.addStyle(new StyledElement("Link ID: ", text));
        doc.addStyle(new StyledElement(event.getLinkObjectId().toString(), values));
        doc.addStyle(new StyledElement("\n", text));
        addText(doc);
    }
    
    
    /**
     * Called by the runtime when a new relationship is created
     *
     * @param event The LemRelationshipCreationEvent event representing
     * the circumstances in which the relationship has been created
     */
    public synchronized void relationshipCreation(LemRelationshipCreationEvent event) {
        StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.relationshipCreation(event, counter);
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Relationship Created, Object1 ID: ", text));
        doc.addStyle(new StyledElement(event.getActiveObjectId().toString(), oId));
        doc.addStyle(new StyledElement(" Object2 ID: ", text));
        doc.addStyle(new StyledElement(event.getPassiveObjectId().toString(), oId));
        doc.addStyle(new StyledElement(" Relationship Label: ", text));
        doc.addStyle(new StyledElement(event.getAssociationLabel(), values));
        doc.addStyle(new StyledElement("Link ID: ", text));
        doc.addStyle(new StyledElement(event.getLinkObjectId().toString(), values));
        doc.addStyle(new StyledElement("\n", text));
        addText(doc);
    }
      
    /**
     * Called by the runtime when a ReclassificationAction has been implemented
     * and an existing object in a generalisation heirarchy has been reclassified.
     * The panel displays an object reclassification message listing all the
     * instances of the object which was reclassified.
     *
     *@param event The Object Reclassification event to be logged
     **/
    
    /** commented out until LemObjectReclassificatonEvent has been implemented.
     * public synchronized void reclassification(LemObjectReclassificationEvent event) {
     * runtime.Object o = event.getReclassifiedObject();
     *
     * }
     */

    /**
     * Called by the runtime when a signal is generated
     *
     * @param event The LemEventGenerationEvent event representing
     * the circumstances in which the signal is generated
     */
    public synchronized void eventGenerated(LemEventGenerationEvent event) {
        StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.eventGenerated(event, counter);
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Event Generated ,", text));
        doc.addStyle(new StyledElement(" Source Object Id: ", text));
        if(event.getSenderObjectId() != null)
            doc.addStyle(new StyledElement(event.getSenderObjectId().toString(), oId));
        else
            doc.addStyle(new StyledElement("Scenario generated", oId));
        doc.addStyle(new StyledElement(" Event Name: ", text));
        doc.addStyle(new StyledElement(event.getEventType(), classNames));
        doc.addStyle(new StyledElement(" Parameters Sent: ", text));
        LinkedList parameters = new LinkedList();
        if(event.getEventParameters().size() > 0)
            parameters.addAll(event.getEventParameters());
        String para = "";
        for (Iterator i = parameters.iterator(); i.hasNext();) {
            para = para + i.next() + ", ";
        }
        doc.addStyle(new StyledElement(para, values));
        doc.addStyle(new StyledElement(" Delay: ", text));
        if(event.getEventDelay() != null){
        doc.addStyle(new StyledElement(event.getEventDelay().toString(), values));
        }
        doc.addStyle(new StyledElement("\n", text));
        addText(doc);
    }
    
    public synchronized void receivedEvent(LemEventReceivedEvent event){
      StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.receivedEvent(event, counter); 
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Event Received ,", text));
        doc.addStyle(new StyledElement(" Object Id: ", text));  
        doc.addStyle(new StyledElement(event.getEventId().toString(), oId));
        doc.addStyle(new StyledElement(" Event Type: ", text));
        doc.addStyle(new StyledElement(event.getEventType(), classNames));
        doc.addStyle(new StyledElement(" Event Id: ", text));
        doc.addStyle(new StyledElement(event.getEventId().toString(), oId));
        doc.addStyle(new StyledElement(" Parameters: ", text));
        Collection parameters = event.getEventParameters();
        String para = "";
        for (Iterator i = parameters.iterator(); i.hasNext();){
            para = para + i.next() + ", ";
        }
        doc.addStyle(new StyledElement(para, values));  
         doc.addStyle(new StyledElement("\n", text));
        addText(doc);
    }
      
    /**
     * Called by the runtime when selection statement is executed
     *
     *@param event The event representing the condition and the result.
     **/
    
    public synchronized void selectedEvent(LemSelectionEvent event){
        StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.selectedEvent(event, counter);  
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Selection: ", text));
        doc.addStyle(new StyledElement(" Object Id: ", text));  
        //doc.addStyle(new StyledElement(event.getEventId().toString(), oId));
       doc.addStyle(new StyledElement(" Condition ", text));
       doc.addStyle(new StyledElement(event.getSelectCondition(), values));
       doc.addStyle(new StyledElement(" Object Id's Returned: ", text));
       Collection idList = event.getObjectList();
       String list = "";
       for (Iterator i = idList.iterator(); i.hasNext();){
           list = list + i.next().toString() + ", ";
       }
       doc.addStyle(new StyledElement(list, values));  
        doc.addStyle(new StyledElement("\n", text));
        addText(doc);
    }
    
       /**
     * Called by the runtime when a delayed event sent to self is cancelled.
     *
     * The panel displays an cancelled event message listing the event which was
     * cancelled and the object which cancelled it.
     *
     *@param event The delayed event which has been cancelled.
     **/
    public synchronized void cancelledEvent(LemEventCancellationEvent event){
         StyledDocument doc = new StyledDocument();
        //send the object created event to the table model.
        model.cancelledEvent(event, counter);  
        doc.addStyle(new StyledElement(""+counter, oId));
        doc.addStyle(new StyledElement(" Cancelled Event ,", text));
        doc.addStyle(new StyledElement(" Object Id: ", text));  
        doc.addStyle(new StyledElement(event.getEventId().toString(), oId));
        doc.addStyle(new StyledElement(" Event Type: ", text));
        doc.addStyle(new StyledElement(event.getEventType(), classNames));
        doc.addStyle(new StyledElement(" Event Id: ", text));
        doc.addStyle(new StyledElement(event.getEventId().toString(), oId));
        doc.addStyle(new StyledElement(" Parameters: ", text));
        Collection parameters = event.getEventParameters();
        String para = "";
        for (Iterator i = parameters.iterator(); i.hasNext();){
            para = para + i.next() + ", ";
        }
        doc.addStyle(new StyledElement(para, values));   
         doc.addStyle(new StyledElement("\n", text));
        addText(doc);
    }
    
 
   
         


    // Variables declaration - do not modify
    private javax.swing.JButton clear;
    private javax.swing.JComboBox colSelection;
    private javax.swing.JButton filterButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JComboBox matches;
    private javax.swing.JTable table;
    private javax.swing.JTextPane textArea;
    private javax.swing.JTextField textField;
    // End of variables declaration
	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables
	
}

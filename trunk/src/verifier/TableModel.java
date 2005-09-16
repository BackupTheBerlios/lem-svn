/*
 * TableModel.java
 *
 * Created on 29 July 2005, 03:11
 */

package verifier;
import java.awt.Component;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import runtime.LemAttributeChangeEvent;
import runtime.LemAttributeReadEvent;
import runtime.LemEventGenerationEvent;
import runtime.LemObjectCreationEvent;
import runtime.LemObjectDeletionEvent;
import runtime.LemRelationshipCreationEvent;
import runtime.LemRelationshipDeletionEvent;
import runtime.LemStateTransitionEvent;
import java.lang.Thread;


/**
 *  Provides the model which the table is based on, it holds all the data in the table and adds log
 *  messages as they arrive from the JContextLoggerPanel.
 *  The model also provides all functionality to the table which includes sorting in ascending or decending
 *  order and displaying icons in the table headers
 *
 *
 * @author  David Gavin
 * @author  Donna Aloe
 * @author  Simon Franklin
 * @see JContextLoggerPanel
 */


public class TableModel extends AbstractTableModel {
    int colSort = 0;
    boolean ascend = true;
    JTable table = new JTable();
    ImageIcon upIcon = new ImageIcon();
    ImageIcon downIcon = new ImageIcon();
    
    /** the name of the columns */
    private String[] columnNames = {"Counter","Type", "Id1", "Object1", "Id2", "Object2", "State1","State2", "Event","Parameters", "AttName", "AttValue", "RelName", "RelId"};
    
    /** the rows of the table - initally null (Contains all the data of the table) */
    private Vector rowData = new Vector();
    
    /** The filtered rows of the table */
    private Vector filteredData = new Vector();
    
    //Set up the filter for the model
    public Filter filter = new Filter(this);
    
    
    //begin iconHeaderRenderer
    /** Creates a new icon header renderer */
    TableCellRenderer iconHeaderRenderer = new DefaultTableCellRenderer() {
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            // Inherit the colors and font from the header component
            if (table != null) {
                JTableHeader header = table.getTableHeader();
                if (header != null) {
                    setForeground(header.getForeground());
                    setBackground(header.getBackground());
                    setFont(header.getFont());
                }
            }
            if (value instanceof TextAndIcon) {
                setText(((TextAndIcon)value).text);
                setIcon(((TextAndIcon)value).icon);
            } else {
                setText((value == null) ? "" : value.toString());
                setIcon(null);
            }
            setBorder(UIManager.getBorder("TableHeader.cellBorder"));
            setHorizontalAlignment(JLabel.CENTER);
            return this;
        };
    };//end iconHeaderRenderer
    
    /**
     * Creates a new instance of TableModel which holds the data of the table.
     */
    public TableModel() {
        URL imageURL = getClass().getClassLoader().getResource("verifier/up.gif");
        upIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/down.gif");
        downIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        filteredData = rowData;
    }
    
    /**
     * Called by the JContextLoggerPanel when an ObjectCreation occurs.
     * Adds the objectCreated log message to the table
     * @param event the object creation event
     * @param counter the log message counter
     */
    public void objectCreated(LemObjectCreationEvent event, int counter){
        Vector tmp = new Vector();
        tmp = populate(tmp);
        String instances="";
        
        tmp.set(0, new Integer(counter)) ;
        tmp.set(1, "OC");
        tmp.set(2, event.getObjectId());
        for( Iterator i = event.getObjectClassName().iterator(); i.hasNext(); ) {
            instances = instances + i.next().toString() + " ";
        }
        tmp.set(3, instances);
        addRow(tmp);
        refreshTable();
    }
    
    public void objectDeleted(LemObjectDeletionEvent event, int counter){
        Vector tmp = new Vector();
        tmp = populate(tmp);
        String instances="";
        tmp.set(0, new Integer(counter)) ;
        tmp.set(1, "OD");
        tmp.set(2, event.getObjectId());
        for( Iterator i = event.getObjectClassName().iterator(); i.hasNext(); ) {
            instances = instances + i.next().toString() + " ";
        }
        tmp.set(3, instances);
        addRow(tmp);
        refreshTable();
    }
    
    public void relationshipCreation(LemRelationshipCreationEvent event, int counter){
        Vector tmp = new Vector();
        tmp = populate(tmp);
        tmp.set(0, new Integer(counter)) ;
        tmp.set(1,"RC");
        tmp.set(2, event.getActiveObjectId());
        tmp.set(12, event.getAssociationLabel());
        tmp.set(13, event.getLinkObjectId());
        addRow(tmp);
        refreshTable();
    }
    
    public void relationshipDeletion(LemRelationshipDeletionEvent event, int counter){
        Vector tmp = new Vector();
        tmp = populate(tmp);
        tmp.set(0, new Integer(counter)) ;
        tmp.set(1, "RD");
        tmp.set(2, event.getActiveObjectId());
        tmp.set(4, event.getPassiveObjectId());
        tmp.set(12, event.getAssociationLabel());
        tmp.set(13, event.getLinkObjectId());
        addRow(tmp);
        refreshTable();
    }
    
    public void attributeChange(LemAttributeChangeEvent event, int counter){
        Vector tmp = new Vector();
        tmp = populate(tmp);
        tmp.set(0, new Integer(counter));
        tmp.set(1, "WA");
        tmp.set(2,event.getObjectId());
        tmp.set(10,event.getAttributeName());
        //String values = event.getOldValue() +"->" + event.getNewValue();
        //tmp.set(11, values);
        tmp.set(11, event.getNewValue());
        addRow(tmp);
        refreshTable();
    }
    
    public void attributeRead(LemAttributeReadEvent event, int counter){
        Vector tmp = new Vector();
        tmp = populate(tmp);
        tmp.set(0, new Integer(counter)) ;
        tmp.set(1, "RA");
        tmp.set(2, event.getObjectId());
        tmp.set(10,event.getAttributeName());
        tmp.set(11, event.getValue());
        addRow(tmp);
        refreshTable();
    }
    
    
    public void transitionEvent(LemStateTransitionEvent event, int counter){
        Vector tmp = new Vector();
        tmp = populate(tmp);
        tmp.set(0, new Integer(counter)) ;
        tmp.set(1, "TRAN");
        tmp.set(2, event.getObjectId());
        tmp.set(6, event.getFromState());
        tmp.set(7, event.getToState());
        addRow(tmp);
        refreshTable();
    }
    
    public void eventGenerated(LemEventGenerationEvent event, int counter){
        Vector tmp = new Vector();
        tmp = populate(tmp);
        tmp.set(0, new Integer(counter)) ;
        tmp.set(1, "EG");
        tmp.set(2, event.getSenderObjectId());
        tmp.set(8, event.getEventType());
        LinkedList parameters = new LinkedList();
        parameters.addAll(event.getEventParameters());
        String names = "";
        for (int i=0; i < parameters.size(); i++){
            names = names + parameters.get(i) + ", ";
        }
        tmp.set(9,names);
        addRow(tmp);
        refreshTable();
    }
    
    private synchronized void addRow(Vector row){
        rowData.add(row);
    }
    
    private Vector populate(Vector tmp){
        for(int i =0; i< columnNames.length; i++){
            tmp.add(null);
        }
        return tmp;
    }
    
    public synchronized void refreshTable(){
        filter.applyFilter();
        sortAllRowsBy();
    }
    
    /**
     * @return the number of columns in the table.
     */
    public int getColumnCount() {
        return columnNames.length;
    }
    
    /**
     * @return the number of rows in the table.
     */
    public int getRowCount() {
        return filteredData.size();
    }
    
    /**
     * Returns the name of a column according to its index
     * @param col the index of the column
     * @return the column name
     */
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    /**
     * Returns the value in a particular cell, identified by row and column.
     * @param row The row the cell is in
     * @param col The column the cell is in
     * @return the Object / value in the cell.
     */
    public Object getValueAt(int row, int col) {
        Vector tmp = (Vector)filteredData.get(row);
        if (col < tmp.size()){
            Object data = tmp.get(col);
            return data;
        }
        return null;
    }
    
    /**
     *  Not implemented as the data in the table is not editable.
     */
    public void setValueAt(Object value, int row, int col) {}
    
    public void removeFilter(){
        filter.removeFilter();
    }
    
    public void applyFilter(String text, int colIndex, int type ) {
        filter.applyFilter(text, colIndex, type);
    }
    
    /**
     * Sorts the data in the table according to the column selected and the value of ascend.
     * Called when a new row is added to the table and the table needs to be resorted
     * to include the new row.
     */
    public synchronized void sortAllRowsBy() {
        Vector data = getDataVector();
        Collections.sort(data, new ColumnSorter(colSort, ascend));
        fireTableStructureChanged();
    }
    
    /**
     * Sorts the rows of a table based on a column, in ascending or descending
     * order based on the value of ascend
     * @param colIndex the column the sort is based on
     */
    public synchronized void sortAllRowsBy(int colIndex) {
        changeIcon(colSort, null);
        if(colIndex == colSort) {
            if(ascend){
                ascend=false;
            } else{
                ascend=true;
            }
        } else {
            colSort = colIndex;
            ascend=true;
        }
        
        // sort the data in the table
        Vector data = getDataVector();
        Collections.sort(data, new ColumnSorter(colSort, ascend));
        if (ascend) {
            changeIcon(colSort, downIcon);
        } else {
            changeIcon(colSort, upIcon);
        }
        fireTableStructureChanged();
    }
    
    /**
     * @return the vector holding all the rows of the table
     */
    public synchronized Vector getDataVector() {
        //return rowData;
        return filteredData;
    }
    
    public synchronized Vector getUnfilteredVector(){
        return rowData;
    }
    
    /**
     * Sets the text and icon values on the column headers for the icon renderer
     * @param inTable the Table in which the column headers wish to be set.
     */
    public void setTable(JTable inTable) {
        
        table = inTable;
        
        table.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(iconHeaderRenderer);
        
        // Set the text and icon values on the first column for the icon render
        table.getColumnModel().getColumn(0).setHeaderValue(
                new TextAndIcon(columnNames[0], downIcon));
        // Set a icon render and the text and Icon(null) for the rest of the columns
        for (int i=1; i<columnNames.length; i++){
            table.getTableHeader().getColumnModel()
            .getColumn(i).setHeaderRenderer(iconHeaderRenderer);
            
            table.getColumnModel().getColumn(i).setHeaderValue(
                    new TextAndIcon(columnNames[i], null));
            
        }
    }
    /**
     * Changes the icon displayed in the header of a particular column of the table.
     * @param colIndex The index number of the column
     * @param replaceIcon The new icon which is to be displayed.
     */
    public void changeIcon(int colIndex, Icon replaceIcon){
        table.getColumnModel().getColumn(colIndex).setHeaderValue(
                new TextAndIcon(columnNames[colIndex], replaceIcon));
    }
    
    public void changeDisplayRows(Vector data) {
        filteredData = data;
        fireTableStructureChanged();
    }
    
}

/**
 * Allows a new icon to be set (or a previous icon to be removed)
 * from a table cell.
 */
class TextAndIcon {
    
    /**
     * Sets the inital text and icon for the table cell.
     * @param text The text to be displayed in the cell.
     * @param icon The icon to be displayed in the cell.
     */
    TextAndIcon(String text, Icon icon) {
        this.text = text;
        this.icon = icon;
    }
    String text;
    Icon icon;
    
    void setIcon(Icon newIcon) {
        this.icon=newIcon;
    }
}










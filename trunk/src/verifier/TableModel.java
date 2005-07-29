/*
 * TableModel.java
 *
 * Created on 29 July 2005, 03:11
 */

package verifier;

import javax.swing.table.AbstractTableModel;
/**
 *
 * @author Donna
 */
public class TableModel extends AbstractTableModel {
    
    private String[] columnNames = {"Type","From", "To","Instances","State"};
    
    public Object[][] data = { {"Mary", "Campione", "Snowboarding","" ,""},
            {"Alison", "Huml",
             "Rowing","" ,"" },
            {"Kathy", "Walrath",
             "Knitting", "", ""},
            {"Sharon", "Zakhour",
             "Speed reading", "", ""},
            {"Philip", "Milne",
             "Pool", "", ""}
        };

    
 
    /** Creates a new instance of TableModel */
    public TableModel() {
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public int getRowCount() {
        //return data.length;
        return 0;
    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
    
    /** Changes the value in the table
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    
}


/*
 * LogTableModel.java
 *
 * Created on 29 July 2005, 03:11
 */

package verifier;
import java.awt.Component;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
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
import runtime.LemObjectCreationEvent;


/**
 *
 * @author  David Gavin
 * @author  Donna Aloe
 * @author  Simon Franklin
 */
public class TableModel extends AbstractTableModel {
    int colSort = 0;
    boolean ascend = true;
    JTable table = new JTable();
    ImageIcon lemIcon = new ImageIcon();
    URL imageURL = getClass().getClassLoader().getResource("verifier/lem.jpg");
    
    
    private String[] columnNames = {
        "Counter","Type", "Object1", "Object2", "State1",
                "State2", "Event", "AttName", "AttValue", "RelName"};
                
    private Vector rowData = new Vector();
                
                
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
                            setIcon(((TextAndIcon)value).icon);
                            setText(((TextAndIcon)value).text);
                        } else {
                            setText((value == null) ? "" : value.toString());
                            setIcon(null);
                        }
                        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
                        setHorizontalAlignment(JLabel.CENTER);
                        return this;
                    };};
                    
                    
                    /**
                     * Creates a new instance of LogTableModel which holds the data
                     */
                    public TableModel() {
                        lemIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
                    }
                    public void objectCreated(LemObjectCreationEvent event, int counter){
                        Vector tmp = new Vector();
                        runtime.Object o = event.getCreatedObject();
                        String instances="";
                        tmp.add(counter);
                        tmp.add("OC");
                        for( Iterator i = o.getInstances().iterator(); i.hasNext(); ) {
                            runtime.Instance in = (runtime.Instance)i.next();
                            instances = instances + in.getInstanceClass().getName() + ", ";
                        }
                        tmp.add(instances);
                        for(int i=0;i<7;i++){
                            tmp.add(null);
                        }
                        rowData.add(tmp);
                        sortAllRowsBy();
                    }
                    
                    public int getColumnCount() {
                        return columnNames.length;
                    }
                    
                    public int getRowCount() {
                        return rowData.size();
                    }
                    
                    public String getColumnName(int col) {
                        return columnNames[col];
                    }
                    
                    public Object getValueAt(int row, int col) {
                        Vector tmp = (Vector)rowData.get(row);
                        Object data = tmp.get(col);
                        return data;
                    }
                    
                    /** Changes the value in the table
                     */
                    public void setValueAt(Object value, int row, int col) {
                        /**
                         * Vector tmp = (Vector)rowData.get(row);
                         * Object data = tmp.get(col);
                         * return data;
                         * data[row][col] = value;
                         * fireTableCellUpdated(row, col);
                         */
                    }
                    public void sortAllRowsBy() {
                        Vector data = getDataVector();
                        Collections.sort(data, new ColumnSorter(colSort, ascend));
                        fireTableStructureChanged();
                    }
                    public void sortAllRowsBy(int colIndex) {
                        changeIcon(colSort, null);
                        if(colIndex == colSort) {
                            if(ascend){
                                ascend=false;
                            } else{
                                ascend=true;
                            }
                        } else
                        {
                            colSort = colIndex;
                            ascend=true;
                        }
                        Vector data = getDataVector();
                        Collections.sort(data, new ColumnSorter(colIndex, ascend));
                        if (ascend) {
                            changeIcon(colSort, lemIcon);
                        } else {
                            changeIcon(colSort, null);
                        }
                        fireTableStructureChanged();
                    }
                    public Vector getDataVector() {
                        return rowData;
                    }
                    
                    public void setTable(JTable inTable) {
                        
                        table = inTable;
                        
                        table.getTableHeader().getColumnModel()
                        .getColumn(0).setHeaderRenderer(iconHeaderRenderer);
                        
                        // Set the text and icon values on the first column for the icon render
                        table.getColumnModel().getColumn(0).setHeaderValue(
                                new TextAndIcon(columnNames[0], lemIcon));
                        
                        for (int i=1; i<columnNames.length; i++){
                            table.getTableHeader().getColumnModel()
                            .getColumn(i).setHeaderRenderer(iconHeaderRenderer);
                            
                            table.getColumnModel().getColumn(i).setHeaderValue(
                                    new TextAndIcon(columnNames[i], null));
                            
                        }
                    }
                    public void changeIcon(int colIndex, Icon replaceIcon){
                        table.getColumnModel().getColumn(colIndex).setHeaderValue(
                                new TextAndIcon(columnNames[colIndex], replaceIcon));
                    }
}
class TextAndIcon {
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










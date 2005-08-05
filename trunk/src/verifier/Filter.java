/*
 * Filter.java
 *
 *
 */

package verifier;

import java.util.Vector;

/** Apply a filter on the JTable
 *Allows the user to enter a text field and a column and only show the rows which match the input.
 *
 *@param model The table model which holds the data
 * @author Donna Aloe
 */
public class Filter {
    
    private Vector data = new Vector();
    private Vector newData = new Vector();
    private TableModel tModel = new TableModel();
    
    
    /** Creates a new instance of Filter */
    public Filter(TableModel model) {
        tModel = model;
        data = model.getDataVector();
    }
    
    /** Selects those rows which match the filtering criteria
     *@param text the string to filter on
     *@param colIndex the column to filter the text on
     *@see tableModel
     */
    public void applyFilter(String text, int colIndex){
        Vector tmp = new Vector();
        tmp = tModel.getDataVector();
        
        for (int i=0; i<data.size(); i++) {
            tmp = (Vector) data.get(i);
            String txt = (String)tmp.get(colIndex);
            if ((txt.compareToIgnoreCase(text)) == 0) {
                newData.add(tmp);
            }
            
        }
     tModel.changeDisplayRows(newData);
     newData.removeAllElements();
    }
    
    public void removeFilter(){
        newData.removeAllElements();
        tModel.changeDisplayRows(data);
    }
    
}

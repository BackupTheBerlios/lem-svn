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
    public void applyFilter(String text, int colIndex, int type){
        newData.removeAllElements();
        Vector tmp = new Vector();
        tmp = tModel.getDataVector();
        //removes white space at begining and end
        String input = text.trim();
        if (type == 0){
            for (int i=0; i<data.size(); i++) {
                tmp = (Vector) data.get(i);
                String tmp2 = (String)tmp.get(colIndex);
                String txt = tmp2.trim();
                if ((txt.equalsIgnoreCase(input))) {
                    newData.add(tmp);
                }
            }
        } else {
            for (int i=0; i<data.size(); i++) {
                tmp = (Vector) data.get(i);
                String tmp2 = (String)tmp.get(colIndex);
                String txt = tmp2.trim();
                //txt.contains(input);
                //txt.equalsIgnoreCase(input);
                if ((txt.contains(input))) {
                    newData.add(tmp);
                }
        }
        }
        tModel.changeDisplayRows(newData);
        
    }
    
    /**
     * Removes the filter from the table model
     */
    public void removeFilter(){
        newData.removeAllElements();
        tModel.changeDisplayRows(data);
    }
    
}

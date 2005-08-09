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
    private TableModel Model;
    private String text=null;
    private int colIndex;
    private int type;
    
    /** Creates a new instance of Filter */
    public Filter(TableModel model) {
        this.Model = model;
        data = model.getDataVector();
    }
    
    /** Selects those rows which match the filtering criteria
     *@param text the string to filter on
     *@param colIndex the column to filter the text on
     *@see tableModel
     */
    public void applyFilter(String text, int colIndex, int type){
        this.text = text;
        this.colIndex = colIndex;
        this.type = type;
        newData.removeAllElements();
        Vector tmp = new Vector();
        tmp = Model.getDataVector();
        //removes white space at begining and end
        String input = text.trim();
            for (int i=0; i<data.size(); i++) {
                tmp = (Vector) data.get(i);
                String tmp2 = ""+ tmp.get(colIndex);
                String txt = tmp2.trim();
                if (type == 0) {
                    if ((txt.equalsIgnoreCase(input))) {
                        newData.add(tmp);
                    }
                }else {
                    if (((txt.toLowerCase()).contains(input.toLowerCase()))) {
                        newData.add(tmp);
                    }
                }
            }
        Model.changeDisplayRows(newData);
        
    }
    
    
     public void applyFilter(){
         if(text!=null){
        newData.removeAllElements();
        Vector tmp = new Vector();
        tmp = Model.getDataVector();
        //removes white space at begining and end
        String input = text.trim();
            for (int i=0; i<data.size(); i++) {
                tmp = (Vector) data.get(i);
                String tmp2 = ""+ tmp.get(colIndex);
                String txt = tmp2.trim();
                if (type == 0) {
                    if ((txt.equalsIgnoreCase(input))) {
                        newData.add(tmp);
                    }
                }else {
                    if (((txt.toLowerCase()).contains(input.toLowerCase()))) {
                        newData.add(tmp);
                    }
                }
            }
        Model.changeDisplayRows(newData);
         }
    }
    /**
     * Removes the filter from the table model
     */
    public void removeFilter(){
        newData.removeAllElements();
        Model.changeDisplayRows(data);
    }
    
}

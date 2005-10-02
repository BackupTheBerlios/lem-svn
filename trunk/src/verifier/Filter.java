/*
 * Filter.java
 *
 * Copyright (C) 2005 Donna Aloe
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

import java.util.Vector;

/** 
 * Apply a filter on the JTable to display rows which match a selected condition.
 * Contains methods: Filter, applyFiler and remove filter.
 *
 * @author Donna Aloe
 * @ see TableModel
 *
 */
public class Filter {
    
    private Vector data = new Vector();
    private Vector newData = new Vector();
    private TableModel Model;
    private String text=null;
    private int colIndex;
    private int type;
    
    /** Creates a new instance of Filter 
     *
     * @param model The table model to apply the filter to
     */
    public Filter(TableModel model) {
        this.Model = model;
        data = model.getUnfilteredVector();
    }
    
    /** 
     * Apply the filter to the table by selecting the rows which match the filtering criteria
     *
     * @param text the string to filter on
     * @param colIndex the column to filter the text on
     * @param inType 0 if match text exactly, or 1 to match all data which contains text.
     *
     */
    public void applyFilter(String inText, int inColIndex, int inType){
        text = inText;
        colIndex = inColIndex;
        type = inType;
        newData.removeAllElements();
        Vector tmp = Model.getUnfilteredVector();
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
                if (((txt.toLowerCase()).matches(".*" + input.toLowerCase() + ".*"))) {
                    newData.add(tmp);
                }
            }
        }
        Model.changeDisplayRows(newData);
        
    }
    
    /** 
     * Apply the filter to the table based on previous filtering criteria
     * 
     */
    
    public void applyFilter(){
        if(text!=null){
            newData.removeAllElements();
            Vector tmp = new Vector();
            tmp = Model.getUnfilteredVector();
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
                    if (((txt.toLowerCase()).matches(".*" + input.toLowerCase() + ".*"))) {
                        newData.add(tmp);
                    }
                }
            }
            Model.changeDisplayRows(newData);
        }
    }
    
    
    /**
     * Removes the filter from the table model to display all rows 
     */
    public void removeFilter(){
        newData.removeAllElements();
        text=null;
        Model.changeDisplayRows(Model.getUnfilteredVector());
    }
    
}

/*
 * ColumnHeaderListener.java
 *
 * Created on 2 August 2005, 02:50
 */

package verifier;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/** Detects if the user clicks on the head of a column to sort.
 * 
 * @author  David Gavin
 * @author  Donna Aloe
 * @author  Simon Franklin
 *
 * @see JContextLoggerPanel
 * @see TableModel
 */
public class ColumnHeaderListener extends MouseAdapter {
        public ColumnHeaderListener(){}
        
	/** Receieves a mouse clicked event from the GUI 
	* @param evt the Mouse click event
	*/
        public void mouseClicked(MouseEvent evt) {
            JTable table = ((JTableHeader)evt.getSource()).getTable();
            TableModel model = (TableModel)table.getModel();
            TableColumnModel colModel = table.getColumnModel();
    
            // The index of the column whose header was clicked
            int vColIndex = colModel.getColumnIndexAtX(evt.getX());
            int mColIndex = table.convertColumnIndexToModel(vColIndex);
    
            // Return if not clicked on any column header
            if (vColIndex == -1) {
                return;
            }
    
            // Determine if mouse was clicked between column heads
            Rectangle headerRect = table.getTableHeader().getHeaderRect(vColIndex);
            if (vColIndex == 0) {
                headerRect.width -= 3;    // Hard-coded constant
            } else {
                headerRect.grow(-3, 0);   // Hard-coded constant
            }
            if (!headerRect.contains(evt.getX(), evt.getY())) {
                // Mouse was clicked between column heads
                // vColIndex is the column head closest to the click
    
                // vLeftColIndex is the column head to the left of the click
                int vLeftColIndex = vColIndex;
                if (evt.getX() < headerRect.x) {
                    vLeftColIndex--;
                }
            }
            // Calls a sort when a column header is clicked
            model.sortAllRowsBy(vColIndex);
            table.getTableHeader().updateUI();
        }
    }


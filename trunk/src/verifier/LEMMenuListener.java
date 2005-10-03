/*
 * LEMMenuListener.java
 *
 * Created on 6 September 2005, 08:14
 * 
 * Copyright (C) 2005 David Gavin
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

import java.awt.event.ActionListener;
import javax.swing.JFrame;
 /**
 * Associates a action listener to a frame
 * @author David Gavin
 */
public class LEMMenuListener implements ActionListener {
    private JFrame frame;
    /**
     * Creates a new instance of WindowMenuListener 
     * @param inFrame The associated frame
     */
    public LEMMenuListener(JFrame inFrame) {
        frame=inFrame;
    }
    
    /**
     * Sets the frame visibile upon an action event
     * @param evt The Action event received  
     */
    public void actionPerformed(java.awt.event.ActionEvent evt) {
         frame.setState(JFrame.NORMAL);
         frame.setVisible(true);
    }
}

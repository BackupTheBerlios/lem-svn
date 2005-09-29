/*
 * SplashWindow.java
 *
 * Created on April 18, 2005, 5:43 PM
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

package splash;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

/**
 * SplashWindow is a borderless JWindow object, with an image painted upon it.
 * It displays a picture in the foreground while a designated application is 
 * loaded in the background. 
 * @author  David Gavin
 */
class SplashWindow extends JWindow {
    /**
     * Creates new instance of SplashWindow. A SplashWindow requires 3 parameters:
     * The image file name, a frame object and an arbitrary time in milliseconds.
     * @param filename the filename of the image to be displayed.
     * @param f the frame to contain the Splashwindow.
     * @param waitTime the time (in milliseconds) to display the splash window. 
     */
    public SplashWindow(String filename, Frame f, int waitTime) {
        super(f);
        URL imageURL = getClass().getClassLoader().getResource(filename);
        JLabel l = new JLabel(new ImageIcon(imageURL));
        getContentPane().add(l, BorderLayout.CENTER);
        pack();
        Dimension screenSize =
                Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = l.getPreferredSize();
        setLocation(screenSize.width/2 - (labelSize.width/2),
                screenSize.height/2 - (labelSize.height/2));
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setVisible(false);
                dispose();
            }
        });
        final int pause = waitTime;
        final Runnable closerRunner = new Runnable() {
            public void run() {
                setVisible(false);
                dispose();
            }
        };
        Runnable waitRunner = new Runnable() {
            public void run() {
                try {
                    Thread.sleep(pause);
                    SwingUtilities.invokeAndWait(closerRunner);
                } catch(Exception e) {
                    e.printStackTrace();
                    // can catch InvocationTargetException
                    // can catch InterruptedException
                }
            }
        };
        setVisible(true);
        Thread splashThread = new Thread(waitRunner, "SplashThread");
        splashThread.start();
    }
    /**
     * Invokes the main method of an executable java class. Passes any arguments
     * to the main method of the executable java class. Throws an error if the the
     * java class is not executable or doesnt exist.
     * @param className the name of the class to be executed.
     * @param args any commandline arguments to be passed.
     */
    public static void invokeMain(String className, String[] args) {
        try {
            Class.forName(className)
            .getMethod("main", new Class[] {String[].class})
            .invoke(null, new Object[] {args});
        } //end Try
        catch (Exception e) {
            InternalError error =
                    
                    new InternalError("Failed to invoke main method");
            error.initCause(e);
            throw error;
        } //end catch
    }
}

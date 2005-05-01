/*
 * SplashWindow.java
 *
 * Created on April 18, 2005, 5:43 PM
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
 *
 * @author  David Gavin
 */
class SplashWindow extends JWindow {
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

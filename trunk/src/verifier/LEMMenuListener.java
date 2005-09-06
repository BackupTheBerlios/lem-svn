package verifier;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
/*
 * LEMMenuListener.java
 *
 * Created on 6 September 2005, 08:14
 */
 /**
 *
 * @author David Gavin
 */
public class LEMMenuListener implements ActionListener {
    private JFrame frame;
    /** Creates a new instance of WindowMenuListener */
    public LEMMenuListener(JFrame inFrame) {
        frame=inFrame;
    }
    public void actionPerformed(java.awt.event.ActionEvent evt) {
         frame.setState(JFrame.NORMAL);
         frame.setVisible(true);
    }
}

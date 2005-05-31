/*
 * CustomTreeRenderer.java
 *
 * Created on 2 May 2005, 04:13
 */

package verifier;

import java.awt.Component;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Administrator
 */
public class CustomTreeRenderer extends DefaultTreeCellRenderer{
    ImageIcon stateIcon = new ImageIcon();
    ImageIcon transitionIcon = new ImageIcon();
    ImageIcon relationshipIcon = new ImageIcon();
    ImageIcon attributeIcon = new ImageIcon();
    ImageIcon stateMachineIcon = new ImageIcon();
    ImageIcon openIcon = new ImageIcon();
    ImageIcon closedIcon = new ImageIcon();
    ImageIcon eventIcon = new ImageIcon();
    ImageIcon eventsIcon = new ImageIcon();
    ImageIcon classIcon = new ImageIcon();
    
    /** Creates a new instance of CustomTreeRenderer */
    public CustomTreeRenderer() {
        URL imageURL = getClass().getClassLoader().getResource("verifier/state.jpg");
        stateIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/transition.jpg");
        transitionIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/relationship.jpg");
        relationshipIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/attribute.jpg");
        attributeIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/statemachine.jpg");
        stateMachineIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/open.jpg");
        openIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/closed.jpg");
        closedIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/event.jpg");
        eventIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/events.jpg");
        eventsIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/class.jpg");
        classIcon.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
    }
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
        
        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);
        setOpenIcon(openIcon);
        setClosedIcon(closedIcon);
        
        if (value instanceof ClassNode) {
            setIcon(classIcon);
        } else if (leaf && value instanceof RelationshipNode){
            setIcon(relationshipIcon);
        } else if (leaf && value instanceof AttributeNode){
            setIcon(attributeIcon);
        } else if (value instanceof StateMachineNode){
            setIcon(stateMachineIcon);
        } else if (value instanceof StateNode){
            setIcon(stateIcon);
        } else if (value instanceof TransitionNode){
            setIcon(transitionIcon);
        } else if (value instanceof EventsTreeNode){
            setIcon(eventsIcon);
        } else if (value instanceof EventNode){
            setIcon(eventIcon);
        }
        
        return this;
    }
    
    
}

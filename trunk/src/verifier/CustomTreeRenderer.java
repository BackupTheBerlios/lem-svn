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
    ImageIcon State = new ImageIcon();
    ImageIcon Transition = new ImageIcon();
    ImageIcon Relationship = new ImageIcon();
    ImageIcon Attribute = new ImageIcon();    
    ImageIcon StateMachine = new ImageIcon();
    ImageIcon Open = new ImageIcon();
    ImageIcon Closed = new ImageIcon();
    ImageIcon Event = new ImageIcon();
    ImageIcon Events = new ImageIcon();
    ImageIcon Class = new ImageIcon();
    
    /** Creates a new instance of CustomTreeRenderer */
    public CustomTreeRenderer() {
        URL imageURL = getClass().getClassLoader().getResource("verifier/state.jpg");
        State.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/transition.jpg");
        Transition.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/relationship.jpg");
        Relationship.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/attribute.jpg");
        Attribute.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/statemachine.jpg");
        StateMachine.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/open.jpg");
        Open.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/closed.jpg");
        Closed.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/event.jpg");
        Event.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/events.jpg");
        Events.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
        imageURL = getClass().getClassLoader().getResource("verifier/class.jpg");
        Class.setImage(Toolkit.getDefaultToolkit().getImage(imageURL));
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
        setOpenIcon(Open);
        setClosedIcon(Closed);
        
        if (value instanceof ClassNode) {
            setIcon(Class);            
        }
        else if (leaf && value instanceof RelationshipNode){
            setIcon(Relationship);
        }
        else if (leaf && value instanceof AttributeNode){
            setIcon(Attribute);
        }
        else if (value instanceof StateMachineNode){
            setIcon(StateMachine);
        }
        else if (value instanceof StateNode){
            setIcon(State);
        }
        else if (value instanceof TransitionNode){
            setIcon(Transition);
        }
        else if (value instanceof EventsTreeNode){
            setIcon(Events);
        }
        else if (value instanceof EventNode){
            setIcon(Event);
        }
        
        return this;
        }
        
        
}

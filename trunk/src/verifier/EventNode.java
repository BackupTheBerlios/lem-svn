/*
 * EventNode.java
 *
 * Created on 8 May 2005, 11:52
 */

package verifier;

import javax.swing.JPopupMenu;
import metamodel.Event ;

/**
 * Tree node appearing inside a ClassNode. For representing and containing a LEM
 * Event object. Has no children.
 * @author SHukuBOy
 */
public class EventNode extends AbstractDescriptionNode {
    /**The LEM Event object that EventNode contains*/
    Event event; 
    /** 
     * Creates a new instance of EventNode.
     * @param e the LEM Event object to be contained.
     */
    public EventNode(Event e) {
        event = e ; 
    }
    /**
     * Returns name property of LEM Event object.
     * @return The Name of the Event
     */
    public String toString() {
        return "Event: " + event.getName() ;  
    }
    /**
     * Returns description property of LEM Event object.
     * @return The description of the event.
     */
    public String getDescription() {
         if (event.getDescription() != null ) 
            return trim(event.getDescription());   
        else 
            return "" ;         
    }
    /**
     * Returns ContextMenu based on LEM Event object.
     * @return The Context menu based on the event.
     */
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

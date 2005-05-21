/*
 * EventNode.java
 *
 * Created on 8 May 2005, 11:52
 */

package verifier;

import javax.swing.JPopupMenu;
import metamodel.Event ;
/**
 *
 * @author SHukuBOy
 */
public class EventNode extends AbstractDescriptionNode {
    
    Event event ; 
    /** Creates a new instance of EventNode */
    public EventNode(Event e) {
        event = e ; 
    }
    
    public String toString() {
        return "Event: " + event.getName() ;  
    }
    
    public String getDescription() {
         if (event.getDescription() != null ) 
            return trim(event.getDescription());   
        else 
            return "" ;         
    }
    public JPopupMenu getContextMenu()
    {
        JPopupMenu ContextMenu = new JPopupMenu();
        return ContextMenu;
    }
}

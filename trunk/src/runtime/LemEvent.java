/*
 * LemEvent.java
 *
 * Created on June 17, 2005, 12:20 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

/**
 * This class represents events that occur during the running of a model. Events
 * include:
 *  - object creation
 *  - transitioning from one state to another
 *  - attribute changes
 *  - event generation and event delivery
 * 
 * LemEvents are not to be confused with xtUML events or the Event class in the
 * metamodel package.
 * @author sjr
 */
public abstract class LemEvent {
    
    /** Creates a new instance of LemEvent */
    public LemEvent() {
    }
    
}

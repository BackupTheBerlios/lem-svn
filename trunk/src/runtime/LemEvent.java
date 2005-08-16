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

import java.util.Iterator;

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

    /**
     * Notify the given event listener that this LemEvent has occurred.
     *
     * @param listener the LemEventListener to notify
     */
    public abstract void notify( LemEventListener listener );

    /**
     * Notify all listeners of a given Context of this LemEvent's occurrence.
     *
     * @param context the Context to notify
     */
    public void notifyAll( Context context ) {
        for( Iterator i = context.getLemEventListeners().iterator();
                i.hasNext(); ) {
            notify( (LemEventListener)i.next() );
        }
    }
}

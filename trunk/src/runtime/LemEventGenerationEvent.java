/*
 * LemEventGenerationlEvent.java
 *
 * Created on August 20, 2005, 12:11 PM
 */

package runtime;

/**
  * Instances of this event are passed to listeners when a signal is generated.
 * @author thuanseah
 * @see GenerateAction
 */

public class LemEventGenerationEvent extends LemEvent {
    /** 
     * the object that triggers this event
     */
    private runtime.Object source = null;
    /**
     * the action that triggers this event
     */
    private metamodel.GenerateAction action = null;
    
    /** Creates a new instance of LemEventGenerationEvent */
    public LemEventGenerationEvent(runtime.Object s, metamodel.GenerateAction a) {
        source = s;
        action = a;
    }

    /**
     * Retrieve the object reference triggering this event
     */
    public runtime.Object getSignalSource() {  
        return source;
    }
    
    /**
     * Retrieve the action that triggers this event
     */    
    public metamodel.GenerateAction getGenerateAction() {  
        return action;
    }
    
    /**
    * Notify the given listener that this event has occurred.
    * @param listener The listener to notify
    */
    public void notify(LemEventListener listener) {
	//listener.eventGenerated(this);
    }    
}

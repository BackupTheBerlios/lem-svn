/*
 * EventSignature.java
 *
 * Created on September 26, 2004, 7:44 AM
 */

package metamodel;

/**
 * The parameter signature for a event.
 *
 * @author  smr
 */
public class EventSignature extends Signature {
    
    /** The event to which this signature belongs */
    private Event event = null;
    
    /** Creates a new instance of EventSignature */
    public EventSignature() {
    }

    /**
     * Return the Event associated with this EventSignature
     *
     * @return the Event associated with this EventSignature
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Set the Event associated with this EventSignature
     *
     * @param aEvent associated with this EventSignature
     */
    public void setEvent(Event aEvent) {
        this.event = aEvent;
    }
    
    /** 
     * Return the domain to which this signature belongs
     *
     * @return the domain to which this signature belongs
     */
    public Domain getDomain(){
        return event.getDomainClass().getDomain();
    }
    
    
}

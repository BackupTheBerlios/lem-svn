/*
 * LemRelationshipCreationEvent.java
 *
 * Created on August 20, 2005, 12:11 PM
 */

package runtime;

/**
  * Instances of this event are passed to listeners when an association is created.
 * @author thuanseah
 * @see RelateAction
 */

public class LemRelationshipCreationEvent extends LemEvent {
    /** 
     * the association instance created from the action
     */
    private AssociationInstance association = null;
    /**
     * the action that triggers this event
     */
    private metamodel.RelateAction action = null;
    
    /** Creates a new instance of LemRelateEvent */
    public LemRelationshipCreationEvent(AssociationInstance a, metamodel.RelateAction r) {
        association = a;
        action = r;
    }

    /**
     * Retrieve the association instance responsible for triggering this event
     */
    public AssociationInstance getCreatedRelationship() {  
        return association;
    }
    
    /**
     * Retrieve the action that triggers this event
     */    
    public metamodel.RelateAction getRelateAction() {  
        return action;
    }
    
    /**
    * Notify the given listener that this event has occurred.
    * @param listener The listener to notify
    */
    public void notify(LemEventListener listener) {
	//listener.relationshipCreation(this);
    }    
}

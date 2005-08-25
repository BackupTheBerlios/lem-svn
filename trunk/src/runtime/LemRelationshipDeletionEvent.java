/*
 * LemRelationshipDeletionEvent.java
 *
 * Created on August 20, 2005, 12:11 PM
 */

package runtime;

/**
  * Instances of this event are passed to listeners when an association is created.
 * @author thuanseah
 * @see RelateAction
 */

public class LemRelationshipDeletionEvent extends LemEvent {
    /** 
     * the association instance created from the action
     */
    private AssociationInstance association = null;
    /**
     * the action that triggers this event
     */
    private metamodel.UnrelateAction action = null;
    
    /** Creates a new instance of LemRelateEvent */
    public LemRelationshipDeletionEvent(AssociationInstance a, metamodel.UnrelateAction r) {
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
    public metamodel.UnrelateAction getUnrelateAction() {  
        return action;
    }
    
    /**
    * Notify the given listener that this event has occurred.
    * @param listener The listener to notify
    */
    public void notify(LemEventListener listener) {
	//listener.relationshipDeletion(this);
    }    
}

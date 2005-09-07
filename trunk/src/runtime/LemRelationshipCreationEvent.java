/*
 * LemRelationshipCreationEvent.java
 *
 * Created on August 20, 2005, 12:11 PM
 */

package runtime;
import java.util.Collection;
import java.util.LinkedList;


/**
  * Instances of this event are passed to listeners when an association is created.
 * @author thuanseah
 * @see RelateAction
 */

public class LemRelationshipCreationEvent extends LemEvent {
    
    /**
     * the unique alpha-numeric object id for object 1
     */
    private Integer object_id1;
    
    /**
     * the unique alpha-numeric object id for object 2
     */
    private Integer object_id2;
    
    /**
     * the alpha-numeric association label for the association
     */
    private String association_label=null;
    
    /**
     * the unique alpha-numeric object id for the link object
     */
    private Integer linkobject_id=null;
    
    /** Creates a new instance of LemRelateEvent for events with no link object */
    public LemRelationshipCreationEvent(int id1, int id2, String name) {
        object_id1 = new Integer(id1);
        object_id2 = new Integer(id2);
        association_label = name;
    }

    /** Creates a new instance of LemRelateEvent for events with link object */
    public LemRelationshipCreationEvent(int id1, int id2, String name, int id4) {
        object_id1 = new Integer(id1);
        object_id2 = new Integer(id2);
        association_label = name;
        linkobject_id = new Integer(id4);
    }    
    
    /**
     * function to return the unique object id of the active object
     */
    public Integer getActiveObjectId() {
        return object_id1;
    }
   
    /**
     * function to return the unique object id of the passive object
     */
    public Integer getPassiveObjectId() {
        return object_id2;
    }
    
    /**
     * function to return the unique object id of the association instance
     */
    public String getAssociationLabel() {
        return association_label;
    }    
    
    /**
     * function to return the unique object id of the link object
     */
    public Integer getLinkObjectId() {
        return linkobject_id;
    }        
    
    /**
    * Notify the given listener that this event has occurred.
    * @param listener The listener to notify
    */
    public void notify(LemEventListener listener) {
	listener.relationshipCreation(this);
    }    
}

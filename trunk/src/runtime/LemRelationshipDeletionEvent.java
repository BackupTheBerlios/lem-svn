/*
 * LemRelationshipDeletionEvent.java
 *
 * Created on August 20, 2005, 12:11 PM
 */

package runtime;
import java.util.Collection;
import java.util.LinkedList;


/**
  * Instances of this event are passed to listeners when an association is unrelated
 * @author thuanseah
 * @see UnrelateAction
 */

public class LemRelationshipDeletionEvent extends LemEvent {
    
    /**
     * the unique alpha-numeric object id for object 1
     */
    private Integer object_id1;
    
    /**
     * the names of all classes object1 is an instance of
     */
    private Collection object_name1 = null;
    
    /**
     * the unique alpha-numeric object id for object 2
     */
    private Integer object_id2;
    
    /*
     * the names of all classes object2 is an instance of
     */
    private Collection object_name2 = null;
    
    /**
     * the unique alpha-numeric association id for the association instance
     */
    private Integer association_id;
    
    /**
     * the unique alpha-numeric object id for the link object
     */
    private Integer linkobject_id=null;
    
    /** Creates a new instance of LemRelationshipDeletionEvent for events with no link object */
    public LemRelationshipDeletionEvent(int id1, Collection c1, int id2, Collection c2, int id3) {
        object_id1 = new Integer(id1);
        object_name1 = new LinkedList(c1);
        object_id2 = new Integer(id2);
        object_name2 = new LinkedList(c2);
        association_id = new Integer(id3);
    }

    /** Creates a new instance of LemRelationshipDeletionEvent for events with link object */
    public LemRelationshipDeletionEvent(int id1, Collection c1, int id2, Collection c2, int id3, int id4) {
        object_id1 = new Integer(id1);
        object_name1 = new LinkedList(c1);
        object_id2 = new Integer(id2);
        object_name2 = new LinkedList(c2);
        association_id = new Integer(id3);
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
    public Integer getAssociationId() {
        return association_id;
    }    
    
    /**
     * function to return the list of classes that made up the active object
     */
    public Collection getActiveObjectNames() {
        return object_name1;
    }    
    
    /**
     * function to return the list of classes that made up the passive object
     */
    public Collection getPassiveObjectNames() {
        return object_name2;
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
	listener.relationshipDeletion(this);
    }    
}

/*
 * LemObjectDeletionEvent.java
 *
 * Created on June 17, 2005, 12:23 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

/**
 * Instances of this object are delieverd to LemEventListeners when a is deleted 
 * by the execution of some state procedure
 * @author thuanseah
 */
public class LemObjectDeletionEvent extends LemEvent {
    /*
     * A list of the class names for all the instances in the object
     */
    private java.util.Collection className = new java.util.LinkedList();
    /*
     * A Integer value of the object's unique Id
     */
    private Integer objectId;

    /** Creates a new instance of LemObjectDeletionEvent
     * 
     * @param o the object to be deleted
     */
    public LemObjectDeletionEvent( int id, java.util.Collection c) {
        objectId = new Integer(id);
        className.addAll(c);
    }
    
    /**
     * Returns the class names of all instances in this object
     *
     * @return a list of class names
     */
    public java.util.Collection getObjectClassName() { 
        return className;
    }

    /**
     * Returns the object id of the object involved
     *
     * @return an Integer representation of the object Id
     */    
    public Integer getObjectId() {
        return objectId;
    }
    
    /**
     * Calls the objectDeleted method on the given LemEventListener.
     * Passes <code>this</code> as the parameter to the objectDeleted
     * method.
     *
     * @param listener the LemEventListener to notify
     */
    public void notify( LemEventListener listener ) {
        listener.objectDeleted( this );
    }
}

/*
 * LemObjectCreationEvent.java
 *
 * Created on June 17, 2005, 12:23 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

/**
 * Instances of this object are delieverd to LemEventListeners when a new 
 * object is created by the execution of some state procedure
 *
 * @author sjr
 */
public class LemObjectCreationEvent extends LemEvent {
    
    /**
     * The newly created object
     */
    runtime.Object createdObject;
    
    /**
     * The CreateAction that was responsible for creating this object
     */
    metamodel.CreateAction action;
    
    /** Creates a new instance of LemObjectCreationEvent
     * 
     * @param o the newly created object
     * @param a the CreateAction which caused the creation of this object
     */
    public LemObjectCreationEvent( runtime.Object o, metamodel.CreateAction a ) {
        this.createdObject = o;
        this.action = a;
    }
    
    /**
     * Returns the newly created object
     *
     * @return the newly created object
     */
    public runtime.Object getCreatedObject() { 
        return createdObject;
    }
    
    /** 
     * Returns the CreateAction responsible for creating the new object
     *
     * @return the CreateAction responsible for creating the new object
     */
    public metamodel.CreateAction getCreateAction() {
        return action;
    }

    /**
     * Calls the objectCreated method on the given LemEventListener.
     * Passes <code>this</code> as the parameter to the objectCreated
     * method.
     *
     * @param listener the LemEventListener to notify
     */
    public void notify( LemEventListener listener ) {
        listener.objectCreated( this );
    }
}

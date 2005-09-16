package runtime;

/**
 * Provides the abstract interface all listeners must implement in order to
 * receive notification of events from the runtime
 *
 * @author Donna Aloe
 * @author James Ring
 *
 */

public interface LemEventListener {
    
    /**
     * Change the logging level to Debug which corresponds to level 2.
     */
//   void levelDebug();
    
    /**
     * Change the logging level to Info which corresponds to level 1.
     */
//   void levelInfo();
    
    /**
     * Called when a EventGeneration action has caused an xtUML Event to be placed
     * in the destination object's event queue
     *
     * @param ev the LemEventGenerationEvent which represents the circumstances
     * of the generated event
     */
    public void eventGenerated(LemEventGenerationEvent ev);
    
    /**
     * The logListener can notify the logger that a receiveEvent has occured.
     *
     * @param ev Event object that was received.
     */
    public void receivedEvent(LemEventReceivedEvent ev);
    
    /**
     * Called by the runtime when an event recieved by an object has caused a
     * transition from one state to the next.
     *
     * @param event The event representing the circumstance in which the object
     * transitioned between states
     */
    public void transitionEvent(LemStateTransitionEvent event);
    
    /**
     * Called by the runtime when reading an attribute's value
     * 
     * @param event The event representing the circumstances in which the
     *  attribute is read.
     */
    public void attributeRead(LemAttributeReadEvent event);
    
    /**
     * Called by the runtime when an attribute's value has changed.
     *
     * @param event The event representing the circumstances in which the
     *  attribute changed values.
     * @param attributeName The name of the attribute which has changed.
     */
    public void attributeChange(LemAttributeChangeEvent event);
    
    /**
     * Called by the runtime when a DeleteAction has been executed successfully
     * and an existing relationship has been deleted.
     *
     * @param event The LemRelationshipDeletionEvent representing the circumstances
     * in which the relationship was deleted.
     */
    public void relationshipDeletion(LemRelationshipDeletionEvent event);
    
    /**
     * Called by the runtime when a CreatAction has been executed successfully
     * and a new relationship has been created.
     *
     * @param event the LemRelationshipCreationEvent representing the circumstances
     * in which the relationship is created.
     */
    public void relationshipCreation(LemRelationshipCreationEvent event);
    
    /**
     * Called by the runtime when a CreateAction has been executed successfully
     * and a new Object has been created
     *
     * @param event the LemObjectCreationEvent representing the circumstances
     * in which the new object was created
     */
    public void objectCreated( LemObjectCreationEvent event);
    
    /**
     * Called by the runtime when a DeleteAction has been executed successfully
     * and an object has been deleted
     *
     * @param event The LemObjectDeletionEvent representing the circumstances
     *  in which the existing object was deleted
     */
    public void objectDeleted( LemObjectDeletionEvent event);
    
    /**
     * Called by the runtime when an ReclassificationAction has been executed
     * successfully and an existing object, in a generalisation heirarchy has
     * been reclassified
     *
     * @param event The object which was reclassified.
     * @param previousType The type of object before reclassification.
     */
//    public void reclassification( LemObjectReclassificatonEvent event, String previousType) ;
    
    /**
     * Called by the runtime when a delayed event sent by self is cancelled.
     *
     * @param event The event which has been cancelled.
     */
    public void cancelledEvent(LemEventCancellationEvent event);

    /**
     * Called by the runtime when a select expression is executed
     *
     * @param event The event representing the condition and result of the select 
     * expression
     */
    public void selectedEvent(LemSelectionEvent event);   
}

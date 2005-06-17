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
//    void levelDebug();

    /**
     * Change the logging level to Info which corresponds to level 1.
     */
//    void levelInfo();

    /**
     * Called when a EventGeneration action has caused an xtUML Event to be placed
     * in the destination object's event queue
     *
     * @param ev the LemEventGenerationEvent which represents the circumstances
     * of the generated event
     */
//    void eventGenerated(EventObject ev);

    /**
     * The logListener can notify the logger that a receiveEvent has occured.
     *
     * @param ev Event object that was received.
     */
//    void receiveEvent(EventObject ev);

    /**
     * The logListener can notify the logger that a transition event has occured.
     *
     * @param tev Transition that occured within the runtime.
     */
//    void transitionEvent(TransitionEvent tev);

    /**
     * The logListener can notify the logger that an attribute change has occured.
     *
     * @param ob Object in which the attribute changed.
     * @param attributeName The name of the attribute which has changed.
     */
//    void attributeChange(ObjectObject ob, String attributeName);

    /**
     * The logListener can notify the logger that a realtionship is about to be deleted.
     *
     * @param re The relationship to be deleted.
     */
//    void relationshipDeletion(RelationshipObject re);

    /**
     * The logListener can notify the logger that a realtionship has been created.
     *
     * @param re The relationship created.
     */
//    void relationshipCreation(RelationshipObject re);

    /**
     * Called by the runtime when a CreateAction has been executed successfully
     * and a new Object has been created
     *
     * @param event the LemObjectCreationEvent representing the circumstances
     * in which the new object was created
     */
    void objectCreated( LemObjectCreationEvent event);

    /**
     * The logListener can notify the logger that an object is to be deleted.
     *
     * @param ob The object to be deleted.
     */
//    void objectDeletion(ObjectObject ob);

    /**
     * The logListener can notify the logger that an object has been reclassified.
     *
     * @param ob The object which was reclassified.
     * @param previousType The type of object before reclassification.
     */
//    void reclassification(ObjectObject ob, String previousType) ;

    /**
     * The logListener can notify the logger that an event has been cancelled.
     *
     * @param ev The event which has been cancelled.
     */
//    void cancelledEvent(EventObject ev);

}

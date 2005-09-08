/*
 * ConsoleLogger.java
 *
 */

package verifier;

import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import runtime.LemAttributeChangeEvent;
import runtime.LemRelationshipCreationEvent;
import runtime.LemRelationshipDeletionEvent;
import runtime.LemEventGenerationEvent;
import runtime.LemAttributeReadEvent;
import runtime.LemStateTransitionEvent;

/**
 * Receives all activity by the runtime and logs desired activity to the console
 * and a text file.
 * Logging occurs at one of two levels, Level 1 which only logs events and Level 2
 * which logs all activity.
 * Log messages include:
 *   - ObjectCreation
 *   - ObjectDeletion
 *
 * @author  Donna Aloe
 */
public class ConsoleLogger implements runtime.LemEventListener {
	
	static Logger logger = Logger.getRootLogger();
	private int counter = 0;
	
	
	/**
	 *
	 * Creates the text file in which all log messages are to written to
	 *
	 * @param c The context in which the console logger is operating
	 */
	public ConsoleLogger(runtime.Context c) {
		c.addLemEventListener(this);
		PropertyConfigurator.configure("eventLog.txt");
		
	}
	
	/**
	 * Set the level of loging to Level 1
	 */
	public void levelDebug() {
		//logger.setLevel(Level.DEBUG);
	}
	
	/**
	 * Set the level of loging to Level 2
	 */
	public void levelInfo(){
		//logger.setLevel(Level.INFO);
	}
	
	
	/**
	 * Called by the runtime when a CreateAction has been implemented
	 * and a new object has been created.
	 * A log message is then written to the console and the text file
	 *
	 * @param event The Object Creation event to be logged
	 **/
	
	public void objectCreated(runtime.LemObjectCreationEvent event) {
		String message = " OC \n";
                message = message + "Object id:"+event.getObjectId().intValue()+"\n";
                message = message + "ClassName=";
		for( Iterator i = event.getObjectClassName().iterator(); i.hasNext(); ) {
			message = message + i.next().toString() + ", ";
		}
		logger.debug(counter + message);
		counter ++;
	}
	
	/**
	 * Called by the runtime when a CreateAction has been implemented
	 * and a new object has been created.
	 * A log message is then written to the console and the text file
	 *
	 * @param event The Object Creation event to be logged
	 **/
	public void objectDeleted(runtime.LemObjectDeletionEvent event) {
 		// testing message
                String message = " OD \n";
                message = message + "Object id:"+event.getObjectId().intValue()+"\n";
                message = message + "ClassName=";
		for( Iterator i = event.getObjectClassName().iterator(); i.hasNext(); ) {
			message = message + i.next().toString() + ", ";
		}
		logger.debug(counter + message);
		counter ++;
	}	

        /**
        * Called by the runtime when reading an attribute's value
        * 
        * @param event The event representing the circumstances in which the
        *  attribute is read.
        */
        public void attributeRead(LemAttributeReadEvent event) {
                // testing message starts
                String message = " AR \n";
                message = message + "Object id: "+event.getObjectId().intValue()+"\n";
                message = message + "Attribute name: "+event.getAttributeName()+"\n";
                message = message + "Attribute value: "+event.getValue()+"\n";
                logger.debug(counter + message);
                counter++;
        }
        
	/**
	 * Called by the runtime when an AssignmentAction has been executed.
	 * Prints old value and new value to console.
	 * @param e The event representing the attribute change
	 */
	public void attributeChange(LemAttributeChangeEvent event) {
                // testing message starts
                String message = " AC \n";
                message = message + "Object id: "+event.getObjectId().intValue()+"\n";
                message = message + "Attribute name: "+event.getAttributeName()+"\n";
                message = message + "Attribute old value: "+event.getOldValue()+"\n";                
                message = message + "Attribute new value: "+event.getNewValue()+"\n";                                
		logger.debug(counter + message);
		counter ++;
	}
        
        /**
        * Called by the runtime when a DeleteAction has been executed successfully
        * and an existing relationship has been deleted.
        *
        * @param event The LemRelationshipDeletionEvent representing the circumstances
        * in which the relationship was deleted.
        */        
	 public void relationshipDeletion(LemRelationshipDeletionEvent event) {
                // testing message starts
                String message = " RD \n";
                message = message + "Active object id: "+event.getActiveObjectId().intValue()+ "\n";
                message = message + "Passive object id: "+event.getPassiveObjectId().intValue()+"\n";
                if(event.getLinkObjectId() != null) {
                    message = message + "Link object id: "+event.getLinkObjectId().intValue()+"\n";
                }
                message = message + "Association: " + event.getAssociationLabel();
                // testing message ends
                
                logger.debug(counter + message);
                counter++;
	 }        
        
         /**
         * Called by the runtime when a CreatAction has been executed successfully
         * and a new relationship has been created.
         *
         * @param event the LemRelationshipCreationEvent representing the circumstances
         * in which the relationship is created.
         */        
	public void relationshipCreation(LemRelationshipCreationEvent event) {
                // testing message starts
                String message = " RC \n";
                message = message + "Active object id: "+event.getActiveObjectId().intValue()+ "\n";
                message = message + "Passive object id: "+event.getPassiveObjectId().intValue()+"\n";
                if(event.getLinkObjectId() != null) {
                    message = message + "Link object id: "+event.getLinkObjectId().intValue()+"\n";
                }
                message = message + "Association: " + event.getAssociationLabel();
                // testing message ends
                
                logger.debug(counter + message);
                counter++;
	}
        
        public void eventGenerated(LemEventGenerationEvent event) {
               String message = " EG ";
                message = "Object ID ";
                message = message + " generated signal ";
                message = message + " Signal name ";                
                message = message + "Object ID";
                logger.debug(counter + message);
                counter++;            
        }    
        
        /**
        * Called by the runtime when an event recieved by an object has caused a
        * transition from one state to the next.
        *
        * @param event The event representing the circumstance in which the object
        * transitioned between states
        */
        public void transitionEvent(LemStateTransitionEvent event) {     
                // testing message starts
                String message = " ST \n";
                message = message + "Object id: "+event.getObjectId().intValue()+ "\n";
                message = message + "From state: " + event.getFromState() + "\n";
                message = message + "To state: " + event.getToState() + "\n";
                // testing message ends
                
                logger.debug(counter + message);
                counter++;            
        }
}

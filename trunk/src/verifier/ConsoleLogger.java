/*
 * ConsoleLogger.java
 *
 */

package verifier;

import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import runtime.LemAttributeChangeEvent;

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
		runtime.Object o = event.getCreatedObject();
		String message = " OC ";
		for( Iterator i = o.getInstances().iterator(); i.hasNext(); ) {
			runtime.Instance in = (runtime.Instance)i.next();
			
			message = message + in.getInstanceClass().getName() + ", ";
		}
		
		logger.debug(counter + message);
		counter ++;
	}
	
	
	
	/**
	 * Called by the runtime when an AssignmentAction has been executed.
	 * @param e The event representing the attribute change
	 */
	public void attributeChange(LemAttributeChangeEvent e) {
		/*Hello*/
	}
	
}

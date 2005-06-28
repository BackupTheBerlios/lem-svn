/*
 * CreateAction.java
 *
 * Created on May 14, 2005, 9:54 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package metamodel;
import java.util.Collection;
import java.util.Iterator;
import runtime.*;

/**
 * This Action is responsible for creating new instances of runtime.Object.
 * @author sjr
 */
public class CreateAction extends Action {
    
    /**
     * A new object containing an instance of these classes will be created when 
     * this CreateAction is executed.
     */
    Collection classes = null;
    
    
    /** Creates a new instance of CreateAction */
    public CreateAction() {
    }
    
    /**
     * Sets the class to be instantiated when this action is executed
     *
     * @param theClass the class to be instantiated
     * @todo An object may be an instance of multiple classes
     */
    public void setClasses( Collection classes ) {
        this.classes = classes;
    }
    
    /**
     * Execute the given action. This will cause a new Object to be created and placed
     * in the context's object list
     *
     * @param context The context into which the newly created object should be inserted
     * @todo move listener notification code to Context or LemEvent
     * @todo execute( Context ) should throw LemRuntimeException
     */
    public void execute( Context context ) {
        runtime.Object o = null;
        try {
            // Create the new object
            o = new runtime.Object( classes );
            // Add it to the context
            context.addObject(o);
            // Notify listeners that the object has been added
            LemObjectCreationEvent e = new LemObjectCreationEvent( o, this );
            for( Iterator i = context.getLemEventListeners().iterator(); i.hasNext(); ) {
                runtime.LemEventListener l = (runtime.LemEventListener)i.next();
                l.objectCreated(e);
            }
        } catch( LemRuntimeException e ) {
            e.printStackTrace();
        }
    }
}
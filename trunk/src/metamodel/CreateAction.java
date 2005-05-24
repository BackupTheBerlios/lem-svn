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
import runtime.*;

/**
 * This Action is responsible for creating new instances of runtime.Object.
 * @author sjr
 */
public class CreateAction extends Action {
    
    /**
     * A new object containing an instance of this class will be created when this CreateAction is
     * executed.
     *
     */
    metamodel.Class theClass = null;
    // TODO: An object may be an instance of multiple classes
    
    
    /** Creates a new instance of CreateAction */
    public CreateAction() {
    }
    
    /**
     * Sets the class to be instantiated when this action is executed
     *
     * @param theClass the class to be instantiated
     */
    public void setClass( metamodel.Class theClass ) {
        this.theClass = theClass;
    }
    // TODO: An object may be an instance of multiple classes
    
    /**
     * Execute the given action. This will cause a new Object to be created and placed
     * in the context's object list
     * 
     * @param context The context into which the newly created object should be inserted
     */
    public void execute( Context context ) {
        runtime.Object o = new runtime.Object( new metamodel.Class[] {theClass} );
        context.addObject(o);
    }
}

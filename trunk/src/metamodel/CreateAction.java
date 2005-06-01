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
     */
    public void execute( Context context ) {
        runtime.Object o = null;
        try {
            o = new runtime.Object( classes );
        } catch( LemRuntimeException e ) {
            e.printStackTrace();
        }
        context.addObject(o);
    }
}

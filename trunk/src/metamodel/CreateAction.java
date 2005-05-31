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
import java.util.ArrayList;
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
     * @todo An object may be an instance of multiple classes
     */
    metamodel.Class theClass = null;
    
    
    /** Creates a new instance of CreateAction */
    public CreateAction() {
    }
    
    /**
     * Sets the class to be instantiated when this action is executed
     *
     * @param theClass the class to be instantiated
     * @todo An object may be an instance of multiple classes
     */
    public void setClass( metamodel.Class theClass ) {
        this.theClass = theClass;
    }
    
    /**
     * Execute the given action. This will cause a new Object to be created and placed
     * in the context's object list
     *
     * @param context The context into which the newly created object should be inserted
     */
    public void execute( Context context ) {
        ArrayList classes = new ArrayList();
        runtime.Object o = null;
        classes.add( theClass );
        try {
            o = new runtime.Object( classes );
        } catch( LemRuntimeException e ) {
            e.printStackTrace();
        }
        context.addObject(o);
    }
}

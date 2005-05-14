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
 *
 * @author sjr
 */
public class CreateAction extends Action {
    
    /**
     * A new instance of this class will be created when this CreateAction is
     * executed
     *
     * TODO: An instance may be an instance of multiple classes
     */
    metamodel.Class theClass = null;
    
    /** Creates a new instance of CreateAction */
    public CreateAction() {
    }
    
    public void setClass( metamodel.Class theClass ) {
        this.theClass = theClass;
    }
    
    public void execute() {
        Instance i = new Instance( theClass );
    }
}

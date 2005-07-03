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

/**
 * This is the xtUML object creation action.
 * @author sjr
 */
public class CreateAction extends Action {
    
    /**
     * A new object containing an instance of these classes will be created when 
     * this CreateAction is executed.
     */
    Collection classes = null;
    
    /**
     * The reference to the variable which will contain a reference to the newly
     * created object. May be null if the "create object" statement did not specify
     * an attribute reference.
     */
    VariableReference variable = null;
    
    /** Creates a new instance of CreateAction */
    public CreateAction() {
    }
    
    /**
     * Sets the set of classes whose instances participate in the object
     * created when this action is executed.
     *
     * @param classes the set of classes
     */
    public void setClasses( Collection classes ) {
        this.classes = classes;
    }
    
    /**
     * Gets the the set of classes whose instances participate in the object
     * created when this action is executed.
     *
     * @return the set of classes
     */
    public Collection getClasses() {
	    return classes;
    }

    /**
     * Gets the reference to the variable which will contain a reference to the 
     * newly created object.
     *
     * @return the variable reference
     */
    public VariableReference getVariable() {
        return variable;
    }

    /**
     * Sets the reference to the variable which will contain a reference to the
     * newly created object.
     *
     * @param variable the variable reference
     */
    public void setVariable(VariableReference variable) {
        this.variable = variable;
    }
}

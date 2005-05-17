/*
 * Action.java
 *
 * Created on May 14, 2005, 9:56 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package metamodel;

/**
 *
 * @author sjr
 */
public abstract class Action {
    
    /** Creates a new instance of Action */
    public Action() {
    }
    
    /** Execute the action in the given context.
     *
     * @param context The context in which to execute this action
     */
    public abstract void execute( runtime.Context context );
}

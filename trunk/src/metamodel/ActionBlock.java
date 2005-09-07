
package metamodel;
import java.util.LinkedList;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a LEM action block (a block containing a local variable scope,
 * LEM actions.
 *
 * @author npiggin
 */
public class ActionBlock {
    //private Collection variableDeclarations;
    private HashMap variableDeclarations;
    private LinkedList actionList;
    private ActionBlock parent=null;
    
    /** Creates a new instance of ActionBlock */
    public ActionBlock() {
	actionList = new LinkedList();
	//variableDeclarations = new ArrayList();
        variableDeclarations = new HashMap();
    }
    
    /** returns the local variables declared in this block */
    public Collection getVariableDeclarations() {
	//return variableDeclarations;
        return variableDeclarations.values();
    }

    /** adds a variable declaration to the block 
     *  return false of the variable already exist in the block
     *  and true to indicate variable has been added.
     */
    public boolean addVariableDeclaration(VariableDeclaration v) {
	//variableDeclarations.add(v);
        if(containsVariable(v.getVariableName()))
            return false;
        variableDeclarations.put(v.getVariableName(), v);
        return true;
    }
    
    /**
     * to retrieve the variable type given a variable name
     */
    public DataType getVariableType(String variableName) {
        if(containsVariable(variableName)) {
            return ((VariableDeclaration)variableDeclarations.get(variableName)).getVariableType();
        }
        else if(parent != null) {
            return parent.getVariableType(variableName);
        }
        return null;
    }
    
    public boolean isValidVariable(String variable) {
        if(containsVariable(variable))
            return true;
        else if(parent != null)
            return parent.isValidVariable(variable);
        return false;
    }
    
    public boolean containsVariable(String variable) {
        return variableDeclarations.containsKey(variable);
    }
    
    /** returns the list of actions performed by this block */
    public LinkedList getActions() {
	return actionList;
    }

    /** adds the next action to be performed by the block */
    public void addAction(Action a) {
        actionList.add(a);
    }
    
    /** sets the action block in which this block resides in */
    public void setParent(ActionBlock p) {
        this.parent = p;
    }
    
    /** returns the action block in which this block resides in */
    public ActionBlock getParent() {
        return this.parent;
    }    
}

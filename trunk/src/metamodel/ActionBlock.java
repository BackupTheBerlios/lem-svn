
package metamodel;
import java.util.LinkedList;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Represents a LEM action block (a block containing a local variable scope,
 * LEM actions.
 *
 * @author npiggin
 */
public class ActionBlock {
    private Collection variableDeclarations;
    private LinkedList actionList;
    
    /** Creates a new instance of ActionBlock */
    public ActionBlock() {
	actionList = new LinkedList();
	variableDeclarations = new ArrayList();
    }
    
    /** returns the local variables declared in this block */
    public Collection getVariableDeclarations() {
	return variableDeclarations;
    }

    /** adds a variable declaration to the block */
    public void addVariableDeclaration(VariableDeclaration v) {
	variableDeclarations.add(v);
    }

    /** returns the list of actions performed by this block */
    public LinkedList getActions() {
	return actionList;
    }

    /** adds the next action to be performed by the block */
    public void addAction(Action a) {
	actionList.add(a);
    }
}


package metamodel;
import java.util.LinkedList;
import java.util.Collection;
import java.util.ArrayList;

/**
 * Represents an "if" statement action.
 *
 * @author npiggin
 */
public class IfStatement extends Action {
	LinkedList condList;
    
	/** Creates a new instance of IfStatement */
	public IfStatement() {
		condList = new LinkedList();
	}

	/** returns the conditional alternatives of this if statement */
	public LinkedList getConditionalAlternatives() {
		return condList;
	}

	/** adds a conditional alternative to the if statement */
	public void addConditionalAlternative(Expression c, ActionBlock b) {
		ConditionalAlternative ca = new ConditionalAlternative(c, b);
		condList.add(ca);
	}
        
        /** adds a conditional alternative to the if statement */
        public void addConditionalAlternative( ConditionalAlternative a ) {
            condList.add( a );
        }
}

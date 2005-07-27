
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
	/**
	 * ConditionalAlternative pairs a condition with its action-block.
	 * A list of ConditionalAlternatives is used to represent if / elseif
	 * / else chains.
	 */
	public class ConditionalAlternative {
		public Expression condition;
		public ActionBlock block;
		public ConditionalAlternative(Expression c, ActionBlock b)
		{
			condition = c;
			block = b;
		}
	}

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
}

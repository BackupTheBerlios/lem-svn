
package org.jdns.xtuml.metamodel;

/**
 * Represents a "while" statement action.
 *
 * @author npiggin
 */
public class WhileStatement extends Action {
	public Expression condition;
	public ActionBlock block;
    
	/** Creates a new instance of IfStatement */
	public WhileStatement() {
	}

	/** returns the loop conditional */
	public Expression getCondition() {
		return condition;
	}

	/** returns the loop block */
	public ActionBlock getBlock() {
		return block;
	}

	/** sets the loop condition */
	public void setCondition(Expression c) {
		condition = c;
	}

	/** sets the loop's block */
	public void setBlock(ActionBlock ab) {
		block = ab;
	}
}


package org.jdns.xtuml.metamodel;

/**
 * Represents a "for each" statement action.
 *
 * @author npiggin
 */
public class ForStatement extends Action {
	private String selectVariable;
	private Expression setExpression;
	private ActionBlock block;
    
	/** Creates a new instance of ForStatement */
	public ForStatement() {
	}

	/** returns the loop select variable */
	public String getSelectVariable() {
		return selectVariable;
	}

	/** returns the loop set variable */
	public Expression getSetExpression() {
		return setExpression;
	}

	/** returns the loop block */
	public ActionBlock getBlock() {
		return block;
	}

	/** sets the loop select variable */
	public void setSelectVariable(String s) {
		selectVariable = s;
	}

	/** sets the loop st variable */
	public void setSetExpression(Expression e) {
		setExpression = e;
	}

	/** sets the loop's block */
	public void setBlock(ActionBlock ab) {
		block = ab;
	}
}

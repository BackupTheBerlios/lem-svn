package metamodel;

/**
 * Represents the "return" action language statement. The interpreter
 * should cease executing the current function/procedure and, potentially,
 * return the value represented by the Expression to the caller.
 *
 * @author u3957053
 */
public class ReturnAction {
	/** The expression which this ReturnAction returns. */
	private Expression expression = null;

	/** Creates a new instance of ReturnAction */
	public ReturnAction() {
	}

	/**
	 * The Expression to return, or null if none exists.
	 *
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * Set the Expression to be returned by this ReturnAction.
	 *
	 * @param expression the Expression
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}


package metamodel;

/**
 * Represents a "for each" statement action.
 *
 * @author npiggin
 */
public class ForStatement extends Action {
	private String selectVariable;
	private VariableReference setReference;
	private ActionBlock block;
    
	/** Creates a new instance of IfStatement */
	public ForStatement() {
	}

	/** returns the loop select variable */
	public String getSelectVariable() {
		return selectVariable;
	}

	/** returns the loop set variable */
	public VariableReference getSetReference() {
		return setReference;
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
	public void setSetReference(VariableReference s) {
		setReference = s;
	}

	/** sets the loop's block */
	public void setBlock(ActionBlock ab) {
		block = ab;
	}
}


package metamodel;

/**
 * Represents a "create atomic" statement.
 *
 * @author npiggin
 */
public class CreationTransaction extends Action {
	private ActionBlock block;
    
	/** Creates a new instance of IfStatement */
	public CreationTransaction() {
	}

	/** returns the loop block */
	public ActionBlock getBlock() {
		return block;
	}

	/** sets the loop's block */
	public void setBlock(ActionBlock ab) {
		block = ab;
	}
}


package metamodel;

/**
 * Represents a LEM assignment action. All LEM assignment actions have the form
 *
 * VariableReference := Expression
 *
 * @author sjr
 */
public class AssignmentAction {
  
    /**
     * The lvalue of this assignment action
     */
    private VariableReference left; 
    
    /**
     * The rvalue of this assignment action
     */
    private Expression right;
    
    
    /** Creates a new instance of AssignmentAction */
    public AssignmentAction() {
    }

    /**
     * Gets the reference to the variable which will receive a new value upon
     * the execution of this action.
     * @return the reference to the variable to which a new value will be assigned
     */
    public VariableReference getVariableReference() {
        return left;
    }

    /**
     * Sets the reference to the variable which will receive a new value upon
     * the execution of this action.
     * @param left the reference to the variable
     */
    public void setVariableReference(VariableReference left) {
        this.left = left;
    }

    /**
     * Gets the expression which represents the variable's new value.
     * @return the expression representing the variable's new value.
     */
    public Expression getExpression() {
        return right;
    }

    /**
     * Set the expression representing the variable's new value
     * @param right the expression representing the variable's new value
     */
    public void setExpression(Expression right) {
        this.right = right;
    }
}

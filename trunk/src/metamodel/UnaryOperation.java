
package metamodel;

/**
 * This class represents unary operations (eg. -, absolute value ) that may occur
 * inside LEM expressions.
 *
 * @author sjr
 */
public class UnaryOperation extends Expression {
    // Types of unary operation
    public static int NEGATION = 1;
    public static int ABSOLUTE_VALUE = 2;
    
    /**
     * The expression on which this operation operates
     */
    Expression operand;
    
    /**
     * The type of this expression. This should be one of the constant
     * fields defined in this class.
     */
    int type;
    
    /**
     * Creates a new instance of BinaryOperation
     * @param type the type of this operation. This should be one of the static integral types defined by this class.
     */
    public UnaryOperation( int type ) {
        this.type = type;
    }
    
    /**
     * Sets the operand of this operation to the given Expression.
     *
     * @param e the expression to be the operand of this operation.
     */
    public void setOperand( Expression e ) {
        this.operand = e;
    }
    
    /**
     * Gets the type of this operation.
     *
     * @return the type of this operation
     */
    public int getType() {
        return type;
    }
    
    /**
     * Gets the operand on which this operation operates.
     *
     * @return the operand of this operation
     */
    public Expression getOperand() {
        return operand;
    }
}

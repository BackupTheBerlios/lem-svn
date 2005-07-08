/* 
 * UnaryOperation.java
 *
 * Copyright (C) 2005 Steven James Ring
 *
 * This program is free software; you can redistribute it and/or  
 * modify it under the terms of the GNU General Public License  
 * as published by the Free Software Foundation; either version 2  
 * of the License, or (at your option) any later version.  
 *  
 * This program is distributed in the hope that it will be useful,  
 * but WITHOUT ANY WARRANTY; without even the implied warranty of  
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
 * GNU General Public License for more details.  
 * 
 * You should have received a copy of the GNU General Public License  
 * along with this program; if not, write to the Free Software  
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,  
 * USA. 
 */


package metamodel;

/**
 * This class represents unary operations (eg. -, absolute value ) that may occur
 * inside LEM expressions.
 *
 * @author sjr
 */
public class UnaryOperation extends Expression {
    // Types of unary operation
    public static final int NEGATION = 1;
    public static final int ABSOLUTE_VALUE = 2;
    public static final int LOGICAL_NOT = 3;
    
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

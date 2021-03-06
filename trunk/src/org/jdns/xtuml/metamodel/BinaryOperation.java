/* 
 * BinaryOperation.java
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

package org.jdns.xtuml.metamodel;

/**
 * This class represents binary operations (eg. + - / *) that may occur
 * inside LEM expressions.
 *
 * @author sjr
 */
public class BinaryOperation extends Expression {
    // Types of binary operation
    public static final int ADDITION = 1;
    public static final int SUBTRACTION = 2;
    public static final int MULTIPLICATION = 3;
    public static final int DIVISION = 4;
    public static final int EXPONENTIATION = 5;
    public static final int EQUAL = 6;
    public static final int LESS_THAN = 7;
    public static final int LESS_THAN_OR_EQUAL = 8;
    public static final int GREATER_THAN = 9;
    public static final int GREATER_THAN_OR_EQUAL = 10;
    public static final int MODULO = 11;
    public static final int NOT_EQUAL = 12;
    public static final int LOGICAL_AND = 13;
    public static final int LOGICAL_OR = 14;
    
    /**
     * The expression on the left-hand-side of this operation
     */
    Expression left;
    
    /**
     * The expression on the right-hand-side of this operation
     */
    Expression right;
    
    /**
     * The type of this expression. This should be one of the constant
     * fields defined in this class.
     */
    int type;
    
    /** 
     * Creates a new BinaryOperation with the given type. type should be
     * one of the static integral fields defined in this class. 
     * 
     * @param type the type of this new BinaryOperation
     */
    public BinaryOperation( int type ) {
        this.type = type;
    }
    
    /** 
     * Sets the left-hand-side of this operation to the given Expression.
     *
     * @param e the expression to be on placed on the left-hand-side of this operation.
     */
    public void setLeft( Expression e ) {
        this.left = e;
    }
    
    /** 
     * Sets the right-hand-side of this operation to the given Expression.
     *
     * @param e the expression to be on placed on the left-hand-side of this operation.
     */
    public void setRight( Expression e ) {
        this.right = e;
    }
    
    /**
     * Gets the left-hand-side of this operation.
     *
     * @return the left-hand-side of this operation.
     */
    public Expression getLeft() {
        return left;
    }
    
    /**
     * Gets the right-hand-side of this operation.
     *
     * @return the right-hand-side of this operation.
     */
    public Expression getRight() {
        return right;
    }
    
    /**
     * Gets the type of this operation.
     *
     * @return the type of this operation.
     */
    public int getType() {
        return type;
    }
}

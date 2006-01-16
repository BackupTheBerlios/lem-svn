/* 
 * AssignmentAction.java
 *
 * Copyright (C) 2005 Steven Michael Ring
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
 * Represents a LEM assignment action. All LEM assignment actions have the form
 *
 * VariableReference := Expression
 *
 * @author sjr
 */
public class AssignmentAction extends Action {
  
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

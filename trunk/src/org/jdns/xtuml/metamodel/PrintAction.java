/* 
 * PrintAction.java
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
 * This class represents an action that, when executed, may result in the user
 * being notified that some output has occurred in the model.
 *
 * @author sjr
 */
public class PrintAction extends Expression {
	
	/**
	 * The expression representing the output that should be reported to the
	 * user.
	 */
	Expression output;
		
    /** 
	 * Creates a new instance of PrintAction.
	 * 
	 * @param e the expression to be evaluated and given to the user
	 */
    public PrintAction( Expression e ) {
		this.output = e;
    }

	/**
	 * Returns the expression representing the output.
	 *
	 * @return the expression
	 */
	public Expression getOutputExpression() {
		return output;
	}
}

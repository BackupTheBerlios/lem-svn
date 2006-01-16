/*
 * SelectExpression.java
 *
 * Copyright (C) 2005 Shokouhmand Torabi
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

import runtime.SetVariable;

/**
 *
 * @author shuku
 */
public class SelectExpression extends Expression {

	public static final int MULTIPLICITY_ONE = 1;
	public static final int MULTIPLICITY_ANY = 2;
	public static final int MULTIPLICITY_ALL = 3;
		   
	/** Name of the class to which the objects belong **/
	private org.jdns.xtuml.metamodel.Class theClass = null; 
	
	/** 
	 * Type of selection e.g. all, one or any 
	 *  Default value "all"
	 */
	private int multiplicity = MULTIPLICITY_ALL; 
		
	/** The condition of Select Expression, if any or null otherwise */
	private Expression condition = null; 
	
	/** The relatedToOperation of Select Expression, if any or null otherwise */
	private RelatedToOperation rto = null; 
	
	/** Creates a new instance of SelectExpression */
	public SelectExpression(int multiplicity, org.jdns.xtuml.metamodel.Class theClass, Expression condition ) {
		this.theClass = theClass;
		this.multiplicity = multiplicity;
		this.condition = condition;  
	}

	/** Creates a new instance of SelectExpression */
	public SelectExpression(int multiplicity, org.jdns.xtuml.metamodel.Class theClass, RelatedToOperation rto ) {
		this.theClass = theClass;
		this.multiplicity = multiplicity; 
		this.rto = rto;  
	}
	
	public org.jdns.xtuml.metamodel.Class getSelectedClass() {
		return theClass;
	}

	public void setClass(org.jdns.xtuml.metamodel.Class theClass) {
		this.theClass = theClass;
	}

	public int getMultiplicity() {
		return multiplicity;
	}

	public void setMultiplicity(int multiplicity) {
		this.multiplicity = multiplicity;
	}

	public Expression getCondition() {
		return condition;
	}

	public void setCondition(Expression condition) {
		this.condition = condition;
	}

	public RelatedToOperation getRelatedToOperation() {
		return rto;
	}

	public void setRelatedToOperation(RelatedToOperation rto) {
		this.rto = rto;
	}
}

/*
 * SetVariable.java
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

package runtime;

import metamodel.DataType;
import metamodel.SetType;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Shuku
 */
public class SetVariable extends Variable {
	private Collection value;
	
	/** Creates a new instance of StringVariable */
	public SetVariable() {
		this.setValue(new LinkedList()) ; 
	}
	
	public SetVariable( Collection o ) {
	this.setValue(o); 
	}

	public void setValue( java.lang.Object o ) {
		this.value = (Collection)o;
	}
	
	public java.lang.Object getValue() {
		return value;
	}
	
	public DataType getType() {
		return SetType.getInstance();
	}
	
	/**
	 * This method adds a certain variable to the set, no type checking is done here 
	 * to make sure the types in a set match each other 
	 *
	 * @param b the variable to be added.
	 */
	public void addToSet( Variable b ) throws LemRuntimeException {	   
		value.add(b);	   
	}   
}

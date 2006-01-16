/*
 * LocalContext.java 
 *
 * Copyright (C) 2005 David Gavin
 * Copyright (C) 2005 Nick Piggin
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

package org.jdns.xtuml.runtime;

import java.util.Collection;
import java.util.LinkedList;
import java.util.HashMap;

/**
 *
 * @author u4128551
 */
public class LocalContext extends Context {

	/**
	 * A map of all variables local to the context
	 */
	private HashMap variableMap = new HashMap();
   
	/** Creates a new instance of LocalContext */
	public LocalContext(Context inContext) {
		super(inContext);
	}
 
	/**
	 * Gets the named variable from this context or any parent contexts.
	 *
	 * @param name the name of the variable
	 * @return the variable, or null if there is no variable with that name
	 */
	public Variable getVariable( String name ) {
		Variable v = (Variable)variableMap.get( name );
		
		if( v == null && parentContext != null ) {
			// Search parent contexts
			v = parentContext.getVariable( name );
		}
		
		return v;
	}
	
	/**
	 * Adds the given variable to this context with the given identifying name.
	 * 
	 * @param name the identifier of the variable
	 * @param variable the variable to be added
	 */
	public void addVariable( String name, Variable variable ) {
		variableMap.put( name, variable );
	}

	/**
	 * Returns the collection of local variables known to this context.
	 *
	 * @return the collection
	 */
	public Collection getVariableList() {
		return variableMap.values();
	}
}

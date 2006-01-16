/*
 * ArbitraryIdVariable.java - An optional brief description of the file
 *
 * Copyright (C) 2005 thuanseah
 * Copyright (C) 2005 Shuku Torabi
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

import org.jdns.xtuml.metamodel.ArbitraryIdType;
import org.jdns.xtuml.metamodel.DataType;

public class ArbitraryIdVariable extends Variable {
	private static int currentId = 0;
	private static Integer id;
	
	/** Creates a new instance of ArbitaryIdVariable */
	public ArbitraryIdVariable() throws LemRuntimeException {
	}
	
	public static synchronized Integer getInstance() {
		id = new Integer(currentId);
		currentId++;
		return id; 
	}
	
	/** 
	 * Calling this function does nothing. The value of the ArbitraryIdVariable
	 * is immutable.
	 *
	 * @param ignored is ignored
	 */
	public void setValue(java.lang.Object ignored) {
	}	
	
	public java.lang.Object getValue() {
		return id;
	}
	
	public DataType getType() {
		return ArbitraryIdType.getInstance();
	}
}

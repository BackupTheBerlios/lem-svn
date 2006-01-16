/*
 * TransactionContext.java
 *
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
import java.util.ArrayList;

/**
 *
 * @author npiggin
 */
public class TransactionContext extends LocalContext {
	/** 
	 * A collection of all runtime objects known to this context
	 */
	private Collection objectList = new ArrayList();
   
	/** Creates a new instance of TransactionContext */
	public TransactionContext(Context inContext) {
		super(inContext);
	}

	public void finish() throws LemRuntimeException {
	if (parentContext != null && objectList.size() > 0)
		parentContext.addObjects(objectList);
	}

	/**
	 * Adds the given object to this context
	 * @param inObject the object to add to the context
	 */
	public void addObject(org.jdns.xtuml.runtime.Object inObject) {
		objectList.add(inObject);
	}

	/**
	 * Adds the given object to this context
	 * @param inObject the object to add to the context
	 */
	public void delObject(org.jdns.xtuml.runtime.Object o) throws LemRuntimeException {
		if (!objectList.remove(o)) {
			if (parentContext == null)
				throw new LemRuntimeException("Tried to delete non-existant object");
			parentContext.delObject(o);
		}
	}

	/**
	 * Adds the given collection of objects to this context
	 * @param inObjects the objects to add to the context
	 */
	public void addObjects(Collection inObjects) throws LemRuntimeException {
		objectList.addAll(inObjects);
	}

	/**
	 * Returns the list of objects in this context
	 * @returns the list of objects.
	 */
	public Collection getObjectList() {
		return objectList;
	}
}

/*
 * DomainContext.java
 *
 * Copyright (C) 2005 James Ring
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

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * A domain context represents the state of a particular instance of a LEM
 * Domain.
 *
 * @author sjr
 */
public class DomainContext extends Context {
	private Debug debugObject;

	/** 
	 * A collection of all runtime objects known to this context
	 */
	private Collection objectList = new ArrayList();

	/** Creates a new instance of DomainContext */
	public DomainContext() {
		super(null);
		setDebugObject(new Debug(this));
	}

	/**
	 * Adds the given object to this context
	 * @param inObject the object to add to the context
	 */
	public synchronized void addObject(runtime.Object inObject) {
		objectList.add(inObject);
	}

	/**
	 * Adds the given object to this context
	 * @param inObject the object to add to the context
	 */
	public synchronized void delObject(runtime.Object o) throws LemRuntimeException {
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
	public synchronized void addObjects(Collection inObjects) throws LemRuntimeException {
		objectList.addAll(inObjects);
	}
	
	/**
	 * Returns the list of objects in this context
	 * @returns the list of objects.
	 */
	public Collection getObjectList() {
		return objectList;
	}

	public Debug getDebugObject() {
		return debugObject;
	}

	public void setDebugObject(Debug debugObject) {
		this.debugObject = debugObject;
	}
}

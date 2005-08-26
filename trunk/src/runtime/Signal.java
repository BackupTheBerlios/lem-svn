/*
 * FileName.java - An optional brief description of the file
 *
 * Copyright (C) 2005 Nick Piggin
 * Copyright (C) 2004 Shuku Torabi
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

import java.util.LinkedList;
import java.util.Iterator;
import metamodel.Event;
import metamodel.EventSignature;
import metamodel.Parameter;
import metamodel.Expression ; 

/**
 * This class represents a LEM runtime signal. When the model is being executed,
 * instances of this class are created in response to Events being encountered.
 * @author npiggin
 */
public class Signal {
	private Event event;
	private LinkedList parameters;

	/**
	 * Creates a new instance of Signal.
	 *
	 * @param Event The event to which this signal is associated.
	 * @throws LemRuntimeException if ...
	 */
	public Signal(Event e) throws LemRuntimeException {
		System.out.println("Signal(" + e + ")");
		event = e;
		parameters = null;
	}
        
	/**
	 * Returns the event to which this signal is associated.
	 */
	public Event getEvent() {
		return event;
	}
	
	/**
	 * Returns the list of the Signal's parameters.
	 */
	public LinkedList getParameters()
	{
		return parameters;
	}

	/**
	 * Sets the list of the Signal's parameters to p.
	 */
	public void setParameters(LinkedList newParams) throws LemRuntimeException {
		parameters = newParams;
		
		EventSignature evSig = event.getSignature();
		if (evSig == null) {
			throw new LemRuntimeException("Attaching a parameter list to signal accepting no parameters");
		}

		LinkedList params = evSig.getParameters();
		/* todo: fix up confusing names :( */
		Iterator i = params.iterator();
		Iterator j = parameters.iterator();
		while (true) {
			if (i.hasNext() != j.hasNext()) {
				throw new LemRuntimeException("Mismatched parameter list sizes");
			}
			if (!i.hasNext())
				break;
			
			Parameter p = (Parameter)i.next();
			Variable v = (Variable)j.next();
			if (p.getType() != v.getType()) {
				throw new LemRuntimeException("Mismatched parameter types");
			}
		}
	}
}

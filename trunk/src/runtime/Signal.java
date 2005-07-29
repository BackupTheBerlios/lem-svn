/*
 * Object.java
 *
 * Created on 28 July 2005, 17:43
 */

package runtime;

import java.util.LinkedList;
import java.util.Iterator;
import metamodel.Event;
import metamodel.EventSignature;
import metamodel.Parameter;

/**
 * This class represents a LEM runtime signal. When the model is being executed,
 * instances of this class are created in response to Events being encountered.
 * @author npiggin
 * @see the Signals at Runtime description at http://xtuml.jdns.org/wiki/index.php/TODO!!
 */
public class Signal {
	Event event;
	LinkedList parameters;
	
	/**
	 * Creates a new instance of Signal.
	 *
	 * @param Event The event to which this signal is associated.
	 * @throws LemRuntimeException if ...
	 */
	public Signal(Event e) throws LemRuntimeException {
		event = e;
		parameters = null;

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

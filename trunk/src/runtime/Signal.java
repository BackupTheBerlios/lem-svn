/*
 * Object.java
 *
 * Created on 28 July 2005, 17:43
 */

package runtime;

import java.util.HashMap;
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
	HashMap parameters;
	
	/**
	 * Creates a new instance of Signal.
	 *
	 * @param Event The event to which this signal is associated.
	 * @throws LemRuntimeException if ...
	 */
	public Signal(Event e) throws LemRuntimeException {
		event = e;
		parameters = null;

		EventSignature evSig = event.getSignature();
		if (evSig != null) {
			Parameter[] params;
			params = evSig.getParameterArray();
			if (params.length > 0) {
				parameters = new HashMap();
				for (int i = 0; i < params.length; i++) {
					Parameter p = params[i];
					Variable v = VariableFactory.newVariable(p.getType(), null);
					parameters.put(p.getName(), v);
				}
			}
		}
	}

	/**
	 * Sets the value of one of the Signal's parameters.
	 */
	public void setParameter(java.lang.String name, java.lang.Object value) throws LemRuntimeException
	{
		((Variable)parameters.get(name)).setValue(value);
	}

	/**
	 * Returns one of the Signal's parameters.
	 */
	public Variable getParameter(java.lang.String name) throws LemRuntimeException
	{
		return (Variable)parameters.get(name);
	}
}

/*
 * DelayedSignal.java
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

package runtime;

import metamodel.Event;
import java.math.BigDecimal;

/**
 * This class represents a LEM runtime delayed signal. When the model is being
 * executed, instances of this class are created in response to Events being
 * encountered.
 * @author npiggin
 */
public class DelayedSignal extends Signal implements Comparable {
	private runtime.Object target;
	private long deliveryTime;
	
	public DelayedSignal(Event e) throws LemRuntimeException {
		super(e);
	}
	
	/**
	 * Creates a new instance of DelayedSignal.
	 *
	 * @param e The event to which this signal is associated.
	 * @param o The object which is the target of this signal.
	 * @param t The time when this object is delivered.
	 * @throws LemRuntimeException if ...
	 */
	public DelayedSignal(Event e, runtime.Object o, BigDecimal t) throws LemRuntimeException {
		super(e);
		target = o;
		deliveryTime = o.getContext().getTimeObject().getTimeMs()
							+ t.longValue();
	}

	public runtime.Object getTarget() {
		return target;
	}

	public long getDeliveryTime() {
		return deliveryTime;
	}

	/** 
	 * Compare the given object to this DelayedSignal.
	 *
	 * @param o the DelayedSignal to which to compare this delayed signal
	 * @return Returns -1 if this signal will be delivered before the given
	 * signal, 0 if they will be delivered at the same time, or 1 otherwise.
	 *
	 * @throws ClassCastException if o is not a DelayedSignal. 
	 */
	
	public int compareTo( java.lang.Object o ) {
		DelayedSignal d = (DelayedSignal)o;

		if (deliveryTime < d.getDeliveryTime() )
			return -1;
		else if (deliveryTime == d.getDeliveryTime() ) 
			return 0;
		else
			return 1;
	}
}

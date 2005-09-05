/*
 * DelayedSignal.java - An optional brief description of the file
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
public class DelayedSignal extends Signal {
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
	 * @todo time should be an absolute time.
	 * @throws LemRuntimeException if ...
	 */
	public DelayedSignal(Event e, runtime.Object o, BigDecimal t) throws LemRuntimeException {
		super(e);
		target = o;
		deliveryTime = System.currentTimeMillis() + t.longValue();
	}

	public runtime.Object getTarget() {
		return target;
	}

	public long getDeliveryTime() {
		return deliveryTime;
	}
}

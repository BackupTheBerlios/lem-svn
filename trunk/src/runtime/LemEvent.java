/*
 * LemEvent.java
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

import java.util.Iterator;

/**
 * This class represents events that occur during the running of a model. Events
 * include:
 *  - object creation
 *  - transitioning from one state to another
 *  - attribute changes
 *  - event generation and event delivery
 * 
 * LemEvents are not to be confused with xtUML events or the Event class in the
 * metamodel package.
 * @author sjr
 */
public abstract class LemEvent {
	
	/** Creates a new instance of LemEvent */
	public LemEvent() {
	}

	/**
	 * Notify the given event listener that this LemEvent has occurred.
	 *
	 * @param listener the LemEventListener to notify
	 */
	public abstract void notify( LemEventListener listener );

	/**
	 * Notify all listeners of a given Context of this LemEvent's occurrence.
	 *
	 * @param context the Context to notify
	 */
	public void notifyAll( Context context ) {
		for( Iterator i = context.getLemEventListeners().iterator();
				i.hasNext(); ) {
			notify( (LemEventListener)i.next() );
		}
	}
}

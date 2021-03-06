/*
 * SignalGenerator.java
 *
 * Created on 20 August 2005, 13:38
 *
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

package org.jdns.xtuml.runtime;

import java.util.Calendar ;
import java.util.GregorianCalendar ;

/**
 *
 * @author shuku
 */
public class SignalGenerator extends java.lang.Thread {
	/** The senderObject sending the signal **/
	private org.jdns.xtuml.runtime.Object senderObject = null ;
    	
	/** The domain context in which the SignalGenerator runs */
	private DomainContext context;
    
	/** Creates a new instance of SignalGenerator */
	public SignalGenerator( DomainContext c, org.jdns.xtuml.runtime.Object sender ) {
		context = c;
		this.senderObject = sender;
 		start();
	}
    
	public void run() {
		while (true) {
			DelayedSignal s = senderObject.getNextDelayedSignal();
			if (s == null)
				return;

			org.jdns.xtuml.runtime.Object target = s.getTarget();
			System.out.println(target.getObjectId() + " delivered delayed signal");
			synchronized (target) {
			        Integer signalId = s.getSignalId(); 
			        Integer targetObjectId = target.getObjectId();
				if (target.isActive()) {
					target.addSignal(s);
				} else {
					/*
					 * All interpreters have stopped,
					 * signal shouldn't be deliviered.
					 */
					context.getDebugObject().delEntity();
				}
			}
		}
	}
}

/*
 * Time.java
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

import java.util.LinkedList;

/**
 * A Time object is the keeper of "model time" for the LEM runtime.
 *
 * @author npiggin
 */
public class Time {
	/**
	 * The current System time when this "period" has started.
	 */
	private long startPeriodSystemMs;

	/**
	 * The amount of "lem time" elapsed. This is different from
	 * "System time" (which is real time).
	 */
	private long elapsedLemMs;

	/**
	 * The current "lem time" factor - multiplier for real time to lem time.
	 */
	private double LemTimeFactor;

	/**
	 * The current list of objects being waited upon.
	 */
	private LinkedList timeoutWaiters;
	
	/**
	 * Create a new, initialised Time object.
	 */
	public Time() {
		LemTimeFactor = 1.0;
		elapsedLemMs = 0;
		startPeriodSystemMs = System.currentTimeMillis();
		timeoutWaiters = new LinkedList();
	}

	/**
	 * Return the current "lem time" in milliseconds
	 * @now: assumed current time.
	 */
	private synchronized long getTimeMs(long now) {
		return elapsedLemMs +
			(long)((now - startPeriodSystemMs) * LemTimeFactor);
	}
	
	/**
	 * Return the current "lem time" in milliseconds.
	 */
	public long getTimeMs() {
		return getTimeMs(System.currentTimeMillis());
	}
	
	/**
	 * Sets the current "lem time" multiplier
	 * real time * multiplier = lem time.
	 * Set to 0 to stop lem time.
	 */
	public synchronized void setTimeFactor(float factor) {
		long now = System.currentTimeMillis();
		elapsedLemMs = getTimeMs(now);
		startPeriodSystemMs = now;
		LemTimeFactor = factor;

		while (timeoutWaiters.size() > 0) {
			java.lang.Object o = (java.lang.Object)timeoutWaiters.remove();
			o.notifyAll();
		}
	}

	/**
	 * Returns the current "lem time" multiplier.
	 */
	public synchronized double getTimeFactor() {
		return LemTimeFactor;
	}

	/**
	 * Equivalent of o.wait(timeout), but takes a timeout in LemMs, and wakes
	 * object if time scale changes.
	 */
	public synchronized void wait(java.lang.Object o, long timeoutLemMs) {
		timeoutWaiters.add(o);
		if (LemTimeFactor == 0)
			o.wait();
		else
			o.wait((long)(timeoutLemMs / LemTimeFactor));
		timeoutWaiters.remove(o);
	}
}

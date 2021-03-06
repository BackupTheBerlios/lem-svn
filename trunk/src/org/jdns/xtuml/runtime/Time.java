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

package org.jdns.xtuml.runtime;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * A Time object is the keeper of "model time" for the LEM runtime.
 *
 * @author npiggin
 */
public class Time {
	/**
	 * true if model has been paused. This causes subsequent
	 * calls to setTimeFactor not to restart the model (ie. only
	 * resumeTime will).
	 */
	private boolean paused;
	
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
		paused = true;
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
		if (paused)
			return elapsedLemMs;

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
	 */
	public void setTimeFactor(double factor) {
		LinkedList wakeList;
		java.lang.Object o;

		synchronized (this) {
			long now = System.currentTimeMillis();
			elapsedLemMs = getTimeMs(now);
			startPeriodSystemMs = now;
			LemTimeFactor = factor;

			wakeList = new LinkedList();

			Iterator i = timeoutWaiters.iterator();
			while (i.hasNext()) {
				o = (java.lang.Object)i.next();
				wakeList.add(o);
			}
		}

		while (wakeList.size() > 0) {
			o = (java.lang.Object)wakeList.remove(0);
			synchronized (o) {
				o.notifyAll();
			}
		}
	}

	/**
	 * Returns the current "lem time" multiplier.
	 */
	public synchronized double getTimeFactor() {
		return LemTimeFactor;
	}

	/**
	 * Equivalent of o.wait(timeout), but takes a timeout in LemMs, and
	 * wakes object if time scale changes. Must be called while
	 * synchronized on o.
	 */
	public void wait(java.lang.Object o, long timeoutLemMs) throws InterruptedException {
		boolean timeStopped;

		synchronized (this) {
			timeoutWaiters.add(o);
			timeStopped = paused || (LemTimeFactor == 0);
		}
		
		if (timeStopped)
			o.wait();
		else
			o.wait((long)((float)timeoutLemMs / LemTimeFactor));

		synchronized (this) {
			timeoutWaiters.remove(o);
		}
	}

	public synchronized void resumeTime() {
		setTimeFactor(LemTimeFactor);
		paused = false;
	}

	public synchronized void pauseTime() {
		/* stop lem time */
		setTimeFactor(LemTimeFactor);
		paused = true;
	}
}

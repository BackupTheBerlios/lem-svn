/*
 * Debug.java
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

/**
 * This class is a debugging interface into the state of the running model.
 *
 */
public class Debug {
	public static final int RUNNING = 0;
	public static final int PAUSED  = 1;
	public static final int STOPPED = 2;

	/**
	 * The state of the model - RUNNING, PAUSED, STOPPED
	 */
	private int state;
	
	/**
	 * The running entities in the model. When this falls to 0, the
	 * model will have entered a quiescent state, as there will be
	 * nothing to trigger further execution (external entities excluded).
	 */
	private Refcount liveSignals;

	public Debug() {
		state = RUNNING;
		liveSignals = new Refcount();
	}

	public synchronized void runModel() {
		state = RUNNING;
		notifyAll();
	}
	
	public synchronized void pauseModel() {
		state = PAUSED;
	}
	
	public synchronized void stopModel() {
		state = STOPPED;
	}
	
	public synchronized boolean isQuiescentState() {
		return (liveSignals.references() == 0);
	}
	
	public synchronized void waitQuiescentState() {
		while (!isQuiescentState()) {
			try {
				wait();
			} catch (InterruptedException e) {
				/** these can be ignored */
			}
		}
	}
	
	public void enterQuiescentState() {
		System.out.println("quiescent state entered");
		notifyAll();
	}
	
	public synchronized boolean nextState() {
		while (state != RUNNING) {
			if (state == STOPPED)
				return false;

			if (state != PAUSED) {
				throw new Error("startState found weird state");
			}
			try {
				wait();
			} catch (InterruptedException e) {
				/** these can be ignored */
			}
		}

		return true;
	}

	public synchronized void addSignal() {
		liveSignals.get();
		System.out.println("added signal, " + liveSignals.references() + "live signals");
	}

	public synchronized void delSignal() {
		if (liveSignals.put())
			enterQuiescentState();
		System.out.println("deleted a signal, " + liveSignals.references() + "live signals");
	}
}

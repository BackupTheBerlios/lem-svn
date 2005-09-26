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

import metamodel.State;
import metamodel.Transition;
import metamodel.Action;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;

/**
 * This class is a debugging interface into the runtimeState of the running model.
 * @todo javadoc
 */
public class Debug {
	public static final int RUNNING = 0;
	public static final int PAUSED  = 1;
	public static final int STOPPED = 2;

	/**
	 * The runtimeState of the model - RUNNING, PAUSED, STOPPED
	 */
	private int runtimeState;

	/**
	 * The running entities in the model. When this falls to 0, the
	 * model will have entered a quiescent runtimeState, as there will be
	 * nothing to trigger further execution (external entities excluded).
	 */
	private Refcount liveEntities;

	/**
	 * The DomainContext which this Debug object is associated with.
	 */
	private DomainContext context;

	/**
	 * Breakpoints for State transitions
	 */
	private LinkedList transitionBreakpoints = new LinkedList();

	/**
	 * Breakpoints for State execution
	 */
	private LinkedList stateBreakpoints = new LinkedList();

	/**
	 * Breakpoints for Action execution
	 */
	private LinkedList actionBreakpoints = new LinkedList();
	

	public Debug(DomainContext c) {
		runtimeState = RUNNING;
		liveEntities = new Refcount();
		context = c;
	}

	public synchronized boolean isRunning() {
		return (runtimeState == RUNNING);
	}
	
	public void notifyAllObjects() {
		synchronized (context) {
			Iterator i = context.getObjectList().iterator();
			while (i.hasNext()) {
				runtime.Object o = (runtime.Object)i.next();
				synchronized (o) {
					o.notifyAll();
				}
			}
		}
	}
	
	public synchronized void runModel() {
		runtimeState = RUNNING;
		notifyAll();
		notifyAllObjects();
	}
	
	public synchronized void pauseModel() {
		runtimeState = PAUSED;
	}
	
	public synchronized void stopModel() {
		runtimeState = STOPPED;
	}
	
	public synchronized boolean isQuiescentState() {
		return (liveEntities.references() == 0);
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
		System.out.println("quiescent runtimeState entered");
		notifyAll();
	}
	
	public synchronized void makeTransition(Transition t) {
		if (isBreakpoint(t))
			runtimeState = PAUSED;
	}

	public synchronized void runState(State s) {
		if (isBreakpoint(s))
			runtimeState = PAUSED;
	}

	public synchronized void executeAction(Action a) {
		if (isBreakpoint(a))
			runtimeState = PAUSED;
	}

	public synchronized boolean checkRuntimeState() {
		while (runtimeState != RUNNING) {
			if (runtimeState == STOPPED)
				return false;

			try {
            			System.out.println(Thread.currentThread().getName() + " thread paused");
				wait();
			} catch (InterruptedException e) {
				/** these can be ignored */
			}
		}

		return true;
	}

	public synchronized void addEntity() {
		liveEntities.get();
		System.out.println("added an entity, " + liveEntities.references() + " live entities");
	}

	public synchronized void delEntity() {
		System.out.println("deleted an entity, " + (liveEntities.references() - 1) + " live entities");
		if (liveEntities.put())
			enterQuiescentState();
	}

	public synchronized void addBreakpoint(Transition t) {
		transitionBreakpoints.add(t);
	}

	public synchronized boolean isBreakpoint(Transition t) {
		Iterator i = transitionBreakpoints.iterator();
		while (i.hasNext()) {
			TransitionBreakpoint tb = (TransitionBreakpoint)i.next();
			if (tb.matches(t))
				return true;
		}

		return false;
	}

	public synchronized void delBreakpoint(Transition t) {
		if (!transitionBreakpoints.remove(t)) {
			throw new Error("Breakpoint does not exist!");
		}
	}
	
	public synchronized void addBreakpoint(State s) {
		stateBreakpoints.add(s);
	}

	public synchronized boolean isBreakpoint(State s) {
		Iterator i = transitionBreakpoints.iterator();
		while (i.hasNext()) {
			StateBreakpoint sb = (StateBreakpoint)i.next();
			if (sb.matches(s))
				return true;
		}

		return false;
	}

	public synchronized void delBreakpoint(State s) {
		if (!stateBreakpoints.remove(s)) {
			throw new Error("Breakpoint does not exist!");
		}
	}

	public synchronized void addBreakpoint(Action a) {
		actionBreakpoints.add(a);
	}

	public synchronized boolean isBreakpoint(Action a) {
		Iterator i = actionBreakpoints.iterator();
		while (i.hasNext()) {
			ActionBreakpoint ab = (ActionBreakpoint)i.next();
			if (ab.matches(a))
				return true;
		}

		return false;
	}

	public synchronized void delBreakpoint(Action a) {
		if (!actionBreakpoints.remove(a)) {
			throw new Error("Breakpoint does not exist!");
		}
	}

	public int getRuntimeState() {
		return runtimeState;
	}
}

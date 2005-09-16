/*
 * Object.java
 *
 * Copyright (C) 2005 James Ring
 * Copyright (C) 2004 Nick Piggin
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import metamodel.Generalisation;
import metamodel.Event;

/**
 * This class represents a LEM runtime object. When the model is being executed,
 * instances of this class and deleted via the 'create' and 'delete' action
 * language statements.
 * @author u4128551
 * @author u3957053
 * @see the Objects at Runtime description at http://xtuml.jdns.org/wiki/index.php/Runtime_object
 */
public class Object {
        /** this arbitrary id, will uniquely identify this object **/
        private Integer objectId;

	/** The domain context in which this object resides */
	//private DomainContext context;

	/** Queue of pending signals for the object */
	private LinkedList signalQueue = new LinkedList();

        /**
	 * Queue of pending signals to self. All signals from here must be
	 * processed before any signals from 'signalQueue'
	 */
	private LinkedList signalSelfQueue = new LinkedList();
	
	/**
	 * Delayed Signal generator thread for sending signals to other
	 * objects. Is null until the first delayed signal is generated.
	 */
	private SignalGenerator delayedGenerator = null;

	/**
	 * Queue of pending signals generated by this object.
	 */
	private LinkedList delayedSignalQueue = new LinkedList();

	/**
	 * Queue of pending signals to self. All signals from here must be
	 * processed before any signals from 'signalQueue'
	 */
	private LinkedList delayedSignalSelfQueue = new LinkedList();


	//    InstanceAttribute attributes[] = null;
	Collection instances = new LinkedList();
	
	private Refcount runningInterpreters = new Refcount();

        /** reference to the Context this object is in */
        private Context context;

	
	/**
	 * Creates a new instance of Object. The object will contain instances
	 * of all the classes in the <code>classes</code> collection.
	 *
	 * @todo Could we pass a DataType in here?
	 * @param classes The classes which this object should
	 * @throws LemRuntimeException if the <code>classes</code> collection does
	 * not represent a valid type for the new object
	 */
	public Object(DomainContext c, Collection classes) throws LemRuntimeException {
		context = c;
                objectId = ArbitraryIdVariable.getInstance() ;
                System.out.println( objectId + " : Object Created") ;     
                
		// Do these classes actually participate in the same inheritance
		// hierarchy?
		if ( !validClasses( classes ) ) {
			throw new LemRuntimeException( "Specified class list will not produce a valid Object" );
		}
		
		// Maintain a list of classes already instantiated
		ArrayList instantiatedClasses = new ArrayList();
		
		for ( Iterator i = classes.iterator(); i.hasNext(); ) {
			metamodel.Class theClass = (metamodel.Class) i.next();
			Instance inst = new Instance( theClass, this );
			
			// Instantiate all parent classes as well
			Collection gens = theClass.getAllGeneralisations().values();
			
			for ( Iterator j = gens.iterator(); j.hasNext() ; ) {
				Generalisation g = (Generalisation) j.next();
				
				metamodel.Class superClass = g.getSuperclass();
				/* The root class of a generalisation hierarchy can
				 * appear in more than one call to "getAllGeneraliations",
				 * but we don't want to instantiate it again
				 */
				if ( instantiatedClasses.contains( superClass ) ) {
					continue;
				}
				
				instantiatedClasses.add( superClass );
				Instance superInst = new Instance( superClass, this );
				addInstance( superInst );
			}
			
			addInstance( inst );
		}
	}
	
	public Refcount getRunningInterpretersRefcount()
	{
		return runningInterpreters;
	}

	public boolean isActive()
	{
		return (runningInterpreters.references() > 0);
	}
	
	public void addDelayedSignal(DelayedSignal s) {
		synchronized (delayedSignalQueue) {
			if (delayedGenerator == null)
				delayedGenerator = new SignalGenerator((DomainContext)context, this);
			delayedSignalQueue.add(s);
			delayedSignalQueue.notify();
		}
	}
	
	public synchronized void drainSignals(Debug debug) {
		while (signalQueue.size() > 0) {
			signalQueue.remove(0);
			debug.delEntity();
		}

		while (signalSelfQueue.size() > 0) {
			signalSelfQueue.remove(0);
			debug.delEntity();
		}

		synchronized (delayedSignalQueue) {
			while (delayedSignalQueue.size() > 0) {
				delayedSignalQueue.remove(0);
				debug.delEntity();
			}
		}
		synchronized (delayedSignalSelfQueue) {
			while (delayedSignalSelfQueue.size() > 0) {
				delayedSignalSelfQueue.remove(0);
				debug.delEntity();
			}
		}
	}

	public void delDelayedSignal(DelayedSignal s) {
		synchronized (delayedSignalQueue) {
			delayedSignalQueue.remove(s);
		}
	}
	
	public long getNextDelayedSignalSelfDelay() {
		synchronized (delayedSignalSelfQueue) {
			if (delayedSignalSelfQueue.size() > 0) {
				DelayedSignal sig;
				long delay;
				sig = (DelayedSignal)delayedSignalSelfQueue.get(0);
				delay = sig.getDeliveryTime() -
					System.currentTimeMillis();

				if (delay > 0)
					return delay;
				return 0;
			} else
				return -1;
		}
	}
	
	public DelayedSignal getNextDelayedSignal() {
		synchronized (delayedSignalQueue) {
			while (true) {
				try {
					if (delayedSignalQueue.size() > 0) {
						DelayedSignal sig;
						long delay;
						sig = (DelayedSignal)delayedSignalQueue.get(0);
						delay = sig.getDeliveryTime() -
							System.currentTimeMillis();
						if (delay <= 0) {
							delayedSignalQueue.remove(sig);
							return sig;
						}

						System.out.println("Next delayed signal in " + delay);

						delayedSignalQueue.wait(delay);
					} else {
						if (!isActive())
							return null;
						delayedSignalQueue.wait();
					}
				} catch (InterruptedException e) {
					/*
					 * InterruptedException is OK.
					 */
				}
			}
		}
	}

	public void addDelayedSignalSelf(DelayedSignal s) {
		synchronized (delayedSignalSelfQueue) {
			delayedSignalSelfQueue.add(s);
		}
	}

	public synchronized Signal cancelDelayedSignalSelf(Event e) {
		Iterator i;

		i = signalSelfQueue.iterator();
		while (i.hasNext()) {
			Signal s = (Signal)i.next();
			if (s instanceof DelayedSignal) {
				if (s.getEvent() == e) {
					signalSelfQueue.remove(s);
                                        return s;
                                }
			}
		}

		synchronized (delayedSignalSelfQueue) {
			i = delayedSignalSelfQueue.iterator();
			while (i.hasNext()) {
				Signal s = (Signal)i.next();
				if (s.getEvent() == e) {
					delayedSignalSelfQueue.remove(s);
                                        return s;
                                }
			}
		}

		return null;
	}

	/**
	 * adds a Signal to this object's signal queue
	 */
	public synchronized void addSignal( Signal s ) {
		signalQueue.add( s );
                int id1 = objectId.intValue();
                int id2 = s.getSignalId().intValue();
                String type = s.getEvent().getName();
                Collection p = s.getParameters();
                new LemEventReceivedEvent( id1, id2, type, p ).notifyAll(context);    
		notifyAll();
	}
	
	/**
	 * adds a Signal to this object's "self" signal queue
	 * ie. signals to self.
	 */
	public synchronized void addSignalSelf( Signal s ) {
		signalSelfQueue.add( s );
                int id1 = objectId.intValue();
                int id2 = s.getSignalId().intValue();
                String type = s.getEvent().getName();
                Collection p = s.getParameters();
                new LemEventReceivedEvent( id1, id2, type, p ).notifyAll(context);    
		notifyAll();
	}
	
	/**
	 * propogate the next signal from self to all instances.
	 * Callers must be synchronized on 'this' object.
	 */
	public boolean propogateNextSignalSelf(Debug debugObject) throws LemRuntimeException {
		/*
		 * First propogate any elapsed delayed signals to self
		 * into the signalSelfQueue.
		 */
		synchronized (delayedSignalSelfQueue) {
			while (delayedSignalSelfQueue.size() > 0) {
				DelayedSignal s;
				s = (DelayedSignal)delayedSignalSelfQueue.get(0);
				if (s.getDeliveryTime() <= System.currentTimeMillis()) {
					delayedSignalSelfQueue.remove( s );
					addSignalSelf(s);
				} else
					break;
			}
		}
		
		if (signalSelfQueue.size() > 0) {
			Signal s = (Signal)signalSelfQueue.remove(0);
			for ( Iterator i = instances.iterator(); i.hasNext(); ) {
				Instance in = ( Instance ) i.next();
				if (in.isActive) {
					in.addSignalSelf( s );
					debugObject.addEntity();
				}
			}
			debugObject.delEntity();
			return true;

		}

		return false;
	}

	/**
	 * propogate the next signal (not from self) to all instances.
	 * Callers must be synchronized on 'this' object.
	 */
	public boolean propogateNextSignal(Debug debugObject) throws LemRuntimeException {

		if ( signalQueue.size() > 0 ) {
			Signal s = (Signal) signalQueue.remove( 0 );
			for ( Iterator i = instances.iterator(); i.hasNext(); ) {
				Instance in = ( Instance ) i.next();
				if (in.isActive) {
					in.addSignal( s );
					debugObject.addEntity();
				}
			}
			debugObject.delEntity();
			return true;
		}

		return false;
	}

	
	/**
	 * Returns the attribute of this object with the given name. All instances
	 * which constitute the object are searched.
	 * @param attributeName the attribute to find
	 * @return the attribute with the given name, or <code>null</code> if the
	 * named attribute doesn't exist
	 */
	public Variable getAttribute( String attributeName ) {
		for ( Iterator i = instances.iterator(); i.hasNext(); ) {
			Instance in = ( Instance ) i.next();
			Variable v = in.getAttribute( attributeName );
			if ( v != null )
				return v;
		}
		return null;
	}
	
	/**
	 * Checks whether the given collection of classes, when instantiated, would
	 * represent a valid Object. Objects may not contain instances of classes
	 * which participate in a common Generalisation hierarchy.
	 *
	 * @param classes the collection of classes to check
	 * @return whether the given collection of classes could be instantiated
	 * into a single object
	 */
	
	protected static boolean validClasses( Collection classes ) {
		// A place to put all the generalisations
		HashMap h = new HashMap();
		
		Iterator i = classes.iterator();
		while ( i.hasNext() ) {
			metamodel.Class c = (metamodel.Class)i.next();
			
			if ( c.isAbstract() )
				return false;
			HashMap gens = c.getAllGeneralisations();
			
			// Add each generalisation to the big list of generalisations
			Iterator j = gens.values().iterator();
			while ( j.hasNext() ) {
				if ( h.put( j.next(), "" ) != null ) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Adds an instance to this object.
	 *
	 * @param i the instance to add
	 */
	protected void addInstance( Instance i ) {
		instances.add( i );
	}
	
	/**
	 * Returns the collection of instances inside this object
	 *
	 * @returns the collection of instances composing this object
	 */
	public Collection getInstances() {
		return instances;
	}

	/**
	 * Finds the first event which matches the given event name
	 * and parameter list in this Object. Instances are searched
	 * for events in an arbitrary order.
	 *
	 * @param name the event name
	 * @param params the parameter list
	 * @todo the search only matches event names, not event name + parameter
	 * list
	 */
	public metamodel.Event findEvent( String name, LinkedList params ) {
		Iterator i = instances.iterator();
		
		while( i.hasNext() ) {
			Instance inst = (Instance)i.next();
			metamodel.Class c = inst.getInstanceClass();
			metamodel.Event e = c.getEvent( name );
			if( e != null )
				return e;
		}
		
		return null;
	}
        
        /** this method will return the uniqueId of this object **/
        public Integer getObjectId() {
            return objectId ; 
        }
        
        /**
         * function to return the class names of all instances in this object
         */
        public Collection getClassNames() {
            Collection c = new LinkedList();
            Iterator i = instances.iterator();
            while(i.hasNext()) {
                Instance inst = (Instance)i.next();
                c.add(inst.getInstanceClass().getName());
            }
            return c;
        }
		
		/**
         *
         */
		public Collection getSelfSignalQueue() {
			return signalSelfQueue;
		}
		
		/**
         *
         */
		public Collection getSignalQueue() {
			return signalSelfQueue;
		}
		
		/**
         *
         */
		public Collection getDelayedSelfQueue() {
			return delayedSignalSelfQueue;
		}
		
		/**
         *
         */
		public Collection getDelayedQueue() {
			return delayedSignalQueue;
		}

        /** function to set the Context which this object is in */
        public void setContext(Context c) {
            this.context = c;
        }			
}

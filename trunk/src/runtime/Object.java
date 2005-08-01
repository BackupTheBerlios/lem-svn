/*
 * Object.java
 *
 * Created on 10 May 2005, 17:43
 */

package runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import metamodel.Generalisation;

/**
 * This class represents a LEM runtime object. When the model is being executed,
 * instances of this class and deleted via the 'create' and 'delete' action
 * language statements.
 * @author u4128551
 * @author u3957053
 * @see the Objects at Runtime description at http://xtuml.jdns.org/wiki/index.php/Runtime_object
 */
public class Object {
    Interpreter interpreter;
    
    /** Queue of pending signals for the object */
    LinkedList signalQueue = new LinkedList();
    
    /**
     * Queue of pending signals to self. All signals from here must be
     * processed before any signals from 'signalQueue'
     */
    LinkedList signalSelfQueue = new LinkedList();
    
//    InstanceAttribute attributes[] = null;
    Collection instances = new LinkedList();
    
    /**
     * Creates a new instance of Object. The object will contain instances
     * of all the classes in the <code>classes</code> collection.
     *
     * @todo Could we pass a DataType in here?
     * @param classes The classes which this object should
     * @throws LemRuntimeException if the <code>classes</code> collection does
     * not represent a valid type for the new object
     */
    public Object(Collection classes) throws LemRuntimeException {
        interpreter = new Interpreter(this);

        // Do these classes actually participate in the same inheritance
        // hierarchy?
        if( !validClasses( classes )) {
            throw new LemRuntimeException( "Specified class list will not produce a valid Object" );
        }
        
        // Maintain a list of classes already instantiated
        ArrayList instantiatedClasses = new ArrayList();
        
        for( Iterator i = classes.iterator(); i.hasNext(); ) {
            metamodel.Class theClass = (metamodel.Class)i.next();
            Instance inst = new Instance( theClass );
            
            // Instantiate all parent classes as well
            Collection gens = theClass.getAllGeneralisations().values();
            
            for( Iterator j = gens.iterator(); j.hasNext() ; ) {
                Generalisation g = (Generalisation)j.next();
                
                metamodel.Class superClass = g.getSuperclass();
                /* The root class of a generalisation hierarchy can
                 * appear in more than one call to "getAllGeneraliations",
                 * but we don't want to instantiate it again
                 */
                if( instantiatedClasses.contains(superClass)) {
                    continue;
                }
                
                instantiatedClasses.add( superClass );
                Instance superInst = new Instance( superClass );
                addInstance( superInst );
            }
            
            addInstance( inst );
        }
    }
    
    /**
     * adds a Signal to this object's signal queue
     */
    public void addSignal(Signal s) throws LemRuntimeException
    {
	    signalQueue.add(s);
	    /* todo: a lot more */
    }

    /**
     * adds a Signal to this object's "self" signal queue - ie. signals to self.
     */
    public void addSignalSelf(Signal s) throws LemRuntimeException
    {
	    signalSelfQueue.add(s);
	    /* todo: a lot more */
    }
    
    /**
     * Processes the next pending signal if one exists, otherwise wait until
     * one has been put on the queue.
     */
    public void processNextSignal() throws LemRuntimeException
    {
	Event e;
	Signal s = null;
	do {
		
		if (signalSelfQueue.size() > 0) {
			s = signalSelfQueue.remove(0);
		} else if (signalQueue.size() > 0) {
			s = signalQueue.remove(0);
		} else {
			/* wait until signal arrives */
			continue;
		}
	} while (s == null);
	e = s.getEvent();
	
    }
   
    /**
     * Returns the attribute of this object with the given name. All instances
     * which constitute the object are searched.
     * @param attributeName the attribute to find
     * @return the attribute with the given name, or <code>null</code> if the
     * named attribute doesn't exist
     */
    public Variable getAttribute(String attributeName) {
        for( Iterator i = instances.iterator(); i.hasNext(); ) {
            Instance in = (Instance)i.next();
            Variable v = in.getAttribute( attributeName );
            if( v != null ) return v;
        }
        return null;
    }
    
    /**
     * Place all the parents of the given class into the given hashmap. This
     * method traverses all subclass participation roles, including grandparent
     * roles.
     *
     * @param c the class that we're finding parents for
     * @param h the hash into which to insert the parent names
     *
     * @todo this is not the place for this method...
     * @todo does this method exclude the root of a generalisation hierarchy
     * in the duplicate check?
     */
    /*
     
     I don't think we actually use this
     protected static boolean getParents( metamodel.Class c, HashMap h ) {
        boolean duplicates = false;
     
        HashMap superClasses = c.getSubclassParticipation();
        if( superClasses.isEmpty() ) return duplicates;
     
        Iterator i = superClasses.values().iterator();
     
        while( i.hasNext() ) {
            Generalisation g = (Generalisation)i.next();
            metamodel.Class parent = g.getSuperClassRole().getParticipant();
            if( h.containsKey( parent.getName() )) duplicates = true;
            h.put( parent.getName(), parent );
            return getParents( parent, h );
        }
     
        return duplicates;
    } */
    
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
        while( i.hasNext() ) {
            metamodel.Class c = (metamodel.Class)i.next();
            
            if( c.isAbstract() ) return false;
            HashMap gens = c.getAllGeneralisations();
            
            // Add each generalisation to the big list of generalisations
            Iterator j = gens.values().iterator();
            while( j.hasNext() ) {
                if( h.put( j.next(), "" ) != null ) {
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
}

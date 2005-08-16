/*
 * Instance.java
 *
 * Created on 10 May 2005, 17:44
 */

package runtime;

import com.sun.org.apache.xpath.internal.operations.String;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import metamodel.Association;
import metamodel.Attribute;
import metamodel.State;


/**
 * This class represents an instance of an xtUML Class. An Object is made up of
 * one or more of these Instance instances.
 * 
 * @see the xtUML wiki page on Runtime Objects: http://xtuml.jdns.org/wiki/index.php/Runtime_object
 * @author James Ring
 * @author u4128551
 */
public class Instance {
    /**
     * The state of this instance
     */
    State currentState = null;
    
    /**
     * A map of attribute names to Variable references
     */
    Map attributeInstances = null;
    
    /**
     * A map of Association to AssociationInstance references.
     */
    Map associationInstances = null;
    
    /**
     * The Class of which this Instance is an instance
     */
    metamodel.Class instanceOfClass = null;

    /**
     * The Object in which this Instance participates
     */
    runtime.Object instanceInObject = null;
    
    /** Queue of pending signals for the instance */
    List signalQueue = new LinkedList();
    
    /**
     * Queue of pending signals to self. All signals from here must be
     * processed before any signals from 'signalQueue'
     */
    List signalSelfQueue = new LinkedList();
 
    /** @todo: AssociationInstance needs this to build. Should be deleted though */
    public Instance() {}
    
    /**
     * Creates a new instance of Instance given the template metamodel.Class
     * @param theClass the class which is instantiated by this instance
     * @throws runtime.LemRuntimeException in case any class attributes could not be initialised to their default values
     */
    public Instance(metamodel.Class theClass, runtime.Object theObject) throws LemRuntimeException {
        instanceOfClass = theClass;
	instanceInObject = theObject;
        
        initialiseAttributeInstances();
        initialiseAssociationInstances();
    }
    
    /**
     * 
     * Creates a new instance of Instance given the template Class and an initial state
     * @param theClass the class which is instantiated by this instance
     * @param state the state in which this instance should begin
     * @throws runtime.LemRuntimeException in case any class attributes could not be initialised to their default values
     */
    /* @todo: this shouldn't be used. delete.
    public Instance(metamodel.Class theClass, State state) throws LemRuntimeException {
        instanceOfClass = theClass;
        currentState = state;
        
        initialiseAttributeInstances();
        initialiseAssociationInstances();
    }
    */

    private void initialiseAttributeInstances() throws LemRuntimeException {
        Iterator i = instanceOfClass.getAttributes().values().iterator();
        attributeInstances = new HashMap();
        
        while( i.hasNext() ) {
            Attribute a = (Attribute)i.next();
            attributeInstances.put( a.getName(), VariableFactory.newVariable( a.getType() ));
        }
    }
    
    private void initialiseAssociationInstances() throws LemRuntimeException {
        Iterator i = instanceOfClass.getAssociations().values().iterator();
        associationInstances = new HashMap();
        
        while( i.hasNext() ) {
            associationInstances.put( i.next(), new LinkedList() );
        }
    }
    
    /** 
     * Return the Class of which this Instance is an instance
     *
     * @return the Class of which this Instance is an instance
     */
    public metamodel.Class getInstanceClass() {
        return instanceOfClass;
    }
    
    public Collection getAssociationInstances( Association a ) {
        return (Collection)(associationInstances.get( a ));
    }
    
    public void addAssociationInstance( AssociationInstance a ) {
        ((LinkedList)associationInstances.get( a.getAssociation() )).add( a );
    }
    
    public void removeAssociationInstance(AssociationInstance a) {
        associationInstances.values().remove(a);
    }
    
    /**
     * Gets the named attribute from this instance.
     * 
     * @param name the attribute name
     * @return the attribute with the given name, or null if it doesn't exist
     */
    public Variable getAttribute( java.lang.String name ) {
        return (Variable)attributeInstances.get( name );
    }

    /**
     * adds a Signal to this instance's signal queue
     */
    public void addSignal(Signal s) throws LemRuntimeException
    {
	    synchronized (instanceInObject) {
		    signalQueue.add(s);
		    instanceInObject.notify();
	    }
    }

    /**
     * adds a Signal to this instances's "self" signal queue - ie. signals to self.
     */
    public void addSignalSelf(Signal s) throws LemRuntimeException
    {
	    synchronized (instanceInObject) {
		    signalSelfQueue.add(s);
		    instanceInObject.notify();
	    }
    }

    /**
     * Returns the next pending signal if one exists and remove it from the
     * instance's signal queue, otherwise return null.
     */
    public Signal getNextSignal() throws LemRuntimeException
    {
	synchronized (instanceInObject) {
		while (true) {
			if (signalSelfQueue.size() > 0) {
				return (Signal)signalSelfQueue.remove(0);
			} else if (signalQueue.size() > 0) {
				return (Signal)signalQueue.remove(0);
			}
		
			if (instanceInObject.propogateNextSignal()) {
				/** Have a signal */
				continue;
			}
			
                        try {
                            instanceInObject.wait();                            
			/**
			 * After taking instanceInObject's lock, we must
			 * always recheck this Instance's queue because
			 * another instance may have propogated a signal
			 * from the runtime.Object.
			 *
			 * So go back to the top.
			 */
                        } catch (Exception e) {}
		}
	}
    }
}

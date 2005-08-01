/*
 * Instance.java
 *
 * Created on 10 May 2005, 17:44
 */

package runtime;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
    LinkedList signalQueue = new LinkedList();
    
    /**
     * Queue of pending signals to self. All signals from here must be
     * processed before any signals from 'signalQueue'
     */
    LinkedList signalSelfQueue = new LinkedList();
    

    /**
     * Creates a new instance of Instance.
     */
    protected Instance() {
    }
    
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
    public Instance(metamodel.Class theClass, State state) throws LemRuntimeException {
        instanceOfClass = theClass;
        currentState = state;
        
        initialiseAttributeInstances();
        initialiseAssociationInstances();
    }
    
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
    
    /**
     * Gets the named attribute from this instance.
     * 
     * @param name the attribute name
     * @return the attribute with the given name, or null if it doesn't exist
     */
    public Variable getAttribute( String name ) {
        return (Variable)attributeInstances.get( name );
    }

    /**
     * adds a Signal to this instance's signal queue
     */
    public void addSignal(Signal s) throws LemRuntimeException
    {
	    signalQueue.add(s);
	    /* todo: a lot more */
    }

    /**
     * adds a Signal to this instances's "self" signal queue - ie. signals to self.
     */
    public void addSignalSelf(Signal s) throws LemRuntimeException
    {
	    signalSelfQueue.add(s);
	    /* todo: a lot more */
    }

    /**
     * Returns the next pending signal if one exists and remove it from the
     * instance's signal queue, otherwise return null.
     */
    public Signal getNextSignal() throws LemRuntimeException
    {
	Signal s;
	while (true) {
		if (signalSelfQueue.size() > 0) {
			s = signalSelfQueue.remove(0);
		} else if (signalQueue.size() > 0) {
			s = signalQueue.remove(0);
		} else {
			instanceInObject.propogateNextSignal();
			continue;
		}
		break;
	}

	return s;
    }

    public void processNextSignal() throws LemRuntimeException
    {
	Signal s = getNextSignal();
	/* transition the state machine. Execute it. */
    }
}

/*
 * Instance.java
 *
 * Created on 10 May 2005, 17:44
 */

package runtime;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
     * A map of attribute names to AttributeInstance references
     */
    Map attributeInstances = null;
    
    /**
     * The Class of which this Instance is an instance
     */
    metamodel.Class instanceOfClass = null;
    
    /** 
     * Creates a new instance of Instance given the template Class 
     *
     * @param theClass the class which is instantiated by this instance
     */
    public Instance(metamodel.Class theClass) {
        instanceOfClass = theClass;
        
        initialiseAttributeInstances();
    }
    
    /** 
     * Creates a new instance of Instance given the template Class and an initial state
     *
     * @param theClass the class which is instantiated by this instance
     * @param state the state in which this instance should begin
     */
    public Instance(metamodel.Class theClass, State state) {
        instanceOfClass = theClass;
        currentState = state;
        
        initialiseAttributeInstances();
    }
    
    private void initialiseAttributeInstances() {
        Iterator i = instanceOfClass.getAttributes().values().iterator();
        attributeInstances = new HashMap();
        
        while( i.hasNext() ) {
            Attribute a = (Attribute)i.next();
            attributeInstances.put( a.getName(), new AttributeInstance(a));
        }
    }
}

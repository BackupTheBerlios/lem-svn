/*
 * Object.java
 *
 * Created on 10 May 2005, 17:43
 */

package runtime;

import java.util.HashMap;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class represents a LEM runtime object. When the model is being executed,
 * instances of this class and deleted via the 'create' and 'delete' action
 * language statements.
 * @author u4128551
 * @author u3957053
 * @see the Objects at Runtime description at http://xtuml.jdns.org/wiki/index.php/Runtime_object
 */
public class Object {
//    InstanceAttribute attributes[] = null;
    Collection instances = null;
    
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
        
        // Do these classes actually participate in the same inheritance
        // hierarchy?
        if( !validClasses( classes )) {
            throw new LemRuntimeException( "Specified class list will not produce a valid Object" );
        }
        
        instances = new LinkedList();
        // Right... instantiate them!
        for( Iterator i = classes.iterator(); i.hasNext(); ) {
            instances.add( new Instance( (metamodel.Class) i.next() ));
        }
    }
    
    /**
     * Returns the attribute of this object with the given name. All instances
     * which constitute the object are searched.
     * @param attributeName the attribute to find
     * @return the attribute with the given name, or <code>null</code> if the
     * named attribute doesn't exist
     */
    public AttributeInstance getAttribute(String attributeName) {
        //insert code here :)
//        return attributes[0];
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
            while( j.hasNext () ) {
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

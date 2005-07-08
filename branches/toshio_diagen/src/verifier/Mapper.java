/*
 * Mapper.java
 *
 * Created on September 29, 2004, 4:25 PM
 */

package verifier;

import java.util.*;
import metamodel.*;
import parser.*;

/**
 * Provides a map between SimpleNodes and Metamodel objects. 
 *
 * @author  smr
 */
public class Mapper {
    
    /** node to model map */
    private HashMap nodeToModel = new HashMap(); 
    
    /** modelToNode map */
    private HashMap modelToNode = new HashMap(); 
    
    /** Creates a new instance of Mapper */
    public Mapper() {
    }
    
    
    /**
     * Add the supplied node and model to the map
     * 
     * @param node to add to the map
     * @param object to add to the map
     */
    public void add( SimpleNode node, Object object ) {
        nodeToModel.put( node, object );
        modelToNode.put( object, node );
    }
    
    /**
     * Retrieve a node given a MetamodelObject
     *
     * @param object whose node is required
     * @return the object's associated node
     */
    public SimpleNode getNode( Object object ) {
        return (SimpleNode) modelToNode.get( object );
    }
    
    /**
     * Retrieve a MetamodelObject given a node
     *
     * @param node whose metamodel object is required
     * @return the object associated with the node
     */
    public Object getObject ( SimpleNode node ) {
        return nodeToModel.get( node );
    }
    
    
    
}

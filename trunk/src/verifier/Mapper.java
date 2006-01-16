/*
 * Mapper.java
 *
 * Created on September 29, 2004, 4:25 PM
 * 
 * Copyright (C) 2004 Steven Michael Ring
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

package verifier;

import java.util.*;
import org.jdns.xtuml.parser.SimpleNode;

/**
 * Provides a map between SimpleNodes and Metamodel objects. 
 *
 * @author  Steven Michael Ring
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

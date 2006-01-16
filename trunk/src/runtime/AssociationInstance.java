/*
 * Copyright (C) 2005 James Ring
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

import org.jdns.xtuml.metamodel.Association;

/**
 * Represents an instance of an association class. Association classes can
 * either be explicitly specified by the modeller, or they are implicitly
 * created by the eLEMinator.
 *
 * @author sjr
 */
public class AssociationInstance extends Instance {
    
    /**
     * The association of which this AssociationInstance is an instance.
     */
    Association association = null;
    
    /**
     * A reference to the active instance participating in this association
     */    
    Instance active = null;
    
     /**
     * A reference to the passive instance participating in this association
     */     
    Instance passive = null;
    
     /**
     * A reference to the link object instance participating in this association
     */      
    Object linkobject = null;
    
    /**
     * A unique association id to identify this association instance
     */
    private Integer association_id;
    
    /**
     * Create a new instance of the given Association.
     *
     * @param a the association to instantiate
     */
    public AssociationInstance( Association a ) {
        this.association = a;
        association_id = ArbitraryIdVariable.getInstance();
	// @todo: need something like this.
	//super(a.getAssociationClassRole().getAssociationClass(), o);
    }
    
    /**
     * Default empty constructor
     */
    public AssociationInstance() {}
    
    /**
     * function to set the active object participating in the association
     */
    public void setActiveInstance( Instance i ) {
        this.active = i;
    }
        
    /**
     * function to set the passive object participating in the association
     */
    public void setPassiveInstance( Instance i ) {
        this.passive = i;
    }
    
    /**
     * function to set the link object for this association instance if any
     */
    public void setLinkObjectInstance( Object i ) {
        this.linkobject = i;
    }    
    
    /** 
     * function to retrieve a reference to the active object
     */
    public Instance getActiveInstance() {
        return active;
    }
        
    /**
     * function to retrieve a reference to the passive object
     */
    public Instance getPassiveInstance() {
        return passive;
    }    
    
    /**
     * function to retrieve a reference to the link object
     */
    public Object getLinkObjectInstance() {
        return linkobject;
    }    
    
    /**
     * function to retrieve the unique id for this instance
     */
    public Integer getAssociationId() {
        return association_id;
    }
    
    /** 
     * Get the Association of which this AssociationInstance is an instance.
     *
     * @return the Association
     */
    public Association getAssociation() {
        return association;
    }
    
    /**
     * function to define equality between two association instances
     */
    public boolean equals(java.lang.Object o) {
        boolean a,p, as;
        a = p = as = false;
        if (o instanceof AssociationInstance) {
            if(active.equals(((AssociationInstance)o).active))
                a = true;
            else if(active.equals(((AssociationInstance)o).passive))
                a = true;
            if(passive.equals(((AssociationInstance)o).passive))
                p = true;
            else if(passive.equals(((AssociationInstance)o).active))
                p = true;
            if(association.equals(((AssociationInstance)o).association))
                as = true;
            return (a && p && as);
        }
        return false;
    }
}

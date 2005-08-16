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

import metamodel.Association;

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
     * Create a new instance of the given Association.
     *
     * @param a the association to instantiate
     */
    public AssociationInstance( Association a ) {
        this.association = a;
	// @todo: need something like this.
	//super(a.getAssociationClassRole().getAssociationClass(), o);
    }
    
    public void setActiveInstance( Instance i ) {
        this.active = i;
    }
        
    public void setPassiveInstance( Instance i ) {
        this.passive = i;
    }
    
    public void setLinkObjectInstance( Object i ) {
        this.linkobject = i;
    }    
    
    public Instance getActiveInstance() {
        return active;
    }
        
    public Instance getPassiveInstance() {
        return passive;
    }    
    
    public Object getLinkObjectInstance() {
        return linkobject;
    }    
    
    /** 
     * Get the Association of which this AssociationInstance is an instance.
     *
     * @return the Association
     */
    public Association getAssociation() {
        return association;
    }
    
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

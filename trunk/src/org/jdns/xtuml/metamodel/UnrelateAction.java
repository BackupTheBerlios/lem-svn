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


package org.jdns.xtuml.metamodel;

/**
 * This class represents the "relate" action which, when executed, causes
 * two instances to be associated in some way.
 * @author sjr
 */
public class UnrelateAction extends Action {
    
    /**
     * The name of the Variable containing a reference to the 
     * 'active' object in the Association.
     */
    protected String activeObjectName = null;
    
    /**
     * The name of the Variable containing a reference to the 
     * 'passive' object in the Association.
     */
    protected String passiveObjectName = null;
    
    /**
     * The name of the variable which will be assigned the object reference
     * of any instantiated association class.
     */
    protected String associationClassReference = null;
    
    /**
     * The Association which will be instantiated by the execution of this action.
     */
    protected Association association;
    
    /**
     * boolean value to indicate if verb clause is provided
     **/    
    protected boolean verbClause = false;
    
    /** Creates a new instance of RelateAction */
    public UnrelateAction() {
    }

    
    /**
     * Get the name of the Variable containing a reference to the active object.
     *
     * @return the Variable name
     */
    public String getActiveObjectName() {
        return activeObjectName;
    }

    /**
     * Set the name of the Variable containing a reference to the active object.
     *
     * @param activeObjectName the Variable name
     */
    public void setActiveObjectName(String activeObjectName) {
        this.activeObjectName = activeObjectName;
    }

    /**
     * Get the name of the Variable containing a reference to the passive object.
     *
     * @return the Variable name
     */
    public String getPassiveObjectName() {
        return passiveObjectName;
    }

    /**
     * Set the name of the Variable containing a reference to the passive object.
     *
     * @param passiveObjectName the Variable name
     */
    public void setPassiveObjectName(String passiveObjectName) {
        this.passiveObjectName = passiveObjectName;
    }

    /**
     * Returns the name of the variable which will be assigned the object reference
     * of any instantiated association class.
     *
     * @return the Variable name
     */
    public String getAssociationClassReference() {
        return associationClassReference;
    }
    
    /**
     * Set the name of the variable which will be assigned the object reference
     * of any instantiated association class.
     *
     * @param associationClassReference the Variable name
     */
    public void setAssociationClassReference(String associationClassReference) {
        this.associationClassReference = associationClassReference;
    }

    /**
     * Return the Association which will be instantiated by the execution of this action.
     *
     * @return the Association
     */
    public Association getAssociation() {
        return association;
    }

    /**
     * Set the Association which will be instantiated by the execution of this action.
     *
     * @param association the Association
     */    
    public void setAssociation(Association association) {
        this.association = association;
    }
    
    /**
     * Get the verb clause indicating direction of association if any.
     *
     * @return the verb clause
     */
    public boolean getVerbClause() {
        return verbClause;
    }    
    
    /**
     * Set the verb clause that indicates direction of association.
     *
     * @param the verb clause
     */
    public void setVerbClause(boolean clause) {
        this.verbClause = clause;
    }        
}

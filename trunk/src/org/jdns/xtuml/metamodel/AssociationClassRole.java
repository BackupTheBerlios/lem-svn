/* 
 * AssociationClassRole.java
 *
 * Copyright (C) 2005 Steven Michael Ring
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

/*
 * AssociationClassRole.java
 *
 * Created on May 1, 2004, 3:06 PM
 */

package org.jdns.xtuml.metamodel;
/**
 * Instances of AssociationClassRole represent the relationship between
 * an association and the corresponding Association Class.
 *
 * @author  smr
 */
public class AssociationClassRole extends AssociationRole {
    

    
    /** 
     * Creates a new instance of AssociationClassRole using supplied attributes
     *
     * @param participantClass for this AssociationClassRole
     * @param anAssociation in which the class is participating
     */
    public AssociationClassRole( Class participantClass, Association anAssociation ) {
        
        super( participantClass, anAssociation);
    }
    
    /** Getter for property associationClass.
     * @return Value of property associationClass.
     *
     */
    public Class getAssociationClass() {
        return getParticipant();
    }

    
}

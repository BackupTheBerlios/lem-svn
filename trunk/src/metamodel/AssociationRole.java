/* 
 * AssociationRole.java
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
 * AssociationRole.java
 *
 * Created on April 26, 2004, 2:41 PM
 */

package metamodel;
/**
 * Representats the participation of a class in an Association relationship
 *
 * @author  smr
 */
public abstract class AssociationRole {

    /** the class participating in the association relationship */
    private Class participant = null;
    
    /** the generalisation in which the class has a role */
    private Association association = null;
    
    /** 
     * Creates a new instance of AssociationRole using supplied attributes
     *
     * @param participantClass for this AssociationRole
     * @param anAssociation in which the class is participating
     */
    public AssociationRole( Class participantClass, Association anAssociation ) {
        
        participant = participantClass;
        association = anAssociation;
    }
    
    /** Getter for property association.
     * @return Value of property association.
     *
     */
    public Association getAssociation() {
        return association;
    }

    
    /** Getter for property participant.
     * @return Value of property participant.
     *
     */
    public Class getParticipant() {
        return participant;
    }
}

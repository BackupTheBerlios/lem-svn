/* 
 * ParticipatingClassRole.java
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
 * ParticipatingClassRole.java
 *
 * Created on April 26, 2004, 2:48 PM
 */

package metamodel;
import java.util.*;

/**
 * Represents one class in a binary association
 * @author  smr
 */
public class ParticipatingClassRole extends AssociationRole {
    
    /** the perspectives to which this Participating class role is attached
     * the is a maximum of two (for a binary reflexive assocaiton)
     */
    private Vector perspectives = new Vector();
    
    /** 
     * Creates a new instance of ParticipatingClassRole using supplied attributes
     *
     * @param participantClass for this ParticipatingClassRole
     * @param anAssociation in which the class is participating
     */
    public ParticipatingClassRole( Class participantClass, Association anAssociation ) {
        
        super( participantClass, anAssociation );
    }
    
    /**
     * Add the supplied perspective to this ParticipatingClassRole
     *
     * @param aPerspective to add
     * @throws LemException if the maximum number of perspectives is exceeded
     */
    public void add( Perspective aPerspective ) throws LemException {
        
        if ( perspectives.size() > 1 ) {
            throw new LemException( "Too many perspectives for class " + getClass().getName() + 
                                        " in Association " + getAssociation().getName() );
        }
        
        perspectives.add( aPerspective );
    }
    
}

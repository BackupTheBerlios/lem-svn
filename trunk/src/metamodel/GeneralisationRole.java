/* 
 * GeneralisationRole.java
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
 * GeneralisationRole.java
 *
 * Created on April 25, 2004, 4:08 PM
 */

package metamodel;
/**
 * Representats the participation of a class in a Generalisation relationship
 *
 * @author  smr
 */
public abstract class GeneralisationRole {
    
    /** the class participating in the generalisation relationship */
    private Class participant = null;
    
    /** the generalisation in which the class has a role */
    private Generalisation generalisation = null;
    
    /** 
     * Creates a new instance of GeneralisationRole using supplied attributes
     *
     * @param participantClass for this GeneralisationRole
     * @param aGeneralisation in which the class is participating
     */
    public GeneralisationRole( Class participantClass, Generalisation aGeneralisation ) {
        
        participant = participantClass;
        generalisation = aGeneralisation;
    }
    
    /** Getter for property generalisation.
     * @return Value of property generalisation.
     *
     */
    public Generalisation getGeneralisation() {
        return generalisation;
    }

    
    /** Getter for property participant.
     * @return Value of property participant.
     *
     */
    public Class getParticipant() {
        return participant;
    }

}

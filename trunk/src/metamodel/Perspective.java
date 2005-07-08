/* 
 * Perspective.java
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
 * Perspective.java
 *
 * Created on April 26, 2004, 2:39 PM
 */

package metamodel;
/**
 * Represents a Perspective in a binary association
 *
 * @author  smr
 */
public abstract class Perspective {
    
    /**
     * The "Association End" belonging to this perspective. Refer Leon Starr, R110
     */
    private ParticipatingClassRole attachedClassRole = null;
    
    /** the verb clause describing this perspective of the association */
    private VerbClause verbClause = null;
    
    /** the multiplicity of this perspective */
    private Multiplicity multiplicity = null;
    
    /** the Associaton of which this is a perspective, Refer Leon Starr, R113*/
    private Association association = null;
    
    /** Creates a new instance of Perspective */
    public Perspective() {
    }
    
    /** Getter for property association.
     * @return Value of property association.
     *
     */
    public Association getAssociation() {
        return association;
    }
    
    /** Setter for property association.
     * @param association New value of property association.
     *
     */
    public void setAssociation(Association association) {
        this.association = association;
    }
    
    /** Getter for property attachedClassRole.
     * @return Value of property attachedClassRole.
     *
     */
    public ParticipatingClassRole getAttachedClassRole() {
        return attachedClassRole;
    }
    
    /** Setter for property attachedClassRole.
     * @param attachedClassRole New value of property attachedClassRole.
     *
     */
    public void setAttachedClassRole(ParticipatingClassRole attachedClassRole) {
        this.attachedClassRole = attachedClassRole;
    }
    
    /** Getter for property multiplicity.
     * @return Value of property multiplicity.
     *
     */
    public Multiplicity getMultiplicity() {
        return multiplicity;
    }
    
    /** Setter for property multiplicity.
     * @param multiplicity New value of property multiplicity.
     *
     */
    public void setMultiplicity(Multiplicity multiplicity) {
        this.multiplicity = multiplicity;
    }
    
    /** Getter for property verbClause.
     * @return Value of property verbClause.
     *
     */
    public VerbClause getVerbClause() {
        return verbClause;
    }
    
    /** Setter for property verbClause.
     * @param verbClause New value of property verbClause.
     *
     */
    public void setVerbClause(VerbClause verbClause) {
        this.verbClause = verbClause;
    }
    
    /** 
     * Return the class at this "end" of the association
     *
     * @return the class at this "end" of the association
     */
    public Class getDomainClass() {
        return attachedClassRole.getParticipant();
    }
    
}

/* 
 * Relationship.java
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
 * Relationship.java
 *
 * Created on February 3, 2004, 8:41 PM
 */

package org.jdns.xtuml.metamodel;
/**
 * Represents a relationship in an xtUML model
 *
 * @author  smr
 */
public class Relationship extends DomainElement implements SubsystemElement, DescribedEntity {
    
    /** the relationship number */
    private int number;
    
    /** the Subsystem to which this class belongs */
    private Subsystem subsystem = null;
    
    /** the description of the class */
    private String description = "";
    
    /** Creates a new instance of Relationship */
    public Relationship() {
    }
    
    /** Getter for property number.
     * @return Value of property number.
     *
     */
    public int getNumber() {
        return number;
    }
    
    /** Setter for property number.
     * @param number New value of property number.
     *
     */
    public void setNumber(int number) {
        this.number = number;
    }
    
    
    /**
     * Returns the name of this association. Association names in
     * xtUML have the form R999 where 999 is the association number.
     *
     * @return the name of the association
     */
    public String getName() {
        
        return "R" + getNumber();
    }
    
    /**
     * Sets the name of the Association. This enforces the convention that
     * relationship names must have the format R999
     *
     * @param theNumber of the relationship
     */
    public void setName( int theNumber ) {
        setNumber( theNumber );
    }
    
    /**
     * Setting the name as a number is illegal
     *
     * @param theName of the relationship
     * @throws XtUmlException always - DO NOT USE this method
     */
    private void setName( String theName ) throws LemException {
        
        throw new LemException( "Relationship names must be numbers (" + theName + ")" );
    }
    
    /** Getter for property subsystem.
     * @return Value of property subsystem.
     *
     */
    public Subsystem getSubsystem() {
        return subsystem;
    }
    
    /** Setter for property subsystem.
     * @param subsystem New value of property subsystem.
     *
     */
    public void setSubsystem(Subsystem subsystem) {
        this.subsystem = subsystem;
        setDomain( subsystem.getDomain() );
    }
    
    /** Getter for property description.
     * @return Value of property description.
     *
     */
    public java.lang.String getDescription() {
        return description;
    }
    
    /** Setter for property description.
     * @param description New value of property description.
     *
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }
    
    /**
     * Parse the relationship number from the supplied relation identifer. The
     * relation identifier must have the form "R999".
     * @param relationId to be parsed. Must be of the form "R999"
     * @return the relation number
     * @throws LemException in the event of an error
     */
    public static int parseNumber( String relationId ) throws LemException {
        
        int relationNo = 0;
        if ( relationId.substring( 0, 1 ).equals("R") ) {
            try {
                relationNo = Integer.parseInt( relationId.substring( 1 ) );
            } catch ( Exception e ) {
                throw new LemException( "RelationshipIdentifier must have format 'R999', reading '" +relationId + "'" );
            }
        } else {
            throw new LemException( "RelationshipIdentifier must begin with 'R', reading '" +relationId + "'" );
        }
        
        return relationNo;
        
    }
}

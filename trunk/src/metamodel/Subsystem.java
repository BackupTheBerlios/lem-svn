/* 
 * Subsystem.java
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
 * Subsystem.java
 *
 * Created on January 31, 2004, 11:32 AM
 */

package metamodel;
import java.util.HashMap;

/**
 * An arbitrary collection of Relationships and Classes within a single domain
 *
 * @author  smr
 */
public class Subsystem implements DescribedEntity {
    
    /** the Domain to which this Subsystem belongs */
    private Domain domain = null;
    
    /** the name of the subsystem */
    private String name = "";
    
    /** the prefix associated with this subsystem */
    private String prefix = "";
    
    /** description of the subsystem */
    private String description = "";
    
    /** A collection of classes */
    private HashMap classes = new HashMap();
    
    /** Creates a new instance of Subsystem */
    public Subsystem() {
    }
    
    /** Getter for property domain.
     * @return Value of property domain.
     *
     */
    public Domain getDomain() {
        return domain;
    }
    
    /** Setter for property domain.
     * @param domain New value of property domain.
     *
     */
    public void setDomain(Domain domain) {
        this.domain = domain;
    }
    
    /** Getter for property name.
     * @return Value of property name.
     *
     */
    public java.lang.String getName() {
        return name;
    }
    
    /** Setter for property name.
     * @param name New value of property name.
     *
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }
    
    /** Getter for property prefix.
     * @return Value of property prefix.
     *
     */
    public java.lang.String getPrefix() {
        return prefix;
    }
    
    /** Setter for property prefix.
     * @param prefix New value of property prefix.
     *
     */
    public void setPrefix(java.lang.String prefix) {
        this.prefix = prefix;
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
     * Add a class to the subsystem
     * @param theClass to be added to the subsystem
     * @throws metamodel.LemException in the event of an error
     */
    public void add( Class theClass ) throws LemException {
        
        String key = theClass.getName();
        if ( null != classes.get( key ) ) {
            throw new LemException( "Class " + key + " already exists in subsystem " + name );
        }
        
        // all is ok, so add the class collection
        
        theClass.setSubsystem( this );
        domain.add( theClass );
        classes.put( key, theClass );
     }
    
    /** Getter for property classes.
     * @return Value of property classes.
     *
     */
    public java.util.HashMap getClasses() {
        return classes;
    }
    
    /** Setter for property classes.
     * @param classes New value of property classes.
     *
     */
    public void setClasses(java.util.HashMap classes) {
        this.classes = classes;
    }
    
}

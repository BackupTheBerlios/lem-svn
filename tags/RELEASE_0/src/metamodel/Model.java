/*
 * $Id$
 *
 * $Log$
 * Revision 1.1  2005/03/15 12:17:17  u3957053
 * Initial revision
 *
 * Revision 1.3  2004/09/20 12:14:45  u2550921
 * Source lines output on parse exception
 *
 * Revision 1.2  2004/09/01 03:25:48  u2550921
 * package declaration
 *
 * Revision 1.1  2004/08/21 03:10:07  u2550921
 * moved from src directory
 *
 * Revision 1.3  2004/04/25 18:47:35  smr
 * Add domain accessor
 *
 * Revision 1.2  2004/02/02 12:31:23  smr
 * ModelBuilder now handles subsystems, classes and types
 * CoreDataTypes are validated
 *
 * Revision 1.1  2004/01/26 16:09:31  smr
 * *** empty log message ***
 *
 *
 */

package metamodel;

import java.util.*;

/**
 * An xtUML model consisting of one or more domains and bridge specifications
 *
 * @author  smr
 */
public class Model implements DescribedEntity {
    
    /** A collection of Domains comprising this model */
    private HashMap domains = new HashMap();
    
    /** the name of the model */
    private String name = "";
    
    /** Description of model */
    private String description = "";
    
    /** Creates a new instance of Model */
    public Model() {
    }
    
    /**
     * Add a domain to the Model, throw and exception if the domain already exists
     *
     * @param theDomain to add
     * @throws LemException if the domain already exists
     */
    public void addDomain( Domain theDomain ) throws LemException {
       
        String key = theDomain.getName();
        
        if ( domains.get( key ) == null ) {
            domains.put( key, theDomain );
        } else {
            throw new LemException( "Domain " + key + " already exists in model" );
        }
    }
       
    /** Getter for property domains.
     * @return Value of property domains.
     *
     */
    public java.util.HashMap getDomains() {
        return domains;
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
     * Add a new domain to this model.
     *
     * @param theDomain to add
     * @throws LemException if the supplied domain name is not unique or unspecified
     */
    public void add( Domain theDomain ) throws LemException {
        
        String domainName = theDomain.getName();
        if ( domainName == null || domainName.equals( "" ) ) {
            throw new LemException( "empty domain name" );
        }
        
        if ( null != domains.get( domainName ) ) {
            throw new LemException( "Duplicate domain definition. " + domainName + " already exists in " + name + " model"  );
        }
        
        // all OK, so add it
        
        domains.put( domainName, theDomain );
    }
    
    /**
     * Return the named domain
     *
     * @param domainName of the domain to be returned
     * @return the named domain or null if the domain does not exist
     */
    public Domain getDomain( String domainName ) {
        return (Domain) domains.get( domainName );
    }
   
    
}

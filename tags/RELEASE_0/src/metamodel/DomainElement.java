/*
 * DomainElement.java
 *
 * Created on January 31, 2004, 9:25 AM
 */

package metamodel;
/**
 * An abstract class representing all non-subsystem elements of a domain
 *
 * @author  smr
 */
public abstract class DomainElement  extends SourceElement {
    
    /** the Domain of which this element is a memeber */
    Domain domain = null;
    
    /** ID of this element */
    String id = null;
    
        
    /** the description of the class */
    private String description = "";
    
    
   
    /** Creates a new instance of DomainElement */
    public DomainElement() {
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
    
    /** Getter for property id.
     * @return Value of property id.
     *
     */
    public java.lang.String getId() {
        return id;
    }
    
    /** Setter for property id.
     * @param id New value of property id.
     *
     */
    public void setId(java.lang.String id) {
        this.id = id;
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
    
}

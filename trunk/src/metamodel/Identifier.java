/*
 * Identifier.java
 *
 * Created on January 2, 2005, 1:58 PM
 */

package metamodel;

import java.util.*;

/**
 * Provides a means of uniquely identifying instances of a class
 * @author  smr
 */
public class Identifier implements DescribedEntity {
    
    /** the class whose instances are to be identified */
    private Class domainClass = null;
    
    /** the number of this identifier */
    private int number = 0;
    
    /** the attributes that constitute this identifier */
    private ArrayList attributes = new ArrayList();
    
    /** the description of the identifier */
    private String description = "";
    
    
    
    /** 
     * Creates a new instance of Identifier. Use the factory method
     * createNewIdentifier() in class Class to construct new identifiers.
     */
    protected Identifier() {
    }

   /** Getter for property domainClass.
     * @return Value of property domainClass.
     *
     */
    public Class getDomainClass() {
        return domainClass;
    }
    
    /** Setter for property domainClass.
     * @param domainClass New value of property domainClass.
     *
     */
    public void setDomainClass(Class domainClass) {
        this.domainClass = domainClass;
    }
    
    /** 
     * Return the number of this identifier. Identifiers
     * are automatically numbered (from 1) in order of definition
     * within the LEM object declaration.
     *
     * @return the number of this identifier.
     */
    public int getNumber() {
        return number;
    }

    /** 
     * Set the number of this identifier. Identifiers
     * are automatically number (from 1) in order of definition
     * within the LEM object declaration.
     *
     * @param aNumber to identify this identifier.
     */
    public void setNumber(int aNumber) {
        this.number = aNumber;
    }

    /** 
     * Return the ArrayList containing the attributes which make up this
     * identifier. A identifier must contain at least one attribute. The
     * order of appearance is not significant.
     *
     * @return the ArrayList containing the attributes which make up this
     * identifier.
     */
    public ArrayList getAttributes() {
        return attributes;
    }
    
   /** 
    * Add the supplied attribute to this identifier
    *
    * @param attributeName to be added to the identifier
    * @throws MetaModelException if the attribute is already defined for this Identifier
    * or if the attribute cannot be verified as an attribute of this class.
    */
    public void addAttribute( String attributeName ) throws MetaModelException {
        
        if (null == domainClass) {
            throw new MetaModelException( 
                "No class defined in Identifier object. Cannot verify attribute "
                + attributeName );
        }
        
        // find the attribute
        
        Attribute attribute = domainClass.getAttribute( attributeName );
        if ( null == attribute ) {
            
            throw new MetaModelException( 
                "Attribute " + attributeName + " is not defined in class " + domainClass.getName() );
        }
        
        // check that it does not exist in the identifer
        
        if ( attributes.contains( attribute ) ) {
            
            throw new MetaModelException( 
                "Attribute " + attributeName + " is already specified in this identifier for class " + domainClass.getName() );
        }
        
        // all is well, add the attribute
        
        attributes.add( attribute );
        
        // add this identifier to the list maintained by the attribute
        
        attribute.addIdentifier( this );
        
  
    }
    
    /**
     * Test the equality of the supplied Identifier with this Identifier. Identifiers are 
     * equal if they contain exactly the same identifying attributes.
     *
     * @param object to be tested 
     * @return True if the supplied Identifier is equal to this identifier
     *
     */
    public boolean equals( Object object ) {
        
        // supplied object must be an Identifier
        
        if ( ! ( object instanceof Identifier ) ) {
            return false;
        }
        
        // test the trivial case
        
        if ( object == this ) {
            return true;
        }
        
        // test element by element
        
        Identifier id = (Identifier) object;
        
        ArrayList idAttributes = id.getAttributes();
        int n = idAttributes.size();
        
        // both identifiers must have the same number of attributes
        
        if ( n != attributes.size() ) {
            return false;
        }
        
        // test each attribute
        
        for ( int i = 0; i < n; i++ ) {
            Attribute a = (Attribute) idAttributes.get( i );
            if ( ! ( attributes.contains( a ))) {
                return false;
            }
        }
        
        // they are the same identifier
        
        return true;
    
    }
    
    /**
     * Return the name of this identifier. Identifier names are of the form
     * "Inn" where nn is a sequence number denoting the order of declaration within the class.
     *
     * @return the name of this identifier
     */
    public String getName() {
        
        StringBuffer sb = new StringBuffer();
        
        sb.append( "I" );
        if ( number > 1 )
            sb.append( "" + number );
        return sb.toString();
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

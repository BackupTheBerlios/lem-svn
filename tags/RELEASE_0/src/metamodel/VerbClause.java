/*
 * VerbClause.java
 *
 * Created on April 28, 2004, 4:45 AM
 */

package metamodel;
/**
 * Represents the VerbClause used to describe a perspective of an association
 *
 * @author  smr
 */
public class VerbClause {
    
    /** the verb clause is a simple string containing a verb */
    private String clause = "";
    
    /** 
     * Creates a new instance of VerbClause using the supplied string
     *
     * @param aVerbClause for this instance 
     */
    public VerbClause( String aVerbClause ) {
        clause = aVerbClause;
    }
    
    /** 
     * Return the string representation of the verb clause
     *
     * @return the string representation of the verb clause
     */
    public String toString() {
        return clause;
    }
    
}

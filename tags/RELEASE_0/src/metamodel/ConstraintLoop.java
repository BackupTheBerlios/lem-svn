/*
 * ConstraintLoop.java
 *
 * Created on January 31, 2004, 9:42 AM
 */

package metamodel;
/**
 * Represents a constraint loop
 *
 * @author  smr
 */
public class ConstraintLoop extends DomainElement {
    
    /** The text defining the loop */
    private String constraint = "";
    
    /** Creates a new instance of ConstraintLoop */
    public ConstraintLoop() {
    }
    
    /** Getter for property constraint.
     * @return Value of property constraint.
     *
     */
    public java.lang.String getConstraint() {
        return constraint;
    }
    
    /** Setter for property constraint.
     * @param constraint New value of property constraint.
     *
     */
    public void setConstraint(java.lang.String constraint) {
        this.constraint = constraint;
    }
    
}

/*
 * LocalVariable.java
 *
 * Created on November 8, 2004, 4:46 AM
 */

package metamodel;

/**
 * A data item (variable) declared in a procedure
 *
 * @author  smr
 */
public class LocalVariable extends DataItem {
   
    /** the initialisation expression for this type */
    private String initialisationExpression = "";
    
    /** Creates a new instance of Attribute */
    public LocalVariable() {
    }
    
    /** Getter for property initialisationExpression.
     * @return Value of property initialisationExpression.
     *
     */
    public java.lang.String getInitialisationExpression() {
        return initialisationExpression;
    }
    
    /** Setter for property initialisationExpression
     * @param initialisationExpression New value of property initialisationExpression.
     *
     */
    public void setInitialisationExpression(java.lang.String initialisationExpression) {
        this.initialisationExpression = initialisationExpression;
    }
}
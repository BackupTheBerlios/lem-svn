/*
 * BaseAttribute.java
 *
 * Created on November 8, 2004, 4:51 AM
 */

package metamodel;

/**
 * An attribute native to its enclosing class.
 *
 * @author  smr
 */
public class BaseAttribute extends Attribute {
    
    /** the initialisation expression for this type */
    private String initialisationExpression = "";
    
    /** Creates a new instance of BaseAttribute */
    public BaseAttribute() {
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

/*
 * Parameter.java
 *
 * Created on September 26, 2004, 7:49 AM
 */

package metamodel;

/**
 * A parameter represents a data item of an Event signature
 * or state procedure signature. An element has a name and an associated 
 * data type.
 *
 * @author  smr
 */
public class Parameter extends DataItem {
  
    /** The signature to which this Parameter belongs [R507] */
    private Signature signature = null;
    
    /** the initialisation expression for this parameter */
    private String initialisationExpression = "";
    
   /** Creates a new instance of parameter */
    public Parameter() {
    }
    
    /**
     * Return the Signature associated with this Parameter
     *
     * @return the Signature associated with this Parameter
     */
    public Signature getSignature() {
        return signature;
    }

    /**
     * set the Signature associated with this Parameter
     *
     * @param aSignature associated with this Parameter
     */
    public void setSignature(Signature aSignature) {
        this.signature = aSignature;
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
  
    /**
     * Return the domain to which this parameter belongs
     *
     * @return the domain to which this parameter belongs
     */
    public Domain getDomain() {
        return signature.getDomain();
    }
}

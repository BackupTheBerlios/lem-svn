/*
 * SourceElement.java
 *
 * Created on April 26, 2004, 6:10 AM
 */

package metamodel;
/**
 * Represents an object that has been defined and/or referenced in model/domain source code
 *
 * @author  smr
 */
public abstract class SourceElement {
    
    /** first source code reference to this element */
    private SourceReference firstReferenced = null;
    
    /** position in source code where thie element is formally defined */
    private SourceReference defined = null;
  
    
    /** Creates a new instance of SourceElement */
    public SourceElement() {
    }
    
    /** Getter for property defined.
     * @return Value of property defined.
     *
     */
    public SourceReference getDefined() {
        return defined;
    }
    
    /** Setter for property defined.
     * @param defined New value of property defined.
     *
     */
    public void setDefined(SourceReference defined) {
        this.defined = defined;
    }
    
    /** Getter for property firstReferenced.
     * @return Value of property firstReferenced.
     *
     */
    public SourceReference getFirstReferenced() {
        return firstReferenced;
    }
    
    /** Setter for property firstReferenced.
     * @param firstReferenced New value of property firstReferenced.
     *
     */
    public void setFirstReferenced(SourceReference firstReferenced) {
        if  ( null == this.firstReferenced )
            this.firstReferenced = firstReferenced;
    }
    
}

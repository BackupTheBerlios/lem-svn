/*
 * SelectExpression.java
 *
 * Created on 31 July 2005, 14:26
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package metamodel;

import runtime.SetVariable;

/**
 *
 * @author shuku
 */
public class SelectExpression extends Expression {

    public static final int MULTIPLICITY_ONE = 1;
    public static final int MULTIPLICITY_ANY = 2;
    public static final int MULTIPLICITY_ALL = 3;
           
    /** Name of the class to which the objects belong **/
    private metamodel.Class theClass = null; 
    
    /** Type of selection e.g. all, one or any 
     *  Default value "any"                         **/
    private int multiplicity = MULTIPLICITY_ALL; 
        
    /** Whether selectExpression has a where clause */
    private boolean hasCondition = false; 
    
    /** The condition of Select Expression, if any or null otherwise */
    private Expression condition = null; 
    
    /** Creates a new instance of SelectExpression */
    public SelectExpression(int multiplicity, metamodel.Class theClass, Expression condition ) {        
        super(); 
        this.theClass = theClass;
        this.multiplicity = multiplicity ; 
        if ( condition != null ) {
            this.hasCondition = true; 
            this.condition = condition;  
        }
    }

    public metamodel.Class getSelectedClass() {
        return theClass;
    }

    public void setClass(metamodel.Class theClass) {
        this.theClass = theClass;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }

    public boolean hasCondition() {
        return hasCondition;
    }

    public void setHasCondition(boolean hasCondition) {
        this.hasCondition = hasCondition;
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }
}

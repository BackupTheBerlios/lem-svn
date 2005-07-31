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
           
    /** Name of the class to which the objects belong **/
    private String className = null ; 
    
    /** Type of selection e.g. all, one or any 
     *  Default value "any"                         **/
    private String multiplicity = "any" ; 
        
    /** Whether selectExpression has a where clause */
    private boolean hasCondition = false ; 
    
    /** The condition of Select Expression, if any or null otherwise */
    private Expression condition = null ; 
    
    /** Creates a new instance of SelectExpression */
    public SelectExpression(String multiplicity , String className , Expression condition ) {        
        super() ; 
        this.className = className ;
        this.multiplicity = multiplicity ; 
        if ( condition != null ) {
            this.hasCondition = true ; 
            this.condition = condition ;  
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }

    public boolean isHasCondition() {
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

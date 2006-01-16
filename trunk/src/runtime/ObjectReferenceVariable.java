/*
 * ObjectReferenceVariable.java
 *
 * Created on July 6, 2005, 6:13 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

import org.jdns.xtuml.metamodel.DataType;
import org.jdns.xtuml.metamodel.ObjectReferenceType;

/**
 *
 * @author sjr
 */
public class ObjectReferenceVariable extends Variable {
    runtime.Object value = null;
    
    /** Creates a new instance of ObjectReferenceVariable */
    public ObjectReferenceVariable( runtime.Object o ) throws LemRuntimeException {
        setValue( o );
    }
    
    public void setValue( java.lang.Object o ) {
        this.value = (runtime.Object)o;
    }
    
    public java.lang.Object getValue() {
        return value;
    }

    public DataType getType() {
        return ObjectReferenceType.getInstance();
    }

    /**
     * Returns a new BooleanVariable representing whether this ObjectReferenceVariable
     * refers to the same object as the given ObjectReferenceVariable.
     *
     * @param b the ObjectReferenceVariable with which to compare 
     * @throws runtime.LemRuntimeException if <code>b</code> is not a NumericVariable
     * @return a new BooleanVariable representing the truth of the equality
     */
    public Variable equal( Variable b ) throws LemRuntimeException {
	boolean ret = (value == (runtime.Object)((ObjectReferenceVariable)b).getValue());
        
        if( ret )
            return new BooleanVariable( "true" );
        else
            return new BooleanVariable( "false" );
    }

    /**
     * Returns equal( b ).logicalNot().
     *
     * @param b the Variable with which to compare
     * @throws runtime.LemRuntimeException if the call to equal() generates an exception
     * @return the BooleanVariable representing the truth of this comparison
     * @see NumericVariable#equal( Variable )
     */
    public Variable notEqual(Variable b) throws LemRuntimeException {
        return equal( b ).logicalNot();
    }

    public String toString() {
        String result = "";
        return result + value.getObjectId().intValue();
    }
}

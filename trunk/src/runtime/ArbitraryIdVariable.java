/*
 * ArbitaryIdVariable.java
 *
 * Created on July 22, 2005, 5:06 PM
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author thuanseah
 */

package runtime;

import metamodel.ArbitraryIdType;
import metamodel.DataType;

public class ArbitraryIdVariable extends Variable {
    static int current_id = 0;
    protected Integer id;
    
    /** Creates a new instance of ArbitaryIdVariable */
    public ArbitraryIdVariable() throws LemRuntimeException{
        id = new Integer(current_id);
        current_id++;
    }
    
    public void setValue(java.lang.Object o) {
	    id = (Integer)o;
    }

    public java.lang.Object getValue() {
        return id;
    }
    
    public DataType getType() {
        return ArbitraryIdType.getInstance();
    }
}


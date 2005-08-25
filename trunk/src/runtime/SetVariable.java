/*
 * StringVariable.java
 *
 * Created on July 6, 2005, 5:48 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

import metamodel.DataType;
import metamodel.SetType;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Shuku
 */
public class SetVariable extends Variable {
    private boolean refcounted = false;
    private Collection value;
    
    /** Creates a new instance of StringVariable */
    public SetVariable() {
        this.setValue(new LinkedList()) ; 
    }
    
    public void get() throws LemRuntimeException {
	    if (refcounted) {
		    throw new LemRuntimeException("Tried to get SetVariable that is already refcounted");
	    }
	    refcounted = true;

	    Iterator i = value.iterator();
	    while (i.hasNext()) {
		    Variable v = (Variable)i.next();
		    v.get();
	    }
    }

    public void put() throws LemRuntimeException {
	    if (refcounted) {
		    refcounted = false;

		    Iterator i = value.iterator();
		    while (i.hasNext()) {
			    Variable v = (Variable)i.next();
			    v.put();
		    }
	    }
    }
    
    public SetVariable( Collection o ) {
	this.setValue(o) ; 
    }

    public void setValue( java.lang.Object o ) {
        this.value = (Collection)o;
    }
    
    public java.lang.Object getValue() {
        return value;
    }
    
    public DataType getType() {
        return SetType.getInstance();
    }
    
    /**
     * This method adds a certain variable to the set, no type checking is done here 
     *to make sure the types in a set match each other 
     *@param b the variable to be added.
     */
   public void addToSet( Variable b ) throws LemRuntimeException {       
	if (refcounted)
		b.get();
	value.add ( b ) ;       
   }   

}

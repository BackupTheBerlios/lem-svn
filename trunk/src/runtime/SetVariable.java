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
import java.util.ArrayList ; 

/**
 *
 * @author Shuku
 */
public class SetVariable extends Variable {

    private ArrayList value;
    
    
    /** Creates a new instance of StringVariable */
    public SetVariable() {
        this.value = new ArrayList() ; 
    }
    
    public SetVariable( java.lang.Object o) {
	this.value = (ArrayList) o ; 
    }

    public void setValue( java.lang.Object o ) {
        this.value = (ArrayList) o;
    }
    
    public java.lang.Object getValue() {
        return value;
    }
    
    public DataType getType() {
        return SetType.getInstance();
    }
    
    /** This method adds a certain variable to the set, no type checking is done here 
     *to make sure the types in a set match each other 
     *@param b the variable to be added.
     *@return a Variable with that object added */
 
   public Variable add( Variable b ) throws LemRuntimeException {       
       //    String elementType = b.getValue().getType().getName() ;
       value.add ( b ) ;       
       return new SetVariable ( value ) ; 
    }   
}

/*
 * NullType.java
 *
 * Created on 14 July 2005, 11:04
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package metamodel;

import runtime.* ; 
import runtime.LemRuntimeException ;
/**
 *
 * @author shuku
 */
public class NullType extends CoreDataType {
    
    /** Creates a new instance of NullType */
    public NullType() {
        name = "Null" ; 
    }
    
    /**set value of a Null type variable
     *Will throw a LemRuntimeException.
     *@throws LemRuntimeException
     */
    public void setValue(runtime.Object v) throws LemRuntimeException {
        throw new LemRuntimeException("NullType needs to be given a DataType before a value") ;
    }                
}

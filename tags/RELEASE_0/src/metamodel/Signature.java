/*
 * Signature.java
 *
 * Created on September 26, 2004, 7:39 AM
 */

package metamodel;

import java.util.*;

/**
 * Represents a collection of parameters and associated values carried by an event
 * or required by a State procedure.
 *
 * @author  smr
 */
public abstract class Signature {
    
   /** the Parameters associated with this Signature  [R507] */
    private HashMap parameters = new HashMap();
    
    /** Creates a new instance of Signature */
    public Signature() {
    }
    
    
    /**
     * Add a new parameter to this Signature 
     *
     * @param aParameter to be added to this Signature
     */
    public void add( Parameter aParameter ) {
        parameters.put( aParameter.getName(), aParameter );
        aParameter.setSignature( this );
    }
    
    /**
     * Return an array of the parameters in this Signature
     * 
     * @return an array of the parameters in this Signature
     */
    public Parameter [] getParameterArray() {
        
        Parameter [] result = new Parameter[ parameters.size() ];
        
        int i = 0;
        for ( Iterator it = parameters.values().iterator(); it.hasNext()  ; i++ )
            result[ i ] = (Parameter) it.next();
            
        return result;
    }
    
    /**
     * Return a parameter given a name
     *
     * @param name of the required parameter
     * @return the named parameter or null if it does not exist
     */
    public Parameter getParameter( String name ) {
        return (Parameter) parameters.get( name );
    }
    
    /** 
     * Return the domain to which this signature belongs
     *
     * @return the domain to which this signature belongs
     */
    public abstract Domain getDomain();
}

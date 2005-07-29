/* 
 * Signature.java
 *
 * Copyright (C) 2005 Steven Michael Ring
 *
 * This program is free software; you can redistribute it and/or  
 * modify it under the terms of the GNU General Public License  
 * as published by the Free Software Foundation; either version 2  
 * of the License, or (at your option) any later version.  
 *  
 * This program is distributed in the hope that it will be useful,  
 * but WITHOUT ANY WARRANTY; without even the implied warranty of  
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the  
 * GNU General Public License for more details.  
 * 
 * You should have received a copy of the GNU General Public License  
 * along with this program; if not, write to the Free Software  
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,  
 * USA. 
 */

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
    private LinkedList parameters = new LinkedList();
    
    /** Creates a new instance of Signature */
    public Signature() {
    }
    
    
    /**
     * Add a new parameter to this Signature 
     *
     * @param aParameter to be added to this Signature
     */
    public void add( Parameter aParameter ) {
        parameters.add( aParameter );
        aParameter.setSignature( this );
    }
    
    /**
     * Return the list of parameters in this Signature
     * 
     * @return the list of parameters in this Signature
     */
    public LinkedList getParameters() {
        return parameters; 
    }
    
    /** 
     * Return the domain to which this signature belongs
     *
     * @return the domain to which this signature belongs
     */
    public abstract Domain getDomain();
}

/* 
 * Parameter.java
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
 * Parameter.java
 *
 * Created on September 26, 2004, 7:49 AM
 */

package org.jdns.xtuml.metamodel;

/**
 * A parameter represents a data item of an Event signature
 * or state procedure signature. An element has a name and an associated 
 * data type.
 *
 * @author  smr
 */
public class Parameter extends DataItem {
  
    /** The signature to which this Parameter belongs [R507] */
    private Signature signature = null;
    
    /** the initialisation expression for this parameter */
    private String initialisationExpression = "";
    
   /** Creates a new instance of parameter */
    public Parameter() {
    }
    
    /**
     * Return the Signature associated with this Parameter
     *
     * @return the Signature associated with this Parameter
     */
    public Signature getSignature() {
        return signature;
    }

    /**
     * set the Signature associated with this Parameter
     *
     * @param aSignature associated with this Parameter
     */
    public void setSignature(Signature aSignature) {
        this.signature = aSignature;
    }

    
    /** Getter for property initialisationExpression.
     * @return Value of property initialisationExpression.
     *
     */
    public java.lang.String getInitialisationExpression() {
        return initialisationExpression;
    }
    
    /** Setter for property initialisationExpression
     * @param initialisationExpression New value of property initialisationExpression.
     *
     */
    public void setInitialisationExpression(java.lang.String initialisationExpression) {
        this.initialisationExpression = initialisationExpression;
    }
  
    /**
     * Return the domain to which this parameter belongs
     *
     * @return the domain to which this parameter belongs
     */
    public Domain getDomain() {
        return signature.getDomain();
    }
}

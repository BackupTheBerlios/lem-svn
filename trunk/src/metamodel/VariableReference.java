/* 
 * VariableReference.java
 *
 * Copyright (C) 2005 Steven James Ring
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

package metamodel;

/**
 * This class represents variable references that occur in LEM expressions.
 * The following two action language constructs are examples of variable
 * references:
 *
 * pages
 * publisher.name
 *
 * In the first case, the "object name" field of the corresponding VariableReference
 * class is null.
 *
 * @author sjr
 */
public class VariableReference extends Expression {
    
    /**
     * The name of the variable being referenced.
     */
    protected String variableName;
    
    /**
     * The name of the object to which this variable belongs.
     * May be null.
     */
    protected String objectName;
    
    /** 
     * Creates a new instance of VariableReference. 
     * 
     * @param variableName the name of variable to which this VariableReference refers
     */
    public VariableReference( String variableName ) {
        this.setVariableName(variableName);
        this.setObjectName(null);
    }

    /**
     * Creates a new instance of VariableReference.
     *
     * @param objectName the name of the object which contains the referenced variable
     * @param variableName the name of the variable to which this VariableReference refers
     */
    public VariableReference( String objectName, String variableName ) {
        this.setVariableName(variableName);
        this.setObjectName(objectName);
    }

    /**
     * Gets the variable name referenced by this VariableReference.
     * @return the variable name referenced by this VariableReference
     */
    public String getVariableName() {
        return variableName;
    }

    /**
     * Sets the variable name referenced by this VariableReference
     * @param variableName the variable name referenced by this VariableReference
     */
    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    /**
     * Returns the name of the object to which the referenced variable belongs.
     * This may be null if the variable reference specifies no object.
     * @return the name of the object to which the referenced variable belongs, or null if there is no such object.
     */
    public String getObjectName() {
        return objectName;
    }

    /**
     * Sets the name of the object containing the variable to which this variable reference refers.
     * @param objectName the object which contains the variable to which this variable reference refers
     */
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }
}

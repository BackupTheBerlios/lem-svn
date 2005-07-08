/* 
 * LocalVariable.java
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
 * LocalVariable.java
 *
 * Created on November 8, 2004, 4:46 AM
 */

package metamodel;

/**
 * A data item (variable) declared in a procedure
 *
 * @author  smr
 */
public class LocalVariable extends DataItem {
   
    /** the initialisation expression for this type */
    private String initialisationExpression = "";
    
    /** Creates a new instance of Attribute */
    public LocalVariable() {
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
}
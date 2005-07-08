/* 
 * BaseAttribute.java
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
 * BaseAttribute.java
 *
 * Created on November 8, 2004, 4:51 AM
 */

package metamodel;

/**
 * An attribute native to its enclosing class.
 *
 * @author  smr
 */
public class BaseAttribute extends Attribute {
    
    /** the initialisation expression for this type */
    private String initialisationExpression = "";
    
    /** Creates a new instance of BaseAttribute */
    public BaseAttribute() {
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

/* 
 * Expression.java
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
 * Represents a LEM expression
 *
 * @author sjr
 */
public abstract class Expression extends Action {
    
    /**
     * This expression's type
     *
     * @todo Is it possible to determine this type at compile time? If not,
     * this variable is probably inappropriate
     */
    
//    DataType type;
    
    /** Creates a new instance of Expression */
    public Expression() {
    }
    
    /**
     * Gets this expression's type
     */
//    public abstract DataType getType();
}

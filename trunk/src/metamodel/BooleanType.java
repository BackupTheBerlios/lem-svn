/* 
 * BooleanType.java
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
 * BooleanType.java
 *
 * Created on January 31, 2004, 12:20 PM
 */

package metamodel;

/**
 * A class representing the Boolean core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class BooleanType extends CoreDataType {
    
    /** singleton instance of BooleanType */
    private static BooleanType instance = null;
    
    /** Creates a new instance of BooleanType */
    private BooleanType() {
        name = "boolean";
    }
   
    /** 
     * Return the single instance of BooleanType 
     *
     * @return the single instance of BooleanType 
     */
    public static synchronized BooleanType getInstance() {
        
        if ( instance == null )  {
            instance = new BooleanType();
        }
        return instance;
    }
}

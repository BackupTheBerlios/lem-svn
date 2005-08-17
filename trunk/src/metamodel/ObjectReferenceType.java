/* 
 * ObjectReferenceType.java
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
 * ObjectReference.java
 *
 * Created on May 30, 2005
 */


package metamodel;
/**
 * A class representing the ObjectReference core data type. This class is a singleton. 
 *
 * @author  u3293115
 */
public class ObjectReferenceType extends CoreDataType {
    
    /** singleton instance of ArbitraryIdType */
    private static ObjectReferenceType instance = null;
    
    /** Creates a new instance of ObjectReferenceType */
    private ObjectReferenceType() {
        name = "objref";
    }
    
    /** 
     * Return the single instance of ObjectReferenceType 
     *
     * @return the single instance of ObjectReferenceType 
     */
    public static synchronized ObjectReferenceType getInstance() {
        
        if ( instance == null )  {
            instance = new ObjectReferenceType();
        }
        return instance;
    }
    
    /**
     * Returns the String "objref".
     *
     * @return "objref" 
     */
    public String toString() {
	return "objref";
    }
}

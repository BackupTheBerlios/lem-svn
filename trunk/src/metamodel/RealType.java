/* 
 * RealType.java
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
 * RealType.java
 *
 * Created on January 31, 2004, 12:32 PM
 */

package metamodel;
/**
 * A class representing the Real core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class RealType extends CoreDataType {
    
    /** singleton instance of RealType */
    private static RealType instance = null;
    
    /** Creates a new instance of RealType */
    private RealType() {
        name = "real";
    }

    
    /** 
     * Return the single instance of RealType 
     *
     * @return the single instance of RealType 
     */
    public static synchronized RealType getInstance() {
        
        if ( instance == null )  {
            instance = new RealType();
        }
        return instance;
    }
}

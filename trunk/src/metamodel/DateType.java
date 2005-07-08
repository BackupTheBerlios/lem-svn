/* 
 * DateType.java
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
 * DateType.java
 *
 * Created on January 31, 2004, 12:34 PM
 */

package metamodel;
/**
 * A class representing the Date core data type. This class is a singleton. 
 *
 * @author  smr
 */
public class DateType extends CoreDataType {
    
    /** singleton instance of DateType */
    private static DateType instance = null;
    
    /** Creates a new instance of DateType */
    private DateType() {
        name = "date";
    }
    
    /** 
     * Return the single instance of DateType 
     *
     * @return the single instance of DateType 
     */
    public static synchronized DateType getInstance() {
        
        if ( instance == null )  {
            instance = new DateType();
        }
        return instance;
    }
}

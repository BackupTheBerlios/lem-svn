/*
 * ArbitraryIdVariable.java - An optional brief description of the file
 *
 * Copyright (C) 2005 thuanseah
 * Copyright (C) 2005 Shuku Torabi
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

package runtime;

import metamodel.ArbitraryIdType;
import metamodel.DataType;

public class ArbitraryIdVariable extends Variable {
    private static int currentId = 100000;
    private static Integer id;
    
    /** Creates a new instance of ArbitaryIdVariable */
    public ArbitraryIdVariable() throws LemRuntimeException{
        //id = new Integer(current_id);
        //current_id++;
        //currentId = 100000 ; 
    }
    
    public static synchronized Integer getInstance() {
        currentId++;
        id = new Integer(currentId);        
        return id ; 
    }
    
    /** we don't want to allow set value, for obvious reasons **/
    public void setValue(java.lang.Object o) {
	    //id = (Integer)o;
    }    
    
    public java.lang.Object getValue() {
        return id;
    }
    
    public DataType getType() {
        return ArbitraryIdType.getInstance();
    }
}
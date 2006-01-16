/* 
 * SourceReference.java
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
 * SourceReference.java
 *
 * Created on April 26, 2004, 5:51 AM
 */

package org.jdns.xtuml.metamodel;
/**
 * Defines a specific location in source code
 *
 * @author  smr
 */
public class SourceReference {
    
    /** the file in which the source is contained */
    private String filePath = "";
    
    /** the line number of the specific source */
    private int lineNo = 0;
    
    /** the character position within the line, starting from 1 */
    private int columnNo = 0;
    
    /** 
     * Creates a new instance of SourceReference given required values 
     *
     * @param fileName of source file
     * @param line number of reference
     * @param column number of reference
     */
    public SourceReference( String fileName, int line, int column ) {
        
        filePath = fileName;
        lineNo = line;
        columnNo = column;
    }
    
    /**
     * Return a string describing the source reference
     *
     * @return a string describing the source reference
     */
    public String toString() {
        
        StringBuffer sb = new StringBuffer();
        
        if ( filePath.length() > 0 ) {
            sb.append( "in file '" );
            sb.append( filePath );
            sb.append( "' " );
        }
        
        sb.append( "at line " + lineNo );
        
        if ( columnNo > 0 ) {
            sb.append( ", at column " + columnNo );
        }
        
        return sb.toString();
    }
}

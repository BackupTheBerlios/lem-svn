/* 
 * XtUmlException.java
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
 * XtUmlException.java
 *
 * Created on December 6, 2003, 5:03 PM
 */

package org.jdns.xtuml.metamodel;

import org.jdns.xtuml.parser.ParseException;

/**
 * A general xtUML exception
 *
 * @author  smr
 */
public class XtUmlException extends ParseException {
    
    /**
     * Creates a new instance of <code>XtUmlException</code> without detail message.
     */
    public XtUmlException() {
    }
    
    
    /**
     * Constructs an instance of <code>XtUmlException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public XtUmlException(String msg) {
        super(msg);
    }
}
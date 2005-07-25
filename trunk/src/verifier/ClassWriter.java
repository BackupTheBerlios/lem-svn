/*
 * ClassWriter.java
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

package verifier;

import java.util.Collection;
import metamodel.Domain;
import java.util.Iterator;

/**
 * Generates dot code for class diagrams.
 *
 * @author Toshio Nakamura
 */
public class ClassWriter {
	
	/**
	 * Generates dot code for class diagrams. Note that this procedure only
	 * supports diagrams involving classes from a single domain.
	 *
	 * @param domain the domain inside which the class of interest reside
	 * @param classList a list of class names (in String format)
	 * that should  be included in the diagram
	 * @todo fill in dot generation code
	 * @todo print the class list
	 */
	public static String dumpDot( Domain domain, Collection classList ) {
            
              StringBuffer strBuf = new StringBuffer( );
   
              strBuf.append("digraph classdiagram { \n");
        
              for ( Iterator it = classList.iterator(); it.hasNext(); ) {
                  metamodel.Class umlclass = (metamodel.Class) it.next();
           
                  strBuf.append(umlclass.dumpDot());
              }
             strBuf.append("\n");
             strBuf.append( "}" );
				
	     return strBuf.toString();
            	
	}
}

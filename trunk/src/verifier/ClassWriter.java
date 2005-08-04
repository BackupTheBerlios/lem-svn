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
import metamodel.Association;
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
   
              strBuf.append("digraph classdiagram { \n\n");
              strBuf.append("    edge [fontname=\"Helvetica\",fontsize=10,labelfontname=\"Helvetica\",labelfontsize=10];\n");
              strBuf.append("    node [fontname=\"Helvetica\",fontsize=10,shape=record];\n");
              strBuf.append("    nodesep=1.50;ranksep=1.0;\n\n");
              
              for ( Iterator it = classList.iterator(); it.hasNext(); ) {
                  metamodel.Class umlclass = (metamodel.Class) it.next();
           
                  strBuf.append(umlclass.dumpDot());
              }
              
              strBuf.append("\n");
             
              for( Iterator it = domain.getRelationships().values().iterator(); it.hasNext();) {
                  metamodel.Association association = (metamodel.Association) it.next();
                  strBuf.append("    ");
                  strBuf.append(association.getParticipants()[0].getName() + "->");
                  strBuf.append(association.getParticipants()[1].getName() + " ");
                  strBuf.append("[label=\"\\");
                  strBuf.append(association.getName());
                  strBuf.append("\",");
                  strBuf.append("headlabel=\"");
                  strBuf.append(association.getPassivePerspective().getMultiplicity().toString());
                  strBuf.append("\",");
                  strBuf.append("taillabel=\"");
                  strBuf.append(association.getActivePerspective().getMultiplicity().toString());
                  strBuf.append("\",arrowhead=\"none\",labelfontsize=\"7\"");
                  strBuf.append("]; \n");
              }
                
             strBuf.append("\n");
             strBuf.append( "}" );
				
	     return strBuf.toString();
            	
	}
       
        /**  store all associations in in this domain for a subsystem */
        public static Collection associationList; 

        
        /**
	 * Generates UMLGraph code for class diagrams. Note that this procedure only
	 * supports diagrams involving classes from a single domain.
	 *
	 * @param domain the domain inside which the class of interest reside
	 * @param classList a list of class names (in String format)
	 * that should  be included in the diagram
	 * @todo fill in UMLGraph generation code
	 * @todo print the class list
	 */
	public static String dumpUMLGraph( Domain domain, Collection classList ) {
            
             associationList = domain.getRelationships().values();
              StringBuffer strBuf = new StringBuffer();
              // list core types 
              strBuf.append("/**\n* @opt attributes\n* @opt types\n* @hidden\n" +
                        "* @opt bgcolor \"#F0F0F0\"\n" + "* @opt edgecolor \"green\"" +            
                        "\n*/\nclass UMLOptions {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass integer {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass string {}\n"); 
              strBuf.append("/**\n* @hidden\n*/\nclass Boolean {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass real {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass date {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass timestamp {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass numeric {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass arbitrary_id {}\n");
              strBuf.append("\n");strBuf.append("\n");
             
              // print UMLGraph code for all classes
              for ( Iterator it = classList.iterator(); it.hasNext(); ) {
                   metamodel.Class umlclass = (metamodel.Class) it.next();          
                   strBuf.append(umlclass.dumpUMLGraph());
              }
              
              strBuf.append("\n");
            
              return  strBuf.toString();
        }
        
        /** remove a association in association list */
        public static void removeAssociation (Association association)  {
              associationList.remove(association);
        }    
        
}        
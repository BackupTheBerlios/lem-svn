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
import java.util.ArrayList;

/**
 * Generates dot code for class diagrams.
 *
 * @author Toshio Nakamura
 */
public class ClassWriter {

        /**  store all classes in this domain for a subsystem */
        private static Collection classList;

        /**  store all associations in in this domain for a subsystem */
        private static  Collection associationList;
        
        /**  store all associations in in this domain for a subsystem */
        private static ArrayList list = new ArrayList();
        
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
	public static String dumpUMLGraph( Domain domain, Collection aClassList ) {

              classList = aClassList;
                
              associationList =  domain.getRelationships().values();
              TopupList ();
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
              TopupList ();
              return  strBuf.toString();
        }
        
        /** remove a association in association list */
        public static void removeAssociation (Association association)  {
              list.remove(association);
        }    

        /** return an ArrayList contains all associations */
        public static ArrayList getAssociationList() {
              return list;
        }
        
        /** top up association list */
        private static void TopupList ()  {
               list.clear();
               for ( Iterator it = associationList.iterator(); it.hasNext(); ) {
                    metamodel.Relationship relationalship = (metamodel.Relationship) it.next();   
                    list.add(relationalship);
               }
        }    
        
        /**  return a Collection of classes in this domain (or subsystem) */
        public static Collection getClassList () {
            return classList;
        }
        
}        
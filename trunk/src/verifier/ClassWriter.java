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
 * Generates UMLGraph code for class diagrams.
 *
 * @author Toshio Nakamura
 */
public class ClassWriter {

        /**  store selected classes   */
        private static Collection selectedClassList;

        /**  store all classes in this domain */
        private static Collection allClassList;
        
        /**  
         * Store all associations in this domain,
         * After assigned the value, it never be changed
         */
        private static  Collection allAssociationList;
        
        /**  
          * Store associations in this domain. 
          * Elements (association) in it can be removed and added to it again 
          */
        private static ArrayList list = new ArrayList();
        
        /**  a flag to indicate a class is selected or not  */
        private static boolean isSelected = true;
           
        /**
	 * Generates UMLGraph code for class diagrams. Note that this procedure only
	 * supports diagrams involving classes from a single domain.
	 *
	 * @param domain the domain inside which the class of interest reside
	 * @param aClassList a list of class names (in String format)
	 * that should  be included in the diagram
	 * @todo fill in UMLGraph generation code
	 * @todo print the class list
	 */
	public static String dumpUMLGraph( Domain domain, Collection aClassList ) {
              
              // get selected classes  in selected domain
              selectedClassList = aClassList;
              // get all classes in selected domain
              allClassList = domain.getClasses().values();
              
              // get all associations in selected domain
              allAssociationList =  domain.getRelationships().values();
  
              topUpList ();   // fill the variable "list"
              
              StringBuffer strBuf = new StringBuffer();
             
              // set some parameters for UMLGraph, 
              // include whether display attributes, whether display types, backgound color, 
              // edge color
              strBuf.append("/**\n* @opt attributes\n* @opt types\n* @hidden\n" +
                        "* @opt bgcolor \"#F0F0F0\"\n" + "* @opt edgecolor \"green\"" +            
                        "\n*/\nclass UMLOptions {}\n");
              
              // each type used in UMLGraph needs to be defined first,
              // define core xtUML types 
              strBuf.append("/**\n* @hidden\n*/\nclass integer {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass string {}\n"); 
              strBuf.append("/**\n* @hidden\n*/\nclass Boolean {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass real {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass date {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass timestamp {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass numeric {}\n");
              strBuf.append("/**\n* @hidden\n*/\nclass arbitrary_id {}\n");
              strBuf.append("\n");strBuf.append("\n");
             
              // append  UMLGraph code of all classes into string buffer
              for ( Iterator it = allClassList.iterator(); it.hasNext(); ) {
                   metamodel.Class umlclass = (metamodel.Class) it.next();   
                   // decide  whether a class is selected or not
                   if (selectedClassList.contains(umlclass)) 
                      strBuf.append(umlclass.dumpUMLGraph(isSelected)); 
                   else
                      strBuf.append(umlclass.dumpUMLGraph(!isSelected)); 
              }
              
              strBuf.append("\n");
              
              // variable "list" ,which has become empty , needs to be refilled 
              topUpList ();    
              
              return  strBuf.toString();
        }
        
        /**  
         *  Remove a association in association list
         *
         *  @param association a assocoation in the association list
         */
        public static void removeAssociation (Association association)  {
              list.remove(association);
        }    

        /** 
         *  Return an ArrayList contains all associations 
         *  
         *  @return a ArrayList 
         */
        public static ArrayList getAssociationList() {
              return list;
        }
        
        /** 
         *  Copy all associations from variable "allAssociationList" to 
         *  another variable "list" one by one.
         *  All associations in variable "list" will be all removed after all 
         *  associations being represented. So this function will be called 
         *  again to copy all associations back to variable "list"
         */
        private static void topUpList ()  {
               list.clear(); // empty the list 
               for ( Iterator it = allAssociationList.iterator(); it.hasNext(); ) {
                    metamodel.Relationship relationalship = (metamodel.Relationship) it.next();   
                    list.add(relationalship);
               }
        }    
        
        /**  
         *   Return a Collection of selected classes
         *
         *   @return a collection of Class
         */
        public static Collection getSelectedClassList () {
            return selectedClassList;
        }
        
}        
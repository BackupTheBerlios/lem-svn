/* 
 * CreateAction.java
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
 * CreateAction.java
 *
 * Created on May 14, 2005, 9:54 AM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package metamodel;
import java.util.Collection;
import java.util.Iterator;

/**
 * This is the xtUML object creation action.
 * @author sjr
 */
public class CreateAction extends Expression {
    
    /**
     * A new object containing an instance of these classes will be created when 
     * this CreateAction is executed.
     */
    Collection classes = null;
    
    /** Creates a new instance of CreateAction */
    public CreateAction() {
    }
    
    /**
     * Sets the set of classes whose instances participate in the object
     * created when this action is executed.
     *
     * @param classes the set of classes
     */
    public void setClasses( Collection classes ) {
        this.classes = classes;
    }
    
    /**
     * Gets the the set of classes whose instances participate in the object
     * created when this action is executed.
     *
     * @return the set of classes
     */
    public Collection getClasses() {
	    return classes;
    }
}

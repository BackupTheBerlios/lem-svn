/* 
 * DeleteAction.java
 *
 * Copyright (C) 2005 James Ring
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

package metamodel;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class represents the xtUML object deletion action.
 *
 * @author sjr
 */
public class DeleteAction extends Action {
    
    /**
     * The reference to the variable which will contain a reference to the
     * victim object. 
     */
    VariableReference variable = null;
    
    /** Creates a new instance of DeleteAction */
    public DeleteAction() {
    }
    
    /**
     * Gets the reference to the variable which will contain a reference to the 
     * victim object.
     *
     * @return the variable reference
     */
    public VariableReference getVariable() {
        return variable;
    }

    /**
     * Sets the reference to the variable which will contain a reference to the
     * victim object.
     *
     * @param variable the variable reference
     */
    public void setVariable(VariableReference variable) {
        this.variable = variable;
    }
}

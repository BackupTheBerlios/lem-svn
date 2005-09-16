/*
 * LemSelectionEvent.java
 *
 * Created on September 16, 2005, 6:57 PM
 *
 * Copyright (C) 2005 Thuan Seah Tan
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

/**
 *
 * @author thuanseah
 */

package runtime;

import java.util.Collection;
import java.util.LinkedList;

public class LemSelectionEvent extends LemEvent {
    /** the condition used to select/return objects */
    String condition = null;
    
    /** list of object ids returned by tranversing a selection statement */
    Collection objectList = new LinkedList();

    /** Creates a new instance of LemSelectionEvent
     * 
     * @param c the condition used
     * @param list the list of object ids
     */
    public LemSelectionEvent( String c, Collection list) {
        condition = c;
        objectList.addAll(list);
    }
    
    /**
     * Returns the list of object ids selected
     *
     * @return a list of Integer of the object ids
     */
    public java.util.Collection getObjectList() { 
        return objectList;
    }

    /**
     * Returns the condition used for the select statement
     *
     * @return a String representation of the condition
     */    
    public String getSelectCondition() {
        return condition;
    }
    
    /**
     * Calls the method on the given LemEventListener.
     * Passes <code>this</code> as the parameter to the 
     *
     * @param listener the LemEventListener to notify
     */
    public void notify( LemEventListener listener ) {
        listener.selectedEvent( this );
    }
}

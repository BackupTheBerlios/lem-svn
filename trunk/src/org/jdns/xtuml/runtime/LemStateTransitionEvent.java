/*
 * LemStateTransitionEvent.java
 *
 * Created on September 7, 2005, 4:34 PM
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

package org.jdns.xtuml.runtime;

/**
 *
 * @author thuanseah
 */
public class LemStateTransitionEvent extends LemEvent {
    /**
    * A Integer value of the object's unique Id
    */
    private Integer objectId;
    
    /**
     * the name of the state we are transitting from
     */
    private String fromState = null;
    
    /**
     * the name of the state we are transitting to
     */
    private String toState = null;
    
    /** 
     * Creates a new instance of LemStateTransitionEvent 
     * @param id the object id of the object for which a state transition occurred
     * @param from the name of the state we are transitting from
     * @param to the name of the state we are transitting to
     */
    public LemStateTransitionEvent(int id, String from, String to) {
        objectId = new Integer(id);
        fromState = from;
        toState = to;
    }
   
    /**
     * function to retrieve the object id for which a state transition occurred
     */
    public Integer getObjectId() {
        return objectId;
    }
    
    /**
     * function to retrieve the name of the state we are transitting from
     */
    public String getFromState() {
        return fromState;
    }
    
    /**
     * function to return the name of the state we are transitting to
     */
    public String getToState() {
        return toState;
    }
    
    /**
    * Notify the given listener that this event has occurred.
    * @param listener The listener to notify
    */
    public void notify(LemEventListener listener) {
	listener.transitionEvent(this);
    }       
}

/* 
 * EventSignature.java
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
 * EventSignature.java
 *
 * Created on September 26, 2004, 7:44 AM
 */

package org.jdns.xtuml.metamodel;

/**
 * The parameter signature for a event.
 *
 * @author  smr
 */
public class EventSignature extends Signature {
    
    /** The event to which this signature belongs */
    private Event event = null;
    
    /** Creates a new instance of EventSignature */
    public EventSignature() {
    }

    /**
     * Return the Event associated with this EventSignature
     *
     * @return the Event associated with this EventSignature
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Set the Event associated with this EventSignature
     *
     * @param aEvent associated with this EventSignature
     */
    public void setEvent(Event aEvent) {
        this.event = aEvent;
    }
    
    /** 
     * Return the domain to which this signature belongs
     *
     * @return the domain to which this signature belongs
     */
    public Domain getDomain(){
        return event.getDomainClass().getDomain();
    }
    
    
}

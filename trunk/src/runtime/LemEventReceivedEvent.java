/*
 * LemEventReceivedEvent.java
 *
 * Created on September 6, 2005, 6:57 PM
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

public class LemEventReceivedEvent extends LemEvent {
    /**
     * the unique alpha-numeric object id of the object
     */
    private Integer objectId;
    
    /**
     * the unique alpha-numeric identifier for the event
     */
    private Integer eventId;
    
    /** 
     * the event that is sent
     */
    private String eventType;
    
    /**
     * data sent with the event
     */
    Collection eventParameters = new LinkedList();
    
    /** Creates a new instance of LemAttributeReadEvent */
    public LemEventReceivedEvent(int id, int evtid, String type, Collection p) {
        objectId = new Integer(id);
        eventId = new Integer(evtid);
        eventType = type;
        if(p != null)
            eventParameters.addAll(p);
    }
    
    /**
     * function to retrieve the object id of the object
     */
    public Integer getObjectId() {
        return objectId;
    }
        
     /**
      * returns the object id of the event
      * @return an Integer object representing the unique object id of the event
      */
     public Integer getEventId() {
         return eventId;
     }
     
     /**
      * returns the type of event generated
      * @return a String representation of the event type
      */
     public String getEventType() {
         return eventType;
     }
     
     /**
      * returns the data sent with the event
      * @returns a Collection of the data sent with the event
      */
     public Collection getEventParameters() {
        return eventParameters;
     }    
    
    /**
    * Notify the given listener that this event has occurred.
    * @param listener The listener to notify
    */
    public void notify(LemEventListener listener) {
	listener.receiveEvent(this);
    }    
}

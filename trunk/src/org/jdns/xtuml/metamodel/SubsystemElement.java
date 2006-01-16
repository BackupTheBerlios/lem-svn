/* 
 * SubsystemElement.java
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
 * SubsystemElement.java
 *
 * Created on January 31, 2004, 11:37 AM
 */

package org.jdns.xtuml.metamodel;
/**
 * A subsystem element preserves a relationship to a Subsystem
 *
 * @author  smr
 */
public interface SubsystemElement {

    /** 
     * set the Subsytem to which this SubsystemElement belongs
     * 
     * @param theSubsystem to which this SubsystemElement belongs
     */
    public void setSubsystem( Subsystem theSubsystem );
    
    /** 
     * returns the Subsytem to which this SubsystemElement belongs
     * 
     * @return theSubsystem to which this SubsystemElement belongs
     */
    public Subsystem getSubsystem();
}

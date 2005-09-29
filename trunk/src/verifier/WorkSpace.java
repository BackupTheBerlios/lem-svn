/*
 * WorkSpace.java
 *
 * Created on 2 May 2005, 06:31
 *
 * Copyright (C) 2005 David Gavin
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

import java.io.Serializable;

/**
 *
 * @author David Gavin
 */
public class WorkSpace implements Serializable{
    String CurrentWorkspace;
    /** Creates a new instance of WorkSpace */
    public WorkSpace(String inWorkspace) {
        CurrentWorkspace = inWorkspace;
    }
    /**Returns the CurrentWorkspace.
     *@return String the CurrentWorkspace.
     */
    public String getWorkSpace()
    {
        return CurrentWorkspace;
    }
    
}

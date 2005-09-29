/*
 * ThemeItem.java
 *
 * Created on 4 May 2005, 01:56
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

/**
 *
 * @author David Gavin
 */
public class ThemeItem {
    String name;
    String className;
    /** Creates a new instance of ThemeItem */
    public ThemeItem(String inName, String inClass) {
        name = inName;
        className = inClass;
    }
    
    /** Returns the name of the ThemeItem
     *@return String name of the ThemeItem.
     */
    public String toString()
    {
        return name;
    }
    
    /**Returns the className of the ThemeItem
     *@return String the className of the ThemeItem.
     */
    public String getClassName()
    {
        return className;
    }
}

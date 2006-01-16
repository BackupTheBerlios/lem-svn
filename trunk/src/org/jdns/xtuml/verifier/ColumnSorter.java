/*
 * ColumnSorter.java
 *
 * Copyright (C) 2005 Donna Aloe
 * Copyright (C) 2005 Simon Franklin
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

package org.jdns.xtuml.verifier;

import java.util.Comparator;
import java.util.Vector;

/**
 * Provides an implementation of compare for Collections.sort.
 * It allows elements in a column to be sorted in ascending or descending order
 *
 * @author  David Gavin
 * @author  Donna Aloe
 * @author  Simon Franklin
 * @see     TableModel
 */
 public class ColumnSorter implements Comparator {
    int colIndex;
    boolean ascending;
    
    /**
     *  Sets the column to be sorted and the order
     * @param colIndex The index of the column
     * @param ascending True if ascending order, False if descending
     */
   ColumnSorter(int colIndex, boolean ascending) {
       synchronized (this) {
        this.colIndex = colIndex;
        this.ascending = ascending;
       }
    }
    
    /**
     * Compares two objects according to the order
     * @param a The first object
     * @param b The second object
     */
    public synchronized int compare(Object a, Object b) {
        Vector v1 = (Vector)a;
        Vector v2 = (Vector)b;
        Object o1 = v1.get(colIndex);
        Object o2 = v2.get(colIndex);
        
        // Treat empty strains like nulls
        if (o1 instanceof String && ((String)o1).length() == 0) {
            o1 = null;
        }
        if (o2 instanceof String && ((String)o2).length() == 0) {
            o2 = null;
        }
        
        // Sort nulls so they appear last, regardless
        // of sort order
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        } else if (o1 instanceof Comparable) {
            if (ascending) {
                return ((Comparable)o1).compareTo(o2);
            } else {
                return ((Comparable)o2).compareTo(o1);
            }
        } else {
            if (ascending) {
                return o1.toString().compareTo(o2.toString());
            } else {
                return o2.toString().compareTo(o1.toString());
            }
        }
    }
}


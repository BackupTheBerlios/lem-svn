/*
 * LinkedPriorityQueue.java 
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

package util;

import java.util.LinkedList;


/**
 * A LinkedList implementation of a priority queue. This is not really
 * a priority queue in the sense that you cannot re-order elements already
 * on the queue, but it should suffice for our purposes!
 *
 * @author James Ring
 */
public class LinkedPriorityQueue extends LinkedList {
	public static int MIN_QUEUE = 1;
	public static int MAX_QUEUE = 2;

	/**
	 * The type of this queue (either LinkedList.MIN_QUEUE or MAX_QUEUE)
	 */
	private int type;

	/**
	 * Create a new LinkedPriorityQueue of the given type.
	 */
	public LinkedPriorityQueue( int type ) {
		this.type = type;
	}

	/** 
	 * Add the given Comparable into the queue. The object will be 
	 * inserted in the correct position to maintain the ordering of 
	 * the queue based on the queue's type.
	 *
	 * @param c the object to insert
	 */
	public void add( Comparable c ) {
		if( type == MIN_QUEUE )
			minInsert( c );
		else
			maxInsert( c );
	}
	
	/** 
	 * Insert the given Comparable into the queue. This operation
	 * will place the given object before any other larger 
	 * elements, thereby preserving the ordering.
	 * 
	 * @param c the object to insert
	 */
	private void minInsert( Comparable c ) {
		int i;

		for( i = 0; i < size(); i++ ) {
			// If the element to be inserted is smaller than
			// the current element, break out and insert the
			// new element here
			if( c.compareTo( (Comparable)get(i) ) == -1 ) 
				break;
		}

		add( i, c );
	}

	/** 
	 * Insert the given Comparable into the queue. This operation
	 * will place the given object before any other larger 
	 * elements, thereby preserving the ordering.
	 * 
	 * @param c the object to insert
	 */
	private void maxInsert( Comparable c ) {
		int i;
		
		for( i = 0; i < size(); i++ ) {
			// If the element to be inserted is larger than
			// the current element, break out and insert the
			// new element here
			if( c.compareTo( (Comparable)get(i) ) == 1 )
				break;
		}

		add( i, c );
	}

	/**
	 * Pops the first element off the queue. This element will either
	 * be the smallest or largest element in the queue, depending on the
	 * queue type.
	 *
	 * @return the smallest or largest element, depending on the queue type,
	 * or <code>null</code> if the list is empty
	 */
	public Comparable pop() {
		if( isEmpty() ) 
			return null;
		return (Comparable)removeFirst();
	}
}

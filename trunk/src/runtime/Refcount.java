/*
 * Refcount.java
 *
 * Copyright (C) 2005 Nick Piggin
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

package runtime;

/**
 * Refcount is a thread safe class which manages a basic refount.
 */
public class Refcount
{
	private int num;

	/**
	 * The nullary Refcount constructor. Creates a new Refcount object
	 * with one reference to it.
	 */
	public Refcount()
	{
		num = 1;
	}

	/**
	 * The custom Refcount constructor. Creates a new Refcount object
	 * with n references to it.
	 * @param n number of references to new Refcount. Must be >= 0.
	 */
	public Refcount(int n)
	{
		num = n;
	}
	
	public synchronized void get() throws Error
	{
		/* It's a bug to get an object that has no references to it */
		if (num == 0)
			throw new Error("Tried to get zero refcount");

		num++;

		System.out.println("get: Refcount variable has " + num + " references");
	}

	public synchronized boolean put() throws Error
	{
		/* It's a bug to put an object that has no references to it */
		if (num == 0)
			throw new Error("Tried to put zero refcount");

		num--;

		System.out.println("put: Refcount variable has " + num + " references");
		if (num == 0)
			return true;
		else
			return false;
	}

	public synchronized int references()
	{
		return num;
	}
}

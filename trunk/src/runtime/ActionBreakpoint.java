/*
 * ActionBreakpoint.java
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

import org.jdns.xtuml.metamodel.Action;

/**
 * Objects of this class represent a breakpoint on a state transition.
 */
public abstract class ActionBreakpoint extends Breakpoint {
	private Action action;

	public ActionBreakpoint(Action a) {
		action = a;
	}

	public boolean matches(Action a) {
		return (a == action);
	}
}

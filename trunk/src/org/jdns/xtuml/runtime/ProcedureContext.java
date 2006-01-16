/*
 * ProcedureContext.java 
 *
 * Copyright (C) 2005 James ring
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

import org.jdns.xtuml.metamodel.EventSignature;
import org.jdns.xtuml.metamodel.Parameter;

import java.util.Iterator;

/**
 * Once ProcedureContext is created for each newly executing state procedure. It
 * provides a nice utility constructor for copying signal parameters into the
 * context.
 *
 * @author sjr
 */
public class ProcedureContext extends LocalContext {

	/** 
	 * Create a new procedure context. Procedures are always executed after
	 * the delivery of a signal, and that signal is passed in here.
	 *
	 * @param inContext the parent context
	 * @param s the signal which caused the execution of the procedure for
	 * which we're a context
	 */
	public ProcedureContext(Context inContext, Signal s) {
		super( inContext );

		if( s.getParameters() == null )
			return;

		Iterator vars = s.getParameters().iterator();

		EventSignature sig = s.getEvent().getSignature();
		Iterator params = sig.getParameters().iterator();
		
		// We assume that the parameters in the event signature and the
		// variables in the signal are in the same order 
		while( params.hasNext() ) {
			Parameter p = (Parameter)params.next();
			Variable v = (Variable)vars.next();

			addVariable( p.getName(), v );
		}
	}
}

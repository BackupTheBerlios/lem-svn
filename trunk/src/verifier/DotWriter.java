/*
 * DotWriter.java
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

package verifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Wrapper class for the 'dot' graph generation binary.
 *
 * @author Toshio Nakamura
 */
public class DotWriter {
	
	/**
	 * Uses an external dot binary to convert a given set of dot code into a
	 * PNG file
	 *
	 * @param dotCode the dot code to convert
	 * @param filename the name of the PNG file to write
	 * @todo add error handling code for dot binary
	 * @todo use exception classes?
	 */
	public static void dotToPNG( String dotCode, String filename ) throws
			IOException {
		// Get the dot binary's location
		Parameters params = Parameters.getInstance();
		String dotBinary = params.getProperty( "eleminator.dotPath" );
		String[] cmdString = {dotBinary, "-Tpng", "-o" + filename};
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		OutputStreamWriter out = null;
		InputStreamReader in = null;
		BufferedReader bufRead = null;
		StringBuffer strBuf = new StringBuffer();
		int c;
		
		// Run dot
		p = rt.exec( cmdString );
		// Feed the dot code into the dot binary's standard input
		out = new OutputStreamWriter( p.getOutputStream() );
		in = new InputStreamReader( p.getInputStream() );
		out.write( dotCode );
		out.close();
		bufRead = new BufferedReader( in );
		while (true) {
			c = in.read();
			if (c == -1) {
				break;
			}
			strBuf.append( (char)c );
		}
	}
}
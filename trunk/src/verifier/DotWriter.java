/*
 * DotWriter.java
 *
 * Created on 23 July 2005, 21:53
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
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
	public static void dotToPNG( String dotCode, String filename ) {
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
		try {
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
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}

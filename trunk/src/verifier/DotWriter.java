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
	 * Hardcoded name of dot binary
	 * @todo this is a hack
	 * @todo this needs to be replaced with a Preferences-style solution
	 */
	private static String dotBinary = "/usr/bin/dot";
	
	/**
	 * Uses an external dot binary to convert a given set of dot code into a
	 * PNG file
	 *
	 * @param dotCode the dot code to convert
	 * @param filename the name of the PNG file to write
	 * @todo add error handling code for dot binary
	 */
	public static void dotToPNG( String dotCode, String filename ) {
		String[] cmdString = {dotBinary, "-Tpng", "-o" + filename};
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		OutputStreamWriter out = null;
		InputStreamReader in = null;
		BufferedReader bufRead = null;
		StringBuffer strBuf = new StringBuffer();
		int c;
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
			
			System.err.println( "Standard output: " + strBuf.toString() );
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}

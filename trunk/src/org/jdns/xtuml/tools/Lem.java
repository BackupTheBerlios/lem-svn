/*
 * Lem.java
 *
 * Copyright (C) 2006 Steven Ring
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

package org.jdns.xtuml.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.Reader;
import java.net.URL;

import org.jdns.xtuml.metamodel.LemException;
import org.jdns.xtuml.metamodel.Model;
import org.jdns.xtuml.parser.LEMModelDeclaration;
import org.jdns.xtuml.parser.LemParser;
import org.jdns.xtuml.parser.ParseException;
import org.jdns.xtuml.parser.Token;
import org.jdns.xtuml.util.SourceBuffer;

/**
 * A command-line parser for LEM-coded models - LEM: a Language for Executable
 * Models.
 * <p>
 * LEM allows xtUML models to be expressed in a text-based language. This LEM
 * parser is the first of a series of tools designed to allow models to be
 * verified and translated into code.
 * <p>
 * The LEM parser is a JAVA program which parses the LEM input file (according
 * to the formal grammar) and builds an in-memory graph representing the xtUML
 * model defined by the LEM input file. This software is designed to form the
 * basis of a suite of Open Source tools supporting xtUML.
 * <p>
 * <h2>Acknowledgements</h2>
 * <ol>
 * <li>Emma Margaret Walker, <i>A language for the specification of
 * Shlaer-Mellor object-oriented analysis models</i><br>
 * ANU, BSEng Honours thesis, November 2002 </li>
 * <li>Leon Starr, <i>Metamodel for xtUML, 2002;</i> upon which much of the
 * JAVA object model is based</li>
 * <li>Shayne Flint, instrumental in providing ongoing support and direction</li>
 * </ol>
 * 
 * 
 * <p>
 * Usage:<br>
 * 
 * <pre>
 *    java -jar lem.jar model_File_Path
 * </pre>
 * 
 * @author Steven Ring
 */
public class Lem {

	private static String filename = null;

	private static SourceBuffer source = null;

	private Token errorToken = null;

	public Throwable errorException = null;

	public String errorMessage = "";

	protected LEMModelDeclaration rootNode;

	public static void main(String args[]) {

		// print a copyright notice
		copyrightNotice();
		checkOptions(args);

		// create a LemParser to parse the input file

		Lem parser = null;

		try {
			parser = new Lem();
			parser.parse(getInputStream(args));

			// if no exceptions were thrown, then the input stream is valid LEM

			System.err.println("Input file is VALID LEM.");

			// all done ... bit of an anti-climax really!
		} catch (ParseException e) {
			System.err.println("Doh! ... got " + e.getClass().getName());

			Token token = e.currentToken.next;
			printSourceLine(token);
			System.err.println(e.getMessage());
		} catch (LemException e) {

			System.err.println("Oops! ... got " + e.getClass().getName());

			Token token = e.getToken();
			printSourceLine(token);
			System.err.println(e.getMessage());
			System.err.println(e.getExplanation());

		} catch (Throwable e) {

			// report any exception thrown during LEM parsing or model building

			System.err.println("Ouch! ... got " + e.getClass().getName());
			System.err.println(e.getMessage());
			e.printStackTrace();
			usage(null);
		}

		System.exit(0);
	}

	/**
	 * Return a parsed model based on the supplied LEM input stream
	 * 
	 * @param lemStream
	 *            the model expressed in LEM
	 * @return the model as an instatiated graph of metamodel object instances
	 * @throws Throwable
	 *             in the event of an error. A ParseException is thrown if
	 *             parsing fails, a LemException is thrown if the model fails is
	 *             semantic checking fails. Any Throwable may be thrown in the
	 *             event of a program bug (this is a "work in progress" after
	 *             all!)
	 */
	public Model parse(InputStream lemStream) throws ParseException,
			LemException, IOException {

		// read the source into a SourceBuffer

		source = new SourceBuffer(lemStream);

		Model model = null;
		LemParser parser = new LemParser(source.getInputStream());

		try {

			model = parser.parse();
			rootNode = parser.getRootNode();

		} catch (ParseException e) {

			errorException = e;
			errorToken = e.currentToken.next;
			buildErrorSourceLine();
			throw e;

		} catch (LemException e) {

			errorException = e;
			errorToken = e.getToken();
			buildErrorSourceLine();
			throw e;

		}

		return model;
	}

	/**
	 * Return a parsed model based on the supplied LEM reader
	 * 
	 * @param lemReader
	 *            the model expressed in LEM
	 * @return the model as an instatiated graph of metamodel object instances
	 * @throws Throwable
	 *             in the event of an error. A ParseException is thrown if
	 *             parsing fails, a LemException is thrown if the model fails is
	 *             semantic checking fails. Any Throwable may be thrown in the
	 *             event of a program bug (this is a "work in progress" after
	 *             all!)
	 */
	public Model parse(Reader lemReader) throws ParseException, LemException,
			IOException {
		// read the source into a SourceBuffer

		source = new SourceBuffer(lemReader);

		Model model = null;
		LemParser parser = new LemParser(source.getInputStream());

		try {

			model = parser.parse();
			rootNode = parser.getRootNode();

		} catch (ParseException e) {

			errorException = e;
			errorToken = e.currentToken.next;
			buildErrorSourceLine();
			throw e;

		} catch (LemException e) {

			errorException = e;
			errorToken = e.getToken();
			buildErrorSourceLine();
			throw e;

		}

		return model;
	}

	private static void printSourceLine(Token t) {
		int line = 0;
		int col = 0;

		System.err.print("In file " + filename);

		if (t == null) {
			System.err.println("");
			return;
		}

		line = t.beginLine;
		col = t.beginColumn;
		String sourceLine = source.getLine(line - 1);
		System.err.println(" at line " + line + ", column " + col);
		System.err.println(sourceLine);

		// now print a pointer to the position

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < col - 1; i++)
			sb.append(" ");
		sb.append("|");
		System.err.println(sb.toString());

	}

	/**
	 * Build the error message using the errorException and errorToken
	 */
	private void buildErrorSourceLine() {
		int line = 0;
		int col = 0;
		StringBuffer s = new StringBuffer();

		s.append("In file " + filename + "\n");
		if (errorToken != null) {
			line = errorToken.beginLine;
			col = errorToken.beginColumn;
			String sourceLine = source.getLine(line - 1);
			s.append(" at line " + line + ", column " + col + "\n");
			s.append(sourceLine + "\n");

			// now print a pointer to the position

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < col - 1; i++)
				sb.append(" ");
			sb.append("|");
			s.append(sb.toString() + "\n");

		} else {
			s.append("\n");
		}

		s.append(errorException.getMessage() + "\n");
		if (errorException instanceof LemException) {
			LemException le = (LemException) errorException;
			s.append(le.getExplanation() + "\n");
		}

		// store the resulting error message

		errorMessage = s.toString();
	}

	/**
	 * Return the input stream determined from supplied parameter line arguments
	 * 
	 * @param args
	 *            the command line arguments
	 * @return an InputStream containing the model to be parsed
	 */
	private static InputStream getInputStream(String[] args) throws Exception {
		// select the input stream to read from

		InputStream in = null;

		// read from file if filename is provided

		if (filename != null) {
			URL u = null;
			try {
				u = new URL(filename);
			} catch (java.net.MalformedURLException m) {
				u = null;
			}

			try {
				if (u == null) {
					// Try opening the file with the given
					// filename
					in = new FileInputStream(filename);
				} else {
					in = u.openStream();
				}
			} catch (IOException e) {
				String err = (u == null) ? "reading from" : "opening";
				System.err.println("There was an error " + err
						+ " the given URL" + ": " + e.getMessage());
				usage(null);
				System.exit(1);
			}
		} else {

			// read from stdin

			in = System.in;
			System.err.println("Reading from standard input...");
		}

		return in;
	}

	/**
	 * Prints out a copyright notice
	 */
	private static void copyrightNotice() {

		String msg = "LEM - a language for executable models\n"
				+ LemParser.getRelease() + " (rev: " + getRevision() + ")\n\n"
				+ "Copyright (c) 2006 Steven Ring "
				+ "(steven@southsky.com.au)\n"
				+ "LEM comes with ABSOLUTELY NO WARRANTY. "
				+ "This is free software, and you are welcome "
				+ "to redistribute it under the conditions "
				+ "of the GNU General Public Licence " + "version 2.\n";

		System.out.println(msg);
	}

	/**
	 * Return the revision string
	 * 
	 * @return the revision string
	 */
	public static String getRevision() {

		String revision = LemParser.getRevision();
		String[] substrings = revision.split(" ");
		return (substrings[1]);
	}

	/**
	 * Check parameters
	 */
	private static void checkOptions(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-s")) {
			} else
				filename = args[i];
		}
	}

	/**
	 * Print a usage message
	 * 
	 * @param aMessage
	 *            to be displayed
	 */
	private static void usage(String aMessage) {

		if (null != aMessage) {
			System.err.println("\n" + aMessage + "\n");
		}

		// now the usage text

		System.err.println("Usage:\n");
		System.err.println("java Lem <options> [model_filename]\n");
		System.err.println("options:");
		System.err.println("   -s for syntax only check");
		System.err
				.println("If a model filename is omitted, Lem will read from standard input");
	}

	public LEMModelDeclaration getRootNode() {
		return rootNode;
	}
}

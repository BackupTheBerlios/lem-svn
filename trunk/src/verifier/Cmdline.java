/**
 * Cmdline.java
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

package verifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import runtime.*;

import org.jdns.xtuml.metamodel.LemException;
import org.jdns.xtuml.metamodel.Model;
import org.jdns.xtuml.metamodel.Scenario;
import org.jdns.xtuml.parser.ParseException;

import tools.Lem;

/**
 * Allows the user to load a LEM model from the command line, has methods: Cmdline 
 * and loadModel
 *
 * @author  Nick Piggin
 */
public class Cmdline {
    
    File workingDirectory = null;
    
    /**
     * Load a model from the given file and report any compilation errors.
     *
     * @param file The name of the file to be loaded
     * 
     */
    public Cmdline(String file) {
       
	Model m = null;
        try {
            File selectedFile = new File(file);
            m = loadModel( selectedFile );
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        } catch( ParseException pe ) {
            System.out.println("Parse Error: " + pe.getMessage());
        } catch( LemException le ) {
            System.out.println("Parse Error: " + le.getMessage());
        } catch( IOException ioe ) {
            System.out.println("I/O Error: " + ioe.getMessage());
        }
    }

    /**
     * Creates new form Cmdline 
     *
     * @param file The name of the file to be loaded
     * @param domain The domain context 
     * @param scenario The scenario to be run
     * 
     */
    public Cmdline(String file, String domain, String scenario) {
       
	Model m = null;
        try {
            File selectedFile = new File(file);
            m = loadModel( selectedFile );
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found");
        } catch( ParseException pe ) {
            System.out.println("Parse Error: " + pe.getMessage());
        } catch( LemException le ) {
            System.out.println("Parse Error: " + le.getMessage());
        } catch( IOException ioe ) {
            System.out.println("I/O Error: " + ioe.getMessage());
        }

	DomainContext c = new DomainContext();
	ConsoleLogger logger = new ConsoleLogger( c );
	Interpreter i = new Interpreter( null );

	Scenario s = m.getDomain( domain ).getScenario( scenario );

	if( s == null ) {
		System.err.println( "Couldn't find the named scenario.. sorry!" );
		System.exit( 1 );
	}

	// Kick off the scenario
	c.getDebugObject().runModel();

	try {
		i.interpret( s, c );
	} catch( LemRuntimeException e ) {
		System.err.println( "Got a LemRuntimeException!" );
		e.printStackTrace();
	}

	// wait for a quiet state before quitting
	c.getDebugObject().waitQuiescentState();
    }
    
    /**
     * This method loads a LEM model specified by the parameter, and returns true
     *if model loaded succesfully and false otherwise
     *
     *@param modelFile name of the LEM model file.
     *@return true if model loaded succesfully and false otherweise
     */
    
    public Model loadModel( File modelFile ) throws FileNotFoundException, ParseException, LemException, IOException {
        Lem l = new Lem();
        FileInputStream fis = new FileInputStream( modelFile );
        
        return l.parse( fis );
    }
    
    /**
     * Determines if the user has supplied the correct information and number of parameters
     * and creates a new CmdLine
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        if (args.length == 1)
	    new Cmdline(args[0]);
        else if (args.length == 3)
            new Cmdline(args[0], args[1], args[2]);
	else
            System.out.println("Incorrect usage");
    }
}

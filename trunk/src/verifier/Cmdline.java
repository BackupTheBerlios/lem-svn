/**
 * Cmdline.java
 *
 * Simple command line based model loader. Used for testing.
 */

package verifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Vector;
import runtime.*;

import metamodel.LemException;
import metamodel.Model;
import metamodel.Scenario;

import parser.ParseException;
import tools.Lem;

import java.io.*;


/**
 *
 * @author  Nick Piggin
 */
public class Cmdline {
    
    File workingDirectory = null;
    
    /** Creates new form Cmdline */
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

	try {
		i.interpret( s, c );
	} catch( LemRuntimeException e ) {
		System.err.println( "Got a LemRuntimeException!" );
		e.printStackTrace();
	}
	
    }
    
    /*This method loads a LEM model specified by the parameter, and returns true
     *if model loaded succesfully and false otherwise
     *@param modelFile name of the LEM model file.
     *@return true if model loaded succesfully and false otherweise
     */
    
    public Model loadModel( File modelFile ) throws FileNotFoundException, ParseException, LemException, IOException {
        Lem l = new Lem();
        FileInputStream fis = new FileInputStream( modelFile );
        
        return l.parse( fis );
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        if (args.length != 3)
            System.out.println("Incorrect usage");
        else
            new Cmdline(args[0], args[1], args[2]);
    }
}

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
import metamodel.LemException;
import metamodel.Model;
import parser.ParseException;
import tools.Lem;

import java.io.*;


/**
 *
 * @author  Nick Piggin
 */
public class Cmdline {
    
    Vector models = new Vector();
    File workingDirectory = null;
    
    /** Creates new form Cmdline */
    public Cmdline(String file) {
        
        try {
		File selectedFile = new File(file);
        	Model m = loadModel( selectedFile );
                if( m != null )
                    models.add( m );
        } catch (FileNotFoundException fnfe) {
		System.out.println("File not found");
	} catch( ParseException pe ) {
                System.out.println("Parse Error: " + pe.getMessage());
        } catch( LemException le ) {
                System.out.println("Parse Error: " + le.getMessage());
        } catch( IOException ioe ) {
                System.out.println("I/OO Error: " + ioe.getMessage());
        }
        
/*        metamodel.Procedure p = m.getDomain( "Publications" ).getClass( "Manuscript" ).getStateMachine().getState("Adding").getProcedure();
        runtime.ModelInstance i = new runtime.ModelInstance();
        
        p.execute(i); */
    }

    public Model loadModel( File modelFile ) throws FileNotFoundException, ParseException, LemException, IOException {
        Lem l = new Lem();
        FileInputStream fis = new FileInputStream( modelFile );
        
        return l.parse( fis );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
	if (args.length != 1)
		System.out.println("Incorrect usage");
	else
		new Cmdline(args[0]);
    }
}

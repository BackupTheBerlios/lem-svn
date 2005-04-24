/*
 * Lem.java
 *
 * Created on October 29, 2004, 3:45 PM
 */

package tools;

import metamodel.*;
import verifier.*;
import parser.*;
import util.*;
import java.io.*;
import java.net.URL;


/**
 * A command-line parser for LEM-coded models - LEM: a Language for Executable Models. 
 * <p>
 * LEM allows xtUML models to be expressed in a text-based language. This LEM parser
 * is the first of a series of tools designed to allow models to be verified and translated into code.
 * <p>
 * The LEM parser
 * is a JAVA program which parses the LEM input file (according to the formal grammar) and builds
 * an in-memory graph representing the xtUML model defined by the LEM input file. This software is
 * designed to form the basis of a suite of Open Source tools supporting xtUML.
 * <p>
 * Copyright (c) 2004, Steven Ring; steven@southsky.com.au.<br>All rights reserved pending release
 * under a suitable Open Source license.
 * <p>
 * <h2>Acknowledgements</h2>
 * <ol>
 * <li>Emma Margaret Walker, <i>A language for the specification of Shlaer-Mellor object-oriented
 * analysis models</i><br>ANU, BSEng Honours thesis, November 2002 </li>
 * <li>Leon Starr, <i>Metamodel for xtUML, 2002;</i> upon which much of the JAVA object model is based</li>
 * <li>Shayne Flint, instrumental in providing ongoing support and direction</li>
 * </ol>
 *
 *
 * <p>
 * Usage:<br>
 * <pre>
 * java -jar lem.jar model_File_Path
 * </pre>
 *
 * @author  Steven Ring
 */
public class Lem {
    
    private static boolean semanticCheck = true;
    private static String filename = null;
    private static SourceBuffer source = null;
    
    
    private /*parser.*/Token errorToken = null;
    public Throwable errorException = null;
    public String errorMessage = "";
    
    public static void main(String args[]) {
        
        
        Model model = null;
        
        // print a copyright notice
        
        copyrightNotice();
        checkOptions( args );
        
        // create a LemParser to parse the input file
        
        Lem parser = null;
        try {
            
            parser = new Lem();
            model = parser.parse( getInputStream( args ) );
            
            // if no exceptions were thrown, then the input stream is valid LEM
            
            System.err.println("Input file is VALID LEM.");
            
            // all done ... bit of an anti-climax really!
            
        } catch ( ParseException e) {

            System.err.println("Doh! ... got " + e.getClass().getName() );
            
            /*parser.*/Token token = e.currentToken.next;
            printSourceLine( token );
            System.err.println(e.getMessage());


        } catch ( LemException e ) {

            System.err.println("Oops! ... got " + e.getClass().getName() );
            
            parser.Token token = e.getToken();
            printSourceLine( token );
            System.err.println(e.getMessage());
            System.err.println( e.getExplanation() );

        } catch (Throwable e) {
            
            // report any exception thrown during LEM parsing or model building
            
            System.err.println("Ouch! ... got " + e.getClass().getName() );
            System.err.println(e.getMessage());
            e.printStackTrace();
            usage( null );
        }
        
        System.exit( 0 );
    }
    
    /**
     * Return a parsed model based on the supplied LEM input stream
     *
     * @param lemStream the model expressed in LEM
     * @return the model as an instatiated graph of metamodel object instances
     * @throws Throwable in the event of an error. A ParseException is thrown
     * if parsing fails, a LemException is thrown if the model fails is semantic
     * checking fails. Any Throwable may be thrown in the event of a program bug (this
     * is a "work in progress" after all!)
     */
    public Model parse( InputStream lemStream ) throws ParseException, LemException, IOException {
        
        // read the source into a SourceBuffer
        
        source = new SourceBuffer( lemStream );
        
        Model model = null;
        LemParser parser = new LemParser( source.getInputStream() );
        
        try {
            
            model = parser.parse();
            
            
            
        } catch ( ParseException e) {
            
            errorException = e;
            errorToken = e.currentToken.next;
            buildErrorSourceLine();
            throw e;
            
        } catch ( LemException e ) {
            
            errorException = e;
            errorToken = e.getToken();
            buildErrorSourceLine();
            throw e;
            
        }
        
        return model;
    }
    
    
    private static void printSourceLine( Token t ) {
        int line = 0;
        int col = 0;

        System.err.print("In file " + filename );
        if ( t != null ) {
            line = t.beginLine;
            col = t.beginColumn;
            String sourceLine = source.getLine( line-1 );
            System.err.println(" at line " + line +", column " + col );
            System.err.println( sourceLine );

            // now print a pointer to the position
            
            StringBuffer sb = new StringBuffer();
            for ( int i = 0; i < col-1; i++ )
                sb.append( " " );
            sb.append( "|" );
            System.err.println( sb.toString() );

        } else {
            System.err.println( "" );
        }
    }
    
    
    
    
    /**
     * Build the error message using the errorException and
     * errorToken
     */
    private void buildErrorSourceLine() {
        int line = 0;
        int col = 0;
        StringBuffer s = new StringBuffer();
        
        s.append( "In file " + filename + "\n" );
        if ( errorToken != null ) {
            line = errorToken.beginLine;
            col = errorToken.beginColumn;
            String sourceLine = source.getLine( line-1 );
            s.append(" at line " + line +", column " + col + "\n" );
            s.append( sourceLine + "\n" );
            
            // now print a pointer to the position
            
            StringBuffer sb = new StringBuffer();
            for ( int i = 0; i < col-1; i++ )
                sb.append( " " );
            sb.append( "|" );
            s.append( sb.toString() + "\n" );
            
        } else {
            s.append( "\n" );
        }
        
        s.append(errorException.getMessage() + "\n" );
        if ( errorException instanceof LemException ) {
            LemException le = (LemException) errorException;
            s.append( le.getExplanation() + "\n" );
        }
        
        // store the resulting error message
        
        errorMessage = s.toString();
        
    }
    
    
    
    /**
     * Return the input stream determined from supplied parameter line arguments
     *
     * @param args the command line arguments
     * @return an InputStream containing the model to be parsed
     */
    private static InputStream getInputStream( String [] args ) throws Exception {
        // select the input stream to read from
        
        InputStream in = null;
        
        // read from file if filename is provided
        
        if ( filename != null ) {
            URL u = null;
            try {
                u = new URL( filename );
            } catch( java.net.MalformedURLException m ) {
                u = null;
            }
            
            try {
                if( u == null ) {
                    // Try opening the file with the given filename
                    in = new FileInputStream( filename );
                } else {
                    in = u.openStream();
                }
            } catch( IOException e ) {
                System.err.println( "There was an error " 
                        + (u == null ? "reading from the given file" : "opening the given URL")
                        + ": " + e.getMessage());
                usage( null );
                System.exit( 1 );
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
        
        System.err.println("LEM - a language for executable models" );
        System.err.println( LemParser.getRelease() + " (rev: " + getRevision() + ")" );
        System.err.println( "Copyright (c) 2004 Steven Ring (steven@southsky.com.au)\n" +
                            "All rights reserved pending release by ANU under an Open Source license.\n" );
    }
    
    /**
     * Return the revision string
     *
     * @return the revision string
     */
    public static String getRevision() {
        
        String revision = parser.LemParser.getRevision();
        String [] substrings = revision.split( " " );
        return( substrings[1] );
    }
    
    /**
     * Check parameters
     */
    private static void checkOptions( String [] args ) {
        for( int i = 0; i < args.length ; i++ ) {
            if (args[i].equals("-s") )
                semanticCheck = false;
            else
                filename = args[i];
            
        }
    }
    
    
    /**
     * Print a usage message
     *
     * @param aMessage to be displayed
     */
    private static void usage( String aMessage ) {
        
        if ( null != aMessage ) {
            System.err.println( "\n" + aMessage +"\n" );
        }
        
        // now the usage text
        
        System.err.println( "Usage:\n" );
        System.err.println( "java Lem <options> [model_filename]\n" );
        System.err.println( "options:" );
        System.err.println( "   -s for syntax only check" );
        System.err.println( "If a model filename is omitted, Lem will read from standard input" );
    }
}

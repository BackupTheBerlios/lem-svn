/*
 * ValueObjectGenerator.java
 *
 * Created on October 29, 2004, 9:46 AM
 */

package test.perlgen;

import org.jdns.xtuml.metamodel.Attribute;
import org.jdns.xtuml.metamodel.Domain;
import org.jdns.xtuml.metamodel.Model;
import tools.*;
import parser.*;
import metamodel.*;
import java.util.*;
import java.io.*;

/**
 * Create PERL value objects from a LEM model supplied on standard in
 *
 * @author  smr
 */
public class ValueObjectGenerator {
    
    /** Creates a new instance of ValueObjectGenerator */
    public ValueObjectGenerator() {
    }
    
    public static void main( String [] args ) {
        
        // parse the supplied model
        
        Lem parser = null;
        try {
            parser = new Lem();
            Model model = parser.parse( System.in );

			listDomains( model );
            
        } catch ( Exception e) {

            System.err.println( "Doh! ... got " + e.getClass().getName() );
            System.err.println( parser.errorMessage );
			System.err.println( parser.errorMessage );
            e.printStackTrace();


        } catch (Throwable e) {

            // report any exception thrown during LEM parsing or model building
            
            System.err.println("Ouch! ... got " + e.getClass().getName() );
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

	/**
	 * List some elements
	 */
	private static void listDomains( Model model ) throws IOException {

		HashMap domains = model.getDomains();

		System.out.println( "Model is called: " + model.getName());
		for ( Iterator it = domains.values().iterator(); it.hasNext(); ) {
			Domain domain = (Domain) it.next();
			listClasses( domain );
		}
	}
			
    
	/**
	 * List some classes
	 */
	private static void listClasses( Domain domain ) throws IOException {

		HashMap classes = domain.getClasses();
              
                
                File outDir = new File( "/tmp/");
		System.out.println( "Domain is called: " + domain.getName());
		for ( Iterator it = classes.values().iterator(); it.hasNext(); ) {
			org.jdns.xtuml.metamodel.Class theClass = (org.jdns.xtuml.metamodel.Class) it.next();
			listAttributes( theClass );
                        buildPackage( theClass, outDir );
		}
	}
        
        
        /**
	 * List the attributes
	 */
	private static void listAttributes( org.jdns.xtuml.metamodel.Class theClass ) {

		HashMap attributes = theClass.getAllAttributes();

		System.out.println( "Class is " + theClass.getName() );
               
                
		for ( Iterator it = attributes.values().iterator(); it.hasNext(); ) {
			Attribute attribute = (Attribute) it.next();
			System.out.println( "  attribute: " + attribute.getName() );
		}
	}
        
        private static void buildPackage( org.jdns.xtuml.metamodel.Class theClass, File outDir )
            throws IOException {
            
            
            // create the file for the output source
            
            String className = theClass.getName();
           /* 
            
            PrintWriter out = new PrintWriter( outDir.getAbsolutePath() + "/" + className + ".pm" );
            
            out.println( "use Object;");
            out.println( "use XMLserV2;");
            out.println( "use Carp;");
            
            
            
            out.println("package " + className +";");
            
            out.println( "@ISA = qw( Object XMLserV2 );");
            out.println( "Ser::registerClass( \"" + className +"\" );" );

            out.println( "\n# Attribute definitions\n");
            
            HashMap attributes = theClass.getAttributes();
            out.println( "my %fields = (" );
            for ( Iterator it = attributes.values().iterator(); it.hasNext(); ) {
		Attribute attribute = (Attribute) it.next();
		out.print( "    " + attribute.getName() + " => undef" );
                if ( it.hasNext() )
                    out.println( "," );
            }
            out.println( "\n);");
            
            // standard PERL constructor
            
            out.println( "\n# Default constructor\n");
            out.println( 
                "sub new {\n" +
                "    my $class = shift;\n" +
                "    my $self = { %fields };\n" +
                "    bless $self, $class;\n" +
                "    return $self;\n" +
                "}");
            
            // close the source file
            
            out.flush();
            out.close();
	    */
        }
	
}

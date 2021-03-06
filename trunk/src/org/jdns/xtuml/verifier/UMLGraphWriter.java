/*
 * UMLGraphWriter.java
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

package org.jdns.xtuml.verifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Wrapper class for the UMLGraph graph generation doclet.
 *
 * @author Toshio Nakamura
 */
public class UMLGraphWriter {
    
    /**
     * Converts a Java class specification in UMLGraph format into a dot file.
     * See the UMLGraph documentation for a detailed description of the class
     * specification format
     *
     * @param spec the Java class specification, in UMLGraph format
     * @return a UML diagram in dot format
     * @todo make routine throw exception and not depend on internal try blocks
     */
    public static String specToDot( String spec ) throws IOException {
        Runtime rt = Runtime.getRuntime();
        Process p = null;
        OutputStreamWriter out = null;
        InputStreamReader in = null;
        InputStreamReader err = null;
        BufferedReader bufRead = null;
        StringBuffer strBuf = new StringBuffer();
        StringBuffer errBuf = new StringBuffer();
        Parameters params = Parameters.getInstance();
        int c;
                
        // Temporary file to store the input specification
        File specFile = null;
        try {
            specFile = File.createTempFile( "umlSpec", ".java" );
            // Write the specification to the file
            FileWriter fw = new FileWriter( specFile );
            fw.write( spec );
            fw.write( "\n" );   // Trailing newline
            fw.close();
        } catch (IOException ioe) {
            System.err.println( "Error creating temporary file.");
            ioe.printStackTrace();
        }
        
        // Temporary file to store the generated dot code
        File dotFile = null;
        try {
            dotFile = File.createTempFile( "eleminator", ".dot" );
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        // Run Javadoc over the specification and return the resulting dot code
        
        String javadocPath =
                params.getProperty( "eleminator.javadocPath" );
        
        String[] cmdString = { javadocPath,
                "-docletpath", params.getProperty( "eleminator.umlgraphPath" ),
                "-doclet", params.getProperty( "eleminator.umlgraphDocletName" ),
                "-output", dotFile.getAbsolutePath(),
                "-private", specFile.getAbsolutePath()
        };
        
        p = rt.exec( cmdString );
        in = new InputStreamReader( p.getInputStream() );
        err = new InputStreamReader( p.getErrorStream() );
        while (true) {
            c = in.read();
            if (c == -1) {
                break;
            }
            strBuf.append( (char)c );
        }
        while (true) {
            c = err.read();
            if (c == -1) {
                break;
            }
            errBuf.append( (char)c );
        }
        // START DEBUG
        //System.out.println( strBuf.toString() );
        //System.err.println( errBuf.toString() );
        // END DEBUG
        
        // Read and return the generated dot
        strBuf = new StringBuffer();
        FileReader fr = new FileReader( dotFile );
        while (true) {
            c = fr.read();
            if (c == -1) {
                break;
            }
            strBuf.append( (char)c ); 
        }
        
        return strBuf.toString();
    }
}

/*
 * SourceBuffer.java
 *
 * Created on September 20, 2004, 5:14 PM
 */

package util;

import java.util.*;
import java.io.*;

/**
 * Stores the lem source in a buffer. The buffer can be accessed as a single byte array or
 * as array of source lines.  
 *
 * At the moment, this class is used solely to allow the source line to be displayed
 * in the event of a parse error. The class is somewhat of a sledge hammer and the problem
 * a very small nut.  More efficient implementations are encouraged.
 *
 *
 * @author  smr
 */
public class SourceBuffer {
  
    /** the buffer in which the source code is stored */
    private byte[] buffer = null;
    
    /** a vector of indicies supporting line by line access to the buffer */
    private ArrayList lines = new ArrayList();
    
    /** bytes in source file */
    private int byteCount = 0;
    
    /** lines in source file */
    private int lineCount = 0;
    
    /** Creates a new instance of SourceBuffer */
    public SourceBuffer( InputStream in ) throws IOException {
        readLines( in );
    }
    
    public SourceBuffer( Reader r ) throws IOException {
        readLines( r );
    }
    
    private void readLines( InputStream in ) throws IOException {
        readLines( new InputStreamReader( in ));
    }
    
    private void readLines( Reader r ) throws IOException {
        
        BufferedReader br = new BufferedReader( r );
        
        String line = null;
        while( ( line = br.readLine() ) != null ) {
            lines.add( line );
            lineCount++;
            byteCount += line.length() + 1;
        }
        br.close();
        
        // construct the buffer 
        
        buffer = new byte[ byteCount ];
        
        // and fill it with source bytes
        
        int eob = 0;        // end of buffer
        for ( int i = 0; i < lineCount; i++ ) {
            
            // get line from buffer
            
            line = (String) lines.get( i );
            
            // set the line pointer to position in the buffer where this line begins
            
            lines.set( i, new Integer( eob ));
            
            // add the bytes for this line to the end of the buffer
            
            byte [] bytes = line.getBytes();
            System.arraycopy( bytes, 0, buffer, eob, bytes.length );
            
            // update the end of buffer pointer
            
            eob += bytes.length + 1;
            buffer[eob-1] = '\n';
            
        }
        
        
    }
     
    /**
     * Return the source line at the supplied position
     * 
     * @param index of the line to be returned (commencing from line 0)
     * @return the source code line or null if index > lineCount
     */
    public String getLine( int index ) {
        
        if ( index > lineCount-1 ) 
            return null;
        
        int offset = ((Integer) lines.get( index )).intValue();
        int length = 0;
        byte b = 0;
        while ( (b = buffer[offset + length] ) != 10 ) {
            length ++;
        }
        
        return new String( buffer, offset, length );
    }
    
    
    /** 
     * Return an Input Stream on the source buffer
     *
     * @return an InputStream from which the source code can be read
     */
    public InputStream getInputStream() {
  
        return new ByteArrayInputStream( buffer );
   
    }
}

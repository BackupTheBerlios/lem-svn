/*
 * SourceReference.java
 *
 * Created on April 26, 2004, 5:51 AM
 */

package metamodel;
/**
 * Defines a specific location in source code
 *
 * @author  smr
 */
public class SourceReference {
    
    /** the file in which the source is contained */
    private String filePath = "";
    
    /** the line number of the specific source */
    private int lineNo = 0;
    
    /** the character position within the line, starting from 1 */
    private int columnNo = 0;
    
    /** 
     * Creates a new instance of SourceReference given required values 
     *
     * @param fileName of source file
     * @param line number of reference
     * @param column number of reference
     */
    public SourceReference( String fileName, int line, int column ) {
        
        filePath = fileName;
        lineNo = line;
        columnNo = column;
    }
    
    /**
     * Return a string describing the source reference
     *
     * @return a string describing the source reference
     */
    public String toString() {
        
        StringBuffer sb = new StringBuffer();
        
        if ( filePath.length() > 0 ) {
            sb.append( "in file '" );
            sb.append( filePath );
            sb.append( "' " );
        }
        
        sb.append( "at line " + lineNo );
        
        if ( columnNo > 0 ) {
            sb.append( ", at column " + columnNo );
        }
        
        return sb.toString();
    }
}

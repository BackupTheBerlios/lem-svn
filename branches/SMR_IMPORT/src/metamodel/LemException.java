/*
 * LemException.java
 *
 * Created on December 6, 2003, 5:03 PM
 */

package metamodel;

import parser.Token;
import java.util.*;
import java.io.*;

/**
 * A general LEM exception
 *
 * @author  sm0r
 */
public class LemException extends Exception  {
    
    /** 
     * the id of the semantic check that generated the error. 
     * see file verifier.SemanticChecks for a full list of checks 
     */
    private String checkId  = null;
    
    /** the token that was being processed when the exception occured */
    private Token token = null;
    
    /**
     * Creates a new instance of <code>LemException</code> without detail message.
     */
    public LemException() {
    }
    
    
    /**
     * Constructs an instance of <code>LemException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public LemException(String msg) {
        super(msg);
    }
    
    /**
     * Constructs an instance of <code>LemException</code> with the specified detail message.
     *
     * @param msg the detail message.
     * @param token that caused the error
     * @param checkId of the semantic check that raised the error
     */
    public LemException(String msg, Token token, String checkId ) {
        super(msg);
        this.token = token;
        this.checkId = checkId;
    }

    
    /**
     * Return the id of the semantic check that generated the error
     *
     * @return the id of the semantic check that generated the error
     */
    public String getCheckId() {
        return checkId;
    }

    
    /**
     * Return the Token that generated the error
     *
     * @return the Token that generated the erro
     */
    public parser.Token getToken() {
        return token;
    }
    
    /**
     * Return a full explanation of the exception based on the checkId
     *
     * @return a full explanation of the exception
     */
    public String getExplanation() {
        StringBuffer sb = new StringBuffer();
        
        if ( checkId != null ) {
            sb.append( checkId );
            sb.append( ": " );
            
            PropertyResourceBundle bundle = (PropertyResourceBundle) ResourceBundle.getBundle( "SemanticChecks" );
            String exp = bundle.getString( checkId );
            sb.append( exp );
            
        }
        
        return sb.toString();
    }
}

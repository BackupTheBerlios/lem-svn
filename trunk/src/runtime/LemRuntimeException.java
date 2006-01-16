/*
 * LemRuntimeException.java
 */

package runtime;

import org.jdns.xtuml.parser.Token;

/**
 * A general LEM runtime exception. It is expected that instances of this class
 * will be thrown when something bad happens in a running model (eg, if an
 * association constraint is violated).
 *
 * @author  sjr
 */
public class LemRuntimeException extends Exception  {
    
    /**
     * Constructs an instance of <code>LemRuntimeException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public LemRuntimeException(String msg) {
        super(msg);
    }
}

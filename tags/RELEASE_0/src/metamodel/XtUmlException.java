/*
 * XtUmlException.java
 *
 * Created on December 6, 2003, 5:03 PM
 */

package metamodel;
/**
 * A general xtUML exception
 *
 * @author  smr
 */
public class XtUmlException extends parser.ParseException {
    
    /**
     * Creates a new instance of <code>XtUmlException</code> without detail message.
     */
    public XtUmlException() {
    }
    
    
    /**
     * Constructs an instance of <code>XtUmlException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public XtUmlException(String msg) {
        super(msg);
    }
}

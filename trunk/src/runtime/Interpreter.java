/*
 * Interpreter.java
 */

package runtime;
import metamodel.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This is the main interpreter file.
 * @author npiggin
 */
public class Interpreter {
    /** Creates a new instance of Interpreter */
    public Interpreter() {
    }

    public void interpret(Procedure p, Context c) throws LemRuntimeException {
	    executeProcedure(p, c);
    }
    
    public void executeProcedure ( Procedure p, Context c ) throws LemRuntimeException {
	LinkedList actions = p.getActions();
        Iterator i = actions.iterator();
        
        while( i.hasNext() ) {
            Action a = (Action)i.next();
	    executeAction(a, c);
        }
    }

    public void executeAction ( Action a, Context c ) throws LemRuntimeException {
	if ( a instanceof CreateAction )
		executeCreateAction((CreateAction)a, c);
	else {
		throw new LemRuntimeException ("executeAction encountered unknown action");
	}
    }

    public runtime.Object executeCreateAction( CreateAction a, Context c ) throws LemRuntimeException {
	// Create the new object
        runtime.Object o = new runtime.Object( a.getClasses() );
	    
        // Add it to the context
        c.addObject(o);
	    
        // Notify listeners that the object has been added
        LemObjectCreationEvent e = new LemObjectCreationEvent( o, a );
        for( Iterator i = c.getLemEventListeners().iterator(); i.hasNext(); ) {
        	LemEventListener l = (LemEventListener)i.next();
                l.objectCreated(e);
         }
	    
	 return o;
    }
}

/*
 * ClassNode.java
 *
 * Created on April 24, 2005, 3:54 PM
 */

package verifier;

import java.util.Iterator;
import metamodel.StateMachine;

/**
 *
 * @author sjr
 */
public class ClassNode extends AbstractDescriptionNode {
    
    metamodel.Class thisClass;
    
    /** Creates a new instance of ClassNode */
    public ClassNode( metamodel.Class c ) {
        this.thisClass = c;
        
        Iterator i = c.getAllAttributes().values().iterator();
        
        while( i.hasNext() ) {
            add( new AttributeNode( (metamodel.Attribute) i.next() ));
        }
        
        StateMachine m = thisClass.getStateMachine();
        if( m != null ) {
            add( new StateMachineNode( m ));
        }
    }
    
    public String toString() {
        return "Class " + thisClass.getName();
    }
    
    public String getDescription(){
        return thisClass.getDescription();
    }
}

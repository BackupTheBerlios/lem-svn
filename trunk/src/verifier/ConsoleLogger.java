package verifier;

import java.util.Iterator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

class ConsoleLogger implements runtime.LemEventListener {
    
    static Logger logger = Logger.getRootLogger();
    private int counter = 0;

    public ConsoleLogger(runtime.Context c) {
	c.addLemEventListener(this);
	PropertyConfigurator.configure("eventLog.txt");
	
    }

     public void levelDebug() {
	 //logger.setLevel(Level.DEBUG);
    }

    public void levelInfo(){
	//logger.setLevel(Level.INFO);
    }
    
//    public void objectCreated(ObjectObject ob) {
//
//	// this might not be the way this should be done it may have to
//	// be: ob.state.name ?
//	String message = " OC: (" + ob.name() + ") [" + ob.state() + "]";
//
//	logger.debug(counter + message);
//	counter ++;
//    }

    public void objectCreated(runtime.LemObjectCreationEvent event) {
        // this might not be the way this should be done it may have to
	// be: ob.state.name ?
        runtime.Object o = event.getCreatedObject();
	String message = " New Object created, instance(s): ";
        for( Iterator i = o.getInstances().iterator(); i.hasNext(); ) {
            runtime.Instance in = (runtime.Instance)i.next();
            
            message = message + in.getInstanceClass().getName() + ", ";
        }

	logger.debug(counter + message);
	counter ++;
    }
}

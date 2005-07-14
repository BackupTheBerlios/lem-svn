/*
 * JContextLoggerPanelTest.java
 * JUnit based test
 *
 * Created on 14 July 2005, 03:10
 */
package verifier;

import java.util.Vector;
import metamodel.ActionBlock;
import metamodel.CreateAction;
import metamodel.Procedure;
import metamodel.VariableReference;
import metamodel.Class;
import runtime.DomainContext;
import runtime.LemRuntimeException;
import runtime.Interpreter;
import javax.swing.JFrame;
/**
 *
 * @author David Gavin
 */
public class JContextLoggerPanelTest extends junit.framework.TestCase {
   Procedure p = new Procedure();
   ActionBlock aB = new ActionBlock();
   CreateAction a = new CreateAction();
   Class newClass = new Class();
   DomainContext d = new DomainContext();
   JFrame frame = new JFrame();
   JContextLoggerPanel Logger = new JContextLoggerPanel(d);
   Interpreter I = new Interpreter();
   
    public JContextLoggerPanelTest(String testName) {
        super(testName);
        newClass.setName("newClass");
        Vector c = new Vector();
        c.add(newClass);
        a.setClasses(c);
        aB.addAction(a);
        p.setActionBlock(aB);
        frame.getContentPane().add(Logger, java.awt.BorderLayout.CENTER);
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void testCreateObject(){
        try{
            I.interpret(p, d);
        }
        catch(LemRuntimeException e)
        {
            System.out.println(e);
        }
    }
    
}

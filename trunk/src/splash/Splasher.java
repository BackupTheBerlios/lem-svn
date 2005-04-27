/*
 * SplashWindow.java
 *
 * Created on April 18, 2005, 5:43 PM
 */

package splash;


/**
 *
 * @author  David Gavin
 */

public class Splasher {
    public static void main(String[] args) {
          SplashWindow spl;
          spl = new SplashWindow("c:/xtuml/src/img/eLEMinator.jpg", new java.awt.Frame(), 3000); //Insert splash image here
          spl.invokeMain("verifier.Eleminator", args); //Insert target class here
    }
}

/*
 * SplashWindow.java
 *
 * Created on April 18, 2005, 5:43 PM
 */

package splash;


/**
 * Splash window for eLEMinator application.
 * @author  David Gavin
 */

public class Splasher {
    /**
     * Creates a new instance of Splasher. Splasher contains the amount of time
     * that the splash window should be displayed (2000), the target class to be
     * run (verifier.Eleminator) and the target image to be displayed (splash/eLEMinator.jpg).
     */
    public static void main(String[] args) {
          SplashWindow spl;
          spl = new SplashWindow("splash/eLEMinator.jpg", new java.awt.Frame(), 2000); //Insert splash image here
          spl.invokeMain("verifier.Eleminator", args); //Insert target class here
    }
}

/*
 * SplashWindow.java
 *
 * Created on April 18, 2005, 5:43 PM
 *
 * Copyright (C) 2005 David Gavin
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 */

package splash;


/**
 * Splash window for eLEMinator application.
 * @author  David Gavin
 */

public class Splasher {
    /**
     * Creates a new instance of Splasher. 
     */
    public Splasher()
    {}
    /**
     * Splasher contains the amount of time that the splash window should be 
     * displayed (2000), the target class to be run (verifier.Eleminator) and 
     * the target image to be displayed (splash/eLEMinator.jpg).
     * @param args command line arguments
     */
    public static void main(String[] args) {
          SplashWindow spl;
          spl = new SplashWindow("splash/eLEMinator.jpg", new java.awt.Frame(), 2000); //Insert splash image here
          spl.invokeMain("verifier.Eleminator", args); //Insert target class here
    }
}

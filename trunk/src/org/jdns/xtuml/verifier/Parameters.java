/*
 * Parameters.java
 *
 * Copyright (C) 2005 Steven Michael Ring
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

package org.jdns.xtuml.verifier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Manages eLEMinator's persistent properties.
 *
 * There are two types of properties: default properties and user properties.
 * Default properties (such as the program's version number or default
 * file locations) are set initially by the application developer. User
 * properties are set by the user when running the application. The Parameters
 * class is initialized with default properties. User properties are then
 * loaded. If a user property exists that has the same name as a default
 * property, then the default property is overriden by the user property -
 * unless the default property is requested explicitly by calling
 * getDefaultProperty().
 *
 * This class uses the Singleton pattern to ensure that at most one instance
 * of the class can exist at a time.
 *
 * For consistency, all eLEMinator property names should be of form
 * 'eleminator.<propertyname>'.  For example, 'eleminator.version' or
 * 'eleminator.diagram.dotPath'.
 *
 * @author Toshio Nakamura
 * @todo This should be implemented as a Singleton
 */
public class Parameters {
    
    static Parameters currentParams = new Parameters();
    
    /**
     * The name of the default resource bundle
     */
    String defaultBundleName = "eleminatorDefault";
    
    /**
     * The name of the resource bundle containing the user properties list
     */
    String userListBundleName = "userPropertiesList";
    
    /**
     * The default properties list
     */
    Properties defaultProps = null;
    
    /**
     * The user properties list
     */
    Properties userProps = null;
    
    /**
     * Loads the default and user properties files. If no user properties file exists,
     * a new default one is created. Note that the class constructor is private
     * to prevent arbitrary instantiation, following the Singleton pattern.
     * @todo currently a test stub only
     * @todo implement user properties
     * @todo remove debugging code
     * @todo break into two separate procedures?
     */
    private Parameters() {
        
        // Initialise default properties
        
        PropertyResourceBundle defaultBundle =
                (PropertyResourceBundle) ResourceBundle.getBundle( defaultBundleName );
        
        // Convert the ResourceBundle into a Properties set. This allows the default
        // properties to be easily set as a 'fallback' for the user properties, as
        // described in the class header comment.
        Enumeration keyList = defaultBundle.getKeys();
        String currentKey = null;
        defaultProps = new Properties();
        while (keyList.hasMoreElements()) {
            currentKey = (String)keyList.nextElement();
            defaultProps.setProperty( currentKey, defaultBundle.getString( currentKey ) );
        }
        
        // Initialise user properties
        
        // Get the user's home directory
        String baseDir = System.getProperty( "user.home" );
        
        // Get the path to the properties file
        
        // Two separate variables are required here due to the separate mkdir() and
        // createNewFile() calls that are required to create a new file and its
        // containing directories
        String userPropsDir = null; // Directory containing the properties file
        String userPropsPath = null; // Full path, including filename
        
        if (baseDir.charAt( baseDir.length() - 1) != System.getProperty( "file.separator" ).charAt( 0 )) {
            baseDir = baseDir + System.getProperty( "file.separator" );
        }
        userPropsDir = baseDir + this.getDefaultProperty( "eleminator.userPropertiesPath.linux" );
        // Get the full path to the file
        userPropsPath = userPropsDir + this.getDefaultProperty( "eleminator.userPropertiesFileName" );
        
        // If no user properties file exists, create a new properties file with a
        // list of default keys
        if (new File(userPropsPath).exists() == false) {
            System.err.println( "DIAG: Properties file not found. Creating..." );
            // Get the default keys
            PropertyResourceBundle defaultKeys =
                    (PropertyResourceBundle) ResourceBundle.getBundle( userListBundleName );
            Properties defaultUserProps = new Properties();
            keyList = defaultKeys.getKeys();
            while (keyList.hasMoreElements()) {
                defaultUserProps.setProperty( (String)keyList.nextElement(), "" );
            }
            // Write the default keys to a new user properties file
            try {
                new File( userPropsDir ).mkdirs();
                new File( userPropsPath ).createNewFile();
                FileOutputStream out = new FileOutputStream( userPropsPath );
                defaultUserProps.store( out, "user properties file" );
            } catch (FileNotFoundException fnfe) {
                System.err.println( "Error: Unable to find user properties file" );
                fnfe.printStackTrace();
            } catch (IOException ioe) {
                System.err.println( "Error: Unable to save user properties file" );
                ioe.printStackTrace();
            }
        }
        
        // Load the user properties file
        userProps = new Properties( defaultProps );
        FileInputStream in = null;
        
        try {
            in = new FileInputStream( userPropsPath );
            userProps.load( in );
        } catch (FileNotFoundException fnfe) {
            System.err.println( "Error: Unable to find user properties file" );
            fnfe.printStackTrace();
        } catch (IOException ioe ) {
            System.err.println( "Error: Unable to load user properties file" );
            ioe.printStackTrace();
        }
    }
    
    /**
     * Returns an instance of the Parameters class. This ensures that at most
     * one instance of Parameters exists at a time, in accordance with the
     * Singleton pattern.
     */
    public static Parameters getInstance() {
        return currentParams;
    }
    
    /**
     * Get the property corresponding to a particular key. First searches user
     * properties. If the property is not found there, the default properties
     * are searched. Returns null if the property does not exist in either set.
     *
     * @param key the name of the property to retrieve
     */
    public String getProperty( String key ) {
        return userProps.getProperty( key );
    }
    
    /**
     * Returns a default property corresponding to a particular key. Note that
     * user properties are never searched by this method, even if a user
     * property exists with the same name. Returns null if no corresponding
     * default properties are found.
     *
     * @param key the name of the default property to retrieve
     *
     * @todo remove debugging code
     */
    public String getDefaultProperty( String key ) {
        return defaultProps.getProperty( key );
    }
}
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

package verifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
 * getDefaultProperty()
 *
 * For consistency, all eLEMinator property names should be of form
 * 'eleminator.<propertyname>'.  For example, 'eleminator.version' or
 * 'eleminator.graphgen.dotlocation'.
 *
 * @author Toshio Nakamura
 * @todo This should be implemented as a Singleton
 */
public class Parameters {
	
	/**
	 * The location of the default properties file, relative to the xtuml base
	 * directory
	 */
	private static final String defaultPropertiesFile =
					"eleminator_default.properties";
	
	/**
	 * The default properties list
	 */
	private Properties defaultProps = null;
	
	/**
	 * The user properties list
	 */
	private Properties userProps = null;
	
	/**
	 * Loads the default and user properties files
	 * @todo currently a test stub only
	 * @todo implement user properties
	 */
	public Parameters() {
		// Load the default properties
		defaultProps = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream( defaultPropertiesFile );
			defaultProps.load( in );
		} catch (FileNotFoundException fnfe) {
			System.err.println( "Error: Could not find default properties file" );
			fnfe.printStackTrace();
		} catch (IOException ioe ) {
			System.err.println( "Error: Unable to load default properties file" );
			ioe.printStackTrace();
		}
		
		// Load the user properties file
		String userPropsFile = this.getDefaultProperty( "eleminator.userpropsfile" );
		userProps = new Properties( defaultProps );
		try {
			in = new FileInputStream( this.getProperty( "eleminator.userpropsfile" ) );
			userProps.load( in );
		} catch (FileNotFoundException fnfe) {
			System.err.println( "Error: Could not find default user file" );
			fnfe.printStackTrace();
		} catch (IOException ioe ) {
			System.err.println( "Error: Unable to load user properties file" );
			ioe.printStackTrace();
		}
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
	 */
	public String getDefaultProperty( String key ) {
		return defaultProps.getProperty( key );
	}
}
/*
 * ThemeItem.java
 *
 * Created on 4 May 2005, 01:56
 */

package verifier;

/**
 *
 * @author David Gavin
 */
public class ThemeItem {
    String name;
    String className;
    /** Creates a new instance of ThemeItem */
    public ThemeItem(String inName, String inClass) {
        name = inName;
        className = inClass;
    }
    
    /** Returns the name of the ThemeItem
     *@return String name of the ThemeItem.
     */
    public String toString()
    {
        return name;
    }
    
    /**Returns the className of the ThemeItem
     *@return String the className of the ThemeItem.
     */
    public String getClassName()
    {
        return className;
    }
}

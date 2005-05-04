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
public class ThemeItem extends Object {
    String Name;
    String ClassName;
    /** Creates a new instance of ThemeItem */
    public ThemeItem(String inName, String inClass) {
        Name = inName;
        ClassName = inClass;
    }
    public String toString()
    {
        return Name;
    }
    public String getClassName()
    {
        return ClassName;
    }
}

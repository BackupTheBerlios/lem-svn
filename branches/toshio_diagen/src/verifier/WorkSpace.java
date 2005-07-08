/*
 * WorkSpace.java
 *
 * Created on 2 May 2005, 06:31
 */

package verifier;

import java.io.Serializable;

/**
 *
 * @author Administrator
 */
public class WorkSpace implements Serializable{
    String CurrentWorkspace;
    /** Creates a new instance of WorkSpace */
    public WorkSpace(String inWorkspace) {
        CurrentWorkspace = inWorkspace;
    }
    /**Returns the CurrentWorkspace.
     *@return String the CurrentWorkspace.
     */
    public String getWorkSpace()
    {
        return CurrentWorkspace;
    }
    
}

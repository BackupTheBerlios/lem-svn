/*
 * SubsystemElement.java
 *
 * Created on January 31, 2004, 11:37 AM
 */

package metamodel;
/**
 * A subsystem element preserves a relationship to a Subsystem
 *
 * @author  smr
 */
public interface SubsystemElement {

    /** 
     * set the Subsytem to which this SubsystemElement belongs
     * 
     * @param theSubsystem to which this SubsystemElement belongs
     */
    public void setSubsystem( Subsystem theSubsystem );
    
    /** 
     * returns the Subsytem to which this SubsystemElement belongs
     * 
     * @return theSubsystem to which this SubsystemElement belongs
     */
    public Subsystem getSubsystem();
}

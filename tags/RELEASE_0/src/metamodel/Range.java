/*
 * Range.java
 *
 * Created on November 6, 2004, 3:14 PM
 */

package metamodel;

/**
 * Represents a range of values
 *
 * @author  smr
 */
public class Range {
    
    /** the low value in the range */
    private double lowValue = Double.NEGATIVE_INFINITY;
    
    /** the high value in the range */
    private double highValue = Double.POSITIVE_INFINITY;
    
    /** Creates a new instance of Range */
    public Range() {
    }

    /**
     * Return the low value of the range
     * 
     * @return the low value of the range
     */
    public double getLowValue() {
        return lowValue;
    }

    /**
     * Set the low value of the range
     * 
     * @param lowValue the new low value of the range
     */
    public void setLowValue(double lowValue) {
        this.lowValue = lowValue;
    }

    /**
     * Return the high value of the range
     * 
     * @return the high value of the range
     */
    public double getHighValue() {
        return highValue;
    }

    /**
     * Set the high value of the range
     * 
     * @param highValue the new low value of the range
     */
    public void setHighValue(double highValue) {
        this.highValue = highValue;
    }
    
    
    /** 
     * Check that the limits are valid
     *
     * @return true if the high limit is greater than the low limit, false otherwise
     */
    public boolean isValid() {
        return ( highValue > lowValue );
    }
    
    
}

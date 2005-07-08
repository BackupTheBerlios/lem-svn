/* 
 * Range.java
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

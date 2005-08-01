/*
 * DateVariable.java
 *
 * Created on 14 July 2005, 12:59
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package runtime;

import java.util.Calendar ; 
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer ; 
import metamodel.DateType ; 
import metamodel.DataType ; 
/**
 *
 * @author shuku
 */
public class DateVariable extends Variable 
        implements Comparable {
        
    /** Date object representing the date, in milliseconds */
    private Date date; 

    /** Creates a new instance of DateVariable
     *with date set to today. */    
    public DateVariable() {
        Calendar rightNow = Calendar.getInstance();
        // Date d = rightNow.getTime() ; 
        this.date = rightNow.getTime() ;          
    }
    
    /**creates an instance of DateVariable, initializing it to the
     *specified date.
     *@param Date date in milliseconds.
     */
    public DateVariable(Date d) {        
        this.date = d ; 
        Calendar cal = new GregorianCalendar() ; 
        cal.setTime(d) ;                         
    }
    
    /**Creates a date object out of an object, representing date
     * 
     *@param object representing date in form of dd-mm-yyyy */
    public DateVariable(String date) throws LemRuntimeException {        
        this.date = StringToDate ( date );
    }
    
    public Date StringToDate(String d) throws LemRuntimeException {         
        StringTokenizer st = new StringTokenizer(d, "-");
        int day=0, month= 0, year = 0 ;
        Date newDate = null ;
        if (st.countTokens() != 3) {
            throw new LemRuntimeException("Invalid date") ; 
        } else {
            try {
                day = Integer.parseInt( st.nextToken() ) ; 
                month = Integer.parseInt( st.nextToken() ) ; 
                year = Integer.parseInt( st.nextToken() ) ;
            }catch ( Exception e ) {
                throw new LemRuntimeException ("Invalid date, date should be in the form dd-mm-yyyy") ; 
            }
            Calendar cal = new GregorianCalendar(year, month, day) ; 
            newDate = cal.getTime() ; 
        }                
        return newDate ; 
    }
    
    /**
     * DateToString
     * 
     * @return Object representing the value of this variable, in form of
     * a String
     */
    public String DateToString(Date date) {
        Calendar cal = new GregorianCalendar() ;
        cal.setTime(date) ; 
        int day = cal.get(Calendar.DAY_OF_MONTH) ; 
        int month = cal.get(Calendar.MONTH) ; 
        int year = cal.get(Calendar.YEAR) ;
        return day + "-" + month + "-" + year ;                
    }

    /**
     * Returns the value of date variable 
     */
    public java.lang.Object getValue() {
	    return date;
    }
    
    public void setValue(java.lang.Object o) {
	    date = (Date)o;
    }
    
    /**Returns the type of this variable 
     *@return DataType of this variable */
    public DataType getType() {
        return DateType.getInstance();
    }
    
    /**Returns the date object of this variable 
     *@return Date of this variable */
    public java.util.Date getDate() {
        return date;
    }
    
    /**Compares this object with the specified date Object and returns an integer
     *@return a negative integer, zero, or a positive integer as this object is less than, 
     * equal to, or greater than the specified object. */
    public int compareTo(java.lang.Object anotherDate) {        
        Date other = ((DateVariable) anotherDate).getDate() ;
        int result = 0 ; 
        if ( this.date.after(other) ) {
            result = 1 ; 
        } else if ( this.date.before ( other )) {
            result = -1 ;
        } else 
            result = 0 ;         
        return result ; 
    }    
}

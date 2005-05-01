/*
 * DisplayElement.java
 *
 * Created on 1 May 2005, 21:19
 */

package verifier;

import java.util.ArrayList;

/** 
 * DisplayElement specifies the content and attributes (font, colour, size ,etc) 
 * of a piece of text.  
 * @author SHukuBOy
 */
public class StyledDocument {
    
    private ArrayList elements;     
    
    
    /** Creates a new instance of DisplayElement */
    public StyledDocument() {
        elements = new ArrayList() ;        
    }
    
    public int getLength() {
        return elements.size(); 
    }
    public void addStyle(StyledElement element) {        
        elements.add(element) ;
    }
                        
    public StyledElement getStyledElement (int index) {
       if (index <= elements.size() )
            return (StyledElement) elements.get(index);   
       else {
           StyledElement s = null ;
           return s; 
       }
           
    }                
}

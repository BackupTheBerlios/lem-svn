/*
 * StyledElement.java
 *
 * Created on 1 May 2005, 21:19
 */

package verifier;

import javax.swing.text.SimpleAttributeSet;

/** 
 * StyledElement specifies the content and attributes (font, colour, size ,etc) 
 * of a piece of text.  
 * @author SHukuBOy
 */
public class StyledElement {
    
    private SimpleAttributeSet attribute ;
    private String content ;     
    
    /** Creates a new instance of DisplayElement */
    public StyledElement(String content, SimpleAttributeSet attribute) {   
        this.attribute = attribute ;
        this.content = content ;
    }
                            
    public SimpleAttributeSet getAttributeSet () {
        return attribute ; 
    }
    
    public String getContent () {
        return content ;
    }
    
    
    
}

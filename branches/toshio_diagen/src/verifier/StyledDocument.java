/*
 * DisplayElement.java
 *
 * Created on 1 May 2005, 21:19
 */

package verifier;

import java.util.ArrayList;

/** 
 * DisplayDocument specifies the content and attributes (font, colour, size ,etc) 
 * of a document, made of pieces of rich text format.  
 * @author SHukuBOy
 */
public class StyledDocument {
    
    /** An ArrayList containing StyledElement objects which all together
     *form the text and formating of the StyledDocument*/
    private ArrayList elements;     
        
    /** Creates a new instance of DisplayDocument */
    public StyledDocument() {
        elements = new ArrayList() ;        
    }
    
    /** returns the number of StyledElements in the document
     *@return number of StyledElements attached to the StyledDocument*/
    public int getLength() {
        return elements.size(); 
    }
    
    /**Adds an StyledElement to the StyledDocument
     *@param element the Styled element to be attached to end of current
     *Styled Document*/ 
    public void addStyle(StyledElement element) {        
        elements.add(element) ;
    }
                       
    /**
     * Returns a StyledElement specified by the index parameter, or null if the
     * given index does not exist.
     * @param index index of the returned StyledElement
     * @return A StyledElement corresponding to the given index, or null if given index doesn't exit
     */
    public StyledElement getStyledElement (int index) {
       if (index <= elements.size() )
            return (StyledElement) elements.get(index);   
       else {
           StyledElement s = null ;
           return s; 
       }           
    }   
    
    /**Appends a whole StyledDocument to the end of the current document,
     *by adding all the elements of the latter to the end of the former.
     *@param doc The StyledDocument to add to the end of current document.
     */
    public void append (StyledDocument doc) {      
      for (int i = 0 ; i < doc.getLength() ; i++) {
         elements.add( (StyledElement) doc.getStyledElement(i)) ;
      }         
    }
    
}

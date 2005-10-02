/* 
 * Literal.java
 *
 * Copyright (C) 2005 Steven James Ring
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


package metamodel;

/**
 * This class represents a literal that can appear in a LEM Expression.
 *
 * @author sjr
 */
public class Literal extends Expression {
    
    /** 
     * The value of this Literal. It is up to any runtime classes
     * to, given this Literal, convert its value and type to an
     * instance of Variable (or similar).
     */
    String value;
    
    /**
     * The type of this Literal.
     */
    DataType type;
    
    /** 
     * Creates a new instance of Literal given the literal's String 
     * representation. There is currently no way to determine what
     * type of literal is passed in, so this constructor does some 
     * pattern matching in order to determine that.
     *
     *
     * @todo In LemParser.jjt, split Literal() into StringLiteral(), BooleanLiteral(), etc 
     */
    public Literal( DataType type, String literal ) throws LemException {
        this.value = literal;
        if( type != null && type.equals( StringType.getInstance() ))
            value = value.substring( 1 ).substring(0, value.length() - 2 );
        this.type = type;
    }

    public DataType getType() {
        return type;
    }
    
    public String getValue() {
        return value;
    }
}

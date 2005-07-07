
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
        this.type = type;
    }

    public DataType getType() {
        return type;
    }
    
    public String getValue() {
        return value;
    }
}

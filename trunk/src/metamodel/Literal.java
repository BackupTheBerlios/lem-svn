
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
    public Literal( String literal ) throws LemException {
        if( literal == null || literal.length() == 0 ) {
            throw new LemException( "Zero-length literal" );
        }
        
        this.value = literal;
        if( literal.charAt(0) == '"' ) {
            this.type = StringType.getInstance();
        } else if( "true".equals( literal ) || "false".equals( literal )) {
            this.type = BooleanType.getInstance();
        } else if( "null".equals( literal )) {
            // TODO: What type does the 'null' literal carry, if any?
            this.type = ObjectReferenceType.getInstance();
        } else {
            // If it's none of the above, it's probably a numeric literal of some sort
            this.type = NumericType.getInstance();
        }
    }

    public DataType getType() {
        return type;
    }
    
    public String getValue() {
        return value;
    }
}

/*
 * Multiplicity.java
 *
 * Created on December 6, 2003, 6:02 PM
 */

package metamodel;
/**
 * Defines constants for the standard xtUML multiplicities:
 * ( "1..1" | "0..1" | "0..*" | "1..*" )
 *
 *
 * @author  smr
 */
public class Multiplicity {
    
    /** ONE and only ONE */
    public static final Multiplicity ONE = new Multiplicity( "1..1", "EXACTLY ONE", false, false );
    
    /** Zero or ONE */
    public static final Multiplicity ZERO_OR_ONE = new Multiplicity( "0..1", "ZERO OR ONE", false, true );
    
    /** Zero, one or many */
    public static final Multiplicity ZERO_OR_MANY = new Multiplicity( "0..*", "ZERO OR MORE", true, true );
    
    /** One or many */
    public static final Multiplicity ONE_OR_MANY = new Multiplicity( "1..*", "ONE OR MORE", true, false );
   
    /** The symbolic representation (eg. "1..*") */
    private String symbolic;

    /** The textual representation (eg. "ONE OR MORE") */    
    private String textual;

    /** Whether the multiplicity is multivalued */
    private boolean multivalued;

    /** Whether the multiplicity is optional */
    private boolean optional;
    
    
    /** 
     * Construct a new multiplicity with a supplied value 
     *
     * @param symbolic representation of the multiplicity (eg.
     * "1..*")
     * @param textual representation of the multiplicity (eg. "ONE OR MORE")
     * @param multivalued whether the multiplicity is multivalued
     * @param optional whether the multiplicity is optional
     */
    private Multiplicity( String symbolic, String textual, boolean multivalued, boolean optional ) {
        this.symbolic = symbolic;
        this.textual = textual;
        this.multivalued = multivalued;
        this.optional = optional;
    }
    
    /**
     * Return a string representation of the multiplicity
     *
     * @return a string representation of the multiplicity
     */
    public String toString() {
        return textual;
    }
    
    /**
     * Return true if this multiplicity represents multiple associated objects
     *
     * @return true if this multiplicity represents multiple associated object
     */
    public boolean isMultivalued() {
        return multivalued;
    }
    
    /**
     * Return true if this multiplicity implies a potential null value
     *
     * @return true if this multiplicity implies a potential null value
     */
    public boolean isOptional() {
        return optional;
    }

    /**
     * Return the multiplicity described by the symbolic representation
     *
     * @param symbolic representation of the required multiplicity
     * @return the multiplicity described by the symbolic representation, or
     * null if the supplied string is an invalid representation
     */

    public static Multiplicity fromString( String symbolic ) {
        if( "1..1".equals( symbolic )) return ONE;
        if( "0..1".equals( symbolic )) return ZERO_OR_ONE;
        if( "0..*".equals( symbolic )) return ZERO_OR_MANY;
        if( "1..*".equals( symbolic )) return ONE_OR_MANY;
        return null;
    }
 }

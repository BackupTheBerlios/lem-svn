/*
 * ValueObjectGenerator.java
 *
 * Created on October 29, 2004, 9:46 AM
 */

package org.jdns.xtuml.tools.dbmapping;

import org.jdns.xtuml.metamodel.ArbitraryIdType;
import org.jdns.xtuml.metamodel.Association;
import org.jdns.xtuml.metamodel.Attribute;
import org.jdns.xtuml.metamodel.BooleanType;
import org.jdns.xtuml.metamodel.DataType;
import org.jdns.xtuml.metamodel.DateType;
import org.jdns.xtuml.metamodel.Domain;
import org.jdns.xtuml.metamodel.DomainSpecificDataType;
import org.jdns.xtuml.metamodel.Generalisation;
import org.jdns.xtuml.metamodel.IntegerType;
import org.jdns.xtuml.metamodel.Model;
import org.jdns.xtuml.metamodel.NumericType;
import org.jdns.xtuml.metamodel.Relationship;
import org.jdns.xtuml.metamodel.StringType;
import java.util.*;
import java.io.*;
import org.jdns.xtuml.tools.Lem;

/**
 * Create PERL value objects from a LEM model supplied on standard in
 *
 * @author  smr
 */
public class SQLMapper {
    
    /** Creates a new instance of ValueObjectGenerator */
    public SQLMapper( Model m ) {
        Iterator i = m.getDomains().values().iterator();
        
        while( i.hasNext() ) {
            mapDomain( (Domain)i.next() );
        }
    }
    
    public static void main( String [] args ) {
        // parse the supplied model
        
        Lem parser = null;
        try {
            parser = new Lem();
            Model model = parser.parse( System.in );
            SQLMapper m = new SQLMapper( model );
        } catch ( Exception e) {
            
            System.err.println( "Doh! ... got " + e.getClass().getName() );
            System.err.println( parser.errorMessage );
            System.err.println( parser.errorMessage );
            e.printStackTrace();
            
            
        } catch (Throwable e) {
            
            // report any exception thrown during LEM parsing or model building
            
            System.err.println("Ouch! ... got " + e.getClass().getName() );
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void mapDomain(Domain d) {        
        Iterator i = d.getClasses().values().iterator();
        
        System.out.println( "DROP DATABASE IF EXISTS " + d.getName() + ";" );
        System.out.println( "CREATE DATABASE " + d.getName() + ";" );
        System.out.println( "USE " + d.getName() + ";" );
        while( i.hasNext() ) {
            mapClass( (org.jdns.xtuml.metamodel.Class)i.next() );
        }
        
        i = d.getClasses().values().iterator();
        org.jdns.xtuml.metamodel.Class c = null;
        org.jdns.xtuml.metamodel.Class superclass = null;
        org.jdns.xtuml.metamodel.Generalisation g = null;
        
        while( i.hasNext() ) {
            c = (org.jdns.xtuml.metamodel.Class)i.next();
            Map h = null;
            if( (h = c.getSubclassParticipation()) != null ) {
                Iterator j = h.values().iterator();
                
                while( j.hasNext() ) {
                    g = (Generalisation)j.next();
                    superclass = (org.jdns.xtuml.metamodel.Class)(g.getSuperclass());
                    System.out.println( "ALTER TABLE " + c.getName()
                    + " ADD CONSTRAINT FOREIGN KEY FK_" + superclass.getName()
                    + "_" + g.getName() + " (oid) REFERENCES " + superclass.getName() + " (oid);" );
                }
            }
        }
        
        i = d.getRelationships().values().iterator();
        
        Relationship r = null;
        while( i.hasNext() ) {
            r = (Relationship)i.next();
            System.err.println( "Relationship: " + (r != null ? r.getName() : "null!" ));
            if( r instanceof Association ) {
                mapAssociation( (Association)r );
            }
        }
    }
    
    protected void mapClass( org.jdns.xtuml.metamodel.Class c ) {
        System.out.println( "CREATE TABLE " + c.getName() + " (" );
        
        Iterator i = c.getAllAttributes().iterator();
        
        System.out.print( "oid BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL COMMENT 'Auto-generated identifier'" );
        
        while( i.hasNext() ) {
            System.out.println( ", " );
            mapAttribute( (Attribute)i.next() );
        }
        
        System.out.println( ") TYPE=InnoDB;\n" );
    }
    
    protected void mapAssociation( org.jdns.xtuml.metamodel.Association a ) {
        System.err.println( "Mapping " + a.getName() );
        
        org.jdns.xtuml.metamodel.Class act = a.getActivePerspective().getDomainClass();
        org.jdns.xtuml.metamodel.Class pasv = a.getPassivePerspective().getDomainClass();
        
        boolean[][] acm = new boolean[2][2];
        acm[0][0] = !a.getActivePerspective().getMultiplicity().isOptional();
        acm[1][0] = !a.getPassivePerspective().getMultiplicity().isOptional();
        acm[0][1] = a.getActivePerspective().getMultiplicity().isMultivalued();
        acm[1][1] = a.getPassivePerspective().getMultiplicity().isMultivalued();
        
        if( a.getAssociationClassRole() == null ) {
            if( !acm[0][1] && !acm[1][1] ) {
                /* The relationship is one-to-one */
                System.out.println( "ALTER TABLE " + act.getName() + " ADD COLUMN (" + pasv.getName() + "_"
                        + a.getName() + "_oid BIGINT" + (acm[0][0] ? " NOT NULL" : "") + ");" );
                System.out.println( "ALTER TABLE " + act.getName() + " ADD CONSTRAINT FOREIGN KEY FK_"
                        + pasv.getName() + "_" + a.getName() + " (oid) REFERENCES " + pasv.getName() + " (oid);" );
            } else if( acm[0][1] && !acm[1][1] ) {
                // Association is one-to-many
                System.out.println( "ALTER TABLE " + pasv.getName() + " ADD COLUMN (" + act.getName()
                + "_" + a.getName() + "_oid BIGINT" + (acm[1][0] ? " NOT NULL" : "" ) + ");" );
                System.out.println( "ALTER TABLE " + pasv.getName() + " ADD CONSTRAINT FOREIGN KEY FK_"
                        + act.getName() + "_" + a.getName() + " (oid) REFERENCES " + act.getName() + " (oid);" );
            } else if( !acm[0][1] && acm[1][1] ) {
                System.out.println( "ALTER TABLE " + act.getName() + " ADD COLUMN (" + pasv.getName()
                + "_" + a.getName() + "_oid BIGINT" + (acm[0][0] ? " NOT NULL" : "" ) + ");" );
                System.out.println( "ALTER TABLE " + act.getName() + " ADD CONSTRAINT FOREIGN KEY FK_"
                        + pasv.getName() + "_" + a.getName() + " (oid) REFERENCES " + pasv.getName() + " (oid);" );
            } else {
                String assocTableName = act.getName() + "_" + pasv.getName() + "_" + a.getName();
                System.out.println( "CREATE TABLE " + assocTableName + " (" + act.getName()
                + "_oid BIGINT NOT NULL, " + pasv.getName() + "_oid BIGINT NOT NULL ) TYPE=InnoDB;" );
                System.out.println( "ALTER TABLE " + assocTableName + " ADD CONSTRAINT FOREIGN KEY FK_" + act.getName() + "_" + a.getName()
                + " (" + act.getName() + "_oid) REFERENCES " + act.getName() + " (oid);" );
                System.out.println( "ALTER TABLE " + assocTableName + " ADD CONSTRAINT FOREIGN KEY FK_" + pasv.getName() + "_" + a.getName()
                + " (" + pasv.getName() + "_oid) REFERENCES " + pasv.getName() + " (oid);" );
            }
        } else {
            // Ok.. there's an association class
            // Add the relevant columns
            org.jdns.xtuml.metamodel.Class assClass = a.getAssociationClassRole().getAssociationClass();
            System.out.println( "ALTER TABLE " + assClass.getName() + " ADD COLUMN (" + act.getName() + "_oid BIGINT NOT NULL"
                    + (!acm[0][1] ? " UNIQUE" : "" ) + ", " + pasv.getName() + "_oid BIGINT NOT NULL" + (!acm[1][1] ? " UNIQUE" : "" )
                    + ");" );
           
            System.out.println( "ALTER TABLE " + assClass.getName() + " ADD CONSTRAINT FK_" + act.getName() + "_" 
                    + a.getName() + " FOREIGN KEY (" + act.getName() + "_oid) REFERENCES " + act.getName() + " (oid);" );
            System.out.println( "ALTER TABLE " + assClass.getName() + " ADD CONSTRAINT FK_" + pasv.getName() + "_" 
                    + a.getName() + " FOREIGN KEY (" + pasv.getName() + "_oid) REFERENCES " + pasv.getName() + " (oid);" );
            
        }
    }
    
    protected void mapAttribute( org.jdns.xtuml.metamodel.Attribute a ) {
        System.out.print( a.getName() + " " );
        
        DataType type = a.getCoreDataType();
        
        // Does this attribute have a domain-specific type?
        
        DomainSpecificDataType dsdt = null;
        if ( a.getType() instanceof  DomainSpecificDataType )
            dsdt = (DomainSpecificDataType) a.getType();

	if( type instanceof NumericType ) {
            if( dsdt == null || dsdt.getPrecision() == 0 ) {
                // We assume that non-domain-specific numeric types are integers
                System.out.print( "INTEGER" );
            } else {
                System.out.print( "DOUBLE" );
            }
        } else if( type instanceof StringType ) {
            if( dsdt == null || dsdt.getLength() <= 0 ) {
                System.out.print( "VARCHAR(255)" );
            } else if( dsdt.getLength() <= 65535 ) {
                System.out.print( "TEXT" );
            } else {
                System.out.print( "LONGTEXT" );
            }
        } else if( type instanceof DateType ) {
            System.out.print( "DATETIME" );
        } else if( type instanceof BooleanType ) {
            System.out.print( "BOOL" );
        } else if( type instanceof IntegerType ) {
            System.out.print( "INTEGER" );
        } else if( type instanceof ArbitraryIdType ) {
            System.out.print( "INTEGER UNSIGNED" );
        }
        
        System.out.print( " COMMENT '" + a.getDescription().replaceAll("[\\r\\n]", "" ).replaceAll("'","") + "'" );
    }
    
    public static void usage( String reason ) {
        System.err.println( "Usage: java SQLMapper <domain>" );
    }
}

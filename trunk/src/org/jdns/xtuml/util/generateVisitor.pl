#!/usr/bin/perl
# 
# Generate visitor methods 
#
# $Id: generateVisitor.pl 596 2005-08-03 02:35:37Z u3293115 $
# --------------------

	use FileHandle;

	my $filename = $ARGV[0];
	

	print <<EOF;
 /*
  * Code generated by src/util/generateVisitor.pl  ... DO NOT EDIT this file
 */

package org.jdns.xtuml.verifier;

import org.jdns.xtuml.parser.*;
/**
 * A generic visitor for the LemParser
 */

public class Visitor implements LemParserVisitor { 

        
    /** a Mapper to maintain a working map between SimpleNodes and Metamodel object */
    protected Mapper mapper = new Mapper();
    

    private void logVisit( SimpleNode node, Object data ) {
    	/*
        Token t1 = node.getFirstToken(); 
        Token t2 = node.getLastToken();
        System.err.print( "Visited " + node.getClass().getName() + " token=" + t1.image ); 
        if ( t1==t2)
            System.err.println( "" );
        else
            System.err.println( " to token=" + t2.image );
	*/
    }


    /**
     * Get the Mapper object from this visitor
     * 
     * @return the Mapper object maintained by this visitor
     */
    public Mapper getMapper() {
        return mapper;
    }



    /**
     * Set the Mapper object for this visitor
     * 
     * @param the Mapper object to be maintained by this visitor
     */
    public void setMapper( Mapper aMapper) {
        mapper = aMapper;
    }
    
EOF
#	my $fh = FileHandle->open( $filename ) or die( "Could not open $filename: $!" );
	open FH, $filename or die( "Cannot not open $filename: $!" );
	while (<FH>) {
		chomp;
		if ( m/public Object/ ) {
			s/;/ { /;
			print "\t$_\n";
			print "\t\tlogVisit( node, data );\n";
			print "\t\tnode.childrenAccept( this, data );\n";
			print "\t\treturn data;\n";
			print "\t}\n";
		}
	}



	print "}\n"

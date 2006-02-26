#!/usr/bin/perl -w
#
# Read a LEM file as input and output highlighted LEM text as a series of
# postscript files, one page per file. This script uses 'enscript' as the
# formatting engine. See 'man enscript' for more information.
#
# The script returns the count of the number of PostScript output files.
#
# Steven Ring, February, 2006
#
#------------------------------------------------------------------------------

use strict;

sub usage;

	my $lemInputFile = $ARGV[0];
    $lemInputFile =~ /(.*)\.lem/ ;
	my$fileStem = $1;
	usage() if ! $fileStem;
	my $pagesPrinted = 1;
	my $pageNo = 1;
	my $outFileName = "";
	while ( $pagesPrinted eq 1 ) {
		$outFileName = $fileStem."_page_".$pageNo.".ps"; 
		my $cmd = "enscript -o $outFileName -a $pageNo -Elem -T 3 --color -B -j $lemInputFile";
		my $output = eval{ `$cmd 2>&1`; };
		$pageNo++;
		my @fields = split( / /, $output );
		$pagesPrinted = $fields[1];
	}
	unlink $outFileName;
	exit( $pageNo-1);

sub usage {

		die "Usage: highlightLem.pl <filename.lem>";
}	

#!/usr/bin/perl
#
# produce an enscript-generated postscript file from the supplied LEM file
#
# --------------------------------------------------------
	use FileHandle;

	my $path = $ARGV[0];
	if ( $path =~ m/(.*).lem$/ ) {
		my $file = $1;

# produce postscript file using enscript

		my $cmd = "enscript $file.lem -o $file.ps -Elem --color -B -C -T 4 ";
		`$cmd`;	

# convert to encapsulated postscript

		$cmd = "ps2epsi $file.ps";
		`$cmd`;

# tidy up by removing the postscript file

		unlink "$file.eps";
		link "$file.epsi", "$file.eps"
			or die "could not rename $file.epsi : $!";

		unlink "$file.epsi"
			or die "could not rename to $file.eps : $!";
		
		
	}

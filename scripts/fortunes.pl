#!/usr/bin/env perl
$infile = "fortunes.txt";

open (INFILE, $infile);

$i = 1000;
while (<INFILE>) {
  chomp;
  print("insert into fortunes (id, text) values ($i, '$_');\n");
  $i++;
}

close INFILE;

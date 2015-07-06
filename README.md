# atc_console
The console application that takes the input parameters from the command line 
and displays the message, depending on this parameters.

Format of a parameters: -T<name>=<value>

name: "week","fact".

Format of a value for "week": DDMMYYYY.
Format of a value for "fact": natural number.

If parameter doesn't match with (-T), application ignores input.
If input name doesn't exist, application displays value of 42!
If week includes Friday, 13th, application displays "Hurray!".
If format is incorrect, application displays an error with message.

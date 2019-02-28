# suduko-validator

This is a simple validator for a suduko puzzle.  It takes a string of 81 numbers 1-9 and checks
that those numbers are a valid suduko solution.  The 81 numbers are split into the 9 rows
of the suduko board.

Each row is validated, then each column, and then each of the 9 smaller grids.

In clojure getting the rows is easy. *(partition foo 9)*
The columns are a little harder and the grids are the hardest.

If anyone has other solutions to the creation of the columns and small grids
I would be interested in seeing them.

I never connected the cli, I guess that's a to do.  But the tests are all there.

And I can't imagine any reason someone would use this.  


## Installation

You'll need leiningen and clojure and java installed...

Check out this repository or download the zip.

compile with,

'lein compile
lein uberjar'

## Usage

FIXME: explanation

    $ java -jar suduko-validator-0.1.0-standalone.jar [args]    

## License

Copyright © 2019 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

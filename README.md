# erdos.sudoku

A small Sudoku solver written in Clojure.

Sudoku boards are represented as a vector of integer (or `nil`) values in row major order.

```Clojure
user=> (in-ns 'erdos.sudoku)

erdos.sudoku=> (def test-board (sudoku-line ".5..83.17...1..4..3.4..56.8....3...9.9.8245....6....7...9....5...729..861.36.72.4"))
#'erdos.sudoku/test-board

erdos.sudoku=> test-board
[nil 5 nil nil 8 3 nil 1 7 nil nil nil 1 nil nil 4 nil nil 3 nil 4 nil nil 5 6 nil 8 nil nil nil nil 3 nil nil nil 9 nil 9 nil 8 2 4 5 nil nil nil nil 6 nil nil nil nil 7 nil nil nil 9 nil nil nil nil 5 nil nil nil 7 2 9 nil nil 8 6 1 nil 3 6 nil 7 2 nil 4]

erdos.sudoku=> (solution test-board)
[6 5 2 4 8 3 9 1 7 9 7 8 1 6 2 4 3 5 3 1 4 9 7 5 6 2 8 8 2 5 7 3 6 1 4 9 7 9 1 8 2 4 5 6 3 4 3 6 5 1 9 8 7 2 2 6 9 3 4 8 7 5 1 5 4 7 2 9 1 3 8 6 1 8 3 6 5 7 2 9 4]
```

## License

Copyright © 2021 Janos Erdos

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

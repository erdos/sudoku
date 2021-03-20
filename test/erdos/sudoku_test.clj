(ns erdos.sudoku-test
  (:require [clojure.test :refer [deftest is are testing]]
            [clojure.string :refer [split-lines]]
            [erdos.sudoku :refer [solution sudoku-line]]))

(defmethod clojure.test/report :begin-test-var [m]
  (println (str \u001b "[36m"  "Testing" \space (-> m :var meta :name) (str \u001b "[0m"))))

(deftest test-1
  (are [line] (is (some? (time (solution (sudoku-line line)))))
    ".5..83.17...1..4..3.4..56.8....3...9.9.8245....6....7...9....5...729..861.36.72.4"
    "2.6.3......1.65.7..471.8.5.5......29..8.194.6...42...1....428..6.93....5.7.....13"
    "..45.21781...9..3....8....46..45.....7.9...128.12.35..4.......935..6.8.7.9.3..62."
    "59....147...9....8.72....3.7...4.29..2..3.8.68..17..5...5764..9.36..5...1..8....2"
    "9...84.6.6.4..52.7.3..7..8.76...15...53.....1...4.96.31.5.26.9...2.4....8....371."
    "68.9.5.....3...5.84.21.87.339.72.8.........1..45..69...6.8.4..2..1..2.757...13..."
    "...34...2..6.82.737..1..45..82..5.14....983..67......514.7.....9.5.3..2..3....8.6"
    "6...5.....73..8.2.854.27...2.17..53.4...69..7.8....9...273.1.84.6.54...93.......1"
    "..75..9.4....823.5..16....28...36.7..16..42..43.19..5.54...8....29.71.3.......6.9"
    "........8..3...4...9..2..6.....79.......612...6.5.2.7...8...5...1.....2.4.5.....3" ;; 7.03
    "........2..8.1.9..5....3.4....1.93...6..3..8...37......4......53.1.7.8..2........" ;; 0.25
    "..2...7...1.....6.5......18....37.......49.....41.23....3.2.9...8.....5.6.......2" ;; 3.25
    ;
    ))

#_
(deftest test-top-95
  (doseq [line (split-lines (slurp "http://magictour.free.fr/top95"))]
    (println "Solving" line)
    (assert (some? (time (solution (sudoku-line line)))))))

(deftest test-hard-1
  (testing "hard-1 from http://norvig.com/sudoku.html"
    (is (some? (time (solution (sudoku-line ".....6....59.....82....8....45........3........6..3.54...325..6..................")))))))


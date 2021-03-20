(ns erdos.sudoku)

(def range-9 (range 1 10))
(def range-81 (range 81))

(def row-indices (vec (partition 9 range-81)))
(def index->row (into {} (for [i range-81] [i (quot i 9)])))

(def col-indices (vec (partition 9 (for [i (range 9) j (range 9)] (+ (* j 9) i)))))
(def index->col (into {} (for [i range-81] [i (mod i 9)])))

(def block-indices
  (vec (for [i [0 27 54] id [0 3 6]]
         (for [j [0 9 18] jd [0 1 2]]
           (+ i id j jd)))))

(def index->block
  (into {} (mapcat #(for [index %1] [index %2]) block-indices range-81)))

(defn sudoku-line [s]
  (assert (string? s))
  (assert (= 81 (count s)))
  (mapv (zipmap "123456789" range-9) s))

(defn annotate [board]
  (assert (vector? board))
  (letfn [(vec-of [f] (vec (for [row (range 9)] (set (keep board (f row))))))]
    {:board       board
     ;; heuristic: try substituting most used numbers first
     :numbers     (vec (sort-by (some-fn (frequencies board) (constantly 0)) > range-9))
     :row-elems   (vec-of row-indices)
     :col-elems   (vec-of col-indices)
     :block-elems (vec-of block-indices)}))

(defn candidates [{:keys [row-elems col-elems block-elems board numbers]} index]
  (assert (nil? (board index)))
  (->> numbers
       (remove (row-elems (index->row index)))
       (remove (col-elems (index->col index)))
       (remove (block-elems (index->block index)))))

(defn substitute [annotated index value]
  (assert (number? value))
  {:numbers     (:numbers annotated)
   :board       (assoc  (:board annotated) index value)
   :row-elems   (update (:row-elems annotated) (index->row index) conj value)
   :col-elems   (update (:col-elems annotated) (index->col index) conj value)
   :block-elems (update (:block-elems annotated) (index->block index) conj value)})

(defn empty-indices [{:keys [board block-elems]}]
  (assert (vector? board))
  (->> (for [i range-81
             :when (nil? (board i))]
         i)
       ;; heuristic to speed up some cases
       (sort-by (fn [index]
                  (- (count (block-elems (index->block index))))))))

(defn solve-annotated [annotated]
  (assert (:board annotated))
  (reduce (fn [annotateds index]
            (for [annotated annotateds
                  candidate (candidates annotated index)]
              (substitute annotated index candidate)))
          [annotated]
          (empty-indices annotated)))

(defn solutions [board]
  (assert (vector? board))
  (map :board (solve-annotated (annotate board))))

(def solution (comp first solutions))

(defn solve [board]
  (time (doall (solutions board))))

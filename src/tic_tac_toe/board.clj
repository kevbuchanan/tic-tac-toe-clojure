(ns tic-tac-toe.board)

(def empty-space :-)

(defn new-board [size]
  (vec (repeat (* size size) empty-space)))

(defn lines [board]
  (let [rows (partition 3 board)]
    (concat rows
      (apply map vector rows)
      (vector (map #(nth board %) [0 4 8]))
      (vector (map #(nth board %) [2 4 6])))))

(defn winner [board]
  (first
  (first
    (filter
      #(and (= 1 (count %)) (not (contains? % empty-space)))
      (map #(set %) (lines board))))))

(defn draw? [board]
  (not (some #(= empty-space %) board)))

(defn over? [board]
  (or (not= (winner board) nil) (draw? board)))

(defn make-move [board space piece]
  (assoc board space piece))

(defn empty-board? [board]
  (every? #(= empty-space %) board))

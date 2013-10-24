(ns tic-tac-toe.board)

(def empty-space :-)

(def board-size (ref 3))

(defn new-board [size]
  (do (dosync (ref-set board-size size))
  (vec (repeat (* size size) empty-space))))

(defn first-diagonal [board]
  (take @board-size (iterate #(+ (inc @board-size) %) 0)))

(defn second-diagonal [board]
  (take @board-size (iterate #(+ (dec @board-size) %) (dec @board-size))))

(defn lines [board]
  (let [rows (partition @board-size board)]
    (concat rows
      (apply map vector rows)
      (vector (map #(nth board %) (first-diagonal board)))
      (vector (map #(nth board %) (second-diagonal board))))))

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

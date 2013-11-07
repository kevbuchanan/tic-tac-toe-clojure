(ns tic-tac-toe.board)

(def empty-space :-)

(defn board-size [board]
  (case (count board)
    9 3
    16 4
    25 5
    36 6
    49 7))

(defn new-board [size]
  (vec (repeat (* size size) empty-space)))

(defn first-diagonal [board]
  (let [size (board-size board)]
  (take size (iterate #(+ (inc size) %) 0))))

(defn second-diagonal [board]
  (let [size (board-size board)]
  (take size (iterate #(+ (dec size) %) (dec size)))))

(defn lines [board]
  (let [rows (partition (board-size board) board)]
    (concat rows
      (apply map vector rows)
      (vector (map #(nth board %) (first-diagonal board)))
      (vector (map #(nth board %) (second-diagonal board))))))

(defn winner [board]
  (->> board
      lines
      (map set)
      (filter #(and (= 1 (count %)) (not (contains? % empty-space))))
      first
      first))

(defn draw? [board]
  (not (some #(= empty-space %) board)))

(defn over? [board]
  (or (not= (winner board) nil) (draw? board)))

(defn make-move [board space piece]
  (assoc board space piece))

(defn empty-board? [board]
  (every? #(= empty-space %) board))

(defn other-piece [board piece]
  (first (filter #(and (not= piece %) (not= empty-space %)) board)))

(defn possible-moves [board]
  (keep-indexed #(if (= empty-space %2) %1) board))

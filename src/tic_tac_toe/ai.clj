(ns tic-tac-toe.ai
  (:require [tic-tac-toe.board :refer [empty-space winner draw? over? empty-board? board-size]]))

(defrecord Node [board parent children move best-score])

(def node-score [node]
  (board-state-score (:board node) (:move node)))

(def node-children [node]
  (possible-boards (:board node) (:move node)))

(def other-piece [node]
  (first (filter #(and (not= (:move node) %) (not= empty-space %)) (:board node))))

(defn possible-moves [board]
  (keep-indexed #(if (= empty-space %2) %1) board))

(defn possible-boards [board piece]
  (let [spaces (possible-moves board)]
    (map #(assoc board % piece) spaces)))

(defn board-state-score [board piece]
  (cond (= piece (winner board)) 1000
        (draw? board) 0))

(defn score-factor [player]
  (if (= player :ai) 1000 -1000))

(def calculate-score (memoize (fn [node player depth]
  (let [other-piece (other-piece node)
        other-player (if (= player :ai) :human :ai)]
    (if (over? moved-board)
      (* (board-state-score moved-board piece) (score-factor player) (/ 1 depth))
      (if (= player :ai)
        (apply min (map #(calculate-score moved-board % other-player other-piece (inc depth)) (possible-moves moved-board)))
        (apply max (map #(calculate-score moved-board % other-player other-piece (inc depth)) (possible-moves moved-board)))))))))

(defn move-scores [board piece]
  (let [moves (possible-moves board)
        boards (possible-boards board piece)]
    (loop [spaces possibilities boards boards scores {}]
      (if (empty? spaces)
        scores
        (recur (rest spaces) (rest boards) (assoc scores (first spaces) (calculate-score (first boards) :ai piece 1)))))))

(defn next-move [board piece]
  (if (empty-board? board)
    (inc (board-size board))
    (let [scores (move-scores board piece)]
      (reduce #(if (< (get scores %1) (get scores %2)) %2 %1) (keys scores)))))

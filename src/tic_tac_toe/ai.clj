(ns tic-tac-toe.ai
  (:require [tic-tac-toe.board :refer [empty-space winner draw? over? empty-board? board-size]]))

(defn possible-moves [board]
  (keep-indexed #(if (= empty-space %2) %1) board))

(defn board-state-score [board piece]
  (cond (= piece (winner board)) 1000
        (draw? board) 0))

(defn score-factor [player]
  (if (= player :ai) 1000 -1000))

(def calculate-score (memoize (fn [board space player piece depth]
  (let [other-piece (first (filter #(and (not= piece %) (not= empty-space %)) board))
        other-player (if (= player :ai) :human :ai)
        moved-board (assoc board space piece)]
      (if (over? moved-board)
        (* (board-state-score moved-board piece) (score-factor player) (/ 1 depth))
        (if (= player :ai)
          (apply min (map #(calculate-score moved-board % other-player other-piece (inc depth)) (possible-moves moved-board)))
          (apply max (map #(calculate-score moved-board % other-player other-piece (inc depth)) (possible-moves moved-board)))))))))

(defn move-scores [board piece]
  (let [possibilities (possible-moves board)]
    (loop [spaces possibilities scores {}]
      (if (empty? spaces)
        scores
        (recur (rest spaces) (assoc scores (first spaces) (calculate-score board (first spaces) :ai piece 1)))))))

(defn next-move [board piece]
  (if (empty-board? board)
    (inc (board-size board))
    (let [scores (move-scores board piece)]
      (reduce #(if (< (get scores %1) (get scores %2)) %2 %1) (keys scores)))))

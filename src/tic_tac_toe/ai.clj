(ns tic-tac-toe.ai
  (:require [tic-tac-toe.board :refer [empty-space winner draw? over? empty-board? board-size]]))

(defn other-piece [board piece]
  (first (filter #(and (not= piece %) (not= empty-space %)) board)))

(defn possible-moves [board]
  (keep-indexed #(if (= empty-space %2) %1) board))

(defn possible-boards [board piece]
  (let [spaces (possible-moves board)]
    (map #(assoc board % piece) spaces)))

(defn score-factor [type]
  (if (= type :max) 1 -1))

(defn board-state-score [board piece type depth]
  (cond (= piece (winner board)) (/ (* (score-factor type) 1) depth)
        (draw? board) 0))

(defn alpha [depth]
  (if (odd? depth) (/ -1 (inc depth)) 0))

(defn optimizations [board piece depth]
  (let [piece-count (count (filter #{piece} board))]
    (or (and (> (board-size board) 3) (< piece-count (dec (board-size board))))
        (and (> (board-size board) 3) (> depth 3)))))

(defrecord Node [parent board children type depth score piece])

(def calculate-score (memoize (fn [node]
  (let [alpha (alpha (:depth node))
        board-score (board-state-score (:board node) (:piece node) (:type node) (:depth node))
        other-piece (other-piece (:board node) (:piece node))]
    (if (or board-score (empty? (:children node))
        (and (:score node) (= (:score node) alpha))
        (optimizations (:board node) other-piece (:depth node)))
      (let [score (or board-score (:score node) 0)]
        (if (= nil (:parent node))
          score
          (if (= :min (:type node))
            (if (or (= nil (:score (:parent node))) (< score (:score (:parent node))))
              (recur (update-in (:parent node) [:score] #(or score %)))
              (recur (:parent node)))
            (if (or (= nil (:score (:parent node))) (> score (:score (:parent node))))
              (recur (update-in (:parent node) [:score] #(or score %)))
              (recur (:parent node))))))
      (let [next-node (Node. (update-in node [:children] rest)
                             (first (:children node))
                             (possible-boards (first (:children node)) (:piece node))
                             (if (= (:type node) :max) :min :max)
                             (inc (:depth node))
                             nil
                             other-piece)]
        (recur next-node)))))))

(defn move-scores [board piece]
  (let [moves (possible-moves board)
        all-boards (possible-boards board piece)]
    (loop [spaces moves boards all-boards scores {}]
      (if (empty? spaces)
        scores
        (let [score (calculate-score (Node. nil
                                            (first boards)
                                            (possible-boards (first boards) (other-piece (first boards) piece))
                                            :max
                                            1
                                            nil
                                            piece))]
          (recur (rest spaces) (rest boards) (assoc scores (first spaces) score)))))))

(defn next-move [board piece difficulty]
  (cond (= difficulty 1) (rand-nth (possible-moves board))
        (= difficulty 2)
          (let [other-piece-count (count (filter #(= % (other-piece board piece)) board))]
            (if (< other-piece-count (dec (board-size board)))
              (recur board piece 1)
              (recur board piece 3)))
        (= difficulty 3)
          (if (empty-board? board)
            (inc (board-size board))
            (let [scores (move-scores board piece)]
              (reduce #(if (< (get scores %1) (get scores %2)) %2 %1) (keys scores))))))

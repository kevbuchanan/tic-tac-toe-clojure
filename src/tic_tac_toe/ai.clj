(ns tic-tac-toe.ai
  (:require [tic-tac-toe.board :refer :all]))

(defn possible-boards [board piece]
  (let [spaces (possible-moves board)]
    (map #(make-move board % piece) spaces)))

(defn score-factor [type]
  (if (= type :min) 1 -1))

(defn board-state-score [board piece type depth]
  (cond (= piece (winner board)) (/ (* (score-factor type) 1) depth)
        (draw? board) 0))

(defn get-max [depth]
  (if (odd? depth) (/ -1 (inc depth)) (/ 1 (inc depth))))

(defrecord Node [parent board children type depth score piece alpha beta])

(defn calculate-score [node max-depth]
  (let [next-max (get-max (:depth node))
        board-score (board-state-score (:board node) (:piece node) (:type node) (:depth node))
        other-piece (other-piece (:board node) (:piece node))]
    (if (or board-score
        (empty? (:children node))
        (= (:score node) next-max)
        (> (:alpha node) (:beta node))
        (= (:depth node) max-depth))
      (let [score (or board-score (:score node))]
        (if (= nil (:parent node))
          score
          (if (= :min (:type node))
            (if (> score (:score (:parent node)))
              (recur (assoc (:parent node) :score score :alpha (max score (:alpha (:parent node)))) max-depth)
              (recur (:parent node) max-depth))
            (if (< score (:score (:parent node)))
              (recur (assoc (:parent node) :score score :beta (min score (:beta (:parent node)))) max-depth)
              (recur (:parent node) max-depth)))))
      (let [next-node (Node. (update-in node [:children] rest)
                             (first (:children node))
                             (possible-boards (first (:children node)) (:piece node))
                             (if (= (:type node) :max) :min :max)
                             (inc (:depth node))
                             (if (= (:type node) :max) Float/POSITIVE_INFINITY Float/NEGATIVE_INFINITY)
                             other-piece
                             (:alpha node)
                             (:beta node))]
        (recur next-node max-depth)))))

(defn get-max-depth [board piece]
  (if (> (board-size board) 3)
    3
    10))

(defn move-scores [board piece]
  (let [moves (possible-moves board)
        all-boards (possible-boards board piece)
        max-depth (get-max-depth board piece)]
    (loop [spaces moves boards all-boards scores {}]
      (if (empty? spaces)
        scores
        (let [score (calculate-score (Node. nil
                                            (first boards)
                                            (possible-boards (first boards) (other-piece (first boards) piece))
                                            :min
                                            1
                                            Float/POSITIVE_INFINITY
                                            piece
                                            Float/NEGATIVE_INFINITY
                                            Float/POSITIVE_INFINITY) max-depth)]
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

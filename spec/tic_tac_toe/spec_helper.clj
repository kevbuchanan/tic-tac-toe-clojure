(ns tic-tac-toe.spec-helper
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ai :refer :all]
            [tic-tac-toe.board :refer :all]))

(defn find-turn [board first-move]
  (let [x-count (count (filter #{:X} board))
        o-count (count (filter #{:O} board))]
    (cond (= x-count o-count) first-move
          (> x-count o-count) :O
          (> o-count x-count) :X)))

(defn play-all-boards [boards outcomes first-move difficulty]
  (if (empty? boards)
    outcomes
    (let [board (first boards)
          turn (find-turn board first-move)
          other-piece (if (= turn :X) :O :X)]
      (if (over? board)
          (cond (draw? board) (recur (rest boards) (conj outcomes :draw) first-move difficulty)
              (= (winner board) :X) (recur (rest boards) (conj outcomes :win) first-move difficulty)
              (= (winner board) :O) (recur (rest boards) (conj outcomes :lose) first-move difficulty))
          (if (= turn :X)
              (recur (conj (rest boards) (make-move board (next-move board turn difficulty) turn)) outcomes first-move difficulty)
              (recur (into (rest boards) (possible-boards board turn)) outcomes first-move difficulty))))))

(defn all-games [boards outcomes first-move difficulty]
  (play-all-boards boards outcomes first-move difficulty))

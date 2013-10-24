(ns tic-tac-toe.validations
  (:require [tic-tac-toe.board :refer :all]))

(defn valid-move? [move board]
  (let [size (board-size board)]
  (and (contains? (set (range 1 (inc (* size size)))) move)
       (= empty-space (nth board (dec move))))))

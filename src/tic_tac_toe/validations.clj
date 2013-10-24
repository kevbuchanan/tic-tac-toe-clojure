(ns tic-tac-toe.validations
  (:require [tic-tac-toe.board :refer :all]))

(defn valid-move? [move board]
  (and (contains? (vec (range 1 10)) move)
       (= empty-space (nth board (dec move)))))

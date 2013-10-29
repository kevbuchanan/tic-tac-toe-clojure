(ns tic-tac-toe.round
  (:require [tic-tac-toe.board :refer [winner over? make-move]]
            [tic-tac-toe.interface :refer :all]
            [tic-tac-toe.ai :refer [next-move]]))

(defn get-move [player board piece difficulty]
  (if (= player :ai) (next-move board piece difficulty) (request-human-move board piece)))

(defn end-game [board]
  (if (not= nil (winner board))
    (declare-winner (winner board) board)
    (declare-draw board)))

(defn start [{:keys [players pieces board difficulty]}]
  (if (over? board)
    (end-game board)
    (let [player (first players)
          piece (first pieces)]
      (show-turn piece board)
      (recur {:players [(last players) (first players)]
              :pieces [(last pieces) (first pieces)]
              :board (make-move board (get-move player board piece difficulty) piece)}))))

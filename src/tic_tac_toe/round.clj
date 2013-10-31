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

(defn start [{:keys [players pieces board difficulty move-fn end-fn turn-fn]}]
  (let [move-fn (or move-fn get-move)
        end-fn (or end-fn end-game)
        turn-fn (or turn-fn show-turn)]
  (if (over? board)
    (end-fn board)
    (let [player (first players)
          piece (first pieces)]
      (turn-fn piece board)
      (recur {:players [(last players) (first players)]
              :pieces [(last pieces) (first pieces)]
              :board (make-move board (move-fn player board piece difficulty) piece)
              :difficulty difficulty
              :move-fn move-fn
              :end-fn end-fn
              :turn-fn turn-fn})))))

(ns tic-tac-toe.spec-helper
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ai :refer :all]
            [tic-tac-toe.board :refer :all]))

(defn check-board [board]
  (let [rows (partition 3 board)]
  (println (nth rows 0))
  (println (nth rows 1))
  (println (nth rows 2))
  (println "")
  :lose))

(defn play-all-boards [board piece player difficulty]
  (let [other-piece (if (= piece :X) :O :X)
        other-player (if (= player :ai) :human :ai)
        spaces (possible-moves board)]
    (if (over? board)
        (cond (draw? board) :draw
            (= player :ai) :lose
            (= player :human) :win)
        (if (= player :ai)
            (recur (make-move board (next-move board piece difficulty) piece) other-piece other-player difficulty)
            (map #(play-all-boards (make-move board % piece) other-piece other-player difficulty) spaces)))))

(defn all-games [board piece player difficulty]
  (flatten (play-all-boards board piece player difficulty)))

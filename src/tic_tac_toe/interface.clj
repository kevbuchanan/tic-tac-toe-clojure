(ns tic-tac-toe.interface
  (:require [tic-tac-toe.board :refer :all]
            [tic-tac-toe.validations :refer :all]))

(defn print-board [board]
  (println "\n\n\n\n\n\n")
  (let [rows (partition 3 (map-indexed #(if (= empty-space %2) (keyword (str (+ 1 %1))) %2) board))]
    (apply println (interpose " | " (map name (nth rows 0))))
    (println "-------------")
    (apply println (interpose " | " (map name (nth rows 1))))
    (println "-------------")
    (apply println (interpose " | " (map name (nth rows 2))))))

(defn declare-winner [piece board]
  (print-board board)
  (println (str (name piece) " won"))
  :win)

(defn declare-draw [board]
  (print-board board)
  (println "Draw")
  :draw)

(defn show-turn [piece board]
  (print-board board)
  (println (str (name piece) ", your move")))

(defn request-human-move [board piece]
  (println "Enter a space number")
  (let [move (read-string (read-line))]
    (if (valid-move? move board)
      (- move 1)
      (recur board piece))))

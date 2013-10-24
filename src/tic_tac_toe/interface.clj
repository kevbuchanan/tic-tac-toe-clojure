(ns tic-tac-toe.interface
  (:require [tic-tac-toe.board :refer [empty-space board-size]]
            [tic-tac-toe.validations :refer [valid-move?]]))

(defn print-board [board]
  (println "\n\n\n\n\n\n")
  (let [rows (partition @board-size (map-indexed #(if (= empty-space %2) (format "%2s" %1) (format "%2s" (name %2))) board))]
    (loop [print-lines (map #(interpose " |" %) rows)]
      (apply println (first print-lines))
      (if (empty? (rest print-lines))
        nil
        (recur (rest print-lines))))))

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

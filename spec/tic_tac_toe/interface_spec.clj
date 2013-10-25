(ns tic-tac-toe.interface-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.interface :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "Printing the board"

  (around [it]
    (with-out-str (it)))

  (it "prints a 3x3 board"
    (pending))

  (it "prints a 7x7 board"
    (pending)))

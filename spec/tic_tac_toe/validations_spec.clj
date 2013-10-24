(ns tic-tac-toe.validations-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.validations :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "Valid move"

  (it "returns true if the move is empty space"
    (should (valid-move? 1 (new-board 3))))

  (it "returns false if the move is off the board"
    (should-not (valid-move? 10 (new-board 3))))

  (it "returns false if the move is taken"
    (should-not (valid-move? 2 [:- :X :- :- :- :- :- :- :-])))

  (it "returns false if the move was not a number"
    (should-not (valid-move? "a;dlsfa" (new-board 3)))))

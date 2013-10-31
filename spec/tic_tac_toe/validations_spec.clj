(ns tic-tac-toe.validations-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.validations :refer :all]
            [tic-tac-toe.board :refer [new-board]]))

(describe "Valid move"

  (it "returns true if the move is empty space"
    (should (valid-move? 1 (new-board 3))))

  (it "returns false if the move is less than 1"
    (should-not (valid-move? 10 (new-board 3))))

  (it "returns false if the move is greater than 9 on a 3x3 board"
    (should-not (valid-move? 0 (new-board 3))))

  (it "returns false if the move is greater than 49 on a 7x7 board"
    (should-not (valid-move? 50 (new-board 7))))

  (it "returns false if the move is taken"
    (should-not (valid-move? 2 [:- :X :- :- :- :- :- :- :-])))

  (it "returns false if the move was not a number"
    (should-not (valid-move? "a;dlsfa" (new-board 3))))

  (it "returns false if the move was a space"
    (should-not (valid-move? " " (new-board 3)))))

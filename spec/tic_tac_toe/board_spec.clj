(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "Creating a new board"

  (it "returns an empty board"
    (should= (new-board 3) [:- :- :- :- :- :- :- :- :-])))

(describe "Getting the lines"

  (it "returns the rows columns and diagonals"
    (should= (lines [:X :X :X :O :O :O :- :- :-]) [[:X :X :X] [:O :O :O] [:- :- :-]
                                                   [:X :O :-] [:X :O :-] [:X :O :-]
                                                   [:X :O :-] [:X :O :-]])))

(describe "Determining a winner"

  (it "returns the winning piece if there is a winner"
    (should= (winner [:X :X :X :O :- :O :- :- :-]) :X))

  (it "returns nil if there is not a winner"
    (should= (winner [:X :X :- :O :- :O :- :- :-]) nil)))

(describe "Determining a draw"

  (it "returns true if the game is a draw"
    (should= (draw? [:X :O :X :O :X :O :O :X :O]) true)))

(describe "Determining if the game is over"

  (it "returns true if the game is over"
    (should= (over? [:X :X :X :X :O :O :O :O :-]) true))

  (it "returns false if the game is not over"
    (should= (over? [:- :- :- :- :- :- :- :- :-]) false)))

(describe "Making a move"

  (it "places the piece on the board and returns the board"
    (should= (make-move [:- :- :- :- :- :- :- :- :-] 0 :X) [:X :- :- :- :- :- :- :- :-])))

(describe "Checking for an empty board"

  (it "returns true if the board is empty"
    (should= (empty-board? [:- :- :- :- :- :- :- :- :-]) true))

  (it "returns false if the board is not empty"
    (should= (empty-board? [:X :- :- :- :- :- :- :- :-]) false)))


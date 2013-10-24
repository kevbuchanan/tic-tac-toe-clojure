(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "Creating a new board"

  (it "returns an empty 3x3 board"
    (should= (new-board 3) [:- :- :- :- :- :- :- :- :-]))

  (it "returns an empty 4x4 board"
    (should= (new-board 4) [:- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :-]))

  (it "returns an empty 5x5 board"
    (should= (new-board 5) [:- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :-]))

  (it "returns an empty 6x6 board"
    (should= (new-board 6) [:- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :-]))

  (it "returns an empty 7x7 board"
    (should= (new-board 7) [:- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :-])))

(describe "Getting the lines"

  (it "returns the rows columns and diagonals for a 3x3 board"
    (should= (lines [:X :X :X :O :O :O :- :- :-]) [[:X :X :X] [:O :O :O] [:- :- :-]
                                                   [:X :O :-] [:X :O :-] [:X :O :-]
                                                   [:X :O :-] [:X :O :-]]))

  (it "returns the rows columns and diagonals for a 7x7 board"
    (should= (lines [:X :X :X :O :O :O :- :- :- :X :X :X :O :O :O :- :- :- :X :X :X :O :O :O :- :- :- :X :X :X :O :O :O :- :- :- :X :X :X :O :O :O :- :- :- :X :X :O :O])
              [[:X :X :X :O :O :O :-] [:- :- :X :X :X :O :O] [:O :- :- :- :X :X :X] [:O :O :O :- :- :- :X] [:X :X :O :O :O :- :-] [:- :X :X :X :O :O :O] [:- :- :- :X :X :O :O]
               [:X :- :O :O :X :- :-] [:X :- :- :O :X :X :-] [:X :X :- :O :O :X :-] [:O :X :- :- :O :X :X] [:O :X :X :- :O :O :X] [:O :O :X :- :- :O :O] [:- :O :X :X :- :O :O]
               [:X :- :- :- :O :O :O] [:- :O :X :- :O :X :-]])))

(describe "Determining a winner"

  (it "returns the winning piece if there is a winner on a 3x3 board"
    (should= (winner [:X :X :X :O :- :O :- :- :-]) :X))

  (it "returns nil if there is not a winner on a 3x3 board"
    (should= (winner [:X :X :- :O :- :O :- :- :-]) nil))

  (it "returns the winning piece if there is a winner on a 7x7 board"
    (should= (winner [:X :X :X :X :X :X :X :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :- :-])
              :X))

  (it "returns nil if there is not a winner on a 7x7 board"
    (should= (winner (new-board 7)) nil)))

(describe "Determining a draw"

  (it "returns true if the game is a draw on a 3x3 board"
    (should= (draw? [:X :O :X :O :X :O :O :X :O]) true))

  (it "returns true if the game is a draw on a 7x7 board"
    (should= (draw? [:X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X :X])
              true)))

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


(ns tic-tac-toe.ai-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ai :refer :all]
            [tic-tac-toe.spec-helper :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "Getting the next move"

  (it "returns the best move"
    (should= 4 (next-move [:- :- :-
                           :- :- :-
                           :- :- :-] :X))
    (should= 2 (next-move [:X :X :-
                           :X :O :O
                           :O :O :X] :X))
    (should= 2 (next-move [:X :- :-
                           :X :O :O
                           :O :- :X] :X))
    (should= 4 (next-move [:X :- :-
                           :X :- :O
                           :O :O :X] :X))
    (should= 2 (next-move [:X :X :-
                           :- :- :-
                           :O :O :-] :X))
    (should= 0 (next-move [:- :- :-
                           :X :X :O
                           :O :O :X] :X))))

(describe "Going first"

  (it "never loses"
    (should= true (not (contains? (set (flatten (play-all-boards (new-board 3) :X :ai))) :lose)))))

(describe "Going second"

  (it "never loses"
    (should= true (not (contains? (set (flatten (play-all-boards (new-board 3) :O :human))) :lose)))))

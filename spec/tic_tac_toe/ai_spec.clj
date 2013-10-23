(ns tic-tac-toe.ai-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ai :refer :all]
            [tic-tac-toe.spec-helper :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "Getting the next move"

  (it "returns the best move"
    (should= (next-move [:- :- :-
                         :- :- :-
                         :- :- :-] :X) 4)
    (should= (next-move [:X :X :-
                         :X :O :O
                         :O :O :X] :X) 2)
    (should= (next-move [:X :- :-
                         :X :O :O
                         :O :- :X] :X) 2)
    (should= (next-move [:X :- :-
                         :X :- :O
                         :O :O :X] :X) 4)
    (should= (next-move [:X :X :-
                         :- :- :-
                         :O :O :-] :X) 2)
    (should= (next-move [:- :- :-
                         :X :X :O
                         :O :O :X] :X) 0)))

(describe "Going first"

  (it "never loses"
    (should= true (not (contains? (set (flatten (play-all-boards (new-board 3) :X :ai))) :lose)))))

(describe "Going second"

  (it "never loses"
    (should= true (not (contains? (set (flatten (play-all-boards (new-board 3) :O :human))) :lose)))))

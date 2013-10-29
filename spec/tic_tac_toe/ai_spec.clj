(ns tic-tac-toe.ai-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ai :refer :all]
            [tic-tac-toe.spec-helper :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "Getting the next move"

  (it "returns the best move with difficulty 3"
    (should= 4 (next-move [:- :- :-
                           :- :- :-
                           :- :- :-] :X 3))
    (should= 2 (next-move [:X :X :-
                           :X :O :O
                           :O :O :X] :X 3))
    (should= 2 (next-move [:X :- :-
                           :X :O :O
                           :O :- :X] :X 3))
    (should= 4 (next-move [:X :- :-
                           :X :- :O
                           :O :O :X] :X 3))
    (should= 4 (next-move [:O :- :-
                           :- :- :-
                           :- :- :-] :X 3))
    (should= 7 (next-move [:O :X :-
                           :O :X :-
                           :- :- :-] :X 3))
    (should= 2 (next-move [:X :X :-
                           :- :- :-
                           :O :O :-] :X 3))
    (should= 0 (next-move [:- :- :-
                           :X :X :O
                           :O :O :X] :X 3))))

(describe "Going first"

  (it "never loses with difficulty 3"
    (should-not-contain :lose (all-games (new-board 3) :X :ai 3)))

  (it "loses sometimes with difficulty 2"
    (def games (all-games (new-board 3) :X :ai 2))
    (should-contain :draw games)
    (should-contain :win games))

  (it "loses more than it wins with difficulty 1"
    (def games (all-games (new-board 3) :X :ai 1))
    (should (> (count (filter #{:lose} games)) (count (filter #{:win} games))))))

(describe "Going second"

  (it "never loses with difficutly 3"
    (should-not-contain :lose (all-games (new-board 3) :O :human 3)))

  (it "wins and loses with difficulty 2"
    (def games (all-games (new-board 3) :O :human 2))
    (should-contain :lose games)
    (should-contain :win games))

  (it "loses more than it wins with difficulty 1"
    (def games (all-games (new-board 3) :O :human 1))
    (should (> (count (filter #{:lose} games)) (count (filter #{:win} games))))))

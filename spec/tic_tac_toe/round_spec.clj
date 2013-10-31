(ns tic-tac-toe.round-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.ai :refer :all]
            [tic-tac-toe.spec-helper :refer :all]
            [tic-tac-toe.round :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "Round"

  (around [it]
    (with-out-str (it)))

  (describe "getting the next move"

    (it "gets the next move from the ai"
      (should= 4 (get-move :ai (new-board 3) :X 3)))

    (it "gets the next move from the human player"
      (should= 4 (with-in-str "5" (get-move :human (new-board 3) :O 3)))))

  (describe "ending the game"

    (it "declares a draw if the game is a draw"
      (should= :draw (end-game [:X :O :X
                                :O :O :X
                                :X :X :O])))

    (it "declares a win if the game is won"
      (should= :win (end-game [:X :X :X
                               :O :O :X
                               :O :X :O]))))

  (describe "starting a round"

    (defn move-test1 [player board piece difficulty] 1)

    (defn end-test [board] board)

    (defn turn-test [piece board] true)

    (it "ends if the game is over"
      (should= [:X :- :X :O :O :O :- :- :-] (start {:players [:ai :human]
                             :pieces [:X :O]
                             :board [:X :- :X :O :O :O :- :- :-]
                             :difficulty 3
                             :move-fn move-test1
                             :end-fn end-test
                             :turn-fn turn-test})))

    (it "gets a move from the first player if the game is not over"
      (should= [:X :X :X :O :O :- :- :- :-] (start {:players [:ai :human]
                             :pieces [:X :O]
                             :board [:X :- :X :O :O :- :- :- :-]
                             :difficulty 3
                             :move-fn move-test1
                             :end-fn end-test
                             :turn-fn turn-test})))

    (defn move-test2 [player board piece difficulty] (if (= player :ai) 8 5))

    (it "recurs and gets a move from the other player if the game is still not over"
      (should= [:X :- :X :O :O :O :- :- :X] (start {:players [:ai :human]
                             :pieces [:X :O]
                             :board [:X :- :X :O :O :- :- :- :-]
                             :difficulty 3
                             :move-fn move-test2
                             :end-fn end-test
                             :turn-fn turn-test})))))

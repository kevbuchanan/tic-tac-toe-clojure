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
      (should= 4 (get-move :ai (new-board 3) :X)))

    (it "gets the next move from the human player"
      (should= 4 (with-in-str "5" (get-move :human (new-board 3) :O)))))

  (describe "ending the game"

    (it "declares a draw if the game is a draw"
      (should= :draw (end-game [:X :O :X
                                :O :O :X
                                :X :X :O])))

    (it "declares a win if the game is won"
      (should= :win (end-game [:X :X :X
                               :O :O :X
                               :O :X :O])))))

(ns tic-tac-toe.core
  (:require [tic-tac-toe.interface :refer :all])
  (:require [tic-tac-toe.board :refer :all])
  (:require [tic-tac-toe.round :refer :all]))

(defn -main [& args]
  (if (= (first args) "first")
    (start {:players [:human :ai] :pieces [:X :O] :board (new-board 3)})
    (start {:players [:ai :human] :pieces [:O :X] :board (new-board 3)})))


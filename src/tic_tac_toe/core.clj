(ns tic-tac-toe.core
  (:require
    [tic-tac-toe.board :refer [new-board]]
    [tic-tac-toe.round :refer [start]]))

(defn -main [& args]
  (if (= (first args) "first")
    (start {:players [:human :ai] :pieces [:X :O] :board (new-board 3)})
    (start {:players [:ai :human] :pieces [:O :X] :board (new-board 3)})))


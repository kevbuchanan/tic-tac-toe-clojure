(ns tic-tac-toe.core
  (:require
    [tic-tac-toe.board :refer [new-board]]
    [tic-tac-toe.round :refer [start]]
    [clojure.tools.cli :refer [cli]]))

(defn -main [& args]
  (let [[options args banner] (cli args
    ["-o" "--order" :default 1 :parse-fn #(Integer. %)]
    ["-s" "--size" :default 3 :parse-fn #(Integer. %)]
    ["-d" "--difficulty" :default 2 :parse-fn #(Integer. %)])]
    (if (= 1 (:order options))
      (start {:players [:human :ai] :pieces [:X :O] :board (new-board (:size options)) :difficulty (:difficulty options)})
      (start {:players [:ai :human] :pieces [:O :X] :board (new-board (:size options)) :difficulty (:difficulty options)}))))


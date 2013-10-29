(ns tic-tac-toe.interface-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.interface :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "Printing the board"

  (it "prints a 3x3 board"
    (should= "\n\n\n\n\n\n\n 1  |  2  |  3\n 4  |  5  |  6\n 7  |  8  |  9\n" (with-out-str (print-board (new-board 3)))))

  (it "prints a 7x7 board"
    (should= "\n\n\n\n\n\n\n 1  |  2  |  3  |  4  |  5  |  6  |  7\n 8  |  9  | 10  | 11  | 12  | 13  | 14\n15  | 16  | 17  | 18  | 19  | 20  | 21\n22  | 23  | 24  | 25  | 26  | 27  | 28\n29  | 30  | 31  | 32  | 33  | 34  | 35\n36  | 37  | 38  | 39  | 40  | 41  | 42\n43  | 44  | 45  | 46  | 47  | 48  | 49\n"
      (with-out-str (print-board (new-board 7))))))

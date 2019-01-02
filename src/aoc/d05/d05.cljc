; day04: https://adventofcode.com/2018/day/5

(ns aoc.d05.d05
  (:require
   [aoc.d05.data :refer [input input-test]]
   [clojure.string :as str]))

(def data (sort (str/split-lines input-test)))
; (def data (sort (str/split-lines input)))
data
; First part
(defn ans1 [string]
  (for [x (range (- (count string) 1))]
    (let [a (nth string x)
          b (nth string (inc x))]
      (if (and
                (not (= a b))
                (= (str/lower-case a) (str/lower-case b)))
        (let [new_string (str (subs string 0 x) (subs string (+ x 2)))]
          (do
            (println new_string (count string))
            (ans1 new_string)))))))
     


(ans1 input-test)
  








(def test "kamil")

(let [new_string (str (take 2 test))]
  new_string)

(println (str take 2 "kamil"))

(str (subs test 0 2) (subs test (+ 2 2)))
(subs test 4)

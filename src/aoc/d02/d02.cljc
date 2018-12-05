; day01: https://adventofcode.com/2018/day/1

(ns aoc.d01.d02
  (:require
   [aoc.d02.data :refer [input]]
   [clojure.string :as str]))

; Load data
(def data
  (str/split-lines input))


; First part:
(defn is_two? [idn]
  "Return 1 if there is the same letter twice"
  (if (some #{2} (vals (frequencies idn)))
    1
    0))

(defn is_three? [idn]
  "Return 1 if there is the same letter twice"
  (if (some #{3} (vals (frequencies idn)))
    1
    0))

(defn ans [data]
  (* 
   (reduce + (map is_two? data))
   (reduce + (map is_three? data))))

(ans data)  ;8715
  

; Second part:


         

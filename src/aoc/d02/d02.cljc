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

(ans data) ; 8715
  

; Second part:
(defn compare_strings [str1 str2 same_string]
  "Return string with the letters that are teh same in both strings"
  (let [ch1 (first str1)
        ch2 (first str2)
        same_string same_string]
    (if (= 0 (count str1))
      (apply str same_string)
      (if (identical? ch1 ch2)
        (recur (rest str1) (rest str2) (concat same_string (vector ch1)))
        (recur (rest str1) (rest str2) same_string)))))

(defn ans2 [data]
  (for [x data
        y data]
    (let [same_string (compare_strings x y {})]
      (if (= 25 (count same_string))
        (println same_string)))))
             
(ans2 data)



         

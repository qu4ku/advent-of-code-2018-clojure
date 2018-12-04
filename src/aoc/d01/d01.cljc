; day01: https://adventofcode.com/2018/day/1

(ns aoc.d01.d01
  (:require
   [aoc.d01.data :refer [input]]
   [clojure.string :as str]))


(def data
  (map #(Integer/parseInt %) 
     (str/split-lines input)))

; First part:
(reduce + data) ; 497

; Second part:
(defn ans [curr_freq data frequencies]
  (let [curr_freq (+ curr_freq (first data))
        data (concat (rest data) (vector (first data)))]
        
    (if (some #{curr_freq} frequencies)
      (println curr_freq)
      (recur curr_freq data (conj frequencies curr_freq)))))

(ans 0 data '(0))


         

; day04: https://adventofcode.com/2018/day/4

(ns aoc.d04.d04
  (:require
   [aoc.d04.data :refer [input input-test]]
   [clojure.string :as str]))

; WIP

; First part
input
input-test

(def data (sort (str/split-lines input-test)))
; (def data (sort (str/split-lines input)))
data

; (defn parse-data [data last-guard parsed-data]
;   (if (= 0 (count data))
;     parsed-data
;     (let [line (first data)
;           data (rest data)]
;       (if (re-find #"Guard" line)
;         (let [last-guard (Integer/parseInt (re-find #"(?<=#)\d*" line))]
;           (conj parsed-data last-guard)
;           (recur data last-guard parsed-data))
;         ()))))
; 
; (parse-data data nil {})
    

(defn parse-guards [data parsed-data]
  (if (= 0 (count data))
    parsed-data
    (let [line (first data)
          data (rest data)]
      (if (re-find #"Guard" line)
        (recur data (conj parsed-data (re-find #"#\d*" line)))
        (recur data parsed-data)))))

(defn parse-data [data parsed-data]
  (if (= 0 (count data))
    parsed-data
    (let [line (first data)
          data (rest data)]
      (if (re-find #"Guard" line)
        (recur data (conj parsed-data (re-find #"#\d*" line)))
        (recur data (conj parsed-data (Integer/parseInt (re-find #"(?<=:)\d*" line))))))))
  
(parse-guards data [])
(parse-data data [])

(def parsed-data (parse-data data []))

  
  

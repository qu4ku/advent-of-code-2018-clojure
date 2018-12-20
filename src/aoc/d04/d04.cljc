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
    
; 
; (defn parse-guards [data parsed-data]
;   (if (= 0 (count data))
;     parsed-data
;     (let [line (first data)
;           data (rest data)]
;       (if (re-find #"Guard" line)
;         (recur data (conj parsed-data (re-find #"#\d*" line)))
;         (recur data parsed-data)))))
; 
; (defn parse-data [data parsed-data]
;   (if (= 0 (count data))
;     parsed-data
;     (let [line (first data)
;           data (rest data)]
;       (if (re-find #"Guard" line)
;         (recur data (conj parsed-data (re-find #"#\d*" line)))
;         (recur data (conj parsed-data (Integer/parseInt (re-find #"(?<=:)\d*" line))))))))
; 
; (parse-guards data [])
; (parse-data data [])
; 
; (def parsed-data (parse-data data []))
; 
; 
; (defn generate-minutes [start end]
;   (range start (inc end)))
; (generate-minutes (get parsed-data 1) (get parsed-data 2))
; 
; ; nthrest test
; (nthrest parsed-data 3)
; 
; ; assoc test
; (assoc {} (get parsed-data 0) (generate-minutes (get parsed-data 1) (get parsed-data 2)))
; 


; need helper function to extend instead 
(assoc {"#10" [23 45 67]} "#10" [23 24])

; if not guard update map with minutes
; if guard exists add new minutes to the old
(defn update-data [data guard minutes]
  (if-not (get data guard)
    (assoc data guard minutes)
    (let [old_minutes (get data guard)
          new_minutes (concat old_minutes minutes)]
      (assoc data guard new_minutes))))
; update-data test
(update-data {"#10" [1 2 3]} "#10" [3 4 5])

(defn process [data processed-data current-guard]
  (if (= 0 (count data))

    processed-data
    (if (re-find #"Guard" (first data))
      (let [new-guard (re-find #"#\d*" (first data))
            start (Integer/parseInt (re-find #"(?<=:)\d*" (nth data 1)))
            end (Integer/parseInt (re-find #"(?<=:)\d*" (nth data 2)))
            minutes (range start (inc end))
            data (nthrest data 3)]

        (recur data (update-data processed-data new-guard minutes) new-guard))
      (let [start (Integer/parseInt (re-find #"(?<=:)\d*" (nth data 0)))
            end (Integer/parseInt (re-find #"(?<=:)\d*" (nth data 1)))
            minutes (range start (inc end))
            data (nthrest data 2)]
        (recur data (update-data processed-data current-guard minutes) current-guard)))))

(def processed_data (process data {} nil))
(map println processed_data)



(defn find_sleeper [processed_data]
  (key (apply max-key count processed_data)))

(def sleeper (find_sleeper processed_data))
sleeper
(get processed_data sleeper)

(defn most_frequent_minute [minutes]
  (->> minutes
       frequencies))
(most_frequent_minute (get processed_data sleeper))
 

; day04: https://adventofcode.com/2018/day/4

(ns aoc.d04.d04
  (:require
   [aoc.d04.data :refer [input input-test]]
   [clojure.string :as str]))


; First part
(def data (sort (str/split-lines input)))

(defn update-data [data guard minutes]
  (if-not (get data guard)
    (assoc data guard minutes)
    (let [old_minutes (get data guard)
          new_minutes (concat old_minutes minutes)]
      (assoc data guard new_minutes))))
; update-data test
(update-data {"#12" [1 2 3]} "#10" [3 4 5])

(defn process [data processed-data current-guard]
  (if (= 0 (count data))
    processed-data
    (if (re-find #"Guard" (first data))
      (let [new-guard (re-find #"#\d*" (first data))
            start (Integer/parseInt (re-find #"(?<=:)\d*" (nth data 1)))
            end (Integer/parseInt (re-find #"(?<=:)\d*" (nth data 2)))
            minutes (range start end)
            data (nthrest data 3)]

        (recur data (update-data processed-data new-guard minutes) new-guard))
      (let [start (Integer/parseInt (re-find #"(?<=:)\d*" (nth data 0)))
            end (Integer/parseInt (re-find #"(?<=:)\d*" (nth data 1)))
            minutes (range start end)
            data (nthrest data 2)]
        (recur data (update-data processed-data current-guard minutes) current-guard)))))


(defn find_sleeper [processed_data]
  (key (last (sort-by #(count (val %)) processed_data))))

(defn get_most_frequent_minute [minutes]
  (key (last (sort-by #(max (val %)) (frequencies minutes)))))

(def processed_data (process data {} nil))
(def sleeper (find_sleeper processed_data))
(def minutes (get processed_data sleeper))
(def most_freq_min (get_most_frequent_minute minutes))
(* (Integer/parseInt (re-find #"\d+" sleeper)) most_freq_min)  ; 85296


; Second part
(defn max_freq [minutes]
  (last (sort-by #(max (val %)) (frequencies minutes))))

(defn map_to_max_freq [m f]
  (into {} (for [[k v] m] [k (f v)])))

(def max_m (last (sort-by #(max (nth (val %) 1)) (map_to_max_freq processed_data max_freq))))

(* (Integer/parseInt (re-find #"\d+" (key max_m))) (nth (val max_m) 0)) ; 58559

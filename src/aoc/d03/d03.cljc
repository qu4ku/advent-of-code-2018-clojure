; day03: https://adventofcode.com/2018/day/3

(ns aoc.d03.d03
  (:require
   [aoc.d03.data :refer [input]]
   [clojure.string :as str]))

(def input2
  "#1 @ 1,3: 4x4
#2 @ 3,1: 4x4
#3 @ 5,5: 2x2")

; First part
; this solutios is outright terrible, should have used get-in
(defn propagate_fabric [size]
  "Create array of 0s: size x size"
  (vec (replicate size (vec (replicate size 0)))))

(defn parse_line [line]
  (-> {}
      (assoc :num (Integer/parseInt (re-find #"(?<=#)\d*" line)))
      (assoc :left (Integer/parseInt (re-find #"(?<=@ )\d*" line)))
      (assoc :top (Integer/parseInt (re-find #"(?<=,)\d*" line)))
      (assoc :width (Integer/parseInt (re-find #"(?<=: )\d*" line)))
      (assoc :height (Integer/parseInt (re-find #"(?<=x)\d*" line)))))

(def parsed_data (map parse_line (str/split-lines input)))

(defn increment_elements [line nums]
  (let [n (first nums)
         line (assoc line n (inc (nth line n)))
         nums (rest nums)]
    (if (= 0 (count nums))
      line
      (recur line nums))))

(defn increment_array [array nums-width nums-height]
  (if (= 0 (count nums-height))
    array
    (let [n (first nums-height)
          array (assoc array n (increment_elements (nth array n) nums-width))]
      (recur array nums-width (rest nums-height)))))

            
(defn analyse_fabric [array parsed_data]
  (if (= 0 (count parsed_data))
    array
    (let [data (first parsed_data)
          nums-width (range (:left data) (+ (:left data) (:width data)))
          nums-height (range (:top data) (+ (:top data) (:height data)))
          array (increment_array array nums-width nums-height)]
      (recur array (rest parsed_data)))))


(def final_fabric (analyse_fabric (propagate_fabric 1000) parsed_data))
(count (filter #(> % 1) (flatten final_fabric))) ; 110546

; Second part
(defn overlap? [data final_fabric]
  (let [numbers 
        (for [x (range (:left data) (+ (:left data) (:width data)))
              y (range (:top data) (+ (:top data) (:height data)))]   
          (get-in final_fabric [y x]))]
    (if (= (count numbers) (reduce + numbers))
      (println (:num data)))))

(map #(overlap? % final_fabric) parsed_data) ; 819

(ns y2021.d1
  (:require [clojure.string :as str]))

(def input
  (->> (slurp "resources/y2021/d1.in")
       (str/split-lines)
       (map read-string)))

(defn part1 [input]
  (->>
    input
    (partition 2 1)
    (filter #(apply < %))
    count))

(defn part2 [input]
  (->>
    input
    (partition 3 1)
    (map #(apply + %))
    part1))

(defn -main []
  (println (part1 input))
  (println (part2 input)))

(ns y2021.d3
  (:require [clojure.string :as str]))

(def input
  (->> (slurp "resources/y2021/d3.in")
       (str/split-lines)
       (map #(str/split % #""))
       (map #(map read-string %))))

(defn bin-string-to-dec [string]
  (read-string (str "2r" string)))

(defn commonness-bools [input]
  (let [cnt (count input)]
    (->> input
         (reduce #(map + %1 %2))
         (map #(>= % (/ cnt 2))))))

(defn part1 [input]
  (let [bools (commonness-bools input)
        gamma (-> (map {false 0 true 1} bools)
                  str/join
                  bin-string-to-dec)
        epsilon (-> (map {false 1 true 0} bools)
                    str/join
                    bin-string-to-dec)]
    (* gamma epsilon)))

(defn calculate-gas [input mapping]
  (->> (range (-> input first count))
       (reduce (fn [acc idx]
                 (if (> (count acc) 1)
                   (let [new-bools (->> acc commonness-bools (map mapping))]
                     (filter (fn [line] (= (nth line idx) (nth new-bools idx))) acc))
                   acc))
               input)
       first
       str/join
       bin-string-to-dec))

(defn part2 [input]
  (let [o2 (calculate-gas input {false 0 true 1})
        co2 (calculate-gas input {false 1 true 0})]
    (* o2 co2)))

(defn -main []
  (println (part1 input))
  (println (part2 input)))

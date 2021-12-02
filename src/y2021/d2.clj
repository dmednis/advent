(ns y2021.d2
  (:require [clojure.string :as str]))

(def input
  (->> (slurp "resources/y2021/d2.in")
       (str/split-lines)))

(defn part1 [input]
  (->>
    input
    (reduce
      (fn [res line]
        (let [[command val-str] (str/split line #" ")
              val (read-string val-str)]
          (cond
            (= command "forward")
            (update res :pos #(+ % val))
            (= command "up")
            (update res :depth #(- % val))
            (= command "down")
            (update res :depth #(+ % val)))))
      {:pos 0 :depth 0})
    (#(apply * (vals %)))))

(defn part2 [input]
  (->>
    input
    (reduce
      (fn [res line]
        (let [[command val-str] (str/split line #" ")
              val (read-string val-str)]
          (cond
            (= command "forward")
            (-> res
                (update :pos #(+ % val))
                ((fn [{:keys [aim] :as res}]
                   (update res :depth #(+ % (* aim val))))))
            (= command "up")
            (update res :aim #(- % val))
            (= command "down")
            (update res :aim #(+ % val)))))
      {:aim 0 :pos 0 :depth 0})
    (#(apply * (vals (select-keys % [:pos :depth]))))))

(defn -main []
  (println (part1 input))
  (println (part2 input)))

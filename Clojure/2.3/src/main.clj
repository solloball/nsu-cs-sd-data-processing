(ns main)

(def partitionSize 100)
(def parallelPartitionCnt 100)

(defn myFilter [f coll]
  (->>
    (partition-all partitionSize coll)
    (map #(future (doall (filter f %))))
    (partition-all parallelPartitionCnt)
    (map (fn [parts] (map deref (doall parts))))
    (flatten)))



(defn heavyEven [x]
  (even? x))

(defn numDivisors [x]
  (count (filter (comp zero? (partial rem x)) (range 1 (inc x)))))
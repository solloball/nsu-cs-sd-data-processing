(ns main)

(defn asyncFilterChunk [pred chunk]
  "Make future which will filter list"
  (future (doall (filter pred chunk))))

(defn lazyFutures [processed-results remainingFutures pred]
  "lazy calculating futures"
  (if-let [remaining (seq remainingFutures)]

    (lazy-seq (lazy-cat (deref (first processed-results))
                        (lazyFutures (rest processed-results) (rest remaining) pred)))

    (apply concat (map deref processed-results))))

(defn myFilter
  "Main implementation"
  ([pred coll]
   (let [n (.availableProcessors (Runtime/getRuntime))
         chunkCount 100

         parts (map doall (partition-all chunkCount coll))

         pool (map #(asyncFilterChunk pred %) parts)]

     (lazyFutures pool (drop n pool) pred))))

(defn numDivisors [x]
  (count (filter (comp zero? (partial rem x)) (range 1 (inc x)))))
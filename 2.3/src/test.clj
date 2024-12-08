(ns test
  (:require [clojure.test :refer :all]
            [main :refer [myFilter, numDivisors]]))

(deftest test-basic-big
  (testing
    (is (= (myFilter even? (range 500))
           (filter even? (range 500)))
        )
    )
  )

(deftest test-performance-big
  (testing
    (let [test-data (range)]
      (println "My Filter: ")
      (time (doall (take 10000 (filter #(> (numDivisors %) 10) test-data))))
      (println "Default filter: ")
      (time (doall (take 10000 (myFilter #(> (numDivisors %) 10) test-data)))))))



(deftest test-lazy
  (testing
    (let [infinite-coll (range)
          result (myFilter even? infinite-coll)]
      (is (= (take 10 result)
             (take 10 (filter even? infinite-coll)))))))
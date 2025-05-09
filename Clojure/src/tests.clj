(ns tests
  (:require [clojure.test :refer :all]
            [task2 :refer :all]))

; isPrime.
(defn prime [n]
  (and (> n 1)
       (not-any? (divisible n)
                 (take-while #(<= % (Math/sqrt n)) primes))))


(deftest test-prime-numbers
  (doseq [p (take 100 primes)]
    (is (prime p))))
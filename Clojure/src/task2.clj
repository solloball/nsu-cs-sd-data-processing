(ns task2)

(defn divisible [p]
(fn [x] (zero? (mod p x))))

; main
(def primes
  (lazy-seq
    (filter
      (fn [p]
        ; for i in range 0..sqr(n) if i.divisible(n) return false
        (not-any? (divisible p) (take-while (fn [x] (<= x (Math/sqrt p))) primes))
      )
      ; exclude 1, 2
      (drop 2 (range))
      )
    )
  )
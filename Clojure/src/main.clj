(ns main)

; Valid word
(defn isCorrectWord [word]
  (not= (last word) (nth word (- (count word) 2))))

; concat words with letter
(defn concatLetter [words letter]
  (map (fn [word] (str word letter)) words))

; Extend word by adding letter, also filter
(defn extendWords [words alphabet]
  (filter isCorrectWord
          (reduce
            (fn [extended-words letter] (concat extended-words (concatLetter words letter)))
            ()
            alphabet)))

; Main generator
(defn createLanguage [length alphabet]
  (if (> length 0)
    (reduce
      (fn [words _] (extendWords words alphabet))
      alphabet
      (range (- length 1)))))

(defn -main []
  (let [alphabet ["a" "b" "c"]
        length 10
        language (createLanguage length alphabet)]
    (println "Generated language:" language)))
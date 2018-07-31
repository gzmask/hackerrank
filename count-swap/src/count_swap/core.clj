(ns count-swap.core)

(defn swapper [swap-count a [i1 i2]]
  (when (< (get @a i2) (get @a i1))
    (swap! swap-count inc)
    (reset! a (-> @a
                  (assoc i1 (get @a i2))
                  (assoc i2 (get @a i1))))))

(defn count-swap [a]
  (let [c (count a)
        a* (atom a)
        indices (partition 2 (interleave (range (- c 2) -1 -1) (range (dec c) 0 -1)))
        swap-count (atom 0)]
    (loop [prev-swap-count @swap-count]
      (doall (map (partial swapper swap-count a*) indices))
      (when (< 0 (- @swap-count prev-swap-count))
        (recur @swap-count)))
    (println "Array is sorted in" @swap-count "swaps.")
    (println "First Element:" (first @a*))
    (println "Last Element:" (last @a*))))

(count-swap [3 2 1])
(count-swap [6 4 1])
(count-swap [1 2 3])
(count-swap [4 2 3 1])
(time (count-swap (vec (range 600))))
(time (count-swap (range 600 0 -1)))

;;Conclusion: aget/aset with reflection for Java array are very slow O(N) algorithms.

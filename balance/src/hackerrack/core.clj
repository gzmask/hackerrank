(ns hackerrack.core)

(def paras {\{ \}
            \[ \]
            \( \)})

(def open-paras (-> paras keys set))

(defn all-match? [expr]
  (let [matches (reduce (fn [a i]
                          (cond
                            (-> a first paras (= i)) (rest a)
                            (open-paras i)           (cons i a)
                            :else                    (reduced (list :failed)))) nil expr)]
    (if (empty? matches)
      (println "YES")
      (println "NO"))))

(defn -main []
  (doseq [t-itr (range (Integer/parseInt (clojure.string/trim (read-line))))]
    (all-match? (read-line))))

(all-match? "[([{{}}]{[[][][([[]]){[]}{}]]}[]{{}}{})[[]]]{{}}(()[[[[[(){}[]]({}{[]})[]")
(all-match? "([[[[")
(all-match? "}()()()(())")
(all-match? "{[()]}")
(all-match? "{[(])}")
(all-match? "{{[[(())]]}}")
(all-match? (apply str
                   (concat
                    (take 10000 (repeat \[))
                    (take 10000 (repeat \])))))

;;conclusion:
;; Avoid list->vector conversion. It is very costly.
;; Subvec is also more costly than drop-last/rest/first

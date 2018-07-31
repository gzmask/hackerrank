;; https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem
(ns two-stacks.core)

;;slow version
(def stacks [(atom [])
             (atom [])])

(defn queue [cmd item]
  (if (= cmd "1")
    (do (when (seq @(second stacks))
          (dotimes [_ (count @(second stacks))]
            (swap! (first stacks) #(conj % (peek @(second stacks))))
            (swap! (second stacks) pop)))

        ;;enqueue
        (swap! (first stacks) #(conj % (read-string item))))

    (do (when (seq @(first stacks))
          (dotimes [_ (count @(first stacks))]
            (swap! (second stacks) #(conj % (peek @(first stacks))))
            (swap! (first stacks) pop)))

        ;;dequeue
        (case cmd
          "2" (swap! (second stacks) pop)
          "3" (println (peek @(second stacks)))
          (println "stacks:" (seq stacks))))))

(defn run []
  (dotimes [_ (read-string (read-line))]
    (let [cmds (read-line)
          [cmd item] (clojure.string/split cmds #" ")]
      (queue cmd item))))

(run)

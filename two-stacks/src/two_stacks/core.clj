;; https://www.hackerrank.com/challenges/ctci-queue-using-two-stacks/problem
(ns two-stacks.core)

;;slow version
(def stacks [(atom nil)
             (atom nil)])

(defn queue [cmd item]
  (if (= cmd "1")
    (swap! (first stacks) #(conj % (read-string item)))
    (do (dotimes [_ (count @(first stacks))]
          (swap! (second stacks) #(conj % (peek @(first stacks))))
          (swap! (first stacks) pop))

        ;;dequeue
        (case cmd
          "2" (swap! (second stacks) pop)
          "3" (println (peek @(second stacks)))
          (println "stacks:" stacks))

        (dotimes [_ (count @(second stacks))]
          (swap! (first stacks) #(conj % (peek @(second stacks))))
          (swap! (second stacks) pop)))))

(defn run []
  (dotimes [_ (read-string (read-line))]
    (let [cmds (read-line)
          [cmd item] (clojure.string/split cmds #" ")]
      (queue cmd item))))

(run)

(ns qwerty.core
  (:require [net.cgrand.enlive-html :as html])
  (:require [clj-time.core :as time])
  (:require [clj-time.format :as time-format]))

(use '[clojure.string :only (lower-case)])
(use 'overtone.at-at)
  
(defn words-in [x] (re-seq #"[a-z]+" (lower-case x)))
;(def NWORDS (frequencies (words-in (slurp "d://big.txt")))) ; @TODO: Move from slurp.
(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn deletion [a word] (list (apply str (concat (take a word) (drop (inc a) word)))))
(defn replaces [a word] (map #(apply str (concat (take a word) (list %) (drop (inc a) word))) alphabet))
(defn transposition [a word] (list (apply str (if (< a (dec (count word))) (concat (take a word) (list (get word (inc a))) (list (get word a)) (drop (inc (inc a)) word))))))
(defn inserts [a word] (map #(apply str (concat (take a word) (list %) (drop a word))) alphabet))

(defn edits1 [word]
  (remove empty? ; @TODO: Fix "transposition" fn so this can be removed.
    (flatten 
      (for [a (range (count word))]
        (list (deletion a word) (inserts a word) (replaces a word) (transposition a word))))))

;(defn known [words]
;  (let [x (filter #(contains? NWORDS %) words)]
;    (if (empty? x) nil x))) ; @TODO: Is there a more idiomatic way to do this?
;(defn correct [word]
;  (key (first (sort (reduce merge (map #(hash-map % (get NWORDS %)) ; @TODO: This can likely be done better.
;    (or
;      (known (list word))
;      (known (edits1 word))
;      (known (flatten (map edits1 (edits1 word))))
;      (list word))))))))

;(def NWORDS (frequencies (words-in (slurp "d://big.txt"))))

(defn fetch-page [url]
  (html/html-resource (java.net.URL. url)))

(defn parse-test []
  (let [url "http://example.com"
        url-data (fetch-page url)
        selector [:div :a]]
    (first (html/select url-data selector))))

(println (apply str 
  (html/emit* [(parse-test)])))


(def page (fetch-page "http://example.com"))

(nth page 0)
(nth (:content (nth page 1)) 0)

(defn saberi [ a b] (+ a b))
(def mapa {:prvi (fn [a b] 
                   (+ a b)), 
           :drugi #(+ %1 %2),
           :treci saberi})

((:prvi mapa) 1 2)
((:drugi mapa) 3 4)
((:treci mapa) 3 4)

(def time-formatter (time-format/formatters :mysql))

(defn i-am-cool [a] 
  (loop [i 10]
    (when (> i 0)
      (println i)
      (println (time-format/unparse 
                 time-formatter 
                 (time/now)))
      (println " " a)
      (recur (- i 1))))
  (println "I am cool!")
  "I am cool!")
(i-am-cool 0)

(def account1 (ref 100)) 
@account1
(dosync 
  (ref-set account1 99))
(dosync 
  (alter account1 - 7 1))


(def my-pool (mk-pool))
(def schedule (every 1000 #(i-am-cool @account1) my-pool))
(stop schedule)


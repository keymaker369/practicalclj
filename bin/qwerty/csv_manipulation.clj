(ns qwerty.csv-manipulation
  (require [semantic-csv.core :refer :all]
           [clojure-csv.core :as csv]
           [clojure.java.io :as io]
           [clojure.core.matrix :as mtx]
           [clojure.math.combinatorics :as cmb]))

(def old-file "d:/ftp/24589/20160311_requestId_695_null_DEALS-old.csv")
(def new-file "d:/ftp/24589/20160311_requestId_694_null_DEALS-new.csv")

(defn get-headers 
  "return vector of strings"
  [old-file]
  (with-open [in-file (io/reader old-file)]
  (first 
      (csv/parse-csv in-file))))

(get-headers old-file)
(get-headers new-file)

(def old-number-of-columns (count (get-headers old-file)))
(def new-number-of-columns (count (get-headers new-file)))

(sort (get-headers old-file))
(sort (get-headers new-file))

(loop [old-headers (sort (get-headers old-file))]
  (if (= 0 (count old-headers))
    old-headers
    (do
      (println (first old-headers))
      (recur (rest old-headers)))))

(defn in? 
  "true if coll contains elm"
  [coll elm]  
  (some #(= elm %) coll))

(defn in? 
  "true if coll contains elm (ignore case)"
  [coll elm]  
  (some 
    #(= % (.toLowerCase elm)) 
    (map #(.toLowerCase %) coll)))

(not (in? '("aaa" "BBB") "bbB"))

(filter 
  #(not (in? (get-headers new-file) % ))
  (get-headers old-file)) 

(with-open [in-file (io/reader old-file)]
  (->>
    (csv/parse-csv in-file)
    mappify
    doall))

(def a [[ 1   2   0]
        ["4" "5" "6"] 
        ["7" "8" "9"]
        ["10" "11" "12"]
        ["a" "b" "c"]]) 
 
(def b [[ 1   2   "w"] 
        ["4" "5" "6"] 
        ["7" "8" "9"]
        ["10" "11" "12"]
        ["a" "b" "c"]])

 (defn compare [a b combination]
  (if (= a b)
    nil
    [a b combination]))
 (defn differences [matrix-a matrix-b]
 (let [witdh (first (mtx/shape matrix-a))
       length (second (mtx/shape matrix-a))
       all-coordinates (cmb/cartesian-product (range witdh) (range length))]
   (filter
     #(not (nil? %))
     (map #(compare
           (mtx/mget matrix-a (first %) (second %))
           (mtx/mget matrix-b (first %) (second %))
           %) 
        all-coordinates))))

(differences a b)

(with-open [old-file (io/reader old-file) new-file (io/reader new-file)]
    (let [old-rows (doall (csv/parse-csv old-file))
          new-rows (doall (csv/parse-csv new-file))]
      (println "differences in matrixes cells (but we are not comparing first row) ="
               (differences 
                 (rest old-rows) 
                 (rest new-rows)))
      (str "matrixes are same dimenzions = " (= 
                                              (mtx/shape old-rows)
                                              (mtx/shape new-rows)))))












































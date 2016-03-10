(ns qwerty.csv-manipulation
  (require [semantic-csv.core :refer :all]
           [clojure-csv.core :as csv]
           [clojure.java.io :as io]))

(def old-file "c:/Users/user/Google Drive/imports/20160309_requestId_676_null_DEALS.csv")
(def new-file "c:/Users/user/Google Drive/imports/20160309_requestId_675_null_DEALS.csv")

(with-open [in-file (io/reader old-file)]
  (->>
    (csv/parse-csv in-file)
    first))

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
    doall
    second))

(def sample-file "d:/ftp/24589/sample.csv")

(with-open [in-file (io/reader sample-file)]
  (->>
    (csv/parse-csv in-file)
    mappify
    doall
    count))



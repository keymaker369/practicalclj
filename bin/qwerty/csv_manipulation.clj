(ns qwerty.csv-manipulation
  (require [semantic-csv.core :refer :all]
           [clojure-csv.core :as csv]
           [clojure.java.io :as io]))

(def old-file "d:/ftp/24589/20160309_requestId_676_null_DEALS.CSV")
(def new-file "d:/ftp/24589/20160309_requestId_675_null_DEALS.CSV")

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


(with-open [in-file (io/reader old-file)]
  (->>
    (csv/parse-csv in-file)
    ;mappify
    doall
    count))

(def sample-file "d:/ftp/24589/sample.csv")

(with-open [in-file (io/reader sample-file)]
  (->>
    (csv/parse-csv in-file)
    mappify
    doall
    count))



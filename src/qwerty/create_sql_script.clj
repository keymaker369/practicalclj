(ns qwerty.create-sql-script
  (require [clojure.string :as str]))

(def dealers-file-path "d:/ot-dealers.txt")

(def dealer-seq (line-seq 
                  (java.io.BufferedReader. 
                    (clojure.java.io/reader dealers-file-path))))
dealer-seq

(defn get-mbr [line]
  (last (str/split line #" ")))

(def mbrs (map get-mbr dealer-seq))

(map println mbrs)

(defn get-pair [line]
  (list 
    (last (str/split line #" "))
    (str/join " " (butlast (str/split line #" ")))))

(def pairs (map get-pair dealer-seq))

(map print-pair pairs)

(defn print-pair [pair]
  (println (str (first pair) "=" (last pair))))

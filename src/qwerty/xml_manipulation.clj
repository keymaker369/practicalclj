(ns qwerty.xml-manipulation
   (:require
     [clojure.java.io :as io]
     [clojure.zip :as zip]
     [clojure.data.zip.xml :as zx]
     [clojure.data.xml :as xml]))


(def file )
  
(def xml 
  (-> "d:/1.xml"
    io/reader
    xml/parse)) 

(zip/xml-zip xml)

(-> "a b c d"
  .toUpperCase 
 (.replace "A" "X") 
 (.split " ") 
 first)

(.toUpperCase "a b c d")

(.replace (.toUpperCase "a b c d") "A" "X")

(first (.split (.replace (.toUpperCase "a b c d") "A" "X") " "))

(def c 5)

(->> c 
  (+ 3) 
  (/ 2) 
  (- 1))

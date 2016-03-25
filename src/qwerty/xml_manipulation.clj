(ns qwerty.xml-manipulation
   (:require
     [clojure.java.io :as io]
     [clojure.xml :as c-xml]
     [clojure.zip :as zip]
     [clojure.data.zip.xml :as zx]
     [clojure.data.xml :as xml]))

(defn dbg [node]
    (if (associative? node)
      (c-xml/emit-element (dissoc node :content))
      (c-xml/emit-element node))
  node)

(defn as-short-xml [node]
  (clojure.string/trim ; remove trailing \n
    (with-out-str
      (if (associative? node)
        (c-xml/emit-element (dissoc node :content))
        (c-xml/emit-element node)))))

(defn dz [zipper] (do
                    (dbg (clojure.zip/node zipper))
                    zipper)) ; return the zipper for more processing

(defn az [zipper] (as-short-xml (clojure.zip/node zipper)))

(def file )
  
(def xml 
  (-> "d:/1.xml"
    io/reader
    xml/parse)) 

(zip/xml-zip xml)

(-> xml
    zip/xml-zip
    zip/down
    dz
    zip/right
    dz
    zip/down
    az
    )
;http://josf.info/blog/2014/04/14/seqs-of-clojure-zippers/
;http://josf.info/blog/2014/03/21/getting-acquainted-with-clojure-zippers/
;http://josf.info/blog/2014/03/28/clojure-zippers-structure-editing-with-your-mind/
(zip/vector-zip [1 [:a :b] 2 3 [40 50 60]])

(->[1 [:a :b] 2 3 [40 50 60]]
  zip/vector-zip 
  zip/down
  zip/right)

(def zzz (zip/vector-zip [1 [:a :b] 2 3 [40 50 60]]))

(-> zzz zip/next zip/next zip/next zip/next zip/next zip/next zip/next zip/next zip/next zip/next zip/next)
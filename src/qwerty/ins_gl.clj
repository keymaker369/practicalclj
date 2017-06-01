(ns qwerty.ins-gl
  (use clojure.core)
  (require [qwerty.ui4j :as ui4j]))

(def browser (ui4j/browser))
(def page (ui4j/page  "" browser))
(def doc (ui4j/document page))

(count (ui4j/query-all "._myci9" doc))

(def load-more-button (ui4j/query "._8imhp._glz1g" doc))

(if (ui4j/present? load-more-button)
  (ui4j/click load-more-button))

(ui4j/show page)

(ui4j/scroll-to-bottom page)

(loop [elements (ui4j/query-all "._myci9" doc)
       elements-ammount 0
       load-attepts-left 3]
  (if (= (count elements) elements-ammount)
    (if (> load-attepts-left 0)
      (do
        (ui4j/scroll-to-bottom page)
        (Thread/sleep 1000)
        (recur (ui4j/query-all "._myci9" doc)
               elements-ammount
               (- load-attepts-left 1)))
      elements)
    (do
      (ui4j/scroll-to-bottom page)
      (Thread/sleep 1000)
      (recur (ui4j/query-all "._myci9" doc) (count elements) 3))))




(.shutdown browser)
(ns qwerty.brave-ch3
  (use clojure.repl))

(defn my-reduce
  ([fun elements]
   (loop [remaining-elements elements
          result 0]
     (if (empty? remaining-elements)
       result
       (let [[first-element & remaining] remaining-elements]
         (recur remaining (fun result first-element)))))))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (set [part (matching-part part)])))
          []
          asym-body-parts))

(defn hit-body-part
  [asym-body-parts]
  (let [sym-body-parts (better-symmetrize-body-parts asym-body-parts)
        all-parts-sum (reduce + (map :size sym-body-parts))
        hit-limit (rand all-parts-sum)]
    (println all-parts-sum)
    (println hit-limit)
    (loop [[part & remaining] sym-body-parts
           current-limit (:size part)]
      (println part)
      (if (> current-limit hit-limit)
        part
        (recur remaining (+ current-limit (:size (first remaining))))))))

(defn mapset [f coll]
  (set (map f coll)))

(defn matching-parts
  [part]
  (hash-set
    {:name (clojure.string/replace (:name part) #"^left-" "top-")
     :size (:size part)}
    {:name (clojure.string/replace (:name part) #"^left-" "upper-left-")
     :size (:size part)}
    {:name (clojure.string/replace (:name part) #"^left-" "upper-right-")
     :size (:size part)}
    {:name (clojure.string/replace (:name part) #"^left-" "down-left-")
     :size (:size part)}
    {:name (clojure.string/replace (:name part) #"^left-" "down-right-")
     :size (:size part)}
    ))

(defn radial-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (into final-body-parts (matching-parts part)))
          []
          asym-body-parts))

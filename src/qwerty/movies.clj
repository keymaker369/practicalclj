(ns qwerty.movies
  (use clojure.core))
;list of movies
(slurp "d:/movies.txt")

(with-open [rdr (clojure.java.io/reader "d:/movies.txt")]
  (count (line-seq rdr)))

(def movies (line-seq (clojure.java.io/reader "d:/movies.txt")))

(defn match-string [matching-string string-coll]
  (not (empty? (filter #(seq?
                          (re-seq
                            (re-pattern (.toLowerCase matching-string)) (.toLowerCase %)))
                       string-coll))))

(match-string "Valeri" movies)





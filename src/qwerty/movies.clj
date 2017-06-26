(ns qwerty.movies
  (use clojure.core))
;list of movies
(slurp "d:/movies.txt")

(with-open [rdr (clojure.java.io/reader "d:/movies.txt")]
  (count (line-seq rdr)))

(def movies (line-seq (clojure.java.io/reader "d:/movies.txt")))

(defn movie-present? [movie-name]
  (some? (filter #(some?
                    (re-seq
                      (re-pattern (.toLowerCase movie-name)) (.toLowerCase %)))
                 movies)))

(some? (filter #(some?
                  (re-seq
                    (re-pattern (.toLowerCase "The House122")) (.toLowerCase %)))
               movies))

(movie-present? "The House111")

(some? (re-seq (re-pattern (.toLowerCase "The House")) (.toLowerCase "The House")))



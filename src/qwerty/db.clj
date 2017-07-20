(ns qwerty.db
  (:require [clojure.java.jdbc :as j]))

(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/sakila"
               :user "root"
               :password "root"})

(defn get-actors []
  (j/query mysql-db
           ["select * from actor"]))

(defn get-actor-by-id [id]
  (first (j/query mysql-db
                  [(str "select * from actor where actor_id = " id)])))

(defn daj-nesto []
  (str "ergegetg"))

(defn get-movies []
  (j/query mysql-db
           ["select * from movie"]))


;(defn insert-movies [movies]
;  (j/insert-multi! ))

(get-movies)
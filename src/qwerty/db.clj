(ns qwerty.db
  (:require [net.cgrand.enlive-html :as html])
  (:require [clj-time.core :as time])
  (:require [clj-time.format :as time-format])
  (:require [clojure.java.jdbc :as j])
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes ANY]]))

(def mysql-db {:subprotocol "mysql"
               :subname "//127.0.0.1:3306/sakila"
               :user "root"
               :password "root"})

(def actors (j/query mysql-db
  ["select * from actor"]))
(:last_name (first actors))
(:actor_id (first actors))

(defn get-actors []
  (j/query mysql-db
           ["select * from actor"]))

(defn get-actor-by-id [id]
  (first (j/query mysql-db
                  [(str "select * from actor where actor_id = " id)])))

(get-actor-by-id 3444)
(:last_name (first (get-actors)))
(:actor_id (first actors))


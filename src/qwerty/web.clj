(ns qwerty.web
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes ANY]]
            [clojure.data.json :as json]
            [qwerty.db :as db]))

(extend-type java.sql.Timestamp
  json/JSONWriter
  (-write [date out]
  (json/-write (str date) out)))


(defroutes app
  (ANY "/" [] (resource))
  (ANY "/foo" [] (resource :available-media-types ["text/html"]
                           :handle-ok "<html>Hello, Internet.</html>"))
  (ANY "/foo2" [] (resource :available-media-types ["text/html"]
                           :handle-ok (fn [ctx]
                                        (format "<html>It's %d milliseconds since the beginning of the epoch."
                                                (System/currentTimeMillis)))))
  (ANY "/foo3" [] (resource :available-media-types ["application/json"]
                           :handle-ok (fn [ctx]
                                        {:aaa 111 :bbb 222})))
  (ANY "/actor" [] (resource :available-media-types ["application/json"]
                           :handle-ok (fn [ctx]
                                        (first (db/get-actors)))))
  (ANY "/actor/:id" [id] (resource :available-media-types ["application/json"]
                           :handle-ok (fn [ctx]
                                        (db/get-actor-by-id id)))))

(def handler 
  (-> app 
      wrap-params))
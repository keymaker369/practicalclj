(ns qwerty.webparse
  (:require [net.cgrand.enlive-html :as html]
            [clj-http.client :as client]
            [clj-http.conn-mgr :as conn-mgr]))

(def req (client/get "https://www.iplocation.net/find-ip-address"
                             {:proxy-host "192.99.71.135"
                             :proxy-port 3128}))

(defn fetch-page [url]
  (html/html-resource (java.net.URL. url)))

(defn get-ip []
  (let [url "http://www.whatsmyip.org"
        url-data (client/get "https://www.iplocation.net/find-ip-address")
        selector [#{:p.subject}]]
    (first 
      (:content 
        (first 
          (html/select (html/html-snippet
                         (:body 
                           url-data)) selector))))))

(get-ip)

(html/select (fetch-page "http://example.com") selector)

(fetch-page "http://www.whatsmyip.org/")

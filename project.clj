(defproject qwerty "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler qwerty.web/handler}
  :repositories {"jitpack.io" "https://jitpack.io"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [enlive "1.1.6"]
                 [clj-http "2.0.1"]
                 [overtone/at-at "1.2.0"]
                 [clj-time "0.8.0"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [org.clojure/data.json "0.2.6"]
                 [liberator "0.13"]
                 [compojure "1.3.4"]
                 [ring/ring-core "1.2.1"]
                 [semantic-csv "0.1.0"]
                 [net.mikera/core.matrix "0.50.0"]
                 [org.clojure/math.combinatorics "0.1.1"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.clojure/data.zip "0.1.1"]
                 [com.github.ui4j.ui4j/ui4j-all "2.2.5"]])

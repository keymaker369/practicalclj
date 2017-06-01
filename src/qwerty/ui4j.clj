(ns qwerty.ui4j)

(defn browser []
  (com.ui4j.api.browser.BrowserFactory/getWebKit))

(defn page [page-url browser]
  (.navigate browser page-url))

(defn document [page]
  (.getDocument page))                                                       ;

(defn query [selector document]
  (.query document selector))

(defn query-all [selector document]
  (.queryAll document selector))

(defn present? [element]
  (.isPresent element))

(defn click [element]
  (.click (.get element)))

(defn show [page]
  (.show page))

(defn execute-script [page javascript]
  (.executeScript page javascript))

(defn scroll-to-bottom [page]
  (.executeScript page "window.scrollTo(0,document.body.scrollHeight)"))
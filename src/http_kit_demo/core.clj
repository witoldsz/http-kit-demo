(ns http-kit-demo.core
  (:gen-class)
  (:require [org.httpkit.server :refer [run-server]]
            [ring.util.response :refer [not-found]]
            [ring.middleware.resource :refer [wrap-resource]]))

(defn handler [req] (not-found "not found"))

(def app
  (-> handler
    (wrap-resource "public")
    (fn [handler]
      (fn [req]
        (with-channel req channel ; get the channel
          (if (websocket? channel) ; if you want to distinguish them
            (on-receive channel (fn [data] (send! channel data)))
            handler))))))

(defn -main
  [& args]
  (println "arguments:" args)
  (let [port (or (first args) "8080")]
    (run-server app {:port (Integer/parseInt port)})
    (println "Hello, World at port" port)))
(ns http-kit-demo.core
  (:gen-class)
  (:require [org.httpkit.server :refer [run-server with-channel on-close on-receive websocket? send!]]
            [ring.util.response :refer [not-found]]
            [ring.middleware.resource :refer [wrap-resource]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]))


(defn ws [req]
  (with-channel req channel
                (on-close channel (fn [status] (println "channel closed")))
                (if (websocket? channel)
                  (println "WebSocket channel")
                  (println "HTTP channel"))
                (on-receive channel (fn [data] (send! channel data)))))

(defroutes all-routes
           (GET "/ws" [] ws)
           (route/resources "/static")
           (route/not-found "<p>Page not found.</p>"))

(defn -main [& args]
  (println "arguments:" args)
  (let [port (or (first args) "8080")]
    (run-server all-routes {:port (Integer/parseInt port)})
    (println "Hello, World at port" port)))
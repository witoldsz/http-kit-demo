(ns http-kit-demo.core
  (:gen-class)
  (:require [org.httpkit.server :refer [run-server]]))

(defn app [req]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "hello HTTP!"})

(defn -main
  [& args]
  (println "arguments:" args)
  (let [port (or (first args) "8080")]
    (run-server app {:port (Integer/parseInt port)})
    (println "Hello, World at port" port)))
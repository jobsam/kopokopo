(ns kopokopo.core
  (:gen-class)
  (:require [kopokopo.service :as service]
            [ring.middleware.reload :refer [wrap-reload]])
  (:use org.httpkit.server))

(defn -main
  "I initialize then start the application"
  []
  (println "Kopokopo server started .....")
  (run-server (-> #'service/app wrap-reload) {:port 9011}))

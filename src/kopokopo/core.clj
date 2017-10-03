(ns kopokopo.core
  (:gen-class)
  (:require [kopokopo.service :as service]
            [ring.middleware.reload :refer [wrap-reload]]
            [clojure.tools.logging :as log]
            [kopokopo.configutil :as config])
  (:use org.httpkit.server))

(defn -main
  "I initialize then start the application"
  []
  (config/init-config)
  (log/infof "Server starting at port (%s)....." config/service-port)
  (run-server (-> #'service/app wrap-reload) {:port config/service-port}))

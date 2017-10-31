(ns kopokopo.core
  (:gen-class)
  (:require [kopokopo.app :as app]))

(defn -main
  "Initializes the application. It expects a map of data sent from
  the KopoKopo APIs"
  [params]
  (app/validate-data params))

(ns kopokopo.configutil
  (:import (java.io FileInputStream)
           (java.util Properties))
  (:require [environ.core :refer [env]]
            [clojure.tools.logging :as log])
  (:use [clojure.walk]))



(defn- read-properties [key]
  (let [file-input-stream (new FileInputStream (env key))
        properties (Properties.)]
    (-> properties (.load file-input-stream))
    (keywordize-keys (into {} properties))))

(def app-configs (atom {}))


(defn load-config [config]
  (let [properties (Properties.)
        fis (FileInputStream. config)]
    ; populate the properties hashmap with values from the output stream
    (.. properties (load fis))
    (keywordize-keys (into {} properties))))

(defn config-value [name & args]
  (let [value (@app-configs name)]
    (if-not (empty? value)
      (let [args (when args (apply assoc {} args))
            {type :type} args
            args (dissoc args :type)
            value (if (vector? value)
                    (loop [x (first value)
                           xs (next value)]
                      (let [properties (dissoc x :value)]
                        (if (or (and (empty? args)
                                     (empty? properties))
                                (and (not (empty? args))
                                     (every? (fn [[k v]]
                                               (= (properties k) v))
                                             args)))
                          (x :value)
                          (when xs
                            (recur (first xs) (next xs))))))
                    value)]
        (when value
          (let [value #^String value]
            (cond (or (nil? type) (= type :string)) value
                  ;; ---
                  (= type :int) (Integer/valueOf value)
                  (= type :long) (Long/valueOf value)
                  ;(= type :integer) (resize-int (Long/valueOf value))
                  (= type :bool) (contains? #{"yes" "true" "y" "t" "1"}
                                            (.toLowerCase value))
                  (= type :keyword) (keyword value)
                  (= type :path) (java.io.File. value)
                  (= type :url) (java.net.URL. value)))))
      (if-let [args (when args (apply assoc {} args))]
        (args :default)
        nil))))


(defn init-config []
  (log/infof "Initializing configurations")
  (reset! app-configs (read-properties :kyc-service-config))
  (log/infof "config = %s" @app-configs)

  ;;Service config
  (def service-port (config-value :service.port :type :int :default 7979))
  ;; Database settings
  (def jdbc-driver (config-value :jdbc.driver))
  (def jdbc-url (config-value :jdb.url))
  (def db-user (config-value :jdbc.user))
  (def db-password (config-value :jdbc.password))

  )
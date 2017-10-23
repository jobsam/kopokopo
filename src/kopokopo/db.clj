(ns kopokopo.db
  (:import com.mchange.v2.c3p0.ComboPooledDataSource)
  (:require [clj-time.jdbc]
            [kopokopo.configutil :as config]
            [clojure.tools.logging :as log]))

(def ^:dynamic connection nil)


(defn pooled-spec
  "return pooled conn spec.
   Usage:
     (def pooled-db (pooled-spec db-spec))
     (with-connection pooled-db ...)"
  []

  (def db-spec
    (assoc {}
      :classname config/jdbc-driver
      :url config/jdbc-url
      :user config/db-user
      :password config/db-password))

  (let [cpds (doto
               (ComboPooledDataSource.)
               (.setDriverClass (:classname db-spec))
               (.setJdbcUrl (:url db-spec))
               (.setUser (:user db-spec))
               (.setPassword (:password db-spec)))]
    {:datasource cpds}))

(def pooled-db (delay (pooled-spec)))

(defn db-connection [] @pooled-db)
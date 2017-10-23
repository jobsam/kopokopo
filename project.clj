(defproject kopokopo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0"]
                 [javax.servlet/servlet-api "2.5"]
                 [ring "1.6.2"]
                 [ring-middleware-format "0.7.2"]
                 [ring/ring-json "0.4.0"]
                 [compojure "1.6.0"]
                 [pandect "0.6.1"]
                 [base64-clj "0.1.1"]
                 [org.clojure/tools.logging "0.4.0"]
                 [clj-time "0.13.0"]
                 [environ "1.1.0"]
                 [org.clojure/java.jdbc "0.7.2"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [c3p0/c3p0 "0.9.1.2"]
                 ]
  :jvm-opts ["-Dkyc.service.config=/home/jgateri/dev/k2/kopokopo/resources/config/dev/config.properties"]
  :main ^:skip-aot kopokopo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

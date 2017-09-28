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
                 [compojure "1.6.0"]]
  :main ^:skip-aot kopokopo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

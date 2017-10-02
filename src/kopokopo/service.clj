(ns kopokopo.service
  (:require
    [kopokopo.app :as app]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [compojure.handler :as handler]
    [ring.middleware.format :refer [wrap-restful-format]]
    [ring.util.response :refer [response]]))

(defn transaction-consumer
  "I receive transactions from the endpoint then channel it to the form"
  [req]
  ;(println (str "KopoKopo sent ->" req))
  (let [response (app/validate-data (:params req))]
    )
  #_(response
    {:status "01" :description "Accepted" :subscriber_message "Thank you {} for your payment of Ksh {}. We value you"})
  )

(defn index
  "I don't do anything big. I just give a generic response"
  [req]
  (println (str "Hit request ->" req))
  (response {:status :ok :description "Welcome to KopoKopo API implementation in Clojure!"}))

(defroutes app-routes
           (POST "/" request (index request) )
           (context "/v1" []
                    (POST "/transactions" request (transaction-consumer request)))
           (route/not-found "Sorry we can't handle this request!"))

(def app
  (-> app-routes
      (wrap-restful-format :formats [:json :json-kw])
      handler/api))


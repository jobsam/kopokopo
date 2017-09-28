(ns kopokopo.service
  (:require
    [compojure.core :refer :all]
    [compojure.route :as route]
    [compojure.handler :as handler]
    [ring.middleware.format :refer [wrap-restful-format]]
    [ring.util.response :refer [response]]))

(defn transaction-consumer
  "I receive transactions from the endpoint then channel it to the form"
  [arg]
  (println arg)
  {:status :ok :description "transaction consumer"}
  )

(defn index
  "I don't do anything big. I just give a response"
  [arg]
  (println arg)
  (response {:status :ok :description "Welcome to KopoKopo!"}))

(defroutes app-routes
           (POST "/" request (index request) )
           (context "/v1" []
                    (POST "/transactions" request (transaction-consumer request)))
           (route/not-found "Sorry we can't handle this request!"))

(def app
  (-> app-routes
      (wrap-restful-format :formats [:json :json-kw])
      handler/api))


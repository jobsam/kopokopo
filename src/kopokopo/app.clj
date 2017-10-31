(ns kopokopo.app
  (:require [pandect.algo.sha1 :refer :all]
            [base64-clj.core :as base64]
            [clojure.string :as string]))

(declare return-base-string get-key return-signature)
(defn validate-data
  "Receives a map of parameter sent from the KopoKopo APIs. Among the parameters needed is the symmetric-key
  of your KopoKopo account. It then validates the transactions using the two signatures and returns 'failed'
  or 'success' status with a short description"
  [{:keys [service_name business_number transaction_reference internal_transaction_id
           transaction_timestamp transaction_type account_number sender_phone first_name
           middle_name last_name amount currency signature symmetric-key]}]
  (if (nil? symmetric-key)
    {:status :failed :description "symmetric key is empty"}
    (let [base-string (return-base-string {:service_name service_name
                                           :business_number business_number
                                           :transaction_reference transaction_reference
                                           :internal_transaction_id internal_transaction_id
                                           :transaction_timestamp transaction_timestamp
                                           :transaction_type transaction_type
                                           :account_number account_number
                                           :sender_phone sender_phone
                                           :first_name first_name
                                           :middle_name middle_name
                                           :last_name last_name
                                           :amount amount
                                           :currency currency
                                           :signature signature})
          new-signature (return-signature {:base-string base-string :symmetric-key symmetric-key})]
      (if (= new-signature signature)
        {:status :success :description "valid transaction"}
        {:status :failed :description "invalid transaction"}))))

(defn- return-base-string
  "Calculates the base string of the that will be used to generate the MAC.
  This is done by sorting the the parameters in ascending then separate them with &"
  [arg-map]
  (let [ordered-map (into (sorted-map) arg-map)
        my-string (for [[k v] ordered-map]
                    (str (name k) "=" v))
        base-string (string/join "&" my-string)]
    base-string))

(defn- return-signature
  "Returns the signature after calculating it using HMAC (HMAC-SHA1)
  and the base-string generated as the parameter"
  [{:keys [base-string symmetric-key]}]
  (let [hmac-sha1 (sha1-hmac base-string symmetric-key)
        new-signature (base64/encode hmac-sha1)]
    new-signature))


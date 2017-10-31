# kopokopo

Kopo Kopo Inc is a kenyan company that offers a platform to receive and manage mobile payments. 
Please read more here http://kopokopo.co.ke/. This is basic implementation of the KopoKopo's 
third party developer(https://app.kopokopo.com/push_api) api using Clojure language.

## Usage

This is an implementation of transaction validation using HMAC (HMAC-SHA1). A map of KopoKopo parameters
need to be passed as the parameter and in return, they will receive two statuses: either :failed for a transaction 
that is not validated well or success for a well validated transaction. 

## TODO

- Writing tests.

### Disclaimer

This project is still under development. Always check the change log.

## License
This project is open for the public to use and edit it in anyway they feel will suit there need.
 
Copyright © 2017 Job Gateri 

Distributed under the Eclipse Public License, same as Clojure.
# kopokopo

Kopo Kopo Inc is a kenyan company that offers a platform to receive and manage mobile payments. 
Please read more here http://kopokopo.co.ke/. This is basic implementation of the Kopo Kopo's 
third party developer(https://app.kopokopo.com/push_api) api using Clojure language.

## Usage

The app is built to receive payment notification sent from the Kopo Kopo system. It receives a post 
notification, validates it, then respond appropriately. You will need to expose the app's transaction endpoint to Kopo Kopo
ie 'http://127.0.0.1:xxxx/v1/transactions' after you have compiled the app using lein uberjar:

    $ java -jar kopokopo-0.1.0-SNAPSHOT-standalone.jar
        

## TODO

- Add more functionality upon receiving the right notification E.g storing data in the db.
- Better handling of configs.
- Better logging experience.
- Writing tests.

### Disclaimer

This implementation may not suit your need, hence you may need to do your own customizations. 
The programmer of this project is not responsible of any damage caused by using this implementation in any way.
This project is still under development. Always check the change log.

## License
This project is open for the public to use and edit it in anyway they feel will suit there need.
 
Copyright © 2017 Job Gateri 

Distributed under the Eclipse Public License, same as Clojure.
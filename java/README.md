# Erick's thoughts

This was an interesting take on the definition of a coin mixer. In my experience with mixers the input/outputs were 1:1, and the house contained multiple addresses to send from instead.

### Get started
* Requires Java 11 +
* The project includes a prebuilt jar with dependencies built with maven called `target/mixer-1.0-SNAPSHOT.jar`, it can be ran via `java -jar target/mixer-1.0-SNAPSHOT.jar`
* `mvn package` to build the jar.
* `mvn test` to run tests, reports are sent to `target/surefire-reports`

### Javadocs
* Can be found in `javadocs/index.html`

### Language of Choice:
* Java 11 - probably not the best choice for this project (due to consumption of API's), but Java can be fun and there wasn't an existing template for it.


### Libraries used:
* Jackson - to parse JSON
* JUnit 5 & surefire for testing

### Some assumptions are made due to time constraints:
* API url is hardcoded and doesn't read from env variables/env file.
* Synchronous requests are OK.
* All methods involving requests are transactional and atomic. All requests are expected to work
* Long polling the transaction endpoint to verify deposits is not needed.
* In order to simplify business logic, I hid many network exceptions. Depending on my stakeholders, this could be changed to make the exceptions visible.
  * InsufficentFundsException was implemented according to api specs.
* Unit tests are bare minimum

### Project was not forked, used the template instead.

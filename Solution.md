**Introduction**:
# CryptoCurrencyExchange
This is sample application to demonstrate realtime conversion rates of different crypto currencies as per locale

**System requirements:**

1)OpenJDK for Java 1.8
2)Git
3)Maven 3.6.1 or higher
4)Junit 4.13
5)Swagger 1.5.2
6)Project Lombok: https://projectlombok.org
7)Jenkins


**Building the project:**

To build the JAR and run some tests:
 ** mvn clean install**

To run the application:
  **java -jar target/crypto-exchange-1.0-SNAPSHOT.jar**

URL: http://localhost:8080/signin
Assumptions:

In the UI, I have used a button to get the exchange rate for the crypto currency which I felt from users perspective
provides the user with more independence to select, submit and get the results.
Apart from that, the symbol for each currency as per locale,its not providing the required results from the Currency api in java,
i.e, provides USD instead of $ for Locale.US.

I have added a registration page as well along with login.

I have used https://coinlayer.com/ to retrieve the real-time exchange rate for the crypto currency.
Since its based on access-key, the provided one may have few tries left.

For convenience,I have used around 10 different type of crypto currencies.

I have added a JenkinsFile with minimal configuration to support CI/CD.

Improvements:
Security and web UI can be enhanced.

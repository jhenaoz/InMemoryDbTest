# GettingStarted

How to start the GettingStarted application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/1.0-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:9005`
1. To run the test `mvn test`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

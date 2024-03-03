curl -w'\n' localhost:8080 \
-H "Ce-Specversion: 1.0" \
-H "Ce-Time: 2024-03-03T21:14:52.583+00:00" \
-H "Ce-Type: com.example.springevent" \
-H "Ce-Source: spring.io/spring-event" \
-H "Content-Type: application/json" \
-H "Ce-Id: 0001" \
-d '{"releaseDate":"2004-03-24", "releaseName":"Spring Framework", "version":"1.0"}'


# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.example.spring-events' is invalid and this project uses 'com.example.springevents' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.3/maven-plugin/reference/html/#build-image)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#web.reactive)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.2.3/reference/htmlsingle/index.html#actuator)
* [Function](https://docs.spring.io/spring-cloud-function/docs/current/reference/html/spring-cloud-function.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Additional Links
These additional references should also help you:

* [Various sample apps using Spring Cloud Function](https://github.com/spring-cloud/spring-cloud-function/tree/main/spring-cloud-function-samples)


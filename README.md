# spring-boot-microservices
Eureka Service–  This Service will register every microservice and then the client microservice will look up the Eureka server to get a dependent microservice to get the job done.This Eureka Server is owned by Netflix and in this, Spring Cloud offers a declarative way to register and invoke services by Java annotation. Item Catalog Service – This service will generate the list of Sports brands which are popular in the market.  Edge Service – It is similar to the standalone Item service created in Bootiful Development with Spring Boot and Angular. However, it will have fallback capabilities which prevent the client from receiving an HTTP error when the service is not available
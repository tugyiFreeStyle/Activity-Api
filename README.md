# Activity App

This project is developed by Java 11. The Spring boot project is used.

  - Java 11 
  - Gradle

## Frameworks & Libraries

  - SpringBoot
  - Lombok
  - Junit5
  - AssertJ
  - Mockito
  - JFixture
  - Springfox (Swagger)
  
  ## Warming up

Api contracts easily can be viewed at http://localhost:8080/swagger-ui.html

 ## Structure
 Hexagonal architecture is used for project structure.
 
 ## Features
 
 - Restfull best practises. (Semantic name routing, api versioning, Meaningfull error response with particular Http Code)
 - Req-response logging handled by globally using “IncomingRequestInterceptor” (Separation of Concerns)
 - Global exception handling by "GlobalExceptionHandler".
 - Swagger usage for api doc.
 - Valuable unit tests for essential parts.
 - Followed the SOLID.
 - In-Proc bus is used to separate command & query handlers.
 - For reading csv gracefully, openCSV is used.
 - InMemory store is used.
 - Unit testing

 
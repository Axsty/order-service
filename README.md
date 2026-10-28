# ORDER SERVICE

En mikrotjänst som skapar ordrar i en fullstack-applikation.

Detta är ett skolprojekt där vi alla har gjort en mikrotjänst var. Länkar till de andra i gruppens repositories finns nedan.
- [auth-service](https://github.com/SaraSoltani98/auth-server)
- [product-service](https://github.com/fabian-berencreutz/product-service-majprojekt)
- [email-service](https://github.com/maryam12370/email-service)

## Hur det fungerar

1. Klienten skickar en `POST /order` med en JWT-token.
2. Order-service skickar ett anrop (produkt, kvantitet och JWT-token) till **product-service** som minskar lagersaldot och returnerar produktinformation.
3. Order-service bygger ordern, räknar ut totalpriset och sparar den i databasen.
4. En orderbekräftelse skickas till en RabbitMQ-kö där **email-service** tar emot den.
5. Ordern och statuskod `201 Created` returneras.

## Tekniker
- Java 17, Spring Boot 4.0.6, Maven
- Spring Security med OAuth2/JWT
- Spring Data JPA, PostgreSQL (hostad på Render) och H2 för lokal körning
- RabbitMQ
- Lombok
- Swagger / OpenAPI

## Köra lokalt

1. Klona repot.
2. Miljövariabler som behöver sättas i IntelliJ configuration `SPRING_PROFILES_ACTIVE=local`
3. Om du har satt miljövariabeln kan du starta via den gröna playknappen, annars kör du `mvn spring-boot:run -Dspring-boot.run.profiles=local` i terminalen
4. Swagger-endpoint för **order-service** [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)
5. För att kunna skapa en order behöver man en JWT-token. Registrering och inloggning görs via [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) **kräver att man har auth-service körandes**

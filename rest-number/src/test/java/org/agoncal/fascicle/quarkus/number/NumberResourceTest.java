package org.agoncal.fascicle.quarkus.number;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static javax.ws.rs.core.Response.Status.OK;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.not;

@QuarkusTest
public class NumberResourceTest {
  @Test
  void shouldGenerateBookNumber() {
    given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when()
        .get("/api/numbers/book").then()
        .statusCode(OK.getStatusCode())
        .body("$", hasKey("isbn_10"))
        .body("$", hasKey("isbn_13"))
        .body("$", hasKey("asin"))
        .body("$", hasKey("ean_8"))
        .body("$", hasKey("ean_13"))
        .body("$", not(hasKey("generationDate")));
  }

  @Test
  void shouldPingOpenAPI() {
    given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).when()
        .get("/openapi").then()
        .statusCode(OK.getStatusCode());
  }

  @Test
  void shouldPingSwaggerUI() {
    given().when()
        .get("/swagger-ui").then()
        .statusCode(OK.getStatusCode());
  }
}

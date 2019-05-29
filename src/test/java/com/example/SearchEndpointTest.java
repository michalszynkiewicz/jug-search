package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class SearchEndpointTest {

    @Test
    public void testSearchEndpoint() {
        given()
          .when().get("/search?query=java&userId=Michal")
          .then()
             .statusCode(200)
             .body(containsString("https://spring.io"));
    }

}
package ca.demo;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class RestConnectorIT {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestConnectorIT.class);

    @Test
    public void testAll() {
        String nodeUUID = UUID.randomUUID().toString();

        //create
        String node = given()
                .contentType("application/json; charset=UTF-8")
                .body(nodeUUID)
                .when()
                .post("/rest/nodes")
                .then()
                .statusCode(200)
                .extract().as(String.class);

        assertNotNull(node);

        //get
        String nodeFound = given()
                .pathParam("id", nodeUUID)
                .get("/rest/nodes/{id}")
                .then()
                .statusCode(200)
                .extract().as(String.class);

        assertNotNull(nodeFound);

        //get all
        List<String> nodes = given()
                .pathParam("id", nodeUUID)
                .when()
                .get("/rest/nodes/{id}")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", String.class);

        assertNotNull(nodes);

    }

}

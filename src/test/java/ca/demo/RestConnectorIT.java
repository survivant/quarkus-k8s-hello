package ca.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class RestConnectorIT {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestConnectorIT.class);

    @Inject
    ObjectMapper mapper;

    @Test
    public void testAll() throws JsonProcessingException, JsonPatchException {
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

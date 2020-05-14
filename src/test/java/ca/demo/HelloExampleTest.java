package ca.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonValue;

import java.io.StringReader;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class HelloExampleTest {

    @Inject
    ObjectMapper mapper;

    @Inject
    @RestClient
    RestConnector connector;

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }


    @Test
    public void testPatch() throws Exception {
        String nodeUUID = UUID.randomUUID().toString();

        //patch
        Fruit fruit = new Fruit();

        JsonValue source = Json.createReader(new StringReader(mapper.writeValueAsString(fruit))).readValue();

        fruit.color="blue";

        JsonValue target = Json.createReader(new StringReader(mapper.writeValueAsString(fruit))).readValue();

        JsonNode dataNode = JsonLoader.fromString(Json.createMergeDiff(source, target).toJsonValue().toString());
        JsonMergePatch jsonMergePatch = JsonMergePatch.fromJson(dataNode);

        connector.updatePartial(UUID.randomUUID(), jsonMergePatch);

    }

}
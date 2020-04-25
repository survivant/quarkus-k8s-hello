package ca.demo;

import com.example.k8s.ChartDTO;
import com.example.k8s.CodecTemplateDTO;
import com.example.k8s.codec.CodecRequestBean;
import com.example.k8s.codec.CodecResponseBean;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class HelloExampleTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }



}
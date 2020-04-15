package ca.demo;

import com.comact.iep.dto.ChartDTO;
import com.comact.iep.dto.CodecTemplateDTO;
import com.comact.iep.dto.codec.CodecRequestBean;
import com.comact.iep.dto.codec.CodecResponseBean;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
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

    @Test
    public void testUpdate() throws Exception {

        CodecRequestBean requestBean = new CodecRequestBean();
        ChartDTO chartDTO = new ChartDTO(null, null, null);

        CodecTemplateDTO codecTemplateDTO = new CodecTemplateDTO();
        codecTemplateDTO.setVersion("0.0.1");
        codecTemplateDTO.setName("template");
        codecTemplateDTO.getCharts().add(chartDTO);

        requestBean.setTemplate("helloCodec-template");
        requestBean.setUuid("uuid");
        requestBean.setCodecTemplate(codecTemplateDTO);

        CodecResponseBean responseBean = given()
                .contentType("application/json; charset=UTF-8")
                .body(requestBean)
                .when()
                .post("/hello/codec/update")
                .then()
                .statusCode(200)
                .extract().as(CodecResponseBean.class);
    }

}
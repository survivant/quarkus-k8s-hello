package ca.demo;

import com.comact.iep.dto.codec.CodecRequestBean;
import com.comact.iep.dto.codec.CodecResponseBean;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/hello")
public class HelloExample {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @POST
    @Path("/codec/update")
    public CodecResponseBean update(@Valid CodecRequestBean requestBean) throws Exception {

        System.out.println("We shouldn't be here");
        Set<ConstraintViolation<CodecRequestBean>> validate = Validation.buildDefaultValidatorFactory().getValidator().validate(requestBean);

        System.out.println(validate);

        return null;
    }
}
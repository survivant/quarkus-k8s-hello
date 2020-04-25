package ca.demo;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.UUID;

/**
 * Allow to expose the Rest client easily for testing purpose with Swagger or junit
 */
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/rest")
public class RestService {

    @Inject
    @RestClient
    RestConnector connector;

    @GET
    @Path("/nodes/{nodeId}")
    public Collection<String> list(@PathParam("nodeId") UUID codecNodeId) {
        return connector.list(codecNodeId);
    }

    @POST
    @Path("/init/{nodeId}")
    public void create(@PathParam("nodeId") UUID nodeId) {
        connector.create(nodeId);
    }

    @GET
    @Path("/nodes/{id}")
    public String get(@PathParam("id") UUID id) {
        return connector.get(id);
    }


}

package ca.demo;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.UUID;

@RegisterRestClient
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api")
public interface RestConnector {

    @GET
    @Path("/nodes/{nodeId}")
    Collection<String> list(@PathParam("nodeId") UUID codecNodeId);

    @POST
    @Path("/init/{nodeId}")
    void create(@PathParam("nodeId") UUID nodeId);

    @GET
    @Path("/nodes/{id}")
    String get(@PathParam("id") UUID id);

}

package ca.demo;

import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.UUID;

@ApplicationScoped
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

    @PATCH
    @Operation(summary = "Patch a node", description = "Patch a node")
    @Produces("application/merge-patch+json")
    @Path("/nodes/patch/{id}")
    void updatePartial(@PathParam("id") UUID id, JsonMergePatch patch);

}

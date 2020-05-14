package ca.demo;

import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

    @PATCH
    @Produces("application/merge-patch+json")
    @Path("/nodes/patch/{id}")
    public void updatePartial(@PathParam("id") UUID id, JsonMergePatch patch) {
        connector.updatePartial(id, patch);
    }


}

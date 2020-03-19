package ca.demo;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.client.KubernetesClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/namespace")
public class Namespaces {

    private final KubernetesClient kubernetesClient;

    public Namespaces(KubernetesClient kubernetesClient) {
        this.kubernetesClient = kubernetesClient;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public List<Namespace> namespaces() {
        return kubernetesClient.namespaces().list().getItems();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/label/{label}")
    public List<Namespace> namespacesWithLabel(@PathParam("label") String label) {
        return kubernetesClient.namespaces().withLabel(label).list().getItems();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/label/{label}/{labelValue}")
    public List<Namespace> namespacesWithLabelValue(@PathParam("label") String label, @PathParam("labelValue") String labelValue) {
        return kubernetesClient.namespaces().withLabel(label, labelValue).list().getItems();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Namespace namespacesWithName(@PathParam("name") String name) {
        return kubernetesClient.namespaces().withName(name).get();
    }
}
package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/modules")
public class ModuleWS {
    private static final ModuleBusiness moduleBusiness = new ModuleBusiness();
    private static final UniteEnseignementBusiness ueBusiness = new UniteEnseignementBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createModule(Module module) {
        boolean created = moduleBusiness.addModule(module);
        if (!created) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Referenced uniteEnseignement not found")
                    .build();
        }

        List<Module> response = Collections.singletonList(module);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModules() {
        return Response.status(Response.Status.OK).entity(moduleBusiness.getAllModules()).build();
    }

    @GET
    @Path("/{matricule}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(@PathParam("matricule") String matricule) {
        Module module = moduleBusiness.getModuleByMatricule(matricule);
        if (module == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(module).build();
    }

    @GET
    @Path("/UE")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(@QueryParam("codeUE") int codeUE) {
        UniteEnseignement ue = ueBusiness.getUEByCode(codeUE);
        if (ue == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(moduleBusiness.getModulesByUE(ue)).build();
    }

    @DELETE
    @Path("/{matricule}")
    public Response deleteModule(@PathParam("matricule") String matricule) {
        boolean deleted = moduleBusiness.deleteModule(matricule);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/{matricule}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateModule(@PathParam("matricule") String matricule, Module updatedModule) {
        if (moduleBusiness.getModuleByMatricule(matricule) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        boolean updated = moduleBusiness.updateModule(matricule, updatedModule);
        if (!updated) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedModule).build();
    }
}

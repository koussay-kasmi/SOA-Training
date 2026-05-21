package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/unite")
public class UeWS {
    private static final UniteEnseignementBusiness ueBusiness = new UniteEnseignementBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUnitesEnseignement() {
        return Response.ok(ueBusiness.getListeUE()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUniteEnseignement(UniteEnseignement ue){
        ueBusiness.addUniteEnseignement(ue);
        return Response.status(Response.Status.CREATED).entity(ue).build();
    }

    @GET
    @Path("/semestre/{semestre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBySemestre(@PathParam("semestre") int semestre) {
        List<UniteEnseignement> liste = ueBusiness.getUEBySemestre(semestre);
        return Response.ok(liste).build();
    }

    @GET
    @Path("/UE")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUniteEnseignementByCode(@QueryParam("code") int code) {
        UniteEnseignement ue = ueBusiness.getUEByCode(code);
        if (ue == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ue).build();
    }

    @DELETE
    @Path("/{code}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUniteEnseignement(@PathParam("code") int code){
        boolean deleted = ueBusiness.deleteUniteEnseignement(code);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("UE with code " + code + " not found")
                    .build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/UE/{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUniteEnseignement(@PathParam("code") int code, UniteEnseignement ue) {
        boolean updated = ueBusiness.updateUniteEnseignement(code, ue);
        if (!updated) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(ue).build();
    }
}

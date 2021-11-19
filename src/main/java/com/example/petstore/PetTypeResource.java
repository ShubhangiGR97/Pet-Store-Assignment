package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/petType")
@Produces("application/json")
public class PetTypeResource {
    // get all types
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "petType"))) })
    @GET
    public Response getPetType() {
        ArrayList<PetType> petTypes = new ArrayList<PetType>();
        petTypes = PetTypeSingleton.getInstance().getPetTypes();
        return Response.ok(petTypes).build();
    }

    // get pet by id
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "petType"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })
    @GET
    @Path("{petTypeId}")
    public Response getType(@PathParam("petTypeId") int petTypeId) {
        if (petTypeId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }
        PetType petType = new PetType();
        petType = PetTypeSingleton.getInstance().getPetType(petTypeId);
        return Response.ok(petType).build();

    }

    // Create new pet
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Added new pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "petType"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })
    @POST
    @Path("addPetType")
    @Produces("application/json")
    public Response addPetType(PetType petType) {
        ArrayList<PetType> petTypes = new ArrayList<PetType>();
        petTypes = PetTypeSingleton.getInstance().addPetType(petType);
        return Response.ok(petTypes).build();

    }

    // update pet
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Updated pet details", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "petType"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })
    @POST
    @Path("updatePetType")
    @Produces("application/json")
    public Response updatePetType(PetType petType) {
        ArrayList<PetType> petTypes = new ArrayList<PetType>();
        petTypes = PetTypeSingleton.getInstance().updatePetType(petType);
        return Response.ok(petTypes).build();

    }

    // delete pet
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Updated pet details", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "petType"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.") })
    @DELETE
    @Path("deletePetType/{petTypeId}")
    @Produces("application/json")
    public Response deletePetType(@PathParam("petTypeId") int petTypeId) {
        ArrayList<PetType> petTypes = new ArrayList<PetType>();
        petTypes = PetTypeSingleton.getInstance().deletePetType(petTypeId);
        return Response.ok(petTypes).build();

    }
}

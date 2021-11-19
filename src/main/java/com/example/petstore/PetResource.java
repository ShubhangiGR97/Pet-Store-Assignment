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

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {
	// get all pets
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	public Response getPets() {
		ArrayList<Pet> pets = new ArrayList<Pet>();
		pets = PetSingleton.getInstance().getPets();
		return Response.ok(pets).build();
	}

	// get pet by id
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		pet = PetSingleton.getInstance().getPet(petId);
		return Response.ok(pet).build();
		
	}

	// Create new pet
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Added new pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@POST
	@Path("addPets")
	@Produces("application/json")
	public Response addPet(Pet pet) {
		ArrayList<Pet> pets = new ArrayList<Pet>();
		if( pet.getPetName() != null && pet.getPetAge() != null && pet.getPetType() != null){
			pets = PetSingleton.getInstance().addPets(pet);
			return Response.ok(pets).build();
		}else{
			return Response.ok("{\n" + "\"successful\":false\n" + "}").build();

		}
	}

	// update pet
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Updated pet details", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@POST
	@Path("updatePet")
	@Produces("application/json")
	public Response updatePet(Pet pet) {
		ArrayList<Pet> pets = new ArrayList<Pet>();
		pets = PetSingleton.getInstance().updatePet(pet);
		return Response.ok(pets).build();

	}

	// delete pet
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Updated pet details", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@DELETE
	@Path("deletePet/{petId}")
	@Produces("application/json")
	public Response deletePet(@PathParam("petId") int petId) {
		ArrayList<Pet> pets = new ArrayList<Pet>();
		pets = PetSingleton.getInstance().deletePet(petId);
		return Response.ok(pets).build();

	}


	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "delete pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("/search")
	public Response searchPet(@DefaultValue("-1") @QueryParam("id") int petId,
							  @DefaultValue("null") @QueryParam("name") String petName,
							  @DefaultValue("0") @QueryParam("age") int petAge)
	{
		boolean isPetFound = false;
		int id = 0;

		if(petId != -1 && petName.equals("null") && petAge == 0){
			if (petId < 0) {
				return Response.status(Status.NOT_FOUND).build();
			}

			for (int i=0;i<PetSingleton.getInstance().getPets().size();i++){
				if(petId == PetSingleton.getInstance().getPets().get(i).getPetId()){
					isPetFound = true;
					id = i;
				}
			}
			if(isPetFound){
				return Response.ok(PetSingleton.getInstance().getPets().get(id)).build();
			}else{
				return Response.ok("There is no pet with id = "+petId).build();
			}
		}
		else if(petId == -1 && !petName.equals("null") && petAge == 0){
			for (int i=0;i<PetSingleton.getInstance().getPets().size();i++){
				if(petName.equals(PetSingleton.getInstance().getPets().get(i).getPetName())){
					isPetFound = true;
					id = i;
				}
			}
			if(isPetFound){
				return Response.ok(PetSingleton.getInstance().getPets().get(id)).build();
			}else{
				return Response.ok("There is no pet with name = "+petName).build();
			}
		}
		else if(petId == -1 && petName.equals("null") && petAge != 0){
			List<Pet> temp = new ArrayList<Pet>();
			for (int i=0;i<PetSingleton.getInstance().getPets().size();i++){
				if(petAge == PetSingleton.getInstance().getPets().get(i).getPetAge()){
					isPetFound = true;
					id = i;
					temp.add(PetSingleton.getInstance().getPets().get(id));
				}
			}
			if(isPetFound){
				return Response.ok(temp).build();
			}else{
				return Response.ok("There is no pet with age = "+petAge).build();
			}
		}
		else{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}

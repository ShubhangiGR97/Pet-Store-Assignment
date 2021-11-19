package com.example.petstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "petType")
public class PetType {
    @Schema(required = true, description = "Pet type")
    @JsonProperty("pet_id")
    private Integer petTypeId;

    @Schema(required = true, description = "Pet type")
    @JsonProperty("pet_type_id")
    private String petType;

    public PetType(){}

    public PetType(Integer petId, String petType) {
        this.petTypeId = petId;
        this.petType = petType;
    }

    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }
}

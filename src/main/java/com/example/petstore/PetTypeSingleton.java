package com.example.petstore;

import org.eclipse.microprofile.openapi.models.parameters.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PetTypeSingleton {
    private static PetTypeSingleton petTypeSingleton;
    public ArrayList<PetType> petTypes = new ArrayList<PetType>();

    public static PetTypeSingleton getInstance(){
        if(petTypeSingleton == null){
            petTypeSingleton = new PetTypeSingleton();
        }
        return petTypeSingleton;
    }

    private PetTypeSingleton(){

        PetType petType1 = new PetType();
        petType1.setPetTypeId(1);
        petType1.setPetType("Bird");

        PetType petType2 = new PetType();
        petType2.setPetTypeId(2);
        petType2.setPetType("Fish");

        PetType petType3 = new PetType();
        petType3.setPetTypeId(3);
        petType3.setPetType("Cat");

        petTypes.add(petType1);
        petTypes.add(petType2);
        petTypes.add(petType3);
    }

    public ArrayList<PetType> getPetTypes(){
        return petTypes;
    }

    public PetType getPetType(Integer petTypeId){
        for(PetType petType : petTypes){
            if(Objects.equals(petType.getPetTypeId(), petTypeId)){
                return petType;
            }
        }
        return null;
    }
    public ArrayList<PetType> addPetType(PetType petType){
        petTypes.add(petType);
        return petTypes;
    }

    public ArrayList<PetType> updatePetType(PetType petType){
        for(PetType tempPetType : petTypes){
            if(petType.getPetTypeId() == tempPetType.getPetTypeId()){
                tempPetType.setPetType(petType.getPetType());
                return petTypes;
            }
        }
        return null;
    }
    public ArrayList<PetType> deletePetType(int petTypeId){
        for(PetType petType : petTypes){
            if(Objects.equals(petType.getPetTypeId(), petTypeId)){
                petTypes.remove(petType);
                return petTypes;
            }
        }
        return null;
    }
}

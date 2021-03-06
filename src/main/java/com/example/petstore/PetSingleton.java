package com.example.petstore;

import org.eclipse.microprofile.openapi.models.parameters.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class PetSingleton {
    private static PetSingleton petSingleton;
    public ArrayList<Pet> pets = new ArrayList<Pet>();

    public static PetSingleton getInstance(){
        if(petSingleton == null){
            petSingleton = new PetSingleton();
        }
        return petSingleton;
    }

    private PetSingleton(){
//        ArrayList<Pet> pets = new ArrayList<Pet>();
        Pet pet1 = new Pet();
        pet1.setPetId(1);
        pet1.setPetAge(3);
        pet1.setPetName("Boola");
        pet1.setPetType("Dog");

        Pet pet2 = new Pet();
        pet2.setPetId(2);
        pet2.setPetAge(4);
        pet2.setPetName("Sudda");
        pet2.setPetType("Cat");

        Pet pet3 = new Pet();
        pet3.setPetId(3);
        pet3.setPetAge(2);
        pet3.setPetName("Peththappu");
        pet3.setPetType("Bird");

        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);
    }

    public ArrayList<Pet> getPets(){
        return pets;
    }

    public Pet getPet(Integer petId){
        for(Pet pet : pets){
            if(Objects.equals(pet.getPetId(), petId)){
                return pet;
            }
        }
        return null;
    }
    public ArrayList<Pet> addPets(Pet pet){
            pets.add(pet);
            return pets;
    }

    public ArrayList<Pet> updatePet(Pet pet){
        for(Pet tempPet : pets){
            if(pet.getPetId() == tempPet.getPetId()){
                tempPet.setPetName(pet.getPetName());
                tempPet.setPetAge(pet.getPetAge());
                tempPet.setPetType(pet.getPetType());
                return pets;
            }
        }
        return null;
    }
    public ArrayList<Pet> deletePet(int petId){
        for(Pet pet : pets){
            if(Objects.equals(pet.getPetId(), petId)){
                pets.remove(pet);
                return pets;
            }
        }
        return null;
    }
}

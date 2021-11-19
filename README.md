Shubangi Rathnayake  - 18001361
  
## PetStore Application

# How to build and deploy the API
## Build Application
    ./gradlew build
## Run the application
    java -jar build/petstore-runner.jar
## Run test suite
    ./gradlew test
  
# CURL Commands 
## Get all pets 
    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/v1/pets/'
## Get a pet by ID
    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/v1/petId/1'
## Add pet 
    curl -XPOST -H "Content-type: application/json" -d '{
        "petAge": 4,
        "petId": 3,
        "petName": "Sudda",
        "petType": "Cat"
      }' 'http://localhost:8080/v1/pets/addPets'
 ## Update pet 
    curl -XPOST -H "Content-type: application/json" -d '{
        "petAge": 4,
        "petId": 3,
        "petName": "Sudda",
        "petType": "Cat"
}' 'http://localhost:8080/v1/pets/updatePet'
 ## Delete Pet 
    curl -XDELETE -H "Content-type: application/json" 'http://localhost:8080/v1/pets/deletePet'
 ## Search 
    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/v1/pets/search?age=4'
 ## Get all pet types 
    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/v1/petType/'
 ## Get pet type by ID 
    curl -XGET 'http://localhost:8080/v1/petType/1'
 ## Add pet type
    curl -XPOST -d '{
        "petType": "Bird",
        "petTypeId": 3
    }' 'http://localhost:8080/v1/petType/addPetType'
 ## Update pet type
    curl -XPOST -d '{
        "petType": "Cat",
        "petTypeId": 3
    }' 'http://localhost:8080/v1/petType/updatePetType'
 ## Delete pet 
    curl -XDELETE 'http://localhost:8080/v1/petType/deletePetType/1'

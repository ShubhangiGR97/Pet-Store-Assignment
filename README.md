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
    curl -XGET -H "Content-type: application/json" 'http://localhost:8080/v1/petType/'

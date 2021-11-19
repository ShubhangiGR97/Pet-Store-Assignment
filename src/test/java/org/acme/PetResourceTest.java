package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;

@QuarkusTest
public class PetResourceTest {

	@Test
    public void testPetEndpoint() {
        given()
          .when().get("/v1/pets")
          .then()
             .statusCode(200);
//             .body(hasItem(
// 		            allOf(
//    		                hasEntry("pet_id", "1"),
//    		                hasEntry("pet_type", "Dog"),
//    		                hasEntry("pet_name", "Boola"),
//    		                hasEntry("pet_age", "3")
//    		            )
//    		      )
//    		 );
    }

    @Test
    public void testPetAddEndpoint(){
        given()
                .header("Content-Type","application/json")
                .body("{\r\n    \"petAge\":8,\r\n   \"petId\":1,\r\n   \"petName\":\"happy\",\r\n   \"petType\":\"Bird\"\r\n}")
                .when().post("pets/addPets")
                .then()
                .statusCode(200)
                .body("petId",equalTo(1))
                .body("petAge",equalTo(8))
                .body("petName",equalTo("happy"))
                .body("petType",equalTo("Bird"));
    }

    @Test
    public void testPetTypeDeleteEndpoint() {
        given()
                .header("Content-Type", "application/json")
                .pathParam("petId", 1)
                .when().delete("pets/deletePet/{petId}")
                .then()
                .assertThat()
                .statusCode(200);
    }

}
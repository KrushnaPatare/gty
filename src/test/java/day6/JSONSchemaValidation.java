package day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class JSONSchemaValidation {
	
	@Test
	void jasonSchemaValidation() {
		
 //convert response from postman to json schema online save it as json file in src/test/resources
		
			given()
			
			.when()
				.get("http://localhost:3000/store")
			
			.then()
				.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("storejsonschema.json"));
		
	}
	
	@Test
	void xmlSchemaValidation() {
		
 //convert response from postman to xml schema(xsd) online save it as xsd file in src/test/resources
		
			given()
			
			.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1")
			
			.then()
				.assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("travellers.xsd"));
		
	}


}

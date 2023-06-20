package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParameter {
	
	@Test
	void testPathAndQueryParameter() {
		
		//https://reqres.in/api/users?page=2&id=3
        //path parameter is before '?' symbol.
		given()  							 //path and query parameters we write in given() section because
		                     				// tomorrow if url changes, then we can change that url without changing in when() section.
			.pathParam("mypath", "users")  // we can give random names to path.
			.queryParam("page", 2)        // query parameters are taken in url automatically.
			.queryParam("id", 3)
		
		.when()
			.get("https://reqres.in/api/{mypath}")
		
		.then()
			.statusCode(200)
			.log().all();
		
	}


	
}

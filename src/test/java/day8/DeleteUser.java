package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteUser {
	
	@Test
	void testDeleteUser(ITestContext context)
	{
		
		int id = (Integer) context.getSuite().getAttribute("user_id");
		String token = "b35ee30984a16f4ef333429117fb2ca9df6045aa1a81b0c1aa886091336ed10c";
		
		 given()
		 	.headers("Authorization", "Bearer "+token)
			.pathParam("id", id)
			
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
			
		.then()
			.statusCode(204)
			.log().all();

	}


}

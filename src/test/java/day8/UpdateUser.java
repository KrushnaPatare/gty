package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {
	
	@Test
	void testUpdateUser(ITestContext context) 
	{
		Faker faker = new Faker();
		
		String genders[] = {"male", "female"};
		Random r = new Random();
		String gender = genders[r.nextInt(genders.length)];
		
		JSONObject data = new JSONObject();
		data.put("name", faker.name().fullName());
		data.put("email", faker.internet().emailAddress());
		data.put("gender", gender);
		data.put("status", "active");
		
		int id = (Integer) context.getSuite().getAttribute("user_id");
		String token = "b35ee30984a16f4ef333429117fb2ca9df6045aa1a81b0c1aa886091336ed10c";
		
		 given()
		 	.headers("Authorization", "Bearer "+token)
			.pathParam("id", id)
			.contentType("application/json")
			.body(data.toString())
				
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
			
		.then()
		.statusCode(200)
		.log().all();
		
	}
	

}

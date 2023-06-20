package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Random;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	
	@Test
	void testCreateUser(ITestContext context)
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
		
		String token = "b35ee30984a16f4ef333429117fb2ca9df6045aa1a81b0c1aa886091336ed10c";
		
			int id = given()
			.headers("Authorization", "Bearer "+token)
			.contentType("application/json")
			.body(data.toString())
			
			.when()
				.post("https://gorest.co.in/public/v2/users")
				.jsonPath().getInt("id");
			
		//context.setAttribute("user_id", id); //we can use this variable at only single test level
			                                   //Check testng01.xml 
			
		context.getSuite().setAttribute("user_id", id); //we can use this variable at Suite level
		// means in multiple test section in testng02.xml
		System.out.println(id);
	}	
}











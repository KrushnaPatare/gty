package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class LoggingDemo {

	@Test(priority=1)
	void testLogs() {
		given()
		
		.when()
			.get("https://reqres.in/api/users")
		
		.then()
//			.log().cookies(); //to show all cookies only
//			.log().headers(); //to show all headers only
//			.log().body();    // to show all body only
			.log().all();     // to show all response
	}

}

package day7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {
	
	//"https://postman-echo.com/basic-auth" this api is provided by postman. same api also works for
	//basic auth, digest auth and preemptive auth
	
	@Test(priority=1)
	void testBasicAuthentication() {
		
		given()
		.auth().basic("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
		
	}
	
	
	@Test(priority=2)
	void testDigestAuthentication() {
		

		given()
		.auth().digest("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
		
	}
	
	@Test(priority=3)
	void testPreemptiveAuthentication() {
		
		given()
		.auth().preemptive().basic("postman", "password")
		
		.when()
		.get("https://postman-echo.com/basic-auth")
		
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true))
		.log().all();
		
		
	}
	
	
	@Test(priority=4)
	void testBearerTokenAuthentication() {
		
		String token = "b35ee30984a16f4ef333429117fb2ca9df6045aa1a81b0c1aa886091336ed10c";
		
		given()
		.headers("Authorization", "Bearer "+token)
		
		.when()
		.get("https://gorest.co.in/public/v2/users/1848")
		
		.then()
		.statusCode(200)
		.body("gender", equalTo("female"))
		.header("Server", "cloudflare")
		.log().all();

		
	}
	
	
	//for Oauth1.0 and Oauth2.0 i don't have api.
	
//	@Test(priority=5)
	void testOauthAuthentication() {
	
		given()
		.auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
		
		.when()
		.get("url")
		
		.then()
		.statusCode(200)
		.log().all();

	}
	
//	@Test(priority=6)
	void testOauth2Authentication() {
	
		given()
		.auth().oauth2("accessToken")
		
		.when()
		.get("url")
		
		.then()
		.statusCode(200)
		.log().all();

	}
	
	
	@Test(priority=7)
	void testApiKeyAuthentication() {
	
		given()
		.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
		
		.when()
		.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
		
		.then()
		.statusCode(200)
		.body("city.name", equalTo("Delhi"))
		.log().all();

	}
	

}

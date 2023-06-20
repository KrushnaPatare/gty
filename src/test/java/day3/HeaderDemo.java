package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;



public class HeaderDemo {
	
	//some headers are dynamic some headers are not dynamic.
	//so we can validate only some headers.
	
	@Test(priority=1)
	void getHeaders() {
		
Response res=given()
		
			.when()
				.get("https://www.google.co.in/");
		
	//		String headerValue = res.header("Content-Type");
	//		System.out.println("The value of Content_Type Header is "+headerValue);
		
			Headers hd =res.getHeaders();
			
			for(Header k : hd) {
				
				System.out.println(k.getName()+"                 "+k.getValue());
				
			}
	
	}
	
	
	@Test(priority=2)
	void testHeaders() {
		
		given()
		
		.when()
			.get("https://www.google.co.in/")
		
		.then()
			.header("Content-Type","text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.header("Server", "gws");
		
	}

	
}	

package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;
import io.restassured.response.Response;

public class DemoCookie {

		@Test(priority=1)
		void testCookies(){
			
		given()
		
		.when()
		   	.get("https://www.google.co.in/")
		
		.then()
			    .statusCode(200)
		    	.cookie("AEC", "ARSKqsJAeyfFrnD8JOPXtbNNJr94ChC7q8tC4rnOyufG2DlRdyNrnziS23NYncxgg")
			    //this 'AEC' we got from postman and its value also.
		    	
		    	.log().cookies(); //this will show all cookies in console.(comment the line before)
			
				//This test should fail because cookie is dynamic in nature its value changes
				//every time you send request. If test fails, means it is working fine.
			}
		
		@Test(priority=2)
	void getCookieInfo() {
			
Response res=given()
	
			.when()
				.get("https://www.google.co.in/");
					
				String cookie_Value = res.getCookie("AEC"); 
				System.out.println("Value of cookie is " +cookie_Value);
		
	}

		@Test(priority=3)
		void getCookiesInfo() {
			
Response res=given()
		
			.when()
				.get("https://www.google.co.in/");
				

				Map<String,String> cookies = res.getCookies();
				Set<String> keys = cookies.keySet();
				System.out.println(keys);
				System.out.println();
				for(String key : keys) {
					
					String cookieValue = res.getCookie(key);
					System.out.println(key+"         "+cookieValue);
					
				}
		}
}

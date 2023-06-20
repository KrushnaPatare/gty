package day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJSONResponseData {
	
	//parsing means validating
	
	
	@Test
	
	void testJSONResponse_Approach1() {
		
		//Approach 1
		
		//This is used if response is small and simple or to validate few things.
		
			given()
				.contentType("application/json")
			
			.when()
				.get("http://localhost:3000/store")
			
			.then()
				.statusCode(200)
				.header("content-type","application/json; charset=utf-8")
				.body("book[2].category", equalTo("fiction"));
			
	}
	
		@Test
	
	void testJSONResponse_Approach2a() {
	
	//Approach 2
		
 Response res = given()
				
				.when()
					.get("http://localhost:3000/store");
			
				Assert.assertEquals(res.getStatusCode(),200);
				Assert.assertEquals(res.getHeader("content-type"),"application/json; charset=utf-8");
				String category = res.jsonPath().get("book[2].category").toString();
				Assert.assertEquals(category, "fiction");
			
		}
	
		@Test
		
		void testJSONResponse_Approach2b() {
	
	
	//Approach 3
		//if response is dynamic and and 'field' changes its place in JSON responce	
			
	 Response res = given()
						.contentType("application/json")
					
					.when()
						.get("http://localhost:3000/store");
			
			JSONObject jo = new JSONObject(res.asString());
			
			boolean bookStatus = false;
			
			for(int i=0; i<jo.getJSONArray("book").length();i++) {
				
				String bookname = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
				
				if(bookname.equals("The Lord of the Rings")) {
					bookStatus = true;
					break;
				}
			}
			
			Assert.assertEquals(bookStatus, true);
		
		
		double totalPrice=0;
		
		for(int a=0;a<jo.getJSONArray("book").length();a++) {
			
			String price = jo.getJSONArray("book").getJSONObject(a).get("price").toString();
			totalPrice = totalPrice + Double.parseDouble(price);

		}	
		
		System.out.println(totalPrice);
		
		boolean priceStatus = false;
		for(int b=0; b<jo.getJSONArray("book").length(); b++) {
			
			String price1 = jo.getJSONArray("book").getJSONObject(b).get("price").toString();
			if(Double.parseDouble(price1) == 75.5) {
				
				priceStatus = true;
				break;
			}
		}
		
		Assert.assertEquals(priceStatus, true);
		
		}

}

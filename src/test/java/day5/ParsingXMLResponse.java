package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParsingXMLResponse {
	
	@Test
	void testXMLResponse_Approach1() {
		
		//postman tool does not support xml schema validation but rest api support it.
			given()
				.contentType("application/xml")
			
			.when()
				.get("http://restapi.adequateshop.com/api/Traveler?page=1")
			
			.then()
				.statusCode(200)
				.header("content-type", "application/xml; charset=utf-8")
				.body("TravelerinformationResponse.travelers.Travelerinformation[0].name", equalTo("Developer"))
				.body("TravelerinformationResponse.page", equalTo("1"));
		
	}
	
	
	@Test
	void testXMLResponse_Approach2a() {
		
	Response res = given()
					.contentType("application/xml")
				
					.when()
						.get("http://restapi.adequateshop.com/api/Traveler?page=1");
				
			      Assert.assertEquals(res.statusCode(), 200);
			      Assert.assertEquals(res.header("content-type"), "application/xml; charset=utf-8");
			      String totalPages = res.xmlPath().get("TravelerinformationResponse.total_pages").toString();
				  Assert.assertEquals(totalPages, "2534");

	}
	
	@Test
	void testXMLResponse_Approach2b() {
		
	Response res = given()
					.contentType("application/xml")
				
					.when()
						.get("http://restapi.adequateshop.com/api/Traveler?page=1");
				
			     XmlPath xobj = new XmlPath(res.asString());
			     List <String> travellers = xobj.getList("TravelerinformationResponse.travelers.Travelerinformation");
			     Assert.assertEquals(travellers.size(), 10);
			     
			     List <String> travellers_names = xobj.getList("TravelerinformationResponse.travelers.Travelerinformation.name");
			     
			     
			     for(String traveller_name: travellers_names) {
			    	 
			    	 System.out.println(traveller_name);
			     }
			     
			     boolean nameStatus = false;
			     for(String traveller_name: travellers_names) {
			    	 
			    	 if(traveller_name.equals("Developer123")) {
			    		 
			    		 nameStatus = true;
			    		 break;
			    	 }
			     }
			     
			     Assert.assertEquals(nameStatus, true);

	}

}

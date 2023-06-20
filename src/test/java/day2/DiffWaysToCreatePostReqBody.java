package day2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DiffWaysToCreatePostReqBody {

	@Test(priority=1)
	void postBodyUsingHashMap() {
		
		//HashMap is used to create body when fields and data is less.
		HashMap  data = new HashMap();
		data.put("name", "Vaibhav");
		data.put("location", "India");
		data.put("phone", "5689724");
		
		String courArr [] = {"Python","Java"};
		data.put("courses", courArr);

		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Vaibhav"))
			.body("location", equalTo("India"))
			.body("phone", equalTo("India"))
			.body("courses[0]", equalTo("Python"))
			.body("courses[1]", equalTo("Java"))
			.header("content-type", "application/json; charset=utf-8")
			.log().all();
	}
	
	@Test(priority=2)
	void deleteUser(){
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/4")
		
		.then()
			.statusCode(200);
	}
	
	/*@Test(priority=1)
	void postBodyUsingOrgJson() {
		
		JSONObject data = new JSONObject();
		
		data.put("name", "Vaibhav");
		data.put("location", "India");
		data.put("phone", "5689724");
		
		String courArr [] = {"Python","Java"};
		data.put("courses", courArr);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Vaibhav"))
			.body("location", equalTo("India"))
			.body("phone", equalTo("5689724"))
			.body("courses[0]", equalTo("Python"))
			.body("courses[1]", equalTo("Java"))
			.header("content-type", "application/json; charset=utf-8")
			.log().all();
			
	}
	
	@Test(priority=2)
	void deleteUser(){
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/4")
		
		.then()
			.statusCode(204);
	}*/
	
	
	/*@Test(priority=1)
	void postBodyUsingPOJO() {
		
		POJO_PostRequest data = new POJO_PostRequest();
		
		data.setName("Vaibhav");
		data.setLocation("India");
		data.setPhone("5689724");
		
		String courArr [] = {"Python","Java"};
		data.setCourses( courArr);
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Vaibhav"))
			.body("location", equalTo("India"))
			.body("phone", equalTo("5689724"))
			.body("courses[0]", equalTo("Python"))
			.body("courses[1]", equalTo("Java"))
			.header("content-type", "application/json; charset=utf-8")
			.log().all();
		
	}
	
	@Test(priority=2)
	void deleteUser(){
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/4")
		
		.then()
			.statusCode(200);
	}*/
	
/*
	@Test(priority=1)
	void postBodyUsingExternalJsonFile() throws FileNotFoundException {
		
		File f = new File (".\\body.json");
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		
		given()
			.contentType("application/json")
			.body(data.toString())
		
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name", equalTo("Shinjo Abe"))
			.body("location", equalTo("Japan"))
			.body("phone", equalTo("1234554231"))
			.body("courses[0]", equalTo("Java"))
			.body("courses[1]", equalTo("Selenium"))
			.header("content-type", "application/json; charset=utf-8")
			.log().all();
		
	}
	
	private FileReader FileReader(File f) {
		return null;
	}

	@Test(priority=2)
	void deleteUser(){
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/4")
		
		.then()
			.statusCode(200);
	}
	*/
}

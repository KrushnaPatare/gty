package day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


//Pojo-----serialize---->Json       Json-----deserialize------Pojo

public class SerializationAndDeserialization {
	
	@Test
	void convetPojoToJson() throws JsonProcessingException {
		
		//Pojo--------->Json (serialization)
		
		StudentsPojo studPojo = new StudentsPojo();
		studPojo.setName("Vaibhav");
		studPojo.setLocation("India");
		studPojo.setPhone("5689724");
		
		String courArr [] = {"Python","Java"};
		studPojo.setCourses( courArr);
		
		ObjectMapper objmap = new ObjectMapper(); //import this from jakson package.
		
		String jsonData = objmap.writerWithDefaultPrettyPrinter().writeValueAsString(studPojo);
		
		System.out.println(jsonData);
	}
	
	@Test
	void convetJsonToPojo() throws JsonProcessingException {
		
		//Json---------->Pojo (deserialization)
		
		String jsondata = "{\r\n"
				+ "  \"name\" : \"Vaibhav\",\r\n"
				+ "  \"location\" : \"India\",\r\n"
				+ "  \"phone\" : \"5689724\",\r\n"
				+ "  \"courses\" : [ \"Python\", \"Java\" ]\r\n"
				+ "}";
		
		ObjectMapper objmapper = new ObjectMapper();
		
		StudentsPojo stu = objmapper.readValue(jsondata, StudentsPojo.class); 
		//(StudentsPojo) classname should be similar to class present in a package.															
		
		System.out.println("name:"+stu.getName());
		System.out.println("location:"+stu.getLocation());
		System.out.println("phone:"+stu.getPhone());
		System.out.println("course 1:"+stu.getCourses()[0]);
		System.out.println("course 2:"+stu.getCourses()[1]);
	
		
	}

}

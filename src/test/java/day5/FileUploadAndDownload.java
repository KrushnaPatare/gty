package day5;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {

	@Test
	void singleFileUpload() {
		
		File myFile = new File("C:\\Users\\Admin\\Documents\\store.json");
		
			given()
				.multiPart("file", myFile) //this is because in postman in body , form-data radio butoon is selected
				.contentType("multipart/form-data") //this content type of any file.
			
			.when()
				.post("https://the-internet.herokuapp.com/upload")
			
			.then()
				.header("Content-Type", "text/html;charset=utf-8");
			
	}
	
	/*
	
	@Test
	void multipleFileUpload_Approach1() {
		
		File myfile01 = new File("C:\\Users\\Admin\\Documents\\store.json");
		File myfile02 = new File("C:\\Users\\Admin\\Documents\\students.json");
		
				given()
					.multiPart("files", myfile01)  
					.multiPart("files", myfile02)
					.contentType("multipart/form-data")
				
				.when()
					.post("")
				
				.then()
					.body("[0].fileName", equalTo("store"))
					.statusCode(201);
		
	}
	
	
	void multipleFileUpload_Approach2() {
		
		//this approach can work on only some websites
		
		File myfile01 = new File("C:\\Users\\Admin\\Documents\\store.json");
		File myfile02 = new File("C:\\Users\\Admin\\Documents\\students.json");
		File filearr[] = {myfile01,myfile02};
		
				given()
					.multiPart("files", filearr)  
					.contentType("multipart/form-data")
				
				.when()
					.post("")
				
				.then()
					.body("[0].fileName", equalTo("store"))
					.statusCode(201);
		
	}
	
	*/
	
	@Test
	void fileDownload() {
		
		//this will show long sun image code in response not actual image.
		//when we send get request for download response is shown in console.
				given()
				
				.when()
					.get("https://www.cleanpng.com/png-computer-icons-black-sun-dark-cool-1475632/download-png.html") //
				
				.then()
					.statusCode(200)
					.log().all();

	}
	
	
}

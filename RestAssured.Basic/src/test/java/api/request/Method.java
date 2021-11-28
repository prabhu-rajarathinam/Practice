package api.request;

import java.time.Instant;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Method {
	final String baseURI = "https://gorest.co.in/public/v1";
	String id;
	final String TOKEN = "66aa011e9d9f4527edda5ca740220ccadaf786393ddc6808d313b393484d86a1";
	
	@Test(priority = 0)
	public void toCreate() {
			RestAssured.baseURI = baseURI;
			String endpoint = "/users";
			String jsonStringInput= "{\"name\": \"mike\",\"email\": \"" +"mike"+String.valueOf(Instant.now().toEpochMilli())+ "@dummy.com\",\"gender\": \"male\",\"status\":\"active\"}";
			Response response = RestAssured.given().auth().oauth2(TOKEN).body(jsonStringInput).when().post(endpoint).thenReturn();
			response.asPrettyString();
	}

	@Test(priority =1)
	public void toRead() {
		RestAssured.baseURI = baseURI;
		String endpoint = "/users/123";
		Response response = RestAssured.given().auth().oauth2(TOKEN).when().get(endpoint).thenReturn();
		response.asPrettyString();
		System.out.println(response.jsonPath().getString("data.name"));
	}
	
	@Test(priority =2)
	public void toUpdate() {
		RestAssured.baseURI = baseURI;
		String endpoint = "/users/123";
		String jsonStringInput= "{\"name\": \"mike\",\"email\": \"" +"mike"+String.valueOf(Instant.now().toEpochMilli())+ "@dummy.com\",\"gender\": \"male\",\"status\":\"active\"}";
		Response response = RestAssured.given().auth().oauth2(TOKEN).body(jsonStringInput).when().put(endpoint).thenReturn();
		response.asPrettyString();
	}
	
	@Test(priority =3)
	public void toDelete() {
		RestAssured.baseURI = baseURI;
		String endpoint = "/users/123";
		Response response = RestAssured.given().auth().oauth2(TOKEN).when().delete(endpoint).thenReturn();
		response.asPrettyString();
	}
}

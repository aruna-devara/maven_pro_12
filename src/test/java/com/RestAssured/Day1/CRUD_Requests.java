package com.RestAssured.Day1;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CRUD_Requests {
	int id;
	HashMap<String,String> hm;
	
	@Test(priority=1)
	public void Get_Single_user()
	{
		RestAssured.given()
		.contentType("application/json")
		.when()
		.get("https://reqres.in/api/users/2")
		//.then().log().all();
	     .then().log().body();

	}
	@Test(priority=2)
	public void datavalidation()
	{
		Response res=RestAssured.given()
		.contentType("application/json")
		.when()
		.get("https://reqres.in/api/users/2");
		res.then().assertThat().body("data.first_name", equalTo("Janet"));
		
		
	}
	@Test(priority=3)
	public void create_user()
	{
		hm=new HashMap();
		hm.put("name", "morpheus");
		hm.put("job","leader");
	   RestAssured.given()
	   .contentType("application/json")
	   .body(hm)
	   .when()
	   .post("https://reqres.in/api/users")
	   .then().log().body();
	   
		
	}
	@Test(priority=4 ,dependsOnMethods= {"create_user"})
	public void update_user()
	{
		hm.put("name", "aruna");
		hm.put("job", "zionssh ");
		Response res=RestAssured.given()
		.contentType("application/json")
		.body(hm)
		.when()
		.put("https://reqres.in/api/users/2");
		   res.then().assertThat().statusCode(200);
	}
	@Test(priority=5)
	public void delete_user() {
		Response res=RestAssured.given()
		.contentType("application/json")
		.when()
		.delete("https://reqres.in/api/users/2");
		res.then().assertThat().statusCode(204);
	}
	

}

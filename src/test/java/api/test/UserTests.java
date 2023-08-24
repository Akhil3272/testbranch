package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;



public class UserTests {
	
	Faker faker;
	User userpayload;
	@BeforeClass
	
	public void setupData()

	{
		faker=new Faker();
		userpayload=new User();
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
		userpayload.setPassword(faker.internet().password(5,10));
		userpayload.setPhone(faker.phoneNumber().phoneNumber());
	
	}
	@Test(priority=1)
	public void testpostUser()
	{
		
		Response response= UserEndpoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
	}
	@Test(priority=2)
	public void testgetuser()
	
	{
		
		Response response=UserEndpoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		
	}
	@Test(priority=3)
	public void testupdateuser()
	{
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().emailAddress());
	Response response=UserEndpoints.updateUser(userpayload,this.userpayload.getUsername());
	response.then().log().all();
	Assert.assertEquals(response.getStatusCode(),200);
	
	Response responseafter=UserEndpoints.readUser(this.userpayload.getUsername());
	response.then().log().all();
	Assert.assertEquals(responseafter.getStatusCode(),200);
	
		
	}
	
	@Test(priority=4)
	public void testdelete()
	{
		
		Response response=UserEndpoints.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
		
	
	

}
